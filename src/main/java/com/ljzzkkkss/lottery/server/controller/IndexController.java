package com.ljzzkkkss.lottery.server.controller;

import com.alibaba.druid.util.StringUtils;
import com.ljzzkkkss.lottery.server.constants.RedisConstants;
import com.ljzzkkkss.lottery.server.constants.ReturnType;
import com.ljzzkkkss.lottery.server.model.Log;
import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.model.User;
import com.ljzzkkkss.lottery.server.service.IndexService;
import com.ljzzkkkss.lottery.server.utils.CodeUtil;
import com.ljzzkkkss.lottery.server.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class IndexController {
    @Resource
    private IndexService indexService;
    @Value("${file.url}")
    private String fileUrl;
    @Resource
    private MessageUtil messageUtil;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @ResponseBody
    @PostMapping("/login")
    public ReturnBody login(HttpServletRequest request,@RequestBody User user){
        User logUser = indexService.findByPhone(user.getPhone());
        if(null == logUser || StringUtils.isEmpty(logUser.getPassword())){
            return ReturnType.LOGIN_ERROR;
        }
        if(!logUser.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))){
            return ReturnType.LOGIN_ERROR;
        }
        logUser.setLastLogin(new Date());
        indexService.updateLastLogin(logUser);
        request.getSession().setAttribute("user",logUser);
        Log log = new Log();
        log.setContent("系统登录");
        log.setPhone(user.getPhone());
        log.setLogTime(new Date());
        indexService.insertLog(log);
        return new ReturnBody(fileUrl);
    }

    @ResponseBody
    @PostMapping("/loginByCode")
    public ReturnBody loginByCode(HttpServletRequest request,@RequestBody Map<String,String> param){
        String phone = param.get("phone");
        String code = param.get("code");
        User logUser = indexService.findByPhone(phone);
        if(null == logUser){
            return ReturnType.PHONE_ERROR;
        }
        if (!code.equals(stringRedisTemplate.opsForValue().get(phone + RedisConstants.CODE_SUFFIX))) {
            return ReturnType.CODE_ERROR;
        }
        logUser.setLastLogin(new Date());
        indexService.updateLastLogin(logUser);
        request.getSession().setAttribute("user",logUser);
        Log log = new Log();
        log.setContent("系统登录");
        log.setPhone(logUser.getPhone());
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


    @ResponseBody
    @PostMapping("/sendCode")
    public ReturnBody sendCode(@RequestBody Map<String,String> param){
        String phone = param.get("phone");
        if (null != stringRedisTemplate.opsForValue().get(phone + RedisConstants.SEND_CHECK_SUFFIX)) {
            return ReturnType.SEND_TO_OFTEN;
        }
        String code = CodeUtil.getCode(6);
        String text = "【viplotto】验证码为" + code + "，10分钟有效。请勿将验证码告知他人!";
        if(messageUtil.sendMessage(phone,text)){
            stringRedisTemplate.opsForValue().set(phone + RedisConstants.CODE_SUFFIX,code,10, TimeUnit.MINUTES);
            stringRedisTemplate.opsForValue().set(phone + RedisConstants.SEND_CHECK_SUFFIX,"1",1, TimeUnit.MINUTES);//加锁，一分钟只能发送一次
        }
        return ReturnType.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/updatePassword")
    public ReturnBody updatePassword(@RequestBody Map<String,String> param){
        String phone = param.get("phone");
        String code = param.get("code");
        String password = param.get("password");
        User user = indexService.findByPhone(phone);
        if(null == user){
            return ReturnType.PHONE_ERROR;
        }
        if (!code.equals(stringRedisTemplate.opsForValue().get(phone + RedisConstants.CODE_SUFFIX))) {
            return ReturnType.CODE_ERROR;
        }
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        indexService.updatePassword(user);
        return ReturnType.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/register")
    public ReturnBody register(@RequestBody Map<String,String> param){
        String username = param.get("username");
        String phone = param.get("phone");
        String code = param.get("code");
        String password = param.get("password");
        User checkUser = indexService.findByPhone(phone);
        if(null != checkUser){
            return ReturnType.PHONE_DUPLICATED;
        }
        if (!code.equals(stringRedisTemplate.opsForValue().get(phone + RedisConstants.CODE_SUFFIX))) {
            return ReturnType.CODE_ERROR;
        }
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setRegisterTime(new Date());
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        indexService.insertUser(user);
        Log log = new Log();
        log.setContent("注册");
        log.setPhone(user.getPhone());
        log.setLogTime(new Date());
        indexService.insertLog(log);
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

    @ResponseBody
    @GetMapping("/private/userInfo")
    public ReturnBody register(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        user.setPassword(null);
        return new ReturnBody(user);
    }
}
