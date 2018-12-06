import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.*;
import java.awt.Color;


public class MessageListe {
	private String message;
	private JColorTextPane zoneConnectes;
	
	public MessageListe(String leMessage,JColorTextPane zoneConnectes){
		this.message = leMessage;
		this.zoneConnectes = zoneConnectes;
	}


	private Color getColorList(int base){
		int blue = base%1000;
		System.out.println(blue);
		int tmp = base/1000;
		int green = tmp % 1000;
		System.out.println(green);
		int red = tmp/1000;
		System.out.println(red);
		Color color = new Color(red,green,blue);
		return color;
	}




	public void setBoxList(){
		Map<String, Integer> myMap = new LinkedHashMap<String, Integer>();
		String s = message;
		s=s.replace("[List]","");
		s=s.replace("{","");
		s=s.replace("}","");
		String[] pairs = s.split(",");
		for (int i=0;i<pairs.length;i++) {
    		String pair = pairs[i];
    		String[] keyValue = pair.split("=");
    		myMap.put(keyValue[0], Integer.valueOf(keyValue[1]));
		}
		String clef = null;
		Integer valeur = null;
		Iterator i = myMap.keySet().iterator();
 		zoneConnectes.setText("");
		while (i.hasNext()){
		    clef = (String)i.next();
		    valeur = (Integer)myMap.get(clef);
		    zoneConnectes.setEditable(true);
			zoneConnectes.append(clef + "\n", this.getColorList(valeur));
			zoneConnectes.setEditable(false);
		}
	}
}