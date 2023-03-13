import org.example.Dice;
import org.example.Game;
import org.example.GameWinnerConsolePrinter;
import org.example.GameWinnerPrinter;
import org.example.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class GameTest {
    @Test
    public void testPlayerOneWIn() {
        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.roll()).thenReturn(6, 5);
        GameWinnerPrinter gameWinnerPrinter = Mockito.mock(GameWinnerPrinter.class);
        Mockito.doAnswer(answer -> {
            Player actualWinner = answer.getArgument(0);
            Assertions.assertEquals("Vasya", actualWinner.getName());
            return null;
        }).when(gameWinnerPrinter).printWinner(any());
        Game game = new Game(dice, gameWinnerPrinter);
        Player player1 = new Player("Vasya");
        Player player2 = new Player("Petya");

        game.playGame(player1, player2);
    }
}
