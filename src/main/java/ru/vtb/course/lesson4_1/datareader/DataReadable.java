package ru.vtb.course.lesson4_1.datareader;

public interface DataReadable<T> {
    T read(String pathFold);
}
