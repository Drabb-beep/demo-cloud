package com.demo.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {

    @GetMapping("/test/t1")
    public String index(){
        return "t1";
    }
    @GetMapping("/test1/t1")
    public String index1(){
        return "t2";
    }
}
