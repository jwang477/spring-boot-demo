package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    public static void main(String[] args) {
        test();

    }

    private static void test() {
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~!@#$%^&*()[{]}-_=+|;:'\",<.>/?`";
        String number = "0123456789";
        String lowerLetter = "abcdefghijklmnopqrstuvwxyz";
        String upperLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialChar = "~!@#$%^&*()[{]}-_=+|;:'\",<.>/?`";

        List<Character> list = new ArrayList<>();
        list.add(number.charAt((int)Math.floor(Math.random() * number.length())));
        list.add(lowerLetter.charAt((int)Math.floor(Math.random() * lowerLetter.length())));
        list.add(upperLetter.charAt((int)Math.floor(Math.random() * upperLetter.length())));
        list.add(specialChar.charAt((int)Math.floor(Math.random() * specialChar.length())));

        for (int l = 0; l < 8; l++) {
            list.add(str.charAt((int)Math.floor(Math.random() * str.length())));
        }
        Collections.shuffle(list);
        StringBuffer sb = new StringBuffer();
        for (Character character : list) {
            sb.append(character);
        }
        System.out.println("sb = " + sb);
    }

}
