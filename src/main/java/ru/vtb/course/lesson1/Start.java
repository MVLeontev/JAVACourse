package ru.vtb.course.lesson1;

import java.util.Date;
import java.util.EnumMap;
import java.util.Stack;


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

class Account {
    private String name;
    private final EnumMap<Currency, Integer> currQuantity = new EnumMap<>(Currency.class);
    private final Stack<Runnable> commandStack = new Stack<>();

    public Account(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public EnumMap<Currency, Integer> getCurrQuantity() {
        return new EnumMap<>(currQuantity) ;
    }

    public void setName(String name) {
        if (name.isBlank()) throw new IllegalArgumentException("Account Name cannot be empty");
        String previousName = this.name;
        commandStack.push( () -> this.name = previousName );
        this.name = name;
    }
    public void setCurrQuantity(Currency cur, Integer quantity){
        if (quantity == null || quantity < 0 ) throw new IllegalArgumentException("Quantity of currency must be positive and not null");
        if (cur == null) throw new IllegalArgumentException("Currency cannot be null");
        Integer previousQuantity = this.currQuantity.get(cur);
        if (previousQuantity != null) {
            // если такая валюта есть - поместим в стек команду установки старого значения
            commandStack.push( () -> this.currQuantity.put(cur, previousQuantity) );
        }
        else {
            // а если нету такой валюты (новая валюта) - в стек команду на удаление
            commandStack.push( () -> this.currQuantity.remove(cur) );
        }
        this.currQuantity.put(cur,quantity);
    }
    public boolean checkUndo() {
        if (commandStack.size() > 1) return true;
        return false;
    }
    public Account undo() {
        if (commandStack.size() <= 1) throw new RuntimeException("No changes to undo");
        commandStack.pop().run();
        return this;
    }

    public SaveAccount save() {
        return new SaveAccount(this.name, this.currQuantity);
    }
    public void load(SaveAccount saveAccount){
        this.name = saveAccount.getName();
        this.currQuantity.clear();
        this.currQuantity.putAll(saveAccount.getCurrQuantity());
    }
    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", currQuantity=" + currQuantity +
                '}';
    }

}

enum Currency {
    RUB("Рубль"),
    USD("Доллар США"),
    EUR("Евро");

    Currency(String name) {
    }

}

class SaveAccount {
    private final String name;
    private final EnumMap<Currency, Integer> currQuantity;
    private final Date date;

    public SaveAccount(String name, EnumMap<Currency, Integer> currQuantity) {
        this.name = name;
        this.date = new Date();
        this.currQuantity = new EnumMap<>(currQuantity);
    }

    public String getName() {
        return name;
    }

    public EnumMap<Currency, Integer> getCurrQuantity() {
        return new EnumMap<>(currQuantity);
    }

    public Date getDate() {
        return date;
    }

}
