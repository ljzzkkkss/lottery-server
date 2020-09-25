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
    @Transactional
    public void sendMessage(Note note) {
        if(null != note.getOptionalId()){
            optionalMapper.confirmSendOptional(note.getOptionalId());
        }
        noteMapper.insertNote(note);
    }

    @Override
    public Map<String, Object> getNoteList(Integer userId, Integer pageIndex, Integer pageSize) {
        Map<String,Object> result = new HashMap<>();
        Integer start = pageSize * (pageIndex - 1);
        List<Note> noteList = noteMapper.getNoteListByPage(userId,start,pageSize);
        List<Map<String,Object>> noteMapList = new ArrayList<>();
        for(Note note : noteList){
            Map<String,Object> noteMap = new HashMap<>();
            noteMap.put("note",note);
            Optional optional = optionalMapper.getOptionalByOptionalId(note.getOptionalId());
            List<OptionalDetail> optionalDetailList = optionalDetailMapper.getOptionalDetailByOptionalId(note.getOptionalId());
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
                for(OptionalDetail optionalDetail : optionalDetails){
                    if(null == optionalDetailMap.get(optionalDetail.getCategory())){
                        optionalDetailMap.put(optionalDetail.getCategory(),new ArrayList<>());
                    }
                    optionalDetailMap.get(optionalDetail.getCategory()).add(optionalDetail);
                }
                match.put("match",matchMap.get(key).get("match"));
                match.put("optionalDetail",optionalDetailMap);
                matchList.add(match);
            }
            noteMap.put("optional",optional);
            noteMap.put("match",matchList);
            noteMapList.add(noteMap);
        }
        Collections.reverse(noteMapList);
        result.put("reply",noteMapper.getReply());
        result.put("noteList",noteMapList);
        return result;
    }

    @Override
    public Map<String, List<Map<String,Object>>> getMatchNotStartList() throws ParseException {
        Map<String, List<Map<String,Object>>> result = new LinkedHashMap<>();
        List<Match> matchList = matchMapper.getMatchListNotStart();
        SimpleDateFormat formatForSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatForDay = new SimpleDateFormat("yyyy-MM-dd E",Locale.CHINA);
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
            String key = formatForDay.format(formatForSecond.parse(match.getEndTime()));
            if(null == result.get(key)){
                result.put(key,new ArrayList<>());
            }
            result.get(key).add(matchMap);
        }
        return result;
    }

}
