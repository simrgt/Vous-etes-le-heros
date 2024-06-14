package ui;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Console UI implementation
 */
public class Console implements Ui {
    protected static final int WIDTH = 80;
    private final Supplier<String> in;
    private final Consumer<String> out;

    public Console(Supplier<String> in, Consumer<String> out) {
        this.in = in;
        this.out = out;
    }

    /**
     * @param text text to show in the console
     * @return
     */

    public void show(String text) {
        StringBuilder sb = new StringBuilder();
        String border = "=".repeat(WIDTH);
        sb.append(border).append("\n");
        sb.append(formatText(text).replace("\\n", System.lineSeparator()));
        sb.append(border).append("\n");
        out.accept(sb.toString());
    }

    /**
     * @param text text to format
     * @return formatted text
     */
    private static String formatText(String text) {
        // Split the text into lines and add | at the beginning and end of each line
        // to make it look like a dialog, the width of the dialog is 80 characters
        StringBuilder sb = new StringBuilder();
        for (String line : text.split("\n")) {
            sb.append("| ");
            int lineLength = line.length();
            int numSpaces = WIDTH - lineLength - 4; // 4 = 2 spaces at the beginning and 2 spaces at the end
            sb.append(line);
            appendSpaces(sb, numSpaces);
            sb.append(" |\n");
        }
        return sb.toString();
    }

    /**
     * @param sb       StringBuilder to append spaces to
     * @param numSpaces number of spaces to append
     */
    private static void appendSpaces(StringBuilder sb, int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            sb.append(' ');
        }
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
            return ask(); // Demander à nouveau à l'utilisateur de saisir un entier valide
        }
    }


    public void clear() {
        out.accept("\033[H\033[2J");
    }


    public String askString() {
        return in.get();
    }
}
