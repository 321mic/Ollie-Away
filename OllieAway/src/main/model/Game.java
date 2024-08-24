package model;

// Represents if the game is over or not
public class Game {
    private boolean gameOver;

    // EFFECTS: Game gameOver is set to false
    public Game() {
        this.gameOver = false;
    }

    // EFFECTS: sets gameOver to true
    public void gameOver() {
        gameOver = true;
    }

    // EFFECTS: returns true if game is over, false otherwise
    public boolean isGameOver() {
        return gameOver;
    }
}
