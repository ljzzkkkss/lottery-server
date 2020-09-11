package com.ljzzkkkss.lottery.server.mapper;

import com.ljzzkkkss.lottery.server.model.Match;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchMapper {
    List<Match> getMatchListByMatchTime(@Param("matchTime")String matchTime);
    Match getMatchById(@Param("id")Long id);
}
