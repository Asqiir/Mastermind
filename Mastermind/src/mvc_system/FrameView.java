package mvc_system;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FrameView extends TextView implements ActionListener {
	JTextField inText;
	JTextArea outText;//10
	JButton newGame;
	
	public FrameView() { }
	
	@Override
	public void fehlermeldung() {
		JFrame f = new JFrame("Fehlermeldung");
		f.setSize(200, 200);
		JLabel l = new JLabel(); //20
		f.add(l);
		
		l.setText(fehlerText);
	}

	@Override
	public void fullfillField() {
		outText.setText(textErzeugen());
	}
	
	@Override //40
	public void nächsteRundeBereit() {	}

	public void actionPerformed(ActionEvent arg0) {
		String s = inText.getText();
		inText.setText("");
		inText.requestFocus();
		
		try {
			fireEvent(textToCode(s));		
		} catch(Exception ex) {
			
		}
	}
	//50
	String fehlerText = "Ein Fehler ist aufgetreten. Dies kann an ihrer Eingabe liegen. \n Bitte geben sie" +
			"immer 5 Zahlen hintereinander ein, von jeweils einem Leerzeichen getrennt."
			+ " Wenn sie wissen wollen, worum es in diesem Spiel geht, klicken sie auf den Button unten. Hier die richtige" +
			"Fehlermeldung: ";
	String anleitung = " Sie spielen Mastermind.\n Sie gewinnen, wenn\n sie den 5stelligen Code\n erraten." +
			" Es sind alle\n Zahlen zwischen 0 und 7 \n möglich. Wenn sie einen\n Treffer 'X' erzielen" +
					", ist eine\n der Zahlen an der richtigen\n Stelle. Erzielen sie einen\n Streifschuss 'O' ist eine\n der Zahlen im Code enthalten.\n" +
					" Sie haben 20 Versuche Zeit.";

	@Override
	public void zeigeAn(String s) {
		outText.append(s);
	}

	@Override
	public void fensterNeuZeichnen(ActionListener al) {
		
		JFrame fenster = new JFrame("Mastermind");
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		outText = new JTextArea();
		inText = new JTextField(20);
		JPanel bottomPanel = new JPanel();
		newGame = new JButton("Neues Spiel");
		newGame.addActionListener(al);
		
		fenster.setSize(200, 500);
		outText.setSize(180, 400);
		outText.setEditable(false);
		inText.addActionListener(this);
		outText.setText(anleitung);
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.add(inText);
		bottomPanel.add(newGame);
		
		fenster.getContentPane().add(outText);
		fenster.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
		fenster.setVisible(true);
		inText.requestFocus();
	}

	@Override
	public void emptyField() {
		outText.setText("");
		inText.requestFocus();
	}
}
