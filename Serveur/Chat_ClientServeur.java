import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Chat_ClientServeur implements Runnable {
    
	private Socket socket = null;
	private BufferedReader in = null;
	private static PrintWriter out = null;
	private String login = null; ;
	private Thread tReception;
	private Clients clients;
	
	public Chat_ClientServeur(Socket s, String log, Clients clients){
		this.clients = clients;
		socket = s;
		login = log;
	}

	public  static PrintWriter getOut(){
		return(out);
	}
	public void run() {
		
		try {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());

		
		clients.addlistLoginColor(login);
		clients.addClient(out);
		clients.addCliOut(out,login);
		System.out.println(clients.getVector());
		Sauvegarde sauvegarde = new Sauvegarde(clients);
		sauvegarde.sendSave(login);
		
		Thread tReception = new Thread(new Reception(in,login,socket,out,clients,sauvegarde));
		tReception.start();
		} catch (IOException e) {
			System.err.println(login +"s'est déconnecté ");
		}
	}
}
