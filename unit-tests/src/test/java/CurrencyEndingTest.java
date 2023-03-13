import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.example.Currency.RUB;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyEndingTest {
    private static Stream<Arguments> provideCurrencyEndingsRub() {
        return Stream.of(
                Arguments.of(1, "рубль"),
                Arguments.of(2,  "рубля"),
                Arguments.of(5,  "рублей"),
                Arguments.of(10,  "рублей")
        );
    }

    @ParameterizedTest
    @MethodSource("provideCurrencyEndingsRub")
    void testCurrencyEndingsForRub(Integer input, String expected) {
        assertEquals(expected, RUB.getEndingMessage(input));
    }
}
