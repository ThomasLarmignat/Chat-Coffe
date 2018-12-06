import java.io.BufferedReader;
import java.io.IOException;
import java.lang.NullPointerException;
import java.io.*;
import java.net.*;


public class Reception implements Runnable {
	private Socket socket = null;
	private BufferedReader in;
	private String message = "tmp", login = null;
	PrintWriter out;
	private boolean active = true;
	private Clients clients;
	private Sauvegarde sauvegarde;


	public static boolean estmp(String message){
		return(message.contains("/mp<"));
	}
	
	
	public Reception(BufferedReader in, String login, Socket socket, PrintWriter out,Clients clients,Sauvegarde sauvegarde){
		this.socket = socket;
		this.in = in;
		this.login = login;
		this.out = out;
		this.clients = clients;
		this.sauvegarde = sauvegarde;
	}
	
	public void run() {

		String indentifeur = clients.getLogColor(login) + login;
		 try {
			while(active){
			message = in.readLine();
			
	        if(message== null){
           	 		active = false;

           	 		try{
    				in.close(); //close resources here!
    				out.close();
    				socket.close();
    					} catch (IOException e) {
							System.err.println(" Serveur ne répond pas !");
					}     
           	 		break;
           	 	}else if(message.equals(login)){
	  			clients.sendAll("[List] " + clients.affichelist());
	        	clients.sendAll(indentifeur+ " viens de se connecter");
	        	sauvegarde.setMessageSauvegarde(indentifeur+ " viens de se connecter");
				
	        }else{	        
				if(message.equals("Deconnexion")){
					clients.removelistLoginColor(login);
					clients.supprimeClient(out);
					
					clients.sendAllList();
					clients.sendAll(indentifeur+ " viens de se déconnecter");
					sauvegarde.setMessageSauvegarde(indentifeur+ " viens de se déconnecter");
					break;
				}
				
				else if(estmp(message)){
					MessagePrive mp  = new MessagePrive(message,clients,login);
					mp.send();
					
				}
				else{
					String newMessage = indentifeur+" : "+message  + "" ;
					clients.sendAll(newMessage);
					sauvegarde.setMessageSauvegarde(newMessage);
					System.out.println(newMessage);
					
				}
	        }
		   	}


			try{
    			clients.supprimeClient(out);
    			out.close();
    			in.close(); //close resources here!
    			socket.close();
    		} catch (IOException e) {
				System.err.println(" Serveur ne répond pas !");
			}
		}catch (IOException e) {
				active = false;
				System.err.println(login + " ne répond pas !");
				
		}
				
		}
	}