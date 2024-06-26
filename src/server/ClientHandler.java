package server;

import game.Classic;
import game.Game;
import ui.Console;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * ClientHandler class
 */
public class ClientHandler extends Thread {
    /**
     * Input stream
     */
    final DataInputStream dis;
    /**
     * Output stream
     */
    final DataOutputStream dos;
    /**
     * Socket
     */
    final Socket s;
    /**
     * Game
     */
    Game game;


    /**
     * @param s socket
     * @param dis input stream
     * @param dos output stream
     */
    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.game = new Classic(new Console(
                () -> {
                    try {
                        return dis.readUTF();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                str -> {
                    try {
                        dos.writeUTF(str);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
        );
    }

    /**
     * Run method
     */
    @Override
    public void run() {
        try {
            game.startNewGame();
        } catch (IOException e) {
            Logger.getGlobal().severe(e.getMessage());
        } finally {
            try {
                // closing resources
                this.dis.close();
                this.dos.close();
            } catch(IOException e){
                Logger.getGlobal().severe(e.getMessage());
            }
        }
    }
}