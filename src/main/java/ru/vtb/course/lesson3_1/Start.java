// для запуска очистки кэша используется класс Timer. Задание на очистку создается в конструкторе InvocationHandler и стартует через 500 мс с интервалом 1000 мс
// для хранения кэша используется потокобезопасная коллекция ConcurrentHashMap
// для корректного кэширования в классе д.б. реализован метод toString

package ru.vtb.course.lesson3_1;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        Fraction fr1 = new Fraction(2, 4);
        Fractionable num1 = Utils.cache(fr1, true);

        System.out.println(num1.doubleValue());
        System.out.println(num1.doubleValue());
        System.out.println(num1.doubleValue());
        num1.setNum(6);
        System.out.println(num1.doubleValue());// sout сработал
        System.out.println(num1.doubleValue());// sout молчит
        num1.setNum(2);
        System.out.println(num1.doubleValue());// sout молчит
        System.out.println(num1.doubleValue());// sout молчит

        Thread.sleep(3000);
        System.out.println("--ОЧИСТКА-----");
        System.out.println(num1.doubleValue());//
        System.out.println(num1.doubleValue());//



    }
}
