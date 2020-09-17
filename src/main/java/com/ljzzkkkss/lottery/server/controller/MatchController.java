package com.ljzzkkkss.lottery.server.controller;

import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MatchController {
    @Resource
    private MatchService matchService;

    @ResponseBody
    @GetMapping("/match/getList")
    public ReturnBody getList(String matchTime){
        return new ReturnBody(matchService.getMatchListByMatchTime(matchTime));
    }

    @ResponseBody
    @GetMapping("/match/getOddByMatchId")
    public ReturnBody getOddByMatchId(Long matchId){
        return new ReturnBody(matchService.getOddByMatchId(matchId));
    }

    @GetMapping("/score")
    public String score(){
        return "score";
    }

}
