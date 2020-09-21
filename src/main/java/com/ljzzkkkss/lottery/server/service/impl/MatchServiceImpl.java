package com.ljzzkkkss.lottery.server.service.impl;

import com.ljzzkkkss.lottery.server.mapper.MatchMapper;
import com.ljzzkkkss.lottery.server.mapper.OddMapper;
import com.ljzzkkkss.lottery.server.model.Match;
import com.ljzzkkkss.lottery.server.model.Odd;
import com.ljzzkkkss.lottery.server.service.MatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService {
    @Resource
    private MatchMapper matchMapper;
    @Resource
    private OddMapper oddMapper;

    @Override
    public List<Match> getMatchListByMatchTime(String matchTime) {
        return matchMapper.getMatchListByMatchTime(matchTime);
    }

    @Override
    public Map<String, List<Odd>> getOddByMatchId(Long matchId) {
        Map<String,List<Odd>> result = new HashMap<>();
        List<Odd> oddList = oddMapper.getOddByMatchId(matchId);
        for(Odd odd : oddList){
            if(null == result.get(odd.getCategory())){
                result.put(odd.getCategory(),new ArrayList<>());
            }else{
                result.get(odd.getCategory()).add(odd);
            }
        }
        return result;
    }

}
