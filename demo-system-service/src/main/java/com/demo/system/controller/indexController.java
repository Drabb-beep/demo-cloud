package com.demo.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: DaiFu
 * Date: 2022/8/4
 */
@RestController
@RequestMapping("/demo-system-service")
public class indexController {
    @GetMapping("/test")
    public String index(){
        return "demo-system-service";
    }
}
