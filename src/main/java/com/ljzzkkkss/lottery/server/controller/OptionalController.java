package com.ljzzkkkss.lottery.server.controller;

import com.ljzzkkkss.lottery.server.constants.ReturnType;
import com.ljzzkkkss.lottery.server.model.OptionalParam;
import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.model.User;
import com.ljzzkkkss.lottery.server.service.OptionalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Controller
public class OptionalController {
    @Resource
    private OptionalService optionalService;

    @GetMapping("/scheme")
    public String scheme(){
        return "scheme";
    }

    @ResponseBody
    @PostMapping("/private/optional/addOptional")
    public ReturnBody addOptional(HttpServletRequest request, @RequestBody OptionalParam optionalParam){
        User user = (User) request.getSession().getAttribute("user");
        optionalParam.getOptional().setUserId(user.getId());
        optionalService.addOptional(optionalParam);
        return ReturnType.SUCCESS;
    }

    @ResponseBody
    @GetMapping("/optional/matchList")
    public ReturnBody getOddByMatchId() throws ParseException {
        return new ReturnBody(optionalService.getMatchNotStartList());
    }
}
