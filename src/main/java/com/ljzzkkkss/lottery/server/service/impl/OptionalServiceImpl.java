package com.ljzzkkkss.lottery.server.service.impl;

import com.ljzzkkkss.lottery.server.mapper.MatchMapper;
import com.ljzzkkkss.lottery.server.mapper.OptionalDetailMapper;
import com.ljzzkkkss.lottery.server.mapper.OptionalMapper;
import com.ljzzkkkss.lottery.server.model.Optional;
import com.ljzzkkkss.lottery.server.model.OptionalDetail;
import com.ljzzkkkss.lottery.server.model.OptionalParam;
import com.ljzzkkkss.lottery.server.service.OptionalService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionalServiceImpl implements OptionalService {
    @Resource
    private OptionalMapper optionalMapper;
    @Resource
    private OptionalDetailMapper optionalDetailMapper;
    @Resource
    private MatchMapper matchMapper;

    @Override
    public List<Optional> getOptionalListByUserId(Integer userId) {
        return optionalMapper.getOptionalListByUserId(userId);
    }

    @Override
    public Map<String, Object> getOptionalDetailByOptionalId(Integer optionalId,Integer userId) {
        Optional optional = optionalMapper.getOptionalByOptionalId(optionalId);
        if(!userId.equals(optional.getUserId())){
            new HashMap<>();
        }
        Map<String,Object> result = new HashMap<>();
        List<OptionalDetail> optionalDetailList = optionalDetailMapper.getOptionalDetailByOptionalId(optionalId);
        List<Map<String,Object>> matchList = new ArrayList<>();
        Map<Long,Map<String,Object>> matchMap = new HashMap<>();
        for(OptionalDetail optionalDetail : optionalDetailList){
            Long matchId = optionalDetail.getMatchId();
            if(null == matchMap.get(matchId)){
                Map<String,Object> map = new HashMap<>();
                map.put("match",matchMapper.getMatchById(matchId));
                map.put("optionalDetail",new ArrayList<>());
                matchMap.put(matchId,map);
            }
            ((List<OptionalDetail>) matchMap.get(matchId).get("optionalDetail")).add(optionalDetail);
        }
        for(Long key : matchMap.keySet()){
            List<OptionalDetail> optionalDetails = (List<OptionalDetail>) matchMap.get(key).get("optionalDetail");
            Map<String,List<OptionalDetail>> optionalDetailMap = new HashMap<>();
            Map<String,Object> match = new HashMap<>();
            for(OptionalDetail optionalDetail : optionalDetailList){
                if(null == result.get(optionalDetail.getCategory())){
                    optionalDetailMap.put(optionalDetail.getCategory(),new ArrayList<>());
                }else{
                    optionalDetailMap.get(optionalDetail.getCategory()).add(optionalDetail);
                }
            }
            match.put("match",matchMap.get(key).get("match"));
            match.put("optionalDetail",optionalDetailMap);
            matchList.add(match);
        }
        result.put("optional",optional);
        result.put("match",matchList);
        return result;
    }

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
}
