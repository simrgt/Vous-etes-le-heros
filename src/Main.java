import game.Classic;
import game.Game;
import representation.node.InnerNode;
import ui.Console;
import ui.Ui;

public class Main {
    public static void main(String[] args) {
        // Création d'une interface
        Ui ui = new Console(); // Remplacez ConsoleUi par le nom de votre classe d'interface

        // Création d'une instance de jeu
        Game jeu = new Classic(ui); // Remplacez MonJeu par le nom de votre classe de jeu

        // Démarrage d'une nouvelle partie
        jeu.startNewGame();
    }
}
