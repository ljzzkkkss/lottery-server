package com.ljzzkkkss.lottery.server.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MessageUtil {
    @Value("${message.url}")
    private String messageUrl;
    @Value("${message.uid}")
    private String uid;
    @Value("${message.pw}")
    private String pw;
    @Resource
    private RestTemplate restTemplate;

    public boolean sendMessage(String phone,String text){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String tm = format.format(new Date());
        String url = messageUrl + "?uid=" + uid + "&pw=" + DigestUtils.md5DigestAsHex((pw + tm).getBytes()) + "&mb=" + phone + "&ms=" + text + "&tm=" + tm;
        String result = restTemplate.getForObject(url,String.class);
        return null != result && !result.startsWith("-");
    }
}
