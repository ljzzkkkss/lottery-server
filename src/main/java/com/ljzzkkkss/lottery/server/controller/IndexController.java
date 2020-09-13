package com.ljzzkkkss.lottery.server.controller;

import com.alibaba.druid.util.StringUtils;
import com.ljzzkkkss.lottery.server.constants.ReturnType;
import com.ljzzkkkss.lottery.server.model.Log;
import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.model.User;
import com.ljzzkkkss.lottery.server.service.IndexService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class IndexController {
    @Resource
    private IndexService indexService;
    @Value("${file.url}")
    private String fileUrl;


    @ResponseBody
    @PostMapping("/login")
    public ReturnBody login(HttpServletRequest request,@RequestBody User user){
        User logUser = indexService.findByUserName(user.getUsername());
        if(null == logUser || StringUtils.isEmpty(logUser.getPassword())){
            return ReturnType.LOGIN_ERROR;
        }
        if(!logUser.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))){
            return ReturnType.LOGIN_ERROR;
        }
        request.getSession().setAttribute("user",user);
        Log log = new Log();
        log.setContent("系统登录");
        log.setPhone(user.getPassword());
        log.setLogTime(new Date());
        indexService.insertLog(log);
        return new ReturnBody(fileUrl);
    }

    @ResponseBody
    @PostMapping("/logout")
    public ReturnBody logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return ReturnType.SUCCESS;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/update_password")
    public String update_password(){
        return "update_password";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/private/userInfo")
    public ReturnBody register(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        user.setPassword(null);
        return new ReturnBody(user);
    }
}
