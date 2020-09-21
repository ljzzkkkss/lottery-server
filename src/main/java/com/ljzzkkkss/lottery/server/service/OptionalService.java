package com.ljzzkkkss.lottery.server.service;

import com.ljzzkkkss.lottery.server.model.OptionalParam;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface OptionalService {
    void addOptional(OptionalParam optionalParam);
    Map<String, List<Map<String,Object>>> getMatchNotStartList() throws ParseException;
}
