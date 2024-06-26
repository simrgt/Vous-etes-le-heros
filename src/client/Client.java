package client;

import javax.sound.sampled.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client class for the text-based adventure game.
 */
public class Client {
    /**
     * Code used to indicate the end of the game.
     */
    private static final String END_CODE = "#!#END#!#";
    /**
     * Flag used to indicate a sound.
     */
    private static final String SOUND_FLAG = "#$#SOUND#$#";
    /**
     * Constants used to clear the screen.
     */
    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    /**
     * Constants of sound file path.
     */
    private static final String SOUND_FILE_PATH = "assets/sound/";
    /**
     * Constants of music file path.
     */
    private static final String MUSIC_FILE_PATH = "assets/music/";
    /**
     * Constants used to escape characters.
     */
    private static final String ESCAPE = "#n#";
    /**
     * Constants used to replace tabs.
     */
    private static final String TAB = "#t#";
    /**
     * Constants used to replace life.
     */
    private static final String BALISE_LIFE = "#LIFE#";
    /**
     * Constants used to set the width of the text.
     */
    private static final int WIDTH = 100;

    /**
     * Clip used to play the background music.
     */
    private static Clip backgroundMusicClip;

    /**
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in);
             Socket s = new Socket(InetAddress.getByName("localhost"), 5056);
             DataInputStream dis = new DataInputStream(s.getInputStream());
             DataOutputStream dos = new DataOutputStream(s.getOutputStream())) {

            // Start background music
            startBackgroundMusic();

            while (true) {
                String received = dis.readUTF();
                handleReceivedMessage(received);

                String tosend = scn.nextLine();
                dos.writeUTF(tosend);

                if (tosend.equals("Exit")) {
                    closeConnection(s);
                    break;
                }
                System.out.println(CLEAR_SCREEN);
            }
        } catch (java.net.ConnectException e) {
            System.out.println("Vérifier la connexion au serveur");
        } catch (Exception e) {
            System.out.println("Connection interrompue");
        } finally {
            stopBackgroundMusic();
        }
    }

    /**
     * @param s the socket to close
     */
    private static void closeConnection(Socket s) {
        System.out.println("Closing this connection : " + s);
        try {
            s.close();
        } catch (Exception e) {
            System.out.println("Error while closing connection.");
        }
        System.out.println("Connection closed");
    }

    /**
     * @param received the message received
     */
    private static void playSound(String received) {
        String soundFileName = getSoundFileName(received);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(SOUND_FILE_PATH + soundFileName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error while playing sound.");
        }
    }

    /**
     * @param received the message received
     */
    private static void handleReceivedMessage(String received) {
        if (received.contains(SOUND_FLAG)) {
            playSound(received);
            received = received.replace(SOUND_FLAG + getSoundFileName(received) + SOUND_FLAG, "");
        }
        if (received.contains(BALISE_LIFE)) {
            String[] parts = received.split(BALISE_LIFE);
            for (String part : parts) {
                System.out.println(formatText(part));
            }
        } else if (received.contains(END_CODE)) {
            System.out.println(formatText(received.replace(END_CODE, "")));
            System.out.println("Game ended.");
        } else {
            System.out.println(formatText(received));
        }
    }

    /**
     * @param received the message received
     * @return the sound file name
     */
    private static String getSoundFileName(String received) {
        return received.substring(received.indexOf(SOUND_FLAG) + SOUND_FLAG.length(), received.lastIndexOf(SOUND_FLAG));
    }

    /**
     * @param text the text to format
     * @return the formatted text
     */
    private static String formatText(String text) {
        StringBuilder result = new StringBuilder();

        // Create the top border
        result.append("=".repeat(WIDTH)).append("\n");

        // Split the text into lines based on \n
        String[] lines = text.split(ESCAPE);
        for (String lineText : lines) {
            // Replace tabs with spaces (assuming 4 spaces per tab)
            lineText = lineText.replace(TAB, "    ");

            // Split the line into words
            String[] words = lineText.split(" ");
            StringBuilder line = new StringBuilder("| ");

            for (String word : words) {
                if (line.length() + word.length() + 1 > WIDTH - 2) {
                    // Fill the rest of the line with spaces
                    while (line.length() < WIDTH - 1) {
                        line.append(" ");
                    }
                    line.append("|\n");
                    result.append(line);
                    line = new StringBuilder("| " + word + " ");
                } else {
                    line.append(word).append(" ");
                }
            }

            // Append the last line for this segment
            while (line.length() < WIDTH - 1) {
                line.append(" ");
            }
            line.append("|\n");
            result.append(line);
        }

        // Create the bottom border
        result.append("=".repeat(WIDTH)).append("\n");

        return result.toString();
    }

    /**
     * Start the background music.
     */
    private static void startBackgroundMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(MUSIC_FILE_PATH + "background_music.wav").getAbsoluteFile());
            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(audioInputStream);
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error while playing background music.");
        }
    }

    /**
     * Stop the background music.
     */
    private static void stopBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
        }
    }
}
