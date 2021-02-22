package com.yiyuan.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan(basePackages = {"com.yiyuan.demo.dao"})
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
       System.out.println(m());
    }

    public static String m() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String b = passwordEncoder.encode("123456");
        return b;
    }
}
