package com.xu.test;

import com.xu.utils.MyClassUtil;

import java.util.List;

public class Test {


    public static void main(String[] args) {
//        ClassLoader classLoader = Test.class.getClassLoader();
//        System.out.println(classLoader);
//        //表示当前线程的类加载器——应用程序类加载器
//        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
//        System.out.println(contextClassLoader);
//        //—启动类加载器
//        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
//        System.out.println(systemClassLoader);

//        List<Class> classes = MyClassUtil.getClasses("com.xu.controller");
//        for (Class clazz : classes) {
//            System.out.println(clazz.getName());
//        }
        String a = "b";
        System.out.println(a.getClass().getName());
        System.out.println(a.getClass().getSimpleName());
    }
}
