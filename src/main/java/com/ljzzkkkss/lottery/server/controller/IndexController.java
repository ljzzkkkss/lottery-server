package com.ljzzkkkss.lottery.server.controller;

import com.ljzzkkkss.lottery.server.constants.ReturnType;
import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.model.User;
import com.ljzzkkkss.lottery.server.service.UserService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ReturnBody login(HttpServletRequest request,@RequestBody User user){
        User logUser = userService.findByUserName(user.getUsername());
        if(null == logUser){
            return ReturnType.LOGIN_ERROR;
        }
        if(!logUser.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))){
            return ReturnType.LOGIN_ERROR;
        }
        request.getSession().setAttribute("user",user);
        return ReturnType.SUCCESS;
    }

    @PostMapping("/logout")
    public ReturnBody logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return ReturnType.SUCCESS;
    }

}
