package game;

import ui.Ui;

public class Classic implements Game {
    private final Ui ui;

    public Classic(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void startNewGame() {
        ui.afficher("Starting new game...");

    }
}
