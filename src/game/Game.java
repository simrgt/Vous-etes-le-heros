package game;

import java.io.IOException;

/**
 * Represents a game.
 */
public interface Game {
    static final String END_CODE = "#!#END#!#";
    /**
     * Starts a new game.
     */
    void startNewGame() throws IOException;
}
