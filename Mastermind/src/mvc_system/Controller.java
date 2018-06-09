package mvc_system;

import java.awt.event.*;

public class Controller implements CodeListener {
	private Model model;
	private View view;
	
	public static void main(String[] args) {
		new Controller(new Model(), new ConsoleView());
	}
	
	public Controller(Model m, View v) {
		neuesSpiel(m, v);
	}
	
	private void neuesSpiel(Model m, View v) {
		model = m;
		view = v;
		
		view.setModel(model);
		view.fensterNeuZeichnen(new NewGameListener());
		view.addActionListener(this);
		
		Code randomCode = new Code(model);
		randomCode.initWithRandomValues();
		model.setCode(randomCode);
		
		view.nächsteRundeBereit();
	}
// bereitet ein neues Spiel vor
	
	private Code eingabeAuswerten(Code in) {
		Code out = new Code(model);
		
		int schwarzeAnzahl = 0;
		int weißeAnzahl = 0;
		
		for(int i = 0; i < 5; i++) {
			if(in.getNummer(i) == model.getCode().getNummer(i)) {
				schwarzeAnzahl++;
			}
		}
			
		for(int i = 0; i < 5; i++) {
			for(int d = 0; d < 5; d++) {
				if(in.getNummer(i) == model.getCode().getNummer(d)) {
					weißeAnzahl++;
					break;
				}
			}
		}
			
		for(int i = 0; i < weißeAnzahl; i++) {
			if(i < schwarzeAnzahl) {
				out.setNummer(i, 1);
			} else {
				out.setNummer(i, 2);
			}
		}
		
		return out;
	}
//Eingaben zu Ausgaben umrechnen

	private void verwerter() {
		if(codeVergleich(model.codeAbrufen(false, model.getAktuelleRunde()), model.getCode()) == true || model.codeAbrufen(false, 0) == null) {
		//true, wenn der aktuelle Code der Lösung entspricht
			view.ende(true);
			model.setSpielVorbei(true);
		} else {
			if(model.getAktuelleRunde() >= model.getRundenanzahl() - 1){
				view.ende(false);
				model.setSpielVorbei(true);
			} else {
				model.rundePP(); //PP für ++
				view.nächsteRundeBereit();
			}
		}
	}
	
	private boolean codeVergleich(Code code1, Code code2) {
		boolean b = true;
		
		for(int i = 0; i < model.getLösungsanzahl(); i++) {
			if(code1.getNummer(i) != code2.getNummer(i)) {
				b = false;
				break;
			}
		}
		
		return b;
	}
//liefert true zurück, wenn die Nummern gleich sind

	public void actionPerformed(CodeEvent ce) {	
		if(model.getSpielVorbei() == false) {
			model.codeSpeichern(false, model.getAktuelleRunde(), ce.getCode());
			model.codeSpeichern(true, model.getAktuelleRunde(), eingabeAuswerten(ce.getCode()));
			view.fullfillField();
		
			verwerter();
		}
	}
//reagiert auf das von der View gefeuerte ActionEvent
	
	
	class NewGameListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			model = new Model();
			
			view.setModel(model);
			view.emptyField();
			
			Code randomCode = new Code(model);
			randomCode.initWithRandomValues();
			model.setCode(randomCode);
			
			view.nächsteRundeBereit();
		}
	}
}