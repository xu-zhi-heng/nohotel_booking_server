package com.xu.nohotel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xu.nohotel.dao")
public class NohotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(NohotelApplication.class, args);
    }

}
