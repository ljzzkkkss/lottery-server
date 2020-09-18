package com.ljzzkkkss.lottery.server.mapper;

import com.ljzzkkkss.lottery.server.model.Recommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendMapper {
    List<Recommend> getRecommendListByArticleId(@Param("articleId")Integer articleId);
}
