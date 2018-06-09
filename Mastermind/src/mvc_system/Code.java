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