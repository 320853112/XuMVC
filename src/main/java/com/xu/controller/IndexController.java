package com.xu.controller;

import com.xu.annotation.XuController;
import com.xu.annotation.XuRequestMapping;

@XuController
@XuRequestMapping("/xu")
public class IndexController {

    @XuRequestMapping("/test")
    public String test(){
        System.out.println("method running");
        return "index";
    }
}
