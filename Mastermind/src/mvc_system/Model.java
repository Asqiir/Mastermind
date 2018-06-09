package mvc_system;

public class Model {
	private static int RUNDENANZAHL = 20;
	private static int ZAHLENBEREICH = 8;
	private static int LÖSUNGSANZAHL = 5;
	
	public static final int SCHWARZ = 1;
	public static final int WEISS = 2;
	//10
	Code[] alleEingaben;
	Code[] alleAusgaben;
	
	private int aktuelleRunde = 0;
	private boolean spielVorbei = false;
	private Code code;
		
	public Model() {
		alleEingaben = new Code[RUNDENANZAHL];
		alleAusgaben = new Code[RUNDENANZAHL]; //20
	}
	
	public int getRundenanzahl() {
		return RUNDENANZAHL;
	}
	public int getZahlenbereich() {
		return ZAHLENBEREICH;
	}
	public int getLösungsanzahl() {
		return LÖSUNGSANZAHL; //30
	}
//um die 3 Standartwerte zu kriegen
	
	public void setCode(Code c) {
		code = c;
	}
	
	public Code getCode() {
		return code;
	} //40
//setter und getter für den Lösungscode
	public boolean getSpielVorbei() {
		return spielVorbei;
	}
	public void setSpielVorbei(boolean b) {
		spielVorbei = b;
	}
	
	public void codeSpeichern(boolean ausgabeCode, int stelle, Code c) {
		Code[] liste; //50
		
		if(ausgabeCode == true) {
			liste = alleAusgaben;
		} else {
			liste = alleEingaben;
		}
		
		liste[stelle] = c;
	}

	public Code codeAbrufen(boolean ausgabeCode, int stelle) {
		Code[] liste;
		
		if(ausgabeCode == true) {
			liste = alleAusgaben;
		} else {
			liste = alleEingaben;
		}
		
		return liste[stelle];
	}
//speichern und abrufen aller wichtigen Daten
	
	
	public int getAktuelleRunde() {
		return aktuelleRunde;
	}

	public void rundePP() {
		aktuelleRunde++;
	}
	public void setAktuelleRunde(int i) { aktuelleRunde = i; }
//für die aktuelle Runde
}