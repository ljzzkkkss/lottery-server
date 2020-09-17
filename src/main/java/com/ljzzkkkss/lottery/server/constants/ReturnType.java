package com.ljzzkkkss.lottery.server.constants;

import com.ljzzkkkss.lottery.server.model.ReturnBody;

public class ReturnType {
    public static ReturnBody SERVER_ERROR = new ReturnBody("0500","系统异常！");
    public static ReturnBody SUCCESS = new ReturnBody("0000","成功！");
    public static ReturnBody METHOD_ERROR = new ReturnBody("0501","请求Method错误！");
    public static ReturnBody LOGIN_ERROR = new ReturnBody("0201","用户名或密码错误！");
    public static ReturnBody PARAM_LEAK = new ReturnBody("0202","缺少参数！");
    public static ReturnBody INVALID_PARAM = new ReturnBody("0203","非法参数！");
    public static ReturnBody NEED_LOGIN = new ReturnBody("0204","需要登陆！");
    public static ReturnBody SEND_TO_OFTEN = new ReturnBody("0205","发送太频繁，一分钟后再试！");
    public static ReturnBody PHONE_ERROR = new ReturnBody("0206","手机号错误！");
    public static ReturnBody CODE_ERROR = new ReturnBody("0206","验证码错误！");
    public static ReturnBody PHONE_DUPLICATED = new ReturnBody("0207","手机号已注册！");
}
