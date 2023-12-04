package ru.vtb.course.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersonInvocationHandler implements InvocationHandler {
    private Person person;

    public PersonInvocationHandler(Person person) {
        this.person = person;
    }
    int counter = 0;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (counter == 0) System.out.println("Привет!");
        counter++;
        //return null;
        if (method.isAnnotationPresent(MyAnno.class)) {
            System.out.println(method.getAnnotation(MyAnno.class).message());
        }
        return method.invoke(person, args);
    }
}
