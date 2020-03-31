package com.example.springboot.Utils;

import javax.naming.Context;

public class LogGenerate {

    public static String log(Class<?> c, String info) {
        return '[' + c.getName() + "] " +
                System.currentTimeMillis() + ": " +
                info;
    }

}
