package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JWANG477@ford.com
 * @date ${DATE} ${TIME}
 */
@SpringBootApplication
@Controller
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }

    @GetMapping("hello")
    @ResponseBody
    public String seyHello(String name) {
        return "hello" + name;
    }
}