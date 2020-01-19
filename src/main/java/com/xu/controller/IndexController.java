package com.xu.controller;

import com.xu.annotation.MyController;
import com.xu.annotation.MyRequestMapping;

import java.nio.charset.Charset;

@MyController
@MyRequestMapping("/xu")
public class IndexController {

    @MyRequestMapping("/test1")
    public String test1(){
        System.out.println("method1 running");
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset().name());
        return "indexB";
    }

    @MyRequestMapping("/test2")
    public String test2(){
        System.out.println("method2 running");
        return "WEB-INF/indexA";
    }
}
