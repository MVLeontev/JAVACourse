package ru.vtb.course.proxy;

public interface Person {
    @MyAnno(message = "Hi!")
    public void introduce(String name);
    public void sayAge(int age);
    public void sayFrom(String city, String country);
}
