import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;
import javax.imageio.ImageIO;


public class Interface extends JFrame implements ActionListener {
	private Client client;
	private boolean active = false;
	
/*Declaration des boutons*/
	private JButton boutonConnection;
	private JButton boutonEnvoyer;
	private JButton bouton1;
	private JButton bouton2;
	private JButton bouton3;
	private JButton boutonMP;
	private JButton boutonMenu;
	

	
/*Declaration des zones de saisies*/
	private JTextField saisieNom;
	private JTextField saisieip;
	private String iP = null;
	
	private JTextField saisiePort;
	private JTextField saisieMessage;
	private String messageAEnvoyer;


/*Declaration des zones de textes*/
	private static JColorTextPane zoneConnectes;
	private static JColorTextPane zoneDiscussion;
	
	
	public Interface( ){
		setSize(700,600);
		setLocationRelativeTo(null);
		setResizable(false);


		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
    	@Override
		public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			if(client !=null && client.getEtat()){
				Emission.send("Deconnexion");
			}
			
				System.exit(0);

    	}
		});

		
		BorderLayout bord = new BorderLayout();
		Container cont = getContentPane();
		bord.setVgap(20);
		bord.setHgap(20);
		
		cont.setLayout(bord);
		cont.add(pannelConnection(), BorderLayout.NORTH);
		cont.add(pannelConnect(), BorderLayout.WEST);
		cont.add(pannelDiscution(),BorderLayout.CENTER);
		setVisible(true);
	}
	

	
	private JPanel pannelConnection(){
		GridLayout grid = new GridLayout(2,4);
		JPanel boxConnection = new JPanel(grid);
		
		grid.setVgap(10);


		/*Creation des label*/
;
		JLabel labelNom = new JLabel("                            Nom");
		JLabel labelIp = new JLabel("                              IP");
		JLabel labelPort = new JLabel("                            Port");
		JLabel labelVide = new JLabel("");



		/*affection des objets Jbutton et JtextField*/
		this.saisieNom = new JTextField();
		this.saisieip = new JTextField();
		this.saisiePort = new JTextField();
		this.boutonConnection = new JButton("Connection");
		boutonConnection.addActionListener(this);
		boutonConnection.setEnabled(false);
		
		ActivableButton ab = new ActivableButton(boutonConnection, new JTextField[] {saisiePort,saisieNom,saisieip });
		

		/*Ajout au Jpanel*/
		boxConnection.add(labelNom);
		boxConnection.add(saisieNom);
		boxConnection.add(labelVide);
		boxConnection.add(boutonConnection);
		boxConnection.add(labelIp);
		boxConnection.add(saisieip);
		boxConnection.add(labelPort);
		boxConnection.add(saisiePort);
		return boxConnection;
	}


	private JPanel pannelConnect(){
		JPanel box = new JPanel(new BorderLayout());
		
		JLabel labelConnectes = new JLabel("Connectés");
		labelConnectes.setHorizontalAlignment(0);//Pour mettre le label au milieu de
		
		this.zoneConnectes = new JColorTextPane();//on creer un nouvel objet JTextArea
		//on ajoute des colonnes pour rendre la zone plus grande
		zoneConnectes.setEditable(false);//on rend la zone de text non etable

		this.bouton1 = new JButton();
		this.bouton2 = new JButton();
		this.bouton3 = new JButton();
		this.boutonMP = new JButton("   MP   ");
		this.boutonMenu = new JButton("Menu");
		Box panneauBouton = Box.createHorizontalBox();

		bouton1.setEnabled(false); 
		bouton2.setEnabled(false); 
		bouton3.setEnabled(false);
		boutonMP.setEnabled(false); 
		boutonMenu.setEnabled(false);

		
		bouton1.addActionListener(this);
		bouton2.addActionListener(this);
		bouton3.addActionListener(this);
		boutonMP.addActionListener(this);
		boutonMenu.addActionListener(this);

		
		bouton1.setPreferredSize(new Dimension(50, 68));
		bouton2.setPreferredSize(new Dimension(50, 68));
		bouton3.setPreferredSize(new Dimension(50, 68));
		try {
				Image img1 = ImageIO.read(getClass().getResource("Images/Smiley1.jpg"));
    			bouton1.setIcon(new ImageIcon(img1));
				Image img2 = ImageIO.read(getClass().getResource("Images/Smiley2.jpg"));
    			bouton2.setIcon(new ImageIcon(img2));
    			Image img3 = ImageIO.read(getClass().getResource("Images/Smiley3.jpg"));
    			bouton3.setIcon(new ImageIcon(img3));
  		} catch (Exception ex) {
    			System.out.println(ex);
  		}



		BorderLayout otherLayout = new BorderLayout();
		JPanel box2 = new JPanel(otherLayout);

		
		/*Ajout au Jpanel*/
		box.add(labelConnectes, BorderLayout.NORTH);
		box.add(zoneConnectes, BorderLayout.CENTER);
		
		
		box2.add(bouton1,otherLayout.WEST);
		box2.add(bouton2,otherLayout.CENTER);
		box2.add(bouton3,otherLayout.EAST);
		panneauBouton.add(boutonMenu);
		panneauBouton.add(boutonMP);
		
		box2.add(panneauBouton,otherLayout.SOUTH);

		box.add(box2,BorderLayout.SOUTH);
		return box;
	}


	private JPanel pannelDiscution(){
		BorderLayout inB = new BorderLayout();
		JPanel box = new JPanel(inB);
		inB.setVgap(10);
		
		
		JLabel labelDiscussion = new JLabel("Discussion");
		labelDiscussion.setHorizontalAlignment(0);
		JLabel labelMessage = new JLabel("Message");

		
		this.zoneDiscussion = new JColorTextPane();
		zoneDiscussion.setText("Vous êtes hors-lignes\n");
		this.saisieMessage = new JTextField();
		this.boutonEnvoyer = new JButton("Envoyer");
		zoneDiscussion.setEditable(false);
		saisieMessage.setEditable(false);
		boutonEnvoyer.setEnabled(false); 
		JScrollPane scroll = new JScrollPane (zoneDiscussion, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel haut = new JPanel(new BorderLayout());
		haut.add(labelDiscussion, BorderLayout.NORTH);
		haut.add(scroll, BorderLayout.CENTER);
		
		JPanel milieu = new JPanel(new BorderLayout());
		milieu.add(labelMessage, BorderLayout.NORTH);
		milieu.add(saisieMessage, BorderLayout.CENTER);
		
		JPanel milieu2 = new JPanel(new BorderLayout());
		milieu2.add(haut, BorderLayout.CENTER);
		milieu2.add(milieu, BorderLayout.SOUTH);
		
		JPanel bas = new JPanel(new BorderLayout());
		bas.add(boutonEnvoyer);
		boutonEnvoyer.addActionListener(this);
		
		box.add(milieu2, BorderLayout.CENTER);
		box.add(bas, BorderLayout.SOUTH);
		return(box);
	}

	public static JColorTextPane getConnectes(){
		return(zoneConnectes);
	}

	public static JColorTextPane getDiscussion(){
		return(zoneDiscussion);
	}

	private void setPannelButtons(boolean b){
			bouton1.setEnabled(b); 
			bouton2.setEnabled(b); 
			bouton3.setEnabled(b);
			boutonMP.setEnabled(b);
			boutonMenu.setEnabled(b);
			boutonEnvoyer.setEnabled(b);
			saisieMessage.setEditable(b);

			saisieNom.setEditable(!b);
			saisiePort.setEditable(!b);
			saisieip.setEditable(!b);
	}



	public void setActive(boolean b){
		active = b;
	}

 	public void actionPerformed(ActionEvent e){
        //quand on a cliqué sur le bouton ici
		Object  source=e.getSource();
		if(source == boutonConnection){

			if(boutonConnection.getLabel().equals("Connection")){
				boutonConnection.setLabel("Déconnection");
				zoneDiscussion.setText("");

				client = new Client(saisieip.getText(),saisiePort.getText(),saisieNom.getText());

				if(!client.getEtat()){
					saisieNom.setText("");
					saisiePort.setText("");
					saisieip.setText("");
					setPannelButtons(false);
					boutonConnection.setLabel("Connection");
					
				}else{
					setPannelButtons(true);
				}
				


			}else if(boutonConnection.getLabel().equals("Déconnection")){
					if(!active){
						saisieNom.setText("");
						saisiePort.setText("");
						saisieip.setText("");
					}
					
					zoneDiscussion.setText("Vous êtes hors-lignes\n");
					zoneConnectes.setText("");
					Emission.send("Deconnexion");
					boutonConnection.setLabel("Connection");
					setPannelButtons(false);
					client.closeSoc();
			}
		}
		if(source == boutonEnvoyer){
			messageAEnvoyer = saisieMessage.getText();
			
			Emission.send(messageAEnvoyer);
			saisieMessage.setText("");
		}

		if(source == bouton1){
			String tmp = saisieMessage.getText();
			saisieMessage.setText(tmp + ":-)");
		}
		if(source == bouton2){
			String tmp = saisieMessage.getText();
			saisieMessage.setText(tmp + ":-|");
		}
		if(source == bouton3){
			String tmp = saisieMessage.getText();
			saisieMessage.setText(tmp + ":-(");
		}

		if(source == boutonMP){
			String tmp = saisieMessage.getText();
			saisieMessage.setText("/mp<login>" + tmp);
		}
		if(source == boutonMenu){
			Options frame = new Options(this,client);
			frame.setLocationRelativeTo(null);
		}
    }

    

    
	public static void main(String[] args){
		Interface inter = new Interface();
	}
}

