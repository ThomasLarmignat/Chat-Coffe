import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.Color; 


public class MessagePrive {
	private String loginEm;
	private Clients clients;
	private String message;

	public MessagePrive(String message,Clients clients,String loginEm){
		this.message = message;
		this.clients = clients;
		this.loginEm=loginEm;
	}

	public String getLoginMp(){
		int ind1 = message.indexOf("<");
		int ind2 = message.indexOf(">");
		//Traitement verification present ou non dns la conv
		return(message.substring(ind1+1,ind2));
	}


	public String getRealMp(){
		int ind = message.indexOf(">");
		return(message.substring(ind+2));
	}


	
	public void send(){
		String login = getLoginMp();

		PrintWriter out = clients.getCliOut(loginEm);
		PrintWriter outMp = clients.getCliOut(login);
		if(outMp == null || out == null){

			String messageNew = "255000000Utilisateur non valide";
			out.println(messageNew);
	    	out.flush();
		}else{
			String messageNew = "{MP} "+"Message envoyé à " + login +":"+ getRealMp();
			String messageNew2 = "{MP} "+"Message Privé de " + loginEm +":"+ getRealMp();

			out.println(messageNew);
	    	out.flush();
		
			outMp.println(messageNew2);
	    	outMp.flush();
		}	    
	} 
}