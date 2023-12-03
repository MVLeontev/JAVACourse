package ru.vtb.course.lesson1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumMap;

public class Account {
    private String name;
    private final EnumMap<Currency, Integer> currQuantity = new EnumMap<>(Currency.class);
    private final Deque<Runnable> commandStack = new ArrayDeque<>();

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
        return commandStack.size() > 1;
    }
    public void undo() {
        if (commandStack.size() <= 1) throw new RuntimeException("No changes to undo");
        commandStack.pop().run();
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
