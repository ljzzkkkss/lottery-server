package com.ljzzkkkss.lottery.server.mapper;

import com.ljzzkkkss.lottery.server.model.Odd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OddMapper {
    List<Odd> getOddByMatchId(@Param("matchId")Long matchId);
}
