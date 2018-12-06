import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.Color;

public class Serveur {
 public static ServerSocket ss = null;
 public static Thread t;
 private static Clients clients = new Clients();


	public static void main(String[] args) {
		try {
			ss = new ServerSocket(2009);
			System.out.println("Le serveur est à l'écoute du port "+ss.getLocalPort());
			
			t = new Thread(new Accepter_connexion(ss,clients));
			t.start();
			
		} catch (IOException e) {
			System.err.println("Le port "+ss.getLocalPort()+" est déjà utilisé !");
		}
	
	}

	
}
