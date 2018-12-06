import java.io.*;
import java.net.*;
import java.util.Scanner;



public class Chat_ClientServeur implements Runnable {


    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc;
    private Thread t3, t4;
    private Emission e;

    

    public Chat_ClientServeur(Socket s){
        socket = s;
    }

    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sc = new Scanner(System.in);
         

            e = new Emission(out);
            Thread t3 = new Thread(new Reception(in,socket,out));
            t3.start();
           
        } catch (IOException e) {

           System.err.println(" ne répond pas !");

        }

    }

}
