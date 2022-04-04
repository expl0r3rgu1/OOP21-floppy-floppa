package main.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.utilities.GraphicJButton;
import main.utilities.GraphicJLabel;

public class EOGMenuGUI extends JPanel {

	private static final long serialVersionUID = -6834009314888626973L;
	private static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private Image background;

	public EOGMenuGUI() {
		this.setLayout(new GridBagLayout());

		try {
			background = ImageIO.read(getClass().getResource("/resources/images/Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.placeGUIComponents();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.scale(background, SIZE), 0, 0, null);
	}

	private Image scale(Image image, Dimension dim) {
		return image.getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_DEFAULT);
	}

	private GridBagConstraints setDimensionObject(int gridx, int gridy, int ipadx, int ipady, Insets i) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = gridx;
		c.gridy = gridy;
		c.ipadx = ipadx;
		c.ipady = ipady;
		c.insets = i;
		return c;
	}

	private void placeGUIComponents() {
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				GraphicJLabel EOGtitle = new GraphicJLabel("G A M E    O V E R", Color.decode("#FF675F"),
						Color.decode("#FF392E"), "pixel.TTF");
				this.add(EOGtitle, setDimensionObject(1, i, (int) SIZE.getWidth() * 10 / 100,
						(int) SIZE.getWidth() * 5 / 100, new Insets(30, 0, 0, 0)));

			} else if (i == 1) {
				GraphicJLabel coins = new GraphicJLabel(
						"<html><center> Old coins: &emsp &emsp New coins: </center> <br> <center> "
								+ EOGMenu.getPreviousCoins() + "&emsp &emsp &emsp " + EOGMenu.updateCoins(meters)
								+ " 1348 </center> </html>",
						Color.decode("#77DD77"), Color.decode("#007542"), "arcade.ttf");
				this.add(coins, setDimensionObject(1, i, (int) SIZE.getWidth() * 25 / 100,
						(int) SIZE.getWidth() * 15 / 100, new Insets(80, 0, 80, 0)));

				GraphicJButton menuButton = new GraphicJButton("MENU", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				this.add(menuButton, setDimensionObject(2, i, (int) SIZE.getWidth() * 3 / 100,
						(int) SIZE.getWidth() * 2 / 100, new Insets(0, 100, 0, 0)));
				menuButton.addActionListener(e -> {
//					frame.cardLayout.show(frame.panel, "menu");
					System.exit(0);
				});

				GraphicJButton shopButton = new GraphicJButton("SHOP", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				this.add(shopButton, setDimensionObject(0, i, (int) SIZE.getWidth() * 3 / 100,
						(int) SIZE.getWidth() * 2 / 100, new Insets(0, 0, 0, 100)));
				shopButton.addActionListener(e -> {
//					frame.cardLayout.show(frame.panel, "shop");
					System.exit(0);
				});

			} else if (i == 2) {
				GraphicJButton leaderboardButton = new GraphicJButton("LEADERBOARD", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				this.add(leaderboardButton, setDimensionObject(1, i, (int) SIZE.getWidth() * 3 / 100,
						(int) SIZE.getWidth() * 2 / 100, new Insets(30, 0, 30, 0)));
				leaderboardButton.addActionListener(e -> {
//					frame.cardLayout.show(frame.panel, "leaderboard");
					System.exit(0);
				});
			}
		}
	}
}
