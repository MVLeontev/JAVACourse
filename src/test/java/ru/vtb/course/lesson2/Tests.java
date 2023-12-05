package ru.vtb.course.lesson2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tests {
    @Test
    @DisplayName("Проверка кеширования вызова методов")
    public void testCacheValue(){
        // для тестирования кеширования используем метод testCache, который возвращает переданный int
        // т.к. при кешировании мы не учитываем параметры метода, то кешированный метод должен вернуть сохраненный результат
        Able a = Utils.cache(new A());
        // первый запуск, метод не кеширован, ожидаем тоже самое значение
        Assertions.assertTrue(a.testCache(5) == 5, "Первый запуск вернул неверное значение");
        // второй запуск, метод кеширован, ожидаем старое значение из кеша
        Assertions.assertTrue(a.testCache(7) == 5, "Второй запуск вернул неверное значение");
        //запуск сеттера, кеш сброшен
        a.setValue();
        Assertions.assertTrue(a.testCache(5) == 5, "Первый запуск после сброса кеша вернул неверное значение");
        //
    }



}
