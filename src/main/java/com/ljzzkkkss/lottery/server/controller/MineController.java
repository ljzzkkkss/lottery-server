package com.ljzzkkkss.lottery.server.controller;

import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.model.User;
import com.ljzzkkkss.lottery.server.service.MineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MineController {
    @Resource
    private MineService mineService;

    @ResponseBody
    @GetMapping("/private/optional/getList")
    public ReturnBody getList(HttpServletRequest request,Integer pageIndex,Integer pageSize){
        User user = (User) request.getSession().getAttribute("user");
        return new ReturnBody(mineService.getOptionalListByUserId(user.getId(),pageIndex,pageSize));
    }

    @ResponseBody
    @GetMapping("/private/optional/getPayedList")
    public ReturnBody getPayedList(HttpServletRequest request,Integer pageIndex,Integer pageSize){
        User user = (User) request.getSession().getAttribute("user");
        return new ReturnBody(mineService.getPayedOptionalListByUserId(user.getId(),pageIndex,pageSize));
    }

    @ResponseBody
    @GetMapping("/private/optional/getOptionalDetailById")
    public ReturnBody getOptionalDetailById(HttpServletRequest request,Integer optionalId){
        User user = (User) request.getSession().getAttribute("user");
        return new ReturnBody(mineService.getOptionalDetailByOptionalId(optionalId,user.getId()));
    }

    @GetMapping("/mine")
    public String mine(){
        return "mine";
    }

    @GetMapping("/help")
    public String help(){
        return "help";
    }

    @GetMapping("/private/bet_programme")
    public String bet_programme(){
        return "bet_programme";
    }

    @GetMapping("/private/save_programme")
    public String save_programme(){
        return "save_programme";
    }

    @ResponseBody
    @GetMapping("/help/list")
    public ReturnBody helpList(){
        return new ReturnBody(mineService.getAllHelp());
    }
}
