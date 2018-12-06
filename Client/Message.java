import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;


public class Message {
	public String message;
	private JColorTextPane zoneDiscussion;
	
	public Message(String leMessage,JColorTextPane zoneDiscussion){
		this.message = leMessage;
		this.zoneDiscussion = zoneDiscussion;
	}

	
	public Color getColor(String base){
		System.out.println(base.substring(0,9));
		int red = Integer.parseInt(base.substring(0,3));
		int green = Integer.parseInt(base.substring(3,6));
		int blue = Integer.parseInt(base.substring(6,9));
		System.out.println(red);
		Color color = new Color(red,green,blue);
		return color;
	}

	public String traitementMessage(){
		return(message.substring(9));
	}

	private void detect(){
			 String base = message;
			 String haystack = this.traitementMessage();
             String needle = ":-)";
             Icon smiley1 = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Images/Smiley11.jpg"));
             String needle1 = ":-|";
             Icon smiley2 = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Images/Smiley31.jpg"));
             String needle2 = ":-(";
             Icon smiley3 = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Images/Smiley21.jpg"));
             String avant;
             String apres = haystack;
             
          while(haystack.indexOf(needle1) != -1 || haystack.indexOf(needle) != -1 || haystack.indexOf(needle2) != -1){
				int ind1 = haystack.indexOf(needle);
          		int ind2 = haystack.indexOf(needle1);
				int ind3 = haystack.indexOf(needle2);


				if(ind1 == -1){
					ind1 = 10000;
				}

				if(ind2 == -1){
					ind2 = 10000;
				}

				if(ind3 == -1){
					ind3 = 10000;
				}

      		if (haystack.indexOf(needle1) != 10000 &&  ind2<ind1 &&  ind2<ind3) {
					avant = haystack.substring(0,haystack.indexOf(needle1));
					zoneDiscussion.setEditable(true);
					zoneDiscussion.append(avant , getColor(base));
					zoneDiscussion.append("");
					zoneDiscussion.insertIcon(smiley2);
					apres = haystack.substring((haystack.indexOf(needle1))+3);
					haystack = apres;
					
        	}
        	
			if (haystack.indexOf(needle2) != 10000 &&  ind3<ind1 &&  ind3<ind2 ) {
					avant = haystack.substring(0,haystack.indexOf(needle2));
					zoneDiscussion.setEditable(true);
					zoneDiscussion.append(avant , getColor(base));
					zoneDiscussion.append("");
					zoneDiscussion.insertIcon(smiley3);
					
					apres = haystack.substring((haystack.indexOf(needle2))+3);
					haystack = apres;
        	}
        	
        	
        	 if (haystack.indexOf(needle) != 10000  &&  ind1<ind2 &&  ind1<ind3)  {
				    avant = haystack.substring(0,haystack.indexOf(needle));
					zoneDiscussion.setEditable(true);
					zoneDiscussion.append(avant , getColor(base));
					zoneDiscussion.append("");
					zoneDiscussion.insertIcon(smiley1);
					
					apres = haystack.substring((haystack.indexOf(needle))+3);
					haystack = apres;
					
        	}
        }
        zoneDiscussion.setEditable(true);
		zoneDiscussion.append(apres , getColor(base));
		zoneDiscussion.append("");			
	}

	public void setmessage(String message){
		this.message = message;
	}

	
	public void setBox(){
		zoneDiscussion.setEditable(true);
		this.detect();
		zoneDiscussion.append("\n");
		zoneDiscussion.setEditable(false);
	}

	
}