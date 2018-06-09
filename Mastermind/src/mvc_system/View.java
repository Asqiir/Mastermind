package mvc_system;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public abstract class View {
	private List<CodeListener> actionListener = new ArrayList<CodeListener>();
	protected Model model;
	protected JFrame fenster;
	
	public View() { }

	public abstract void fensterNeuZeichnen(ActionListener al);
	
	public void addActionListener(CodeListener l) {
		actionListener.add(l);
	}

	public void removeActionListener(CodeListener l) {
		actionListener.remove(l);
	}
//ActionListener hinzufügen und entfernen, vom jeweiligen Listener aktiviert
	
	private void fireActionPerformed(CodeEvent event) {
		for (CodeListener action : actionListener) {
			action.actionPerformed(event);
		}
	}
//feuert die Event, wichtigste Methode, wird NICHT überschrieben, nicht direkt benutzt; ist lediglich der Umweg über den CodeListener

	public void fireEvent(Code c) {
		fireActionPerformed(new CodeEvent(this, ActionEvent.ACTION_PERFORMED, "click", c));
	}
//die eigentliche Event-werfende Methode. liefert einen code mit
	
	protected void setModel(Model m) {
		model = m;
	}

	protected Model getModel() {
		return model;
	}

	public abstract void emptyField();
	
	public abstract void fehlermeldung(); //aufgerufen, damit eine Fehlermeldung angezeigt wird

	public abstract void fullfillField(); //entspricht repaint()
	
	public abstract void ende(boolean sieg); //abstract fürs Ende
	
	public abstract void nächsteRundeBereit(); //nur für die Konsole wichtig, da diese readLine() nutzt
	
	protected String getRundenanzahl() {
		if(model!= null) {
			return "" + model.getRundenanzahl();
		}
		else { return "UNDEFINED ERROR"; }
	}
	
	protected String getLösungsanzahl() {
		if(model!= null) {
			return "" + model.getLösungsanzahl();
		}
		else { return "UNDEFINED ERROR"; }
	}
	
	protected String getZahlenbereich() {
		if(model!= null) {
			return "" + model.getZahlenbereich();
		}
		else { return "UNDEFINED ERROR"; }
	}
}
