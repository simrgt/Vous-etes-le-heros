package game;

import java.util.Scanner;

/**
 * Console UI implementation
 */
public class Console {
    protected static final int WIDTH = 80;
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * @param text text to show in the console
     * @return
     */

    public String show(String text) {
        StringBuilder sb = new StringBuilder();
        String border = "=".repeat(WIDTH);
        sb.append(border).append("\n");
        sb.append(formatText(text).replace("\\n", System.lineSeparator()));
        sb.append(border).append("\n");
        return sb.toString();
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
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            return -1; // Retourner -1 si l'utilisateur appuie sur Entrée sans saisir de valeur
        }

        try {
            return Integer.parseInt(input); // Retourner l'entier si l'utilisateur en fournit un
        } catch (NumberFormatException e) {
            System.out.println("Entrez un entier valide.");
            return ask(); // Demander à nouveau à l'utilisateur de saisir un entier valide
        }
    }


    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public String askString() {
        return scanner.nextLine();
    }


    public void close() {
        scanner.close();
    }
}
