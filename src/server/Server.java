package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Server class
 */
public class Server {
    /**
     * @throws IOException if an I/O error occurs when creating the socket
     */
    public static void launch() throws IOException {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);
        System.out.println("Server is running on port 5056");

        // running infinite loop for getting
        // client request
        while (true) {
            Socket s = null;
            try {
                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method
                t.start();
            } catch (Exception e) {
                Logger.getGlobal().severe(e.getMessage());
                Objects.requireNonNull(s).close();
            }
        }
    }
}
