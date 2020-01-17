package com.xu.springMVC.servlet;

import com.xu.annotation.XuController;
import com.xu.annotation.XuRequestMapping;
import com.xu.utils.ClassUtil;
import com.xu.utils.MyClassUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        try {
            //判断类上是否有XuController的注解
            findClassMVCAnnotation(classes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将url映射出去
        handlerMapping();
    }

    public void findClassMVCAnnotation(List<Class> classes) throws IllegalAccessException, InstantiationException {
        for (Class clazz : classes) {
            Annotation xuController = clazz.getDeclaredAnnotation(XuController.class);
            if(xuController!=null){
                String className = MyClassUtil.toLowerCaseFirstLetter(clazz.getSimpleName());
                springMVCBeans.put(className,clazz.newInstance());
            }
        }
    }

    public void handlerMapping(){
        Set<Map.Entry<String, Object>> entries = springMVCBeans.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            Object object = entry.getValue();
            Class<?> clazz = object.getClass();
            //判断类上是否加了mapping注解
            XuRequestMapping xuRequestMapping = clazz.getDeclaredAnnotation(XuRequestMapping.class);
            String baseUrl =null ;
            if (xuRequestMapping != null) {
//                获取value放入map
                baseUrl = xuRequestMapping.value();
            }
            //判断方法上是否加了url映射地址
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                XuRequestMapping mappingAnnotation = method.getDeclaredAnnotation(XuRequestMapping.class);
                if (mappingAnnotation!=null) {
                    String url = baseUrl+mappingAnnotation.value();
                    urlBeans.put(url,object);
                    urlMethod.put(url,method.getName());
                }
            }
        }
    }
}
