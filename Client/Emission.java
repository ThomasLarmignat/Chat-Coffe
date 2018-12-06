import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;



public class Emission {

	private static PrintWriter out;
	private static String message = null;
	private Scanner sc = null;


	
	public Emission(PrintWriter out) {
		this.out = out;
	}

	public static void send(String message){
    	out.println(message);
    	out.flush();
	}
}
