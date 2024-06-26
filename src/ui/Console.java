package ui;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Console UI implementation
 */
public class Console implements Ui {
    /**
     * Tabulation character
     */
    private final static String TAB = "#t#";
    /**
     * Escape character
     */
    private final static String ESCAPE = "#n#";

    /**
     * Life balise
     */
    private final static String BALISE_LIFE = "#LIFE#";

    /**
     * Input supplier
     */
    private final Supplier<String> in;

    /**
     * Output consumer
     */
    private final Consumer<String> out;

    /**
     * @param in input supplier
     * @param out output consumer
     */
    public Console(Supplier<String> in, Consumer<String> out) {
        this.in = in;
        this.out = out;
    }

    /**
     * @param text text to show in the console
     */

    public void show(String text) {
        out.accept(text);
    }

    /**
     * @return user input
     */
    public int ask() {
        String input = in.get();

        if (input.isEmpty()) {
            return -1; // Retourner -1 si l'utilisateur appuie sur Entrée sans saisir de valeur
        }

        try {
            return Integer.parseInt(input); // Retourner l'entier si l'utilisateur en fournit un
        } catch (NumberFormatException e) {
            return -1; // Demander à nouveau à l'utilisateur de saisir un entier valide
        }
    }

    /**
     * @return user input
     */
    public String askString() {
        return in.get();
    }

    /**
     * @return escape character
     */
    public String getEscape() {
        return ESCAPE;
    }

    /**
     * @return tabulation character
     */
    public String getTab() {
        return TAB;
    }

    /**
     * @return life balise
     */
    public String getBaliseLife() {
        return BALISE_LIFE;
    }
}
