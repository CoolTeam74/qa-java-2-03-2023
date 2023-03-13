package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Game> gameClass = Game.class;
        Field diceField = gameClass.getDeclaredField("dice");
        Constructor<Game> constructor = gameClass.getConstructor(Dice.class, GameWinnerPrinter.class);
        Method declaredMethod = gameClass.getDeclaredMethod("doSomething");
        declaredMethod.setAccessible(true);
        Game newInstance = constructor.newInstance(new DiceImpl(), new GameWinnerConsolePrinter());
        declaredMethod.invoke(newInstance);


        User user1 = new User();
        user1.setName("Vasya");
        user1.setAge(10);
        user1.setRole("Developer");
        user1.setAddresses(new ArrayList<>());


    }
}
