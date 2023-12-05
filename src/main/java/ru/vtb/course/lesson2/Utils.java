package ru.vtb.course.lesson2;

import java.lang.reflect.Proxy;

public class Utils {
    public static <T> T cache(T objectIncome) {

        ClassLoader objectIncomeClassLoader = objectIncome.getClass().getClassLoader();
        Class[] interfaces = objectIncome.getClass().getInterfaces();

        T proxyObject = (T) Proxy.newProxyInstance(objectIncomeClassLoader, interfaces, new ObjectInvocationHandler<T>(objectIncome));

        return proxyObject;

    }
}
