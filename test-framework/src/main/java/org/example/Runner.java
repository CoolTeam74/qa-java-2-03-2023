package org.example;

import com.sun.jdi.InvocationException;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Runner {
    public static void run(String packageName) {
        Set<Class> classes = findAllClasses(packageName);
        // TODO; count successful and failed test
        classes.forEach(clazz -> {
            Map<Class<?>,List< Method>> methods = getMethodsAnnotatedWithJunit(clazz);
            if (!methods.isEmpty()) {
                try {
                    Object instance = createInstance(clazz);
                    if (methods.get(Test.class) != null) {
                        List<Method> testMethods = methods.get(Test.class);
                        testMethods.forEach(method -> {
                            try {
                                if(methods.get(Before.class) != null) {
                                    List<Method> beforeMethods = methods.get(Before.class);
                                    beforeMethods.forEach(beforeMethod -> {
                                        try {
                                            beforeMethod.invoke(instance);
                                        } catch (IllegalAccessException  | InvocationTargetException e) {
                                            e.printStackTrace();
                                            throw new RuntimeException("Before method failed", e.getCause());
                                        }
                                    });
                                }
                                method.invoke(instance);
                                System.out.println(String.format("Test %s passed.", method.getName()));
                                if(methods.get(After.class) != null) {
                                    List<Method> afterethods = methods.get(After.class);
                                    afterethods.forEach(afterMethod -> {
                                        try {
                                            afterMethod.invoke(instance);
                                        } catch (IllegalAccessException | InvocationTargetException e) {
                                            e.printStackTrace();
                                            throw new RuntimeException("After method failed", e.getCause());
                                        }
                                    });
                                }
                            } catch (IllegalAccessException  | InvocationTargetException e) {
                                if (e instanceof InvocationTargetException &&
                                        ((InvocationTargetException)e).getTargetException() instanceof AssertionException ) {
                                    Throwable targetException = ((InvocationTargetException) e).getTargetException();
                                    System.out.println(String.format("Test %s failed: %s", method.getName(), targetException.getMessage()));
                                }
                            }
                        });
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    private static Object createInstance(Class clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> ctor = clazz.getConstructors()[0];
        return ctor.newInstance();
    }

    private static Map<Class<?>, List<Method>> getMethodsAnnotatedWithJunit(Class type) {
        Map<Class<?>,List< Method>> methods = new HashMap<>();
        Class<?> klass = type;
        while (klass != Object.class && klass != null) {
            for (final Method method : klass.getDeclaredMethods()) {
                if(method.isAnnotationPresent(Test.class)) {
                    methods.compute(Test.class, (key, value) -> {
                        if (value == null) {
                            value = new ArrayList<>();
                        }
                        value.add(method);
                        return value;
                    });
                }
                if(method.isAnnotationPresent(Before.class)) {
                    methods.compute(Before.class, (key, value) -> {
                        if (value == null) {
                            value = new ArrayList<>();
                        }
                        value.add(method);
                        return value;
                    });
                }
                if(method.isAnnotationPresent(After.class)) {
                    methods.compute(After.class, (key, value) -> {
                        if (value == null) {
                            value = new ArrayList<>();
                        }
                        value.add(method);
                        return value;
                    });
                }

            }
            klass = klass.getSuperclass();
        }
        return methods;
    }

    private static Set<Class> findAllClasses(String packageName) {
        Reflections reflections = new Reflections(false);
        return  reflections.getSubTypesOf(Object.class).stream().collect(Collectors.toSet());
    }
}
