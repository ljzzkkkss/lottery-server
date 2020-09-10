package com.ljzzkkkss.lottery.server.service;

import com.ljzzkkkss.lottery.server.model.Match;
import com.ljzzkkkss.lottery.server.model.Odd;

import java.util.List;
import java.util.Map;

public interface MatchService {
    List<Match> getMatchListByMatchTime (String matchTime);
    Map<String,List<Odd>> getOddByMatchId(Long matchId);
}
