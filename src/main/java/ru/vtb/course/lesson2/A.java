package ru.vtb.course.lesson2;

public class A implements Able{
    @Cache
    @Override
    public void method1() {
        System.out.println("Вызван оригинальный метод");
    }

    @Setter
    @Override
    public void setValue() {
        System.out.println("Вызван сеттер");
    }
}
