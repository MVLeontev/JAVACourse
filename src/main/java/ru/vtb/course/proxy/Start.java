package ru.vtb.course.proxy;

import java.lang.reflect.Proxy;

public class Start {
    public static void main(String[] args) {
        Man mike = new Man("Mike", 17, "Moskow", "Russia");

        ClassLoader manClassLoader = mike.getClass().getClassLoader();
        Class[] interfaces = mike.getClass().getInterfaces();

        Person proxyMan = (Person) Proxy.newProxyInstance(manClassLoader, interfaces, new PersonInvocationHandler(mike));

        proxyMan.introduce(mike.getName());
        proxyMan.sayAge(mike.getAge());
        proxyMan.sayFrom(mike.getCity(), mike.getCountry());
    }
}
