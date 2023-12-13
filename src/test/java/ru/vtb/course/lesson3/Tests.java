package ru.vtb.course.lesson3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tests {
    private  A a1;
    private Able able;
//--------
    interface Able {
        void setNum(int num);
        double doubleValue();
    }
    class A implements Able {
        private int num;
        private int denum;
        static int flag = 0;

        public A(int num, int denum) {
            this.num = num;
            this.denum = denum;
        }
        @Override
        public void setNum(int num) {
            this.num = num;
        }

        public void setDenum(int denum) {
            this.denum = denum;
        }

        @Override
        public String toString() {
            return "A{" +
                    "num=" + num +
                    ", denum=" + denum +
                    '}';
        }

        @Override
        @Cache(1000)
        public double doubleValue() {
            flag = 1;
            return (double) num/denum;
        }
    }

//--------

    @BeforeEach
    public void initTest() {
        a1 = new A(2, 4);
    }


    @Test
    @DisplayName("Проверка Кеширования после создания объекта")
    public void testFirstCache() {
        able = Utils.cache(a1, true);
        A.flag = 0;
        //первый вызов, кэш пустой, ожидаем выполнение метода, на выходе 0,5 и взведение флага в 1
        Assertions.assertTrue(able.doubleValue() == 0.5 &&  A.flag == 1, "Ошибка кэширования первого вызова"  );
        A.flag = 0;
        // второй вызов, объект и метод в кэше, метод не выполняется, просто возвращается результат 0,5, флаг в 0
        Assertions.assertTrue(able.doubleValue() == 0.5 &&  A.flag == 0, "Ошибка кэширования второго вызова"  );
    }
    @Test
    @DisplayName("Проверка кеширования после изменения объекта")
    public void testChangeObject() {
        able = Utils.cache(a1, true);
        able.doubleValue(); // заполнили кэш значениями 2,4
        able.doubleValue(); // попали в кэш
        able.setNum(6);     // изменили объект, теперь там 6,4
        A.flag = 0;
        // измененного объекта в кэше нет, метод выполняется, возвращает 1,5 и взводит флаг
        Assertions.assertTrue( able.doubleValue() == 1.5 && A.flag == 1, "Ошибка первого вызова после изменения объекта"  );
        A.flag = 0;
        // второй вызов с измененным объектом, результат из кэша 1,5, флаг не взводится
        Assertions.assertTrue( able.doubleValue() == 1.5 && A.flag == 0, "Ошибка второго вызова после изменения объекта"  );
    }
    @Test
    @DisplayName("Проверка кэширования после возврата объекта в предыдущее состояние")
    public void testAfterReturn() {
        able = Utils.cache(a1, true);
        able.doubleValue(); // заполнили кэш значениями 2,4
        able.doubleValue(); // попали в кэш
        able.setNum(6);     // изменили объект, теперь там 6,4
        able.doubleValue(); // заполнили кэш значениями 6,4
        able.doubleValue(); // попали в кэш
        able.setNum(2);     // вернули объект, теперь там опять 2,4
        A.flag = 0;
        //объект должен быть в кеше, поэтому ожидаем 0,5 и флаг не взведен
        Assertions.assertTrue(able.doubleValue() == 0.5 &&  A.flag == 0, "Ошибка кэширования возврата в исходное состояние"  );
    }

    @Test
    @DisplayName("Проверка сброса кэша по таймауту")
    public void testClearCache() throws InterruptedException {
        able = Utils.cache(a1, true);
        able.doubleValue(); // заполнили кэш значениями 2,4
        able.setNum(6);     // изменили объект, теперь там 6,4
        able.doubleValue(); // заполнили кэш значениями 6,4
        Thread.sleep(2000);  // в аннотации задано время жизни объекта в кэше 1000 мс, заснем на больше
        A.flag = 0;
        // ожидаем очистки кэша, значит метод вернет 1,5 и взведет флаг
        Assertions.assertTrue(able.doubleValue() == 1.5 &&  A.flag == 1, "Ошибка после сброса кэша по таймауту" );
    }

    @Test
    @DisplayName("Проверка многопоточной блокировки (длительный тест)")
    public void testConcurrentLock() throws InterruptedException {
        //сравним кол-во потоков в начале и в цикле. Если меньше - значит отвалился поток очистки
        able = Utils.cache(a1, true);
        int threadsCount = Thread.activeCount();
        for (int i = 0; i < 1000; i++) {
            int val = (int)(Math.random() * 100);
            Thread.sleep(10);
            able.doubleValue();
            able.doubleValue();
            able.setNum(val);
            able.doubleValue();
            able.doubleValue();
            if (threadsCount > Thread.activeCount()) throw new RuntimeException("поток остановлен");
        }
    }

}
