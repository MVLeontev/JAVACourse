package ru.vtb.course.lesson3_1;

import java.lang.reflect.Proxy;

public class Utils {
    public static <T> T cache(T objectIncome, boolean runCleaner) {

        ClassLoader objectIncomeClassLoader = objectIncome.getClass().getClassLoader();
        Class[] objectIncomeInterfaces = objectIncome.getClass().getInterfaces();

        T proxyObject = (T) Proxy.newProxyInstance(objectIncomeClassLoader,
                objectIncomeInterfaces,
                new ObjectInvocationHandlerAdv<T>(objectIncome, runCleaner));


        return proxyObject;
    }
}
