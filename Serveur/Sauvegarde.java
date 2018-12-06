import java.io.*;
import java.net.*;
import java.util.*;


public class Sauvegarde {
	private Clients clients = null;

	public Sauvegarde(Clients clients){
		this.clients = clients;
	}


	public void sendSave(String login){
		PrintWriter out = clients.getCliOut(login);
		String str ="";	
		try{
			
			BufferedReader br = new BufferedReader(new FileReader("/info/etu/l3info/l3info012/Dépot _chat_LARMIGNAT/SaveChat"));
			String line;
			while ((line = br.readLine()) != null) {
   					out.println(line);
   					out.flush();
			}
			
			br.close();
			
			
		} catch (FileNotFoundException e) {
    			e.printStackTrace();
		} catch (IOException e) {
    			e.printStackTrace();
		}
	}


	public void setMessageSauvegarde(String message){
		try{
			PrintWriter out = new PrintWriter(new FileWriter("/info/etu/l3info/l3info012/Dépot _chat_LARMIGNAT/SaveChat", true));
			out.println(message);
			out.close();
			
		} catch (FileNotFoundException e) {
			 	e.printStackTrace();
		} catch (IOException e) {
    			e.printStackTrace();
		}		
	}

	
}