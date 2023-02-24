package model;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Klasse speichert Highscores in Datei
 * @author mitch
 *
 */
public class HighscoreWriter {


	public void writeHighscores(ArrayList<Highscore>highscores, String filePath){
		
		try {
			PrintWriter prnt = new PrintWriter (filePath);
			for (Highscore value : highscores) { // for_each
				prnt.write(value.getName() + ";" + value.getScore() +
						System.lineSeparator());
			}
			prnt.flush(); // Puffer schreiben
			prnt.close(); // Systemresource freigeben
		}
		catch (Exception ex) {
			System.out.println("Machine kaputt");
		}




	}



}

