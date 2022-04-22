package main.state_changers;

import java.awt.Graphics2D;

import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that implements an object that helps the character in different ways
 */
public abstract class Booster extends Movable {

	private Skin skin;

	/**
	 * @param position the booster initial position
	 * @param skin     the booster skin
	 */
	public Booster(Position position, Skin skin) {
		super(position);
		this.skin = skin;
	}

	/**
	 * @return the booster skin
	 */
	public Skin getSkin() {
		return skin;
	}

	/**
	 * Sets the booster Skin
	 * 
	 * @param skin the booster skin
	 */
	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public abstract void changeState();

	public void moveOffScreen() {
		setPosition(new Position(-getSkin().getWidth(), getPosition().getY()));
	}
}
