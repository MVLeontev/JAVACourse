package ru.vtb.course.lesson4_1.datatransformer;

@FunctionalInterface
public interface DataTransformerable<T> {
    T transform(T t);
}
