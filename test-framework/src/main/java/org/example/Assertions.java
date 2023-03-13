package org.example;

public class Assertions {
    public static void assertEquals(Object expected, Object actual, String message) throws AssertionException {
        if (!expected.equals(actual)) {
            throw new AssertionException(String.format("Expected: %s, but was %s. %s", expected, actual, message));
        }
    }

    public static void assertNotEquals(Object expected, Object actual, String message) throws AssertionException {
        if (expected.equals(actual)) {
            throw new AssertionException(String.format("Expected: %s, but was %s. %s", expected, actual, message));
        }
    }
}
