package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.properties.TestProperties;
import com.demo.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserProperties userProperties;

    @Value("${xiwen.name}")
    private String name;

    @Value("${xiwen.age}")
    private Integer age;

    @Autowired
    private TestProperties testProperties;
    @GetMapping("test1")
    public ResponseEntity<String> test1(){
        return ResponseEntity.ok(JSON.toJSONString(userProperties));
    }

    @GetMapping("test2")
    public ResponseEntity<String> test2(){
        JSONObject object = new JSONObject();
        object.put("name",name);
        object.put("age",age);
        return ResponseEntity.ok(object.toJSONString());
    }

    @GetMapping("test3")
    public ResponseEntity<String> test3(){
        return ResponseEntity.ok(JSON.toJSONString(testProperties));
    }


}
