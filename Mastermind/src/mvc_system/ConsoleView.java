package mvc_system;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleView extends TextView {	
	public ConsoleView(){
		super();
		reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Das Spiel beginnt. Finde den Code.");
	}
	
	@Override
	public void fehlermeldung() {
		System.out.println("Diese Eingabe war nicht in Ordnung.\n");
	}
	
	@Override
	public void fullfillField() {
		System.out.println(textErzeugen());
	}

	@Override
	public void nächsteRundeBereit() {
		String s;
		Code c;
		
		try {
			do {
//				s = reader.readLine();
//				String[] eingaben = s.split(" ");
//				c = new Code(model);
//			
//				for(int i = 0; i < model.getLösungsanzahl(); i++) {
//						int e = Integer.parseInt(eingaben[i]);
//						c.setNummer(i,e);
//				}
				c = textToCode(reader.readLine());
			} while(c == null || !c.isValid());
			fireEvent(c);
		}
/*!!!*/		catch(Exception e) { // Ich muss rauskriegen, welche exceptions geworfen werden!
			fehlermeldung(); //kann die bitte was konkretes anzeigen?
			nächsteRundeBereit();
		}
	}

	public void zeigeAn(String s) {
		System.out.println(s);
	}

	@Override
	public void fensterNeuZeichnen(ActionListener al) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emptyField() {
		// TODO Auto-generated method stub
		
	}
}