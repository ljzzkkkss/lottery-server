package com.ljzzkkkss.lottery.server.controller;

import com.ljzzkkkss.lottery.server.constants.ReturnType;
import com.ljzzkkkss.lottery.server.model.OptionalParam;
import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.model.User;
import com.ljzzkkkss.lottery.server.service.MineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MineController {
    @Resource
    private MineService mineService;

    @ResponseBody
    @GetMapping("/private/optional/getList")
    public ReturnBody getList(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return new ReturnBody(mineService.getOptionalListByUserId(user.getId()));
    }

    @ResponseBody
    @GetMapping("/private/optional/getOptionalDetailById")
    public ReturnBody getOptionalDetailById(HttpServletRequest request,Integer optionalId){
        User user = (User) request.getSession().getAttribute("user");
        return new ReturnBody(mineService.getOptionalDetailByOptionalId(optionalId,user.getId()));
    }

    @ResponseBody
    @PostMapping("/private/optional/addOptional")
    public ReturnBody addOptional(HttpServletRequest request, @RequestBody OptionalParam optionalParam){
        User user = (User) request.getSession().getAttribute("user");
        optionalParam.getOptional().setUserId(user.getId());
        mineService.addOptional(optionalParam);
        return ReturnType.SUCCESS;
    }

    @GetMapping("/mine")
    public String mine(){
        return "mine";
    }

    @GetMapping("/help")
    public String help(){
        return "help";
    }

    @ResponseBody
    @GetMapping("/help/list")
    public ReturnBody helpList(){
        return new ReturnBody(mineService.getAllHelp());
    }
}
