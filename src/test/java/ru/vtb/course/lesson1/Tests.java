package ru.vtb.course.lesson1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class Tests {
    @Test
    @DisplayName("Корректное наименование счета и валюта с корректным количеством")
    public void correctNameAndCurr() {
        Account acc1 = new Account("Account 1");
        acc1.setCurrQuantity(Currency.RUB, 1000);
        Integer col1 = acc1.getCurrQuantity().get(Currency.RUB);
        Assertions.assertEquals(1000, (int) col1, "Quantity not equals 1000");
    }
    @Test
    @DisplayName("Проверка инкапсуляции массива валют")
    public void checkCurrency() {
        Account acc1 = new Account("Account 1");
        acc1.setCurrQuantity(Currency.RUB, 1000);
        acc1.getCurrQuantity().put(Currency.RUB, 2000);
        Assertions.assertEquals(1000, acc1.getCurrQuantity().get(Currency.RUB), "Incapsulation error in Currency Array");
    }

    @Test
    @DisplayName("Некорректное наименование и кол-во валюты кидает исключение")
    public void checkCorrectNameAndQuantity() {
        Account acc1 = new Account("Account 1");
        Assertions.assertThrows( IllegalArgumentException.class, () -> acc1.setName(" ") );
        Assertions.assertThrows( IllegalArgumentException.class, () -> acc1.setName("") );
        Assertions.assertThrows( NullPointerException.class, () -> acc1.setName(null) );
        Assertions.assertThrows( IllegalArgumentException.class, () -> acc1.setCurrQuantity(Currency.RUB, -1) );
    }

    @Test
    @DisplayName("Проверка корректности UNDO")
    public void checkUndo() {
        Account acc1 = new Account("Account 1");
        acc1.setCurrQuantity(Currency.RUB, 10);
        acc1.setName("Account 2");
        acc1.setCurrQuantity(Currency.RUB, 20);
        acc1.setCurrQuantity(Currency.USD, 100);
        acc1.undo();
        Assertions.assertTrue( acc1.getName().equals("Account 2")
                                     && acc1.getCurrQuantity().size()==1
                                     && acc1.getCurrQuantity().get(Currency.RUB)==20
                                    , "Undo 1 not correct");
        acc1.undo();
        Assertions.assertTrue( acc1.getName().equals("Account 2")
                        && acc1.getCurrQuantity().size()==1
                        && acc1.getCurrQuantity().get(Currency.RUB)==10
                , "Undo 2 not correct");

        acc1.undo();
        Assertions.assertTrue( acc1.getName().equals("Account 1")
                        && acc1.getCurrQuantity().size()==1
                        && acc1.getCurrQuantity().get(Currency.RUB)==10
                , "Undo 3 not correct");

        acc1.undo();
        Assertions.assertTrue( acc1.getName().equals("Account 1")
                        && acc1.getCurrQuantity().isEmpty()
                , "Undo 4 not correct");

        acc1.undo();
        Assertions.assertTrue( acc1.getName().equals("Account 1")
                        && acc1.getCurrQuantity().isEmpty()
                , "Undo 5 not correct");
    }

    @Test
    @DisplayName("Проверка корректности Save & Load")
    public void checkSaveAndLoad() {
        Account acc1 = new Account("Acc_1");
        acc1.setCurrQuantity(Currency.RUB, 10);
        acc1.setCurrQuantity(Currency.USD, 100);
        SaveAccount sa = acc1.save();
        sa.getCurrQuantity().put(Currency.EUR,1000);
        Assertions.assertTrue(
                Objects.equals(acc1.getName(), sa.getName()) && Objects.deepEquals(acc1.getCurrQuantity(), sa.getCurrQuantity()), "Account not equal SaveAccount" );
        acc1.setName("Acc_2");
        acc1.setCurrQuantity(Currency.EUR, 800);
        acc1.load(sa);
        Assertions.assertTrue(Objects.equals(acc1.getName(), sa.getName()) && Objects.deepEquals(acc1.getCurrQuantity(), sa.getCurrQuantity()), "Account not equal SaveAccount after Load");

    }

}

