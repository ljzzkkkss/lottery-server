package com.ljzzkkkss.lottery.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.ljzzkkkss.lottery.server.mapper")
@EnableTransactionManagement
@EnableCaching
public class LotteryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotteryServerApplication.class, args);
    }

}
