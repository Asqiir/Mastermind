package mvc_system;

import java.io.BufferedReader;

public abstract class TextView extends View {
	protected BufferedReader reader;

	@Override
	public abstract void fullfillField();
	//10
	@Override
	public void ende(boolean sieg) {
		zeigeAn(endTexterzeuger(sieg));
	}
	
	@Override
	public void nächsteRundeBereit() { }
	
	public Code textToCode(String s) {
		Code c = new Code(model); //20
		
		try {
			String[] eingaben = s.split(" ");
			
			for(int i = 0; i < model.getLösungsanzahl(); i++) {
						int e = Integer.parseInt(eingaben[i]);
						c.setNummer(i,e);
			}
		}
/*!!!*/	catch(Exception e) { // Ich muss rauskriegen, welche exceptions geworfen werden! //30
			fehlermeldung(); //kann die bitte was konkretes anzeigen?
			nächsteRundeBereit();
		}
		return c;
	}

	public String textErzeugen() {
		String s = "";
		
		for(int i = 0; i <= model.getAktuelleRunde(); i++) { //40
			s = s + '\n' + ' ';
			for(int e = 0; e < model.getLösungsanzahl(); e++) {
				s = s + model.codeAbrufen(false, i).getNummer(e) + " ";
			}
			s = s + '	';
			for(int e = 0; e < model.getLösungsanzahl(); e++) {
				s = s + xoMaker(model.codeAbrufen(true, i).getNummer(e));
			}
		}
		
		s = s + "\n Du hast noch " + (model.getRundenanzahl() - model.getAktuelleRunde() - 1) + " Versuche.";
		
		return s;
	}
	
	public String xoMaker(int i) {
		String s = "" + i;
		
		switch(i){
			case(0): {
				s = " ";
				break;
			}
			case(1): {
				s = "X";
				break;
			}
			case(2): {
				s = "O";
				break;
			}
		}
		return s;
	}

	public String endTexterzeuger(boolean sieg) {
		String s;
		
		if(sieg == true) {
			 s = "\n Super! Du hast gewonnen!\n Du hast nur " + (model.getAktuelleRunde() +1) + " von " + model.getRundenanzahl() + "\n Versuchen gebraucht.";
		} else {
			s = "\n Schade, du hast verloren.\n Du hast alle " + model.getRundenanzahl() +"\nVersuche verbraucht.";
		}
		return s;
	}
	
	public abstract void zeigeAn(String s);
}
