package mvc_system;

import javax.swing.*;

public class NumberGridView extends GridView {
	public NumberGridView () {
		super();
	}
	
	@Override
	public int[] getInput() {
		int[] i = new int[model.getLösungsanzahl()];
		
		for(int e = 0; e < model.getLösungsanzahl(); e++) {
			i[e] = listenToButtons[e].getZustand();
		}
		
		return i; // DAs noch machen
	}

	@Override
	public void setInhalt(JLabel jl, int i) {
		jl.setText("" + i);
	}

	@Override
	public void buttonNeuZeichnen(JButton jb, int zustand) {
		jb.setText("" + zustand);
	}

}
