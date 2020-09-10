package com.ljzzkkkss.lottery.server.mapper;

import com.ljzzkkkss.lottery.server.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    List<Article> getArticleListByCategoryPage(@Param("category")String category,@Param("start")Integer start,@Param("pageSize")Integer pageSize);
    Article getArticleById(@Param("id")Integer id);

}
