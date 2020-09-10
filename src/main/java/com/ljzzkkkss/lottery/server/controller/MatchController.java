package com.ljzzkkkss.lottery.server.controller;

import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.service.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MatchController {
    @Resource
    private MatchService matchService;

    @GetMapping("/match/getList")
    public ReturnBody getList(String matchTime){
        return new ReturnBody(matchService.getMatchListByMatchTime(matchTime));
    }

    @GetMapping("/match/getOddByMatchId")
    public ReturnBody getOddByMatchId(Long matchId){
        return new ReturnBody(matchService.getOddByMatchId(matchId));
    }

}
