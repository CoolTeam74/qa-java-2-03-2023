import org.example.Dice;
import org.example.DiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DIceTest {
    private Dice dice = new DiceImpl();

    @Test
    @Disabled("Unstable, found bug")
    public void testDiceRollRange() {
        Assertions.assertTrue(dice.roll() > 0);
        Assertions.assertTrue(dice.roll() <= 0);
    }
}
