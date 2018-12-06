import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Authentification implements Runnable {
	private Socket socket;
	private static PrintWriter out = null;
	private BufferedReader in = null;
	private String login = null;;
	public boolean authentifier = false;
	public Thread tChat;
	private Clients clients;
	
	public Authentification(Socket s, Clients clients){
		 socket = s;
		 this.clients = clients;
		}
	
	public void run() {
	
		try {
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			
		while(!authentifier){
				out.println("Entrez votre login :");
            	out.flush();
				login = in.readLine();	
				out.println("connecte");
				
				out.flush();
				authentifier = true;
				
		 }
			tChat = new Thread(new Chat_ClientServeur(socket,login,clients));
			tChat.start();
			
		} catch (IOException e) {
			
			System.err.println(login+" ne répond pas !");
		}
	}
}
