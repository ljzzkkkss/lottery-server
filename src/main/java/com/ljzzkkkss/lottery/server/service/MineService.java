package com.ljzzkkkss.lottery.server.service;

import com.ljzzkkkss.lottery.server.model.Help;
import com.ljzzkkkss.lottery.server.model.Optional;
import com.ljzzkkkss.lottery.server.model.OptionalParam;

import java.util.List;
import java.util.Map;

public interface MineService {
    List<Optional> getOptionalListByUserId(Integer userId);
    Map<String,Object> getOptionalDetailByOptionalId(Integer optionalId,Integer userId);
    void addOptional(OptionalParam optionalParam);
    List<Help> getAllHelp();
}
