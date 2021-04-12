package com.kobe;

import lombok.SneakyThrows;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kobe.dao")
public class SpringbootWebApplication {

    @SneakyThrows
    public static void main(String[] args) {

        System.out.println("启动  成功!");
        SpringApplication.run(SpringbootWebApplication.class, args);
    }

}
