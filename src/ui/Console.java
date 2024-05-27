package ui;

import java.util.Scanner;

/**
 * Console UI implementation
 */
public class Console implements Ui {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int WIDTH = 80;

    /**
     * @param text text to show in the console
     */
    @Override
    public void show(String text) {
        String border = "=".repeat(WIDTH);
        System.out.println(border);
        System.out.println(formatText(text).replace("\\n", System.lineSeparator()));
        System.out.println(border);
    }

    /**
     * @param text text to format
     * @return formatted text
     */
    private static String formatText(String text) {
        String[] lines = text.split("\\\\n");
        StringBuilder formattedText = new StringBuilder();
        for (String line : lines) {
            while (line.length() > WIDTH - 4) {
                int spaceIndex = line.lastIndexOf(' ', WIDTH - 4);
                if (spaceIndex == -1) spaceIndex = WIDTH - 4;
                formattedText.append("| ").append(line, 0, spaceIndex);
                appendSpaces(formattedText, WIDTH - 4 - spaceIndex);
                formattedText.append(" |\n");
                line = line.substring(spaceIndex).trim();
            }
            formattedText.append("| ").append(line);
            appendSpaces(formattedText, WIDTH - 4 - line.length());
            formattedText.append(" |\n");
        }
        return formattedText.deleteCharAt(formattedText.length() - 1).toString();
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
    @Override
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

    @Override
    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
