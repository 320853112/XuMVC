package com.xu.springMVC.servlet;

import com.xu.annotation.MyController;
import com.xu.annotation.MyRequestMapping;
import com.xu.utils.MyClassUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class XuDispatcherServlet extends HttpServlet {

    //MVC容器对象 (类名，对象)
    private ConcurrentHashMap<String,Object> springMVCBeans = new ConcurrentHashMap<String, Object>();
    //（url,对象）
    private ConcurrentHashMap<String,Object> urlBeans = new ConcurrentHashMap<String, Object>();
    //（url,方法名）
    private ConcurrentHashMap<String,String> urlMethod = new ConcurrentHashMap<String, String>();


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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求
        //获取请求url
        String requestURL = req.getRequestURI();
        if (requestURL==null) {
            return;
        }
        Object object = urlBeans.get(requestURL);
        if(object==null){
            resp.getWriter().write("page not find 404");
            return;
        }
        String methodName = urlMethod.get(requestURL);
        if(methodName==null){
            resp.getWriter().write("method not find");
            return;
        }
        String resultPage = (String)methodInvoke(object, methodName);
        resp.getWriter().write(resultPage);
        resourceViewResolver(resultPage,req,resp);


    }

    private void resourceViewResolver(String pageName,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String prefix = "/";
        String suffix = ".jsp";
        req.getRequestDispatcher(prefix+pageName+suffix).forward(req,resp);
    }

    private Object methodInvoke(Object object,String methodName){
        try {
            Method method = object.getClass().getMethod(methodName);
            Object result = method.invoke(object);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public void findClassMVCAnnotation(List<Class> classes) throws IllegalAccessException, InstantiationException {
        for (Class clazz : classes) {
            Annotation xuController = clazz.getDeclaredAnnotation(MyController.class);
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
            MyRequestMapping myRequestMapping = clazz.getDeclaredAnnotation(MyRequestMapping.class);
            String baseUrl = "" ;
            if (myRequestMapping != null) {
//                获取value放入map
                baseUrl = myRequestMapping.value();
            }
            //判断方法上是否加了url映射地址
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                MyRequestMapping mappingAnnotation = method.getDeclaredAnnotation(MyRequestMapping.class);
                if (mappingAnnotation!=null) {
                    String url = baseUrl+mappingAnnotation.value();
                    urlBeans.put(url,object);
                    urlMethod.put(url,method.getName());
                }
            }
        }
    }
}
