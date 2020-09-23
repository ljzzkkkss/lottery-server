package com.ljzzkkkss.lottery.server.service;

import com.ljzzkkkss.lottery.server.model.Note;
import com.ljzzkkkss.lottery.server.model.OptionalParam;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface OptionalService {
    void addOptional(OptionalParam optionalParam);
    void sendOptional(OptionalParam optionalParam);
    void sendMessage(Note note);
    Map<String,Object> getNoteList(Integer userId,Integer pageIndex,Integer pageSize);
    Map<String, List<Map<String,Object>>> getMatchNotStartList() throws ParseException;
}
