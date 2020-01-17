package com.xu.springMVC.servlet;

import com.xu.annotation.XuController;
import com.xu.utils.ClassUtil;
import com.xu.utils.MyClassUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class XuDispatcherServlet extends HttpServlet {

    //MVC容器对象 (类名，对象)
    private ConcurrentHashMap<String,Object> springMVCBeans = new ConcurrentHashMap<String, Object>();
    //（url,类名）
    private ConcurrentHashMap<String,Object> urlBeans = new ConcurrentHashMap<String, Object>();
    //（url,方法）
    private ConcurrentHashMap<String,Object> urlMethod = new ConcurrentHashMap<String, Object>();


    @Override
    public void init() throws ServletException {
        //获取当前包下所有的类
        List<Class> classes = MyClassUtil.getClasses("com.xu.controller");
        //将所有类注入到springMVC容器当中
        findClassMVCAnnotation(classes);
        //判断类上是否有XuController的注解

//        classes
    }

    public void findClassMVCAnnotation(List<Class> classes){
        for (Class clazz : classes) {
            Annotation xuController = clazz.getDeclaredAnnotation(XuController.class);
            if(xuController!=null){

            }
                springMVCBeans.put(classes.getClass().getName(),classes.getClass());

        }
    }

}
