import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;

public class MessagePrive extends Message {
	private Color color_default;
	private String LaCouleur;
	
	public MessagePrive(String leMessage,JColorTextPane zoneDiscussion){
		super(leMessage,zoneDiscussion);
	}
	
	@Override
	public String traitementMessage(){
		return(message.substring(14));
	}

	@Override
	public Color getColor(String base){
		LaCouleur = Client.getColor();
		if(LaCouleur.equals("Orange")){
			color_default = Color.ORANGE;
		}else if(LaCouleur.equals("Bleu")){
			color_default = Color.BLUE;
		}else if(LaCouleur.equals("Vert")){
			color_default = Color.GREEN;
		}else if(LaCouleur.equals("Rouge")){
			color_default = Color.RED;
		}else{
			color_default = Color.PINK;
		}
		
		return(color_default);
	}

}