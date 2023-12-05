package ru.vtb.course.lesson2;

public class Main {
    public static void main(String[] args) {

        Able a = Utils.cache(new A());
        a.method1();
        a.method1();
        a.setValue();
        a.method1();
        a.method1();
        a.method1();
        System.out.println(a.testCache(1));
        System.out.println(a.testCache(2));
        a.setValue();
        System.out.println(a.testCache(2));
    }
}
