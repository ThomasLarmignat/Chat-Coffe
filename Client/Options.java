
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Checkbox;
import javax.swing.*;
import java.util.*;

public class Options extends JFrame {
	private Interface inteface = null;
	private Client client = null;

	
	public Options(Interface inteface, Client client) {
		this.inteface= inteface;

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(150, 150, 343, 273);
		setTitle("Menu Option");
		setResizable(false);
		getContentPane().setLayout(null);


		//CheckBox ID
		Checkbox cBox = new Checkbox("Garder mes identifiants");
		cBox.setBounds(120, 30, 175, 23);
		getContentPane().add(cBox);
		if(client.getStateId()){
			cBox.setState(true);
		}else{
			cBox.setState(false);
		}


		//CheckBox resizable
		Checkbox checkBox = new Checkbox("Resizable");
		checkBox.setBounds(120, 60, 100, 23);
		getContentPane().add(checkBox);
		if(client.getStateResize()){
			checkBox.setState(true);
		}else{
			checkBox.setState(false);
		}

		JLabel lcolor = new JLabel(" Couleur des MPs :");
		lcolor.setBounds(120, 100, 200, 23);
		getContentPane().add(lcolor);

		
		// Combobox colors
		String[] colorStrings = { "Bleu", "Rouge", "Vert", "Rose", "Orange" };

		int ind = Arrays.asList(colorStrings).indexOf(client.getColor());
		String colorTmp = colorStrings[ind];
		int taille = colorStrings.length;
		colorStrings[ind] = colorStrings[taille-1];
		colorStrings[taille-1] = colorTmp;

		
		
		final JComboBox comboBox = new JComboBox(colorStrings);
		comboBox.setSelectedIndex(4);
		comboBox.setBounds(126, 120, 107, 20);
		getContentPane().add(comboBox);

			
		// Button
		JButton btn = new JButton("Appliquer");

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Changements appliqués");

				client.setColor(comboBox.getSelectedItem().toString());

				if(cBox.getState()){
					inteface.setActive(true);
					cBox.setState(true);
					client.setStateId(true);
				}else{
					inteface.setActive(false);
					cBox.setState(false);
					client.setStateId(false);
				}

				
				if(checkBox.getState()){//Resizable cassé sous MacOS
					inteface.setResizable(true);
					client.setStateResize(true);
					
				}else{
					inteface.setResizable(false);
					client.setStateResize(false);
				}
				
			}
		});
		btn.setBounds(120, 200, 120, 23);
		getContentPane().add(btn);
		setVisible(true);
	}
}