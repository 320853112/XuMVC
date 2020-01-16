package com.xu.test;

public class Test {


    public static void main(String[] args) {
        ClassLoader classLoader = Test.class.getClassLoader();
        System.out.println(classLoader);
        //表示当前线程的类加载器——应用程序类加载器
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        //—启动类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
    }
}
