package com.xu.controller;

import com.xu.annotation.XuController;
import com.xu.annotation.XuRequestMapping;

@XuController
@XuRequestMapping("/xu")
public class IndexController {

    @XuRequestMapping("/test1")
    public String test1(){
        System.out.println("method running");
        return "indexB";
    }

    @XuRequestMapping("/test2")
    public String test2(){
        System.out.println("method running");
        return "WEB-INF/indexA";
    }
}
