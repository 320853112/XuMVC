package com.xu.utils;

import java.util.ArrayList;
import java.util.List;

public class MyClassUtil {
    public static List<Class> getClasses(String packageName ){
        ArrayList<Class> classes = new ArrayList();
        ClassLoader classLoader = MyClassUtil.class.getClassLoader();
        classLoader.getResource()
    }
}
