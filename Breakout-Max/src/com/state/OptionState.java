package com.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import com.map.Background;

public class OptionState extends State {
	private Background bg;

//	Title Font
	private Font titleFont;

//	Options Font
	private Font optionFont;
	private String[] options = { "Difficulty: ", "Paddle Skin: ", "Cheats: ","Back to Menu" };
	private int currChoice = 0;

//	Select
	public static int currDiff = 0;
	private String[] diff = { "<|  Easy  |>", "<|  Normal  |>", "<|  Hard  |>" };

	public static int currSkin = 0;
	private String[] skin = { "<|  Blue  |>", "<|  Green  |>", "<|  Red  |>" };

	public static int currCheat = 0;
	private String[] cheat = { "<|  Off  |>", "<|  On  |>" };
	
//	Constructor
	public OptionState(StateManager sm) {
		super(sm);

		try {
			bg = new Background("/Backgrounds/optionsbg.png", 1);
			bg.setVector(0, 0);

			titleFont = new Font("04b", Font.PLAIN, 24);
			optionFont = new Font("Minecraftia", Font.PLAIN, 14);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics2D g2d) {
		bg.draw(g2d);

		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		FontMetrics m;
		String text;
		int x;
		int y;

//		draw title
		m = g2d.getFontMetrics(titleFont);
		g2d.setFont(titleFont);
		g2d.setColor(Color.WHITE);
		text = "Options";
		x = makeWidth(text, m);
		y = makeHeight(70, m);

		g2d.drawString(text, x, y);

//		draw options
		m = g2d.getFontMetrics(optionFont);
		g2d.setFont(optionFont);

		for (int i = 0; i < options.length; i++) {
			if (i == currChoice) {
				g2d.setColor(Color.GREEN);
			} else {
				g2d.setColor(Color.WHITE);
			}

			y = makeHeight(140 + i * 27, m);

			if (i == 0) {
				text = options[i] + diff[currDiff];
			} else if (i == 1) {
				text = options[i] + skin[currSkin];
			} else if (i == 2) {
				text = options[i] + cheat[currCheat];
			} else {
				text = options[i];
				y = makeHeight(140 + i * 35, m);
			}

			x = makeWidth(text, m);

			g2d.drawString(text, x, y);
		}
	}

	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP) {
			currChoice--;
			if (currChoice < 0) {
				currChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currChoice++;
			if (currChoice == options.length) {
				currChoice = 0;
			}
		}

		if (currChoice == 0) {
			if (k == KeyEvent.VK_LEFT) {
				currDiff--;
				if (currDiff < 0) {
					currDiff = diff.length - 1;
				}
			} else if (k == KeyEvent.VK_RIGHT) {
				currDiff++;
				if (currDiff == diff.length) {
					currDiff = 0;
				}
			}
		} else if (currChoice == 1) {
			if (k == KeyEvent.VK_LEFT) {
				currSkin--;
				if (currSkin < 0) {
					currSkin = skin.length - 1;
				}
			} else if (k == KeyEvent.VK_RIGHT) {
				currSkin++;
				if (currSkin == skin.length) {
					currSkin = 0;
				}
			}
		} else if (currChoice == 2) {
			if (k == KeyEvent.VK_LEFT) {
				currCheat--;
				if (currCheat < 0) {
					currCheat = cheat.length - 1;
				}
			} else if (k == KeyEvent.VK_RIGHT) {
				currCheat++;
				if (currCheat == cheat.length) {
					currCheat = 0;
				}
			}
		}
	}

	@Override
	public void keyReleased(int k) {
	}

	private void select() {
		if (currChoice == 0) {

		} else if (currChoice == 1) {

		} else if (currChoice == 3) {
			sm.setState(StateManager.MENUSTATE);
		}
	}
}
