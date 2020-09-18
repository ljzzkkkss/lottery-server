package com.ljzzkkkss.lottery.server.service;

import com.ljzzkkkss.lottery.server.model.Article;
import com.ljzzkkkss.lottery.server.model.Banner;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<Article> getArticleListByCategoryPage(String category,Integer pageIndex,Integer pageSize);
    Article getArticleById(Integer id);
    List<Banner> getBannerList();
    List<Map<String, Object>> getRecommendList(Integer articleId);
}
