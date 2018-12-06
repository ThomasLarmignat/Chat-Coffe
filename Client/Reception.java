import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Reception implements Runnable {
    private BufferedReader in;
    private PrintWriter out;
    private String message = "tmp";
	private boolean active = true;
	private Socket s = null;
	

    public Reception(BufferedReader in, Socket s,PrintWriter out){ 
        this.in = in;
        this.s = s;
        this.out = out;
    }

    public void run() {

    	try { 
			while(active){
           	 message = in.readLine();
           		System.err.println(message);
           	 	if(message== null){
           	 		active = false;

           	 		try{
    				in.close(); //close resources here!
    				out.close();
    				s.close();
    					} catch (IOException e) {
							System.err.println(" Serveur ne répond pas !");
					}     
           	 		break;
           	 	}else if(message.contains("{MP}")){
							message = "255000000" + message;
							MessagePrive mp  = new MessagePrive(message,Interface.getDiscussion());
	            			mp.setBox();
						
				}
				
				else if(message.contains("[List]")){
							MessageListe liste  = new MessageListe(message,Interface.getConnectes());
							liste.setBoxList();
						
				}
				
				else{
	           		System.out.println("Le serveur vous dit :" +message);
	           		System.err.println(message);
	            	Message diss  = new Message(message,Interface.getDiscussion());
	            	diss.setBox();
	           	}      
			}

			try{
    				in.close(); //close resources here!
    				out.close();
    				s.close();
    			} catch (IOException e) {
					System.err.println(" Serveur ne répond pas !");
			}            
            } catch (IOException e) {                
              System.err.println(" ne répond pas !");
              active = false ; 
            }
      }
   }