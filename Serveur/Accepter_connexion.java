import java.io.*;
import java.net.*;


public class Accepter_connexion implements Runnable{

	private ServerSocket socketserver = null;
	private Socket socket = null; 
	private Clients clients = null;
	public Thread t1;

	
	public Accepter_connexion(ServerSocket ss, Clients clients){
	 socketserver = ss;
	 this.clients = clients;
	}
	
	public void run() {
		
		try {
			while(true){
				
			socket = socketserver.accept();
			System.out.println("Un client veut se connecter  ");
			
			t1 = new Thread(new Authentification(socket,clients));
			t1.start();
			
			}
		} catch (IOException e) {
			
			System.err.println("Erreur serveur");
		}
		
	}
}
