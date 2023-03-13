package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.example.Currency.USD;

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
        List<User> users = new ArrayList<>();
        List<User> filterdUsers = new ArrayList<>();
        for(User user : users) {
            if(user.getRole().equals("Developer") && user.getAge() < 20) {
                filterdUsers.add(user);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(User user : filterdUsers) {
            sb.append(user.getName() + " " + user.getRole() + ",");
        }
        sb.append("]").toString();

        users.stream()
                .filter(user -> user.getRole().equals("Developer") && user.getAge() < 20)
                .map(user -> user.getName() + " " + user.getRole())
                .collect(Collectors.joining(",", "[", "]"));

        // 1 USD -> 1 доллар
        System.out.println("Enter amount and currency code:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] parameters = userInput.split(" ");
        Integer amount = Integer.parseInt(parameters[0]);
        String currencyCode = parameters[1];
        Currency currency = Currency.valueOf(currencyCode);
        String endingMessage = currency.getEndingMessage(amount);
        System.out.println(endingMessage);

        double convertAmount = currency.convert(100.0, USD);
        System.out.println(convertAmount);

        USD.showMessage("123");

    }
}
