import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

	public static Socket socket = null;
	public static Thread t1;
	private  String ip = null;
	private int port;
	private String pseudo; 
	private static String LaCouleur = "Rouge"; 
	private static boolean resize;
	private static boolean id;
	private boolean etat = true;


	public boolean getEtat(){
		return(etat);
	}


	
	public void setColor(String LaCouleur){
		this.LaCouleur = LaCouleur;
	}


	public static String getColor(){
		return(LaCouleur);
	}


	public static boolean getStateResize(){
		return(resize);
	}


	public static boolean getStateId(){
		return(id);
	}


	public void setStateResize(boolean b){
		this.resize= b;
	}


	public void setStateId(boolean b){
		this.id =b;
	}


	public void closeSoc(){
		try{
			socket.close();
		 } catch (IOException e) {
				
				System.err.println(" ne répond pas !");
		}
	}
	
	public Client(String ip, String port, String pseudo){
	try {
		
		try{
			System.out.println("Demande de connexion");
			socket = new Socket(ip,Integer.parseInt(port));
		}catch(NumberFormatException e){
			System.err.println("Donnez des informations valides");
			etat = false;
		}
		

		if(socket == null){
			etat = false;
		}
		else{
				System.out.println("Connexion établie avec le serveur, authentification :"); 
				t1 = new Thread(new Connexion(socket,pseudo,this));
				t1.start();
		}

	} catch (UnknownHostException e) {
	  System.err.println("Impossible de se connecter à l'adresse ");
	  etat = false;
	} catch (IOException e) {
	  System.err.println("Aucun serveur à l'écoute du port ");
	  etat = false;
	}
	}
}
