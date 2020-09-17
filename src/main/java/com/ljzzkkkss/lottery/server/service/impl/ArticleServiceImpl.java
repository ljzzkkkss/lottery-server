package com.ljzzkkkss.lottery.server.service.impl;

import com.ljzzkkkss.lottery.server.mapper.ArticleMapper;
import com.ljzzkkkss.lottery.server.mapper.BannerMapper;
import com.ljzzkkkss.lottery.server.model.Article;
import com.ljzzkkkss.lottery.server.model.Banner;
import com.ljzzkkkss.lottery.server.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private BannerMapper bannerMapper;

    @Override
    public List<Article> getArticleListByCategoryPage(String category, Integer page, Integer pageSize) {
        Integer start = pageSize * (page - 1);
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
}
