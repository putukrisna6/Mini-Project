package com.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import com.map.Background;

public class LevelSelectState extends State {

	private Background bg;

//	Title Font
	private Font titleFont;
//	Options Font
	private Font levelFont;
//  Levels
	private String[][] levelSets = { { "<|  Adventure I  |>", "Stage I-1", "Stage I-2", "Stage I-3", "Stage I-4", "Stage I-5","Back to Menu" },
			{ "<|  Adventure II  |>", "Stage II-1", "Stage II-2", "Stage II-3", "Stage II-4", "Stage II-5","Back to Menu" }};

	private int currSet = 0;
	private int currLevel = 0;

//	Constructor
	public LevelSelectState(StateManager sm) {
		super(sm);

		try {
			bg = new Background("/Backgrounds/selectbg.png", 1);
			bg.setVector(0, 0);

			titleFont = new Font("04b", Font.PLAIN, 24);
			levelFont = new Font("Minecraftia", Font.PLAIN, 14);
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
		text = "Level Select";
		x = makeWidth(text, m);
		y = makeHeight(70, m);

		g2d.drawString(text, x, y);

//		draw options
		m = g2d.getFontMetrics(levelFont);
		g2d.setFont(levelFont);

		for (int i = 0; i < levelSets[currSet].length; i++) {
			if (i == currLevel) {
				g2d.setColor(Color.RED);
			} else {
				g2d.setColor(Color.WHITE);
			}

			y = makeHeight(140 + i * 27, m);
			// make the "back to menu" lower
			if (i == 6) {
				y = makeHeight(140 + i * 30, m);
			}

			text = levelSets[currSet][i];
			x = makeWidth(text, m);

			g2d.drawString(text, x, y);
		}
	}

	@Override
	public void keyPressed(int k) {
		if (currLevel == 0) {
			if (k == KeyEvent.VK_LEFT) {
				currSet--;
				if (currSet < 0) {
					currSet = levelSets.length - 1;
				}
			} else if (k == KeyEvent.VK_RIGHT) {
				currSet++;
				if (currSet == levelSets.length) {
					currSet = 0;
				}
			}
		}

		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP) {
			currLevel--;
			if (currLevel < 0) {
				currLevel = levelSets[currSet].length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currLevel++;
			if (currLevel == levelSets[currSet].length) {
				currLevel = 0;
			}
		}

	}

	@Override
	public void keyReleased(int k) {
	}

	private void select() {
		if (currLevel == 6) {
			sm.setState(StateManager.MENUSTATE);
		}

		if (currSet == 0) {
			if (currLevel == 1) {
				sm.setState(StateManager.LEVEL1STATE);
			} else if (currLevel == 2) {
				sm.setState(StateManager.LEVEL2STATE);
			} else if (currLevel == 3) {
				sm.setState(StateManager.LEVEL3STATE);
			} else if (currLevel == 4) {
				sm.setState(StateManager.LEVEL4STATE);
			} else if (currLevel == 5) {
				sm.setState(StateManager.LEVEL5STATE);
			}
		}
		else if (currSet == 1) {
			if (currLevel == 1) {
				sm.setState(StateManager.LEVEL6STATE);
			} else if (currLevel == 2) {
				sm.setState(StateManager.LEVEL7STATE);
			} else if (currLevel == 3) {
				sm.setState(StateManager.LEVEL8STATE);
			} else if (currLevel == 4) {
				sm.setState(StateManager.LEVEL9STATE);
			} else if (currLevel == 5) {
				sm.setState(StateManager.LEVEL10STATE);
			}
		}
	}
}
