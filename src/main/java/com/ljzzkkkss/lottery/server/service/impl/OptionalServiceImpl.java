package com.ljzzkkkss.lottery.server.service.impl;

import com.ljzzkkkss.lottery.server.mapper.MatchMapper;
import com.ljzzkkkss.lottery.server.mapper.OddMapper;
import com.ljzzkkkss.lottery.server.mapper.OptionalDetailMapper;
import com.ljzzkkkss.lottery.server.mapper.OptionalMapper;
import com.ljzzkkkss.lottery.server.model.*;
import com.ljzzkkkss.lottery.server.model.Optional;
import com.ljzzkkkss.lottery.server.service.OptionalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OptionalServiceImpl implements OptionalService {
    @Resource
    private OptionalMapper optionalMapper;
    @Resource
    private OptionalDetailMapper optionalDetailMapper;
    @Resource
    private MatchMapper matchMapper;
    @Resource
    private OddMapper oddMapper;

    @Override
    @Transactional
    public void addOptional(OptionalParam optionalParam) {
        Optional optional = optionalParam.getOptional();
        List<OptionalDetail> optionalDetailList = optionalParam.getOptionalDetailList();
        optionalMapper.insertOptional(optional);
        for(OptionalDetail optionalDetail : optionalDetailList){
            optionalDetail.setOptionalId(optional.getId());
            optionalDetailMapper.insertOptionalDetail(optionalDetail);
        }
    }

    @Override
    public Map<String, List<Map<String,Object>>> getMatchNotStartList() throws ParseException {
        Map<String, List<Map<String,Object>>> result = new LinkedHashMap<>();
        List<Match> matchList = matchMapper.getMatchListNotStart();
        SimpleDateFormat formatForSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatForDay = new SimpleDateFormat("yyyy-MM-dd E");
        for(Match match : matchList){
            List<Odd> oddList = oddMapper.getOddByMatchId(match.getId());
            Map<String,List<Odd>> oddMap = new HashMap<>();
            Map<String,Object> matchMap = new HashMap<>();
            for(Odd odd : oddList){
                if(null == oddMap.get(odd.getCategory())){
                    oddMap.put(odd.getCategory(),new ArrayList<>());
                }
                oddMap.get(odd.getCategory()).add(odd);
            }
            matchMap.put("match",match);
            matchMap.put("oddList",oddMap);
            String key = formatForDay.format(formatForSecond.parse(match.getMatchTime()));
            if(null == result.get(key)){
                result.put(key,new ArrayList<>());
            }
            result.get(key).add(matchMap);
        }
        return result;
    }

}
