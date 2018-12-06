import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Connexion implements Runnable {

	private Socket socket = null;
	public static Thread t2;
	public static String login;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Scanner sc = null;
	private boolean connect = false;
	private Client client;

	
	public Connexion(Socket s,String pseudo,Client client){
		this.client = client;
		socket = s;
		this.login = pseudo;
	}

	
	public void run() {
		try {
		out = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
		sc = new Scanner(System.in);
		while(!connect){
		out.println(login);
		out.flush();

		String test = in.readLine();
		System.out.println(test);	

		if(test.equals("connecte")){
			System.out.println("Je suis connecté "); 
			connect = true;
		}
	}
			t2 = new Thread(new Chat_ClientServeur(socket));
			t2.start();
		
		} catch (IOException e) {
			System.err.println("Le serveur ne répond plus ");
		}
	}

}
