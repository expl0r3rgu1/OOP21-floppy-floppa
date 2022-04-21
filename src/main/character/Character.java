package main.character;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.state_changers.Booster;
import main.state_changers.Malus;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

public class Character extends Movable {
	private Skin skin;
	private boolean dead; // true if the character is dead
	private boolean jumping; // true if the character is jumping
	private Timer timer;

	public Character(Position position, Skin skin) {
		super(position);
		this.skin = skin;
		this.dead = false;
		this.jumping = false;
		this.timer = new Timer(Constants.CHARACTER_JUMP_TIMEOUT / Constants.SPEED, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jumping = false;
				timer.stop();
			}

		});
	}

	public boolean isDead() {
		return this.dead;
	}

	public void die() {
		this.dead = true;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public Skin getSkin() {
		return this.skin;
	}

	public void jump() {

		if (!this.jumping) {
			this.timer.start();
			this.jumping = true;
		} else {
			this.timer.restart();
		}
	}

	public void collideFixedObstacle(List<FixedObstacle> fixedObstacleList) {

		for (FixedObstacle fixedObstacle : fixedObstacleList) {

			// variables to make it more readable
			int characterX = this.getPosition().getX();
			int characterWiderX = characterX + this.skin.getWidth();
			int characterY = this.getPosition().getY();
			int characterLowerY = characterY + this.skin.getHeight();
			int obstacleX = fixedObstacle.getPosition().getX();
			int obstacleWiderX = obstacleX + fixedObstacle.getSkin().getWidth();
			int obstacleUpperY = fixedObstacle.getPosition().getY() + (int) FixedObstacle.space / 2;
			int obstacleLowerY = fixedObstacle.getPosition().getY() - (int) FixedObstacle.space / 2;

			// in this if I check the x
			if ((characterX >= obstacleX && characterX <= obstacleWiderX)
					|| (characterWiderX >= obstacleX && characterWiderX <= obstacleWiderX)) {

				// in this if I check the y
				if ((characterY >= obstacleUpperY || characterY <= obstacleLowerY)
						|| (characterLowerY >= obstacleUpperY || characterLowerY <= obstacleLowerY)) {

					this.dead = true;
					return;
				}
			}
		}
	}

	public void collideMovingObstacle(List<MovingObstacle> movingObstacleList) {

		for (MovingObstacle movingObstacle : movingObstacleList) {

			// variables to make it more readable
			int x = movingObstacle.getPosition().getX();
			int y = movingObstacle.getPosition().getY();
			int height = movingObstacle.getSkin().getHeight();
			int width = movingObstacle.getSkin().getWidth();

			if (this.checkCollision(x, y, height, width)) {
				this.dead = true;
				return;
			}
		}
	}

	public void collideMalus(List<Malus> malusList) {

		for (Malus malus : malusList) {

			// variables to make it more readable
			int x = malus.getPosition().getX();
			int y = malus.getPosition().getY();
			int height = malus.getSkin().getHeight();
			int width = malus.getSkin().getWidth();

			if (this.checkCollision(x, y, height, width)) {
				malus.changeState();
				return;
			}
		}
	}

	public void collideBooster(List<Booster> boosterList) {

		for (Booster booster : boosterList) {

			// variables to make it more readable
			int x = booster.getPosition().getX();
			int y = booster.getPosition().getY();
			int height = booster.getSkin().getHeight();
			int width = booster.getSkin().getWidth();

			if (this.checkCollision(x, y, height, width)) {
				booster.changeState();
				return;
			}
		}
	}

	public void collideBorders() {

		// variables to make it more readable
		int characterY = this.getPosition().getY();
		int characterLowerY = characterY + this.skin.getHeight();
		int upperBorder = 0;
		int lowerBorder = (int) Constants.SCREEN_SIZE.getHeight();

		if (characterY <= upperBorder || characterLowerY >= lowerBorder) {
			this.dead = true;
		}
	}

	private boolean checkCollision(int x, int y, int height, int width) {

		// variables to make it more readable
		int characterX = this.getPosition().getX();
		int characterWiderX = characterX + this.skin.getWidth();
		int characterY = this.getPosition().getY();
		int characterLowerY = characterY + this.getSkin().getHeight();
		int entityWiderX = x + width;
		int entityLowerY = y + height;

		// in this if I check the x
		if ((characterX >= x && characterX <= entityWiderX)
				|| (characterWiderX >= x && characterWiderX <= entityWiderX)) {

			// in this if I check the y
			if ((characterY >= y && characterY <= entityLowerY)
					|| (characterLowerY >= y && characterLowerY <= entityLowerY)) {

				return true;
			}
		}

		return false;
	}

	@Override
	public void animate(Graphics2D canvas) {

		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		int width = this.skin.getWidth();
		int height = this.skin.getHeight();
		int angle = this.jumping ? -Constants.CHARACTER_ANGLE_DEGREES : Constants.CHARACTER_ANGLE_DEGREES;
		Image image = CommonMethods.getAngledImage(this.skin.getImage(), angle);

		canvas.drawImage(image, x, y, width, height, null);

		this.updatePosition();
	}

	private void updatePosition() {

		int value = this.jumping ? -2 : 1;
		this.getPosition().setY(this.getPosition().getY() + value * Constants.MOVING_FACTOR);

	}

}
