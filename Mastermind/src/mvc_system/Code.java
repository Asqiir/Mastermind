package mvc_system;

public class Code {
	private int[] nummern; //die entscheidenden Daten
	Model model;
	
	public Code(Model m) {
		model = m;
		nummern = new int[model.getLösungsanzahl()];
	}

	public Code(final int[] nummern, Model m) {
		model = m;
		
		for (int i = 0; i < model.getLösungsanzahl(); i++) {
			setNummer(i, nummern[i]);
		}		
	}
	
	public boolean isValid() {
		return nummern[0] != nummern[1] &&
				nummern[0] != nummern[2] &&
				nummern[0] != nummern[3] &&
				nummern[0] != nummern[4] &&
				nummern[1] != nummern[2] &&
				nummern[1] != nummern[3] &&
				nummern[1] != nummern[4] &&
				nummern[2] != nummern[3] &&
				nummern[2] != nummern[4] &&
				nummern[3] != nummern[4] &&
				
				nummern[0] > 0 && nummern[0] < model.getZahlenbereich() &&
				nummern[1] > 0 && nummern[1] < model.getZahlenbereich() &&				
				nummern[2] > 0 && nummern[2] < model.getZahlenbereich() &&
				nummern[3] > 0 && nummern[3] < model.getZahlenbereich() &&
				nummern[4] > 0 && nummern[4] < model.getZahlenbereich();
	}

	public void setNummer (int stelle, int nummer) {
		nummern[stelle] = nummer;
	}
	
	public int getNummer(int stelle) {
		return nummern[stelle];
	}
	
	public void initWithRandomValues() {
		for (int i = 0; i < model.getLösungsanzahl(); i++) {
			setNummer(i, findeNeueNummer(i));
		}
	}
	
	private int findeNeueNummer(final int position) {
		int neueNummer;
		boolean found;
		do {
			neueNummer = (int) (Math.random() * model.getZahlenbereich());
			found = false;
			for (int j=0; j<position; j++) {
				if (nummern[j] == neueNummer) {
					found = true;
					break;
				}
			}
		} while (found);
		return neueNummer;
	}
}