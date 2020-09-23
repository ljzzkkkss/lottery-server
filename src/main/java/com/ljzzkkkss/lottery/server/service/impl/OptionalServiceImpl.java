package com.ljzzkkkss.lottery.server.service.impl;

import com.ljzzkkkss.lottery.server.mapper.*;
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
    @Resource
    private NoteMapper noteMapper;

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
    @Transactional
    public void sendOptional(OptionalParam optionalParam) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Optional optional = optionalParam.getOptional();
        List<OptionalDetail> optionalDetailList = optionalParam.getOptionalDetailList();
        optionalMapper.insertOptional(optional);
        for(OptionalDetail optionalDetail : optionalDetailList){
            optionalDetail.setOptionalId(optional.getId());
            optionalDetailMapper.insertOptionalDetail(optionalDetail);
        }
        Note note = new Note();
        note.setOptionalId(optional.getId());
        note.setUserId(optional.getUserId());
        note.setNoteTime(format.format(new Date()));
        noteMapper.insertNote(note);
    }

    @Override
    public void insertNote(Note note) {
        noteMapper.insertNote(note);
    }

    @Override
    public Map<String, Object> getNoteList(Integer userId, Integer pageIndex, Integer pageSize) {
        Map<String,Object> result = new HashMap<>();
        Integer start = pageSize * (pageIndex - 1);
        result.put("reply",noteMapper.getReply());
        result.put("noteList",noteMapper.getNoteListByPage(userId,start,pageSize));
        return result;
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
