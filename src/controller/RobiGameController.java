package controller;
import java.util.ArrayList;

import model.Gem;
import model.Robi;
import processing.core.PApplet;
import processing.core.PImage;

public class RobiGameController extends PApplet{

	enum State {START, GAME, END};
	
	State gameState = State.START;
	
	Robi r1;
	ArrayList<Robi> robis;
	ArrayList<Gem> gems; //Topf für Gem-Objekte 
	
	PImage bgImage;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("controller.RobiGameController");
	}

	public void setup() {
		
		bgImage = loadImage("Squirrel.jpg");
		gameState = State.START;

	}
	
	/**
	 * initialisiere alle Spielsachen :-)
	 */
	private void initGame() {
		r1 = new Robi(this, 100, 100, 0xFFFF0000);
		
		robis = new ArrayList<>();
		for (int i=0; i < 3; i++)
			robis.add(
					new Robi(this, (int)random(width), (int)random(height), 0xFF0000FF));
		
		gems = new ArrayList<>();
		
		//neue Gem-Objekte erstellen und direkt im "Topf" ablegen
		for (int i=0; i < 10; i++) {
			gems.add(new Gem(this, random(width), 
					random(height),10 , 0xFFFFFF00));
		}
	}
	
	/**
	 * berechnet distanz zwischen Robi r und Gem g
	 * @param r ein Robi-Objekt
	 * @param g ein Gem-Objekt
	 * @return distanz
	 */
	private double getDistance(Robi r, Gem g) {
		double d = 0;
		float a = abs(r.getX() - g.getX());
		float b = abs(r.getY() - g.getY());
		
		d = Math.sqrt(a*a + b*b);
		return d;
	}

	public void settings() {
		size(800,600);
	}

	/**
	 * bewegt jeden KI-Robi einen Schritt zum nächstgelegenen Gem
	 */
	private void moveKiRobis() {
		for(Robi r: robis) {
			// wenn es keine gems mehr hat ist schluss
			if (gems.size()==0) {
				gameState = State.END;
				return;
			}
			//finde nächsten Gem
			double minDist = Double.MAX_VALUE;
			Gem nearestGem = null;
			for (Gem g: gems) {
				double currentDistance = getDistance(r, g);
				if (currentDistance < minDist) {
					minDist = currentDistance;
					nearestGem = g;
				}				
			}			
			//bewege einen schritt zum gem
			if (abs(r.getX()-nearestGem.getX()) > abs(r.getY()-nearestGem.getY())) {
				if (r.getX()-nearestGem.getX() > 0){
					r.moveLeft();
				} else {
					r.moveRight();
				}
			} else {
				if (r.getY()-nearestGem.getY() > 0){
					r.moveUp();
				} else {
					r.moveDown();
				}
			}
			
			//check collision :-)
			if (getDistance(r, nearestGem) < 30) {
				gems.remove(nearestGem); // Gem aus Liste entfernen
				r.addScore(nearestGem.getValue()); // Robi-Score erhöhen
			}
		}
	}
	
	public void draw() {
		background(0);
		image(bgImage,0,0);

		switch (gameState) {
		case START: drawStart(); break;
		case GAME: drawGame(); break;
		case END: drawEnd();
		}		
	}
	/**
	 * Startbildschirm zeichnen
	 */
	void drawStart() {
		textSize(150);
		text("Press SPACE to Start", 30, 200);
	}
	
	/**
	 * Endbildschirm zeichnen
	 */
	void drawEnd() {
		textSize(150);
		text("FINISH", 30, 200);
	}
	
	
	
	void drawGame() {
	
		r1.drawRobi();
		// Kollisionserkennung
		int i=0;
		while (i < gems.size()) {
			Gem g = gems.get(i);
			if (getDistance(r1, g) < 30) {
				gems.remove(i); // Gem aus Liste entfernen
				r1.addScore(g.getValue()); // Robi-Score erhöhen
			}
			else {
				i++;
			}
		}

		moveKiRobis();
		// alle Robis zeichnen (modernere foreach Variante)
		for (Robi r: robis) {
			r.drawRobi();
		}

		// alle gems zeichnen (die klassische Variante)
		for (i = 0; i < gems.size(); i++) {
			Gem g = gems.get(i); // hole ites Element aus dem Topf
			g.drawStar(); // zeichne das aktuelle Element
		}
		
		textSize(28);
		fill(255);
		text("Score: "+r1.getScore(), 30,30);
	}


	public void keyPressed(){
		// während des eigentlichen Spiels
		if (gameState == State.GAME) {
		
			switch(key){
				case 'w': r1.moveUp(); break;
				case 'a': r1.moveLeft(); break;
				case 's': r1.moveDown(); break;
				case 'd': r1.moveRight  (); break;
			}
		}
		// bei Spielstart und -ende, wenn SPACE Taste gedrückt
		else if (key == ' ') {
			if (gameState == State.START) {
				gameState = State.GAME;
				initGame();
			} else {
				gameState = State.START;
			}
		}

	}

}