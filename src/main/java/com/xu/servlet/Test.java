package com.xu.servlet;

import java.util.concurrent.ConcurrentHashMap;

public class Test {
    //MVC容器对象 (类名，对象)
    private ConcurrentHashMap<String,Object> springMVCBeans = new ConcurrentHashMap<String, Object>();
    //（url,类名）
    private ConcurrentHashMap<String,Object> urlBeans = new ConcurrentHashMap<String, Object>();
    //（url,方法）
    private ConcurrentHashMap<String,Object> urlMethod = new ConcurrentHashMap<String, Object>();







}
