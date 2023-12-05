package ru.vtb.course.lesson2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ObjectInvocationHandler<T> implements InvocationHandler {
    private T currentObject;
    private final HashMap<Method, Object> methodStore = new HashMap();

    public ObjectInvocationHandler(T currentObject) {
        this.currentObject =currentObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object objectResult;

        objectResult = method.invoke(currentObject, args);

        return objectResult;
    }
}
