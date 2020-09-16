package com.ljzzkkkss.lottery.server.utils;

import java.util.Random;

public class CodeUtil {
    public static String getCode(int length){
        if(length <= 0 || length > 20){
            return "";
        }
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}
