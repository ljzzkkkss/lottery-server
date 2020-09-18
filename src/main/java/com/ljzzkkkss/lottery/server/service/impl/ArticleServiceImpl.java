package com.ljzzkkkss.lottery.server.service.impl;

import com.ljzzkkkss.lottery.server.mapper.ArticleMapper;
import com.ljzzkkkss.lottery.server.mapper.BannerMapper;
import com.ljzzkkkss.lottery.server.mapper.MatchMapper;
import com.ljzzkkkss.lottery.server.mapper.RecommendMapper;
import com.ljzzkkkss.lottery.server.model.Article;
import com.ljzzkkkss.lottery.server.model.Banner;
import com.ljzzkkkss.lottery.server.model.Recommend;
import com.ljzzkkkss.lottery.server.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private RecommendMapper recommendMapper;
    @Resource
    private MatchMapper matchMapper;

    @Override
    public List<Article> getArticleListByCategoryPage(String category, Integer pageIndex, Integer pageSize) {
        Integer start = pageSize * (pageIndex - 1);
        return articleMapper.getArticleListByCategoryPage(category,start,pageSize);
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public List<Banner> getBannerList() {
        return bannerMapper.getBannerList();
    }

    @Override
    public List<Map<String, Object>> getRecommendList(Integer articleId) {
        List<Map<String,Object>> result = new ArrayList<>();
        List<Recommend> recommendList = recommendMapper.getRecommendListByArticleId(articleId);
        Map<Long,Map<String,Object>> matchMap = new HashMap<>();
        for(Recommend recommend : recommendList){
            Long matchId = recommend.getMatchId();
            if(null == matchMap.get(matchId)){
                Map<String,Object> map = new HashMap<>();
                map.put("match",matchMapper.getMatchById(matchId));
                map.put("recommend",new ArrayList<>());
                matchMap.put(matchId,map);
            }
            ((List<Recommend>) matchMap.get(matchId).get("recommend")).add(recommend);
        }
        for(Long key : matchMap.keySet()){
            List<Recommend> recommends = (List<Recommend>) matchMap.get(key).get("recommend");
            Map<String,List<Recommend>> recommendMap = new HashMap<>();
            Map<String,Object> match = new HashMap<>();
            for(Recommend recommend : recommends){
                if(null == recommendMap.get(recommend.getCategory())){
                    recommendMap.put(recommend.getCategory(),new ArrayList<>());
                }
                recommendMap.get(recommend.getCategory()).add(recommend);
            }
            match.put("match",matchMap.get(key).get("match"));
            match.put("recommend",recommendMap);
            result.add(match);
        }
        return result;
    }
}
