package com.ljzzkkkss.lottery.server.service;

import com.ljzzkkkss.lottery.server.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getArticleListByCategoryPage(String category,Integer page,Integer pageSize);
    Article getArticleById(Integer id);
}
