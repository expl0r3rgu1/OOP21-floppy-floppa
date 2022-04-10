package main.state_changers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Random;

import main.utilities.Position;
import main.utilities.Skin;

public class CoinsIncrement extends Booster {

	private Random rand = new Random();
	private static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	public CoinsIncrement(Position position, Skin skin) {
		super(position, skin);
	}

	@Override
	public Integer changeState() {
		return (int) rand.nextInt(100);
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(.getSkin().getImage(), getPosition.getX(), getPosition.getY(),
				SIZE.getWidth() * 3 / 100, SIZE.getWidth() * 3 / 100, null);
	}
}