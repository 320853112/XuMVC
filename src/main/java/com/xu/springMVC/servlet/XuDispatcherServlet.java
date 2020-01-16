package com.xu.springMVC.servlet;

import com.xu.utils.ClassUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class XuDispatcherServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        //获取当前包下所有的类
        ClassUtil.getClasses("com.xu.controller");
        //判断类上是否有XuController的注解
    }
}
