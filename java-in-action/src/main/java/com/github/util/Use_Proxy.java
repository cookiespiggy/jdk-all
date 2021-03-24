package com.github.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Use_Proxy {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        Class<?> aClass = Class.forName(Use_Proxy.class.getName());
        Field a = aClass.getField("a");
        a.setAccessible(true);
        Object c = a.get(a);

        //
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(c);

        try {
            Field memberValues = invocationHandler.getClass().getField("memberValues");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    static @interface QueryTable {
        String value() default "";
    }
}
