package mvc_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public abstract class GridView extends View implements ActionListener {
	private JLabel[][] usersNumbers; //
	private JLabel[][] answeredNumbers;//10
	private JButton[] buttons;
	protected ButtonListener[] listenToButtons;

	@Override
	public void fensterNeuZeichnen(ActionListener al) {
		//fenster erstellen
		
		JFrame fenster = new JFrame("Mastermind");
		fenster.setSize(500, 300);//
		//20
		JPanel eingabePanel = new JPanel();
		JPanel ausgabePanel = new JPanel();
		JPanel userPanel = new JPanel();
		
		
		//
		preparePanel(eingabePanel, usersNumbers); //30
		preparePanel(ausgabePanel, answeredNumbers);
		
		//30
		
		JPanel eingabe = new JPanel(); //ACHTUNG! eingabePanel und das JPanel eingabe nicht verwechseln!
		buttons = prepareButtons(eingabe);
		JButton go = new JButton("Go!");
		go.addActionListener(this); //
		JButton newGame = new JButton("Neues Spiel!"); //40
		
		//userPanel:
		userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
		userPanel.add(eingabe); //40
		userPanel.add(go);
		userPanel.add(newGame);
		
		eingabePanel.setBackground(Color.BLACK);
		ausgabePanel.setBackground(Color.GREEN); //Zum testen
		
		fenster.getContentPane().add(BorderLayout.WEST, eingabePanel);
		fenster.getContentPane().add(BorderLayout.EAST, ausgabePanel);				
		fenster.getContentPane().add(BorderLayout.SOUTH, userPanel);
		//50
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void preparePanel(JPanel panel, JLabel[][] jl) {
		int cols = model.getLösungsanzahl();
		int rows = model.getRundenanzahl();
		
		jl = new JLabel[rows][cols];
		panel.setLayout(new GridLayout(rows, cols, 5, 5)); //60
		
		for(int i = 0; i < cols; i++) {
			jl[i] = prepareRow(panel);
		}
	}

	private JLabel[] prepareRow(JPanel p) {
		JLabel[] jl = new JLabel[model.getLösungsanzahl()];
		
		for(int i = 0; i < model.getLösungsanzahl(); i++) { //70
			jl[i] = new JLabel("H");
			p.add(jl[i]);
		}
		return jl;
	}

	private JButton[] prepareButtons(JPanel p) {
		JButton[] jb = new JButton[model.getLösungsanzahl()];
		listenToButtons = new ButtonListener[model.getLösungsanzahl()];
		//80
		for(int i = 0; i < model.getLösungsanzahl(); i++) {
			jb[i] = new JButton();
			listenToButtons[i] = new ButtonListener(jb[i]);
			listenToButtons[i].setZustand(i);
			jb[i].addActionListener(listenToButtons[i]);
	
			buttonNeuZeichnen(jb[i], i);
			
			p.add(jb[i]);
		} //90
		
		return jb;
	}
	
	@Override
	public void emptyField() {
		//alle Zahlen/Bilder entfernen. abstract oder nicht?
	}

	@Override //100
	public void fehlermeldung() {
		//direkt den Fehler in einem etraFenster ausgeben. Für Fehlermeldung eine neue Klasse?
	}
	
	@Override
	public void fullfillField() {
		//Muss das hier abstract sein? Macht doppelten Code, deshalb lieber ne abstracte Methode, die mir Bild/
		//Zahl liefern
		
		for(int i = 0; i <= model.getAktuelleRunde(); i++) { //110
			for(int e = 0; e < model.getLösungsanzahl(); e++) {
				setInhalt(usersNumbers[i][e], model.codeAbrufen(false, i).getNummer(e));
			}
		}//110
	}

	@Override
	public void ende(boolean sieg) {
		//muss nicht abstract sein
		//wie wärs mit ner extra-Klasse dafür? Besser nicht, denn: wird ja nur zusätzlich angezeigt, in TextView
		//soll sich nichts verändern
	}

	@Override //120
	public void nächsteRundeBereit() {	/*die bleibt ja leer*/	}

	public void actionPerformed(ActionEvent arg0) {
		int[] it = getInput();
		Code c = new Code(model);
		
		for(int i = 0; i < model.getLösungsanzahl(); i++) {
			c.setNummer(i, it[i]);
		}
		
		fireEvent(c);
	}

	
	public abstract int[] getInput();
	
	public abstract void setInhalt(JLabel jl, int i);

	public abstract void buttonNeuZeichnen(JButton jb, int zustand);
	
	
	class ButtonListener implements ActionListener {
		int zustand = 0;
		JButton button;

		public ButtonListener(JButton jb) {
			button = jb;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			zustand++;
			
			if(zustand >= model.getZahlenbereich()) {
				zustand = 0;
			}
			buttonNeuZeichnen(button, zustand);
		}
		
		public int getZustand() {
			return zustand;
		}
		
		public void setZustand(int i) {
			zustand = i;
		}
		
	}
}
