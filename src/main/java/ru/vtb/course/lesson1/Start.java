package ru.vtb.course.lesson1;

public class Start {
    public static void main(String[] args) {

        Account acc1 = new Account("Acc_1");
        acc1.setCurrQuantity(Currency.RUB, 10);
        acc1.setName("Acc_2");
        acc1.setCurrQuantity(Currency.RUB, 20);
        acc1.setCurrQuantity(Currency.USD, 100);
        System.out.println(acc1);
        if (acc1.checkUndo()) {
            acc1.undo();
            System.out.println("== UNDO_1 ==");
            System.out.println(acc1);
        }

        if (acc1.checkUndo()) {
            acc1.undo();
            System.out.println("== UNDO_2 ==");
            System.out.println(acc1);
        }

        if (acc1.checkUndo()) {
            acc1.undo();
            System.out.println("== UNDO_3 ==");
            System.out.println(acc1);
        }

        if (acc1.checkUndo()) {
            acc1.undo();
            System.out.println("== UNDO_4 ==");
            System.out.println(acc1);
        }

        if (acc1.checkUndo()) {
            acc1.undo();
            System.out.println("== UNDO_5 ==");
            System.out.println(acc1);
        } else {
            System.out.println("!!! UNDO_5 impossible");
        }

        System.out.println("== SAVE ==");
        SaveAccount sa = acc1.save();
        acc1.setCurrQuantity(Currency.EUR, 800);
        System.out.println("== CHANGE ==");
        System.out.println(acc1);
        acc1.load(sa);
        System.out.println("== LOAD ==");
        System.out.println(acc1);
    }
}


