package model;

/**
 * Klasse zur Verwaltung von Highscore-Daten
 * @author mitch
 *
 */
public class Highscore {

	private String name;
	private int score;

	public Highscore(String name,  int score) {
		this.name = name;
		this.score = score;
	}
	public String getName() {
		return name;
	}

	public int getScore() {
		return score;

	}
}
