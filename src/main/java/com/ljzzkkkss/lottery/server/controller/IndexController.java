package com.ljzzkkkss.lottery.server.controller;

import com.alibaba.druid.util.StringUtils;
import com.ljzzkkkss.lottery.server.constants.ReturnType;
import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.model.User;
import com.ljzzkkkss.lottery.server.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Resource
    private UserService userService;
    @Value("${file.url}")
    private String fileUrl;


    @ResponseBody
    @PostMapping("/login")
    public ReturnBody login(HttpServletRequest request,@RequestBody User user){
        User logUser = userService.findByUserName(user.getUsername());
        if(null == logUser || StringUtils.isEmpty(logUser.getPassword())){
            return ReturnType.LOGIN_ERROR;
        }
        if(!logUser.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))){
            return ReturnType.LOGIN_ERROR;
        }
        request.getSession().setAttribute("user",user);
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
}
