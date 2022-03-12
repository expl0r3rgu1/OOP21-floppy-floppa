package main.character;

import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

public class Character extends Movable {
	private Skin skin;
	private boolean dead; //true if the character is alive
	private boolean hit; //true if the character is hit by an obstacle
	
	Character(Position position, Skin skin) {
		super(position);
		this.skin=skin;
		this.dead=false;
		this.hit=false;
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	public void die() {
		this.dead=true;
	}
	
	public boolean isHit() {
		return this.hit;
	}
	
	public void hit() {
		this.hit=true;
	}
	
	private Position jump() {
		//to-do
		return this.getPosition();
	}
	
	private void collide() {
		//to-do
	}
	
}
