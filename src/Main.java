import game.Classic;
import server.Server;
import ui.Console;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class
 */
public class Main {
    /**
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'server' to launch the server or 'local' to launch the clientMode to debug locally.");
        String input = scanner.nextLine();
        if (input.equals("server")) {
            try {
                System.out.println("Launching server...");
                Server.launch();
            } catch (IOException e) {
                System.out.println("Error while launching server.");
            }
        } else if (input.equals("local")) {
            try {
                new Classic(new Console(
                        () -> new Scanner(System.in).nextLine(),
                        System.out::println
                )).startNewGame();
            } catch (IOException e) {
                System.out.println("Error while launching game.");
            }
        } else {
            System.out.println("Invalid input.");
        }

    }
}
