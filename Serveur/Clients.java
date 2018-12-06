import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.Color;


public class Clients {
	private Vector tabClients; 
 	private HashMap listCliOut;
 	private HashMap listLoginColor;

	public Clients(){
		tabClients = new Vector(); 
		listCliOut = new HashMap();
		listLoginColor = new HashMap();
	}

	public String getColorRnd(){
			Random randomGenerator = new Random();  // Couleur choisie entre 100 et 200 pour pas que ca pique les yeux
			int red = randomGenerator.nextInt(200-100) + 100;
			int green = randomGenerator.nextInt(200-100) + 100;;
			int blue = randomGenerator.nextInt(200-100) + 100;
			return(""+red+green+blue);	// retourne le rgb sous forme de chaine de caracteres
	}

	
	public void sendAll(String message){
	    PrintWriter out; 
	    for (int i = 0; i < tabClients.size(); i++) {
	      out = (PrintWriter) tabClients.elementAt(i); 
	      if (out != null) {
	        out.println(message);
	        out.flush(); 
	      }
	    }
	}

	public String affichelist(){ //Return juste un la liste sous une chaine de caracteres
		String test = listLoginColor.toString();
		return(test);
	}

	
	public PrintWriter getCliOut(String login){
		return (PrintWriter)listCliOut.get(login);
	}

	public void addCliOut(PrintWriter out, String login){
		listCliOut.put(login,out);
	}
	
	public void addClient(PrintWriter out){
	    tabClients.addElement(out); // on ajoute le nouveau flux de sortie au tableau 
	}
	
	public void supprimeClient(PrintWriter out){
		tabClients.removeElement(out);
	}
	
	public Vector getVector(){
		return(tabClients);
	}

	public void sendAllList(){ // Send de la liste des clients
		System.out.println("Envois de la liste des clients ...");
		sendAll("[List]" + affichelist());
	}

	public void addlistLoginColor(String login){
		listLoginColor.put(login, getColorRnd());
	}

	public void removelistLoginColor(String login){
		listLoginColor.remove(login);
	}

	public String getLogColor(String login){
		return(listLoginColor.get(login) + "");
	}

}