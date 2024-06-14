import java.io.*;
import java.net.*;
import java.util.Scanner;

// Client class
public class Client {
    private static final String END_CODE = "#!#END#!#";
    public static void main(String[] args) {
        try {
            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true) {
                String received = dis.readUTF();
                System.out.println(received.replace(END_CODE, "\n"));
                String tosend = scn.nextLine();

                if (received.contains(END_CODE)) {
                    System.out.println("Game ended. Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                System.out.println("Sending: " + tosend);
                dos.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop
                if(tosend.equals("Exit")) {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }
            }

            // closing resources
            scn.close();
            dis.close();
            dos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
