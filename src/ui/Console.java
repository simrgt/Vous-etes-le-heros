package ui;

import java.util.Scanner;

public class Console implements Ui {
    private static final Scanner scanner = new Scanner(System.in);
    @Override
    public void show(String text) {
        System.out.println(text.replace("\\n", System.lineSeparator()));
    }

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
}
