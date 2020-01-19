package com.xu.test;

import com.xu.utils.ClassUtil;
import com.xu.utils.MyClassUtil;

import java.nio.charset.Charset;
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
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset().name());
    }
}
