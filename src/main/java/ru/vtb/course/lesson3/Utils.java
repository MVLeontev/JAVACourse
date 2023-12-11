package ru.vtb.course.lesson3;

import java.lang.reflect.Proxy;

public class Utils {
    public static <T> T cache(T objectIncome) {

        ClassLoader objectIncomeClassLoader = objectIncome.getClass().getClassLoader();
        Class[] objectIncomeInterfaces = objectIncome.getClass().getInterfaces();

        T proxyObject = (T) Proxy.newProxyInstance(objectIncomeClassLoader,
                objectIncomeInterfaces,
                new ObjectInvocationHandlerAdv<T>(objectIncome));

        return proxyObject;
    }
}
