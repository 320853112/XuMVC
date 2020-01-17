package com.xu.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MyClassUtil {
    public static List<Class> getClasses(String packageName) throws IOException {
        packageName = packageName.replace(".", "/");
        ArrayList<Class> classes = new ArrayList();
        ClassLoader classLoader = MyClassUtil.class.getClassLoader();
        Enumeration<URL> dirs = classLoader.getResources(packageName);
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                // 获取包的物理路径
                String packagePath = URLDecoder.decode(url.getFile(), "UTF-8");
                // 以文件的方式扫描整个包下的文件 并添加到集合中
                findAndAddClassesInPackageByFile(packagePath,classes);

            }
        }


    }

    private static void findAndAddClassesInPackageByFile(String packagePath,ArrayList<Class> classes) {
        File dir = new File(packagePath);
        //如果不存在或不是文件，直接退出
        if(!dir.exists()||!dir.isDirectory()){
            return ;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        for (File file : dirfiles) {
//            是文件夹则继续递归
            if(file.isDirectory()){
                findAndAddClassesInPackageByFile(packagePath + "." + file.getName(),classes);
            }else{
                //获取类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Class.forName(packagePath+"."+className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}