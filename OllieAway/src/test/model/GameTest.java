package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game1;
    private Game game2;

    @BeforeEach
    void runBefore() {
        game1 = new Game();
        game2 = new Game();
    }

    @Test
    void testConstructor() {
        assertFalse(game1.isGameOver());
        assertFalse(game2.isGameOver());
    }

    @Test
    void testGameOver() {
        assertFalse(game1.isGameOver());
        game1.gameOver();
        assertTrue(game1.isGameOver());

        assertFalse(game2.isGameOver());
        game2.gameOver();
        assertTrue(game2.isGameOver());
    }
}
