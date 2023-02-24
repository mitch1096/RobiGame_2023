package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Klasse HighscoreReader
 * @author mitch
 *
 */
public class HighscoreReader {
	private static final String filePath = null;

	/**
	 * 
	 * @param args ausführbares Programm
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public ArrayList<Highscore> readHighscores(String filePath){

		ArrayList<Highscore> result = new ArrayList<>();

		File liste = new File(filePath);
		try {
			FileReader reader = new FileReader(liste);
			BufferedReader bReader = new BufferedReader(reader);

			String zeile = "";
			while(bReader.ready()) {
				zeile = bReader.readLine();

				Highscore hs = parseHighscore(zeile);
				result.add(hs);

				System.out.println(zeile);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fehler: Datei nicht gefunden...");
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fehler: kann nicht lesen...");
			e.printStackTrace();
		}
		return result; 
	}

	public ArrayList<Highscore> readHighscores(){
		ArrayList<Highscore> result = new ArrayList<>();

		return result;
	}

	private Highscore parseHighscore(String line) {
		Highscore result;
		String[] parts =line.split(";");
		String name = parts[0];
		int score = Integer.parseInt(parts[1]);
		result = new Highscore(name,score);
		return result;
	}
}

