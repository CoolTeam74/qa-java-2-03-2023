package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Game> gameClass = Game.class;
        Field diceField = gameClass.getDeclaredField("dice");
        Constructor<Game> constructor = gameClass.getConstructor(Dice.class, GameWinnerPrinter.class);
        Method declaredMethod = gameClass.getDeclaredMethod("doSomething");
        declaredMethod.setAccessible(true);
        Game newInstance = constructor.newInstance(new DiceImpl(), new GameWinnerConsolePrinter());
        declaredMethod.invoke(newInstance);
    }
}
