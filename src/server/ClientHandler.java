package server;

import game.Classic;
import game.Game;
import ui.Console;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    Game game;


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

    @Override
    public void run() {
        try {
            game.startNewGame();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // closing resources
            this.dis.close();
            this.dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}