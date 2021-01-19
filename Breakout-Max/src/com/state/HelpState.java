package com.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import com.map.Background;

public class HelpState extends State {
	private Background bg;

//	Title Font
	private Font titleFont;
//	Options Font
	private Font optionFont;
	private String[] options = { "Select: Enter Key", "Paddle Left: Left Arrow Key", "Paddle Right: Right Arrow Key",
			"Exit Stage: Escape Key", "Back to Menu" };

	public HelpState(StateManager sm) {
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
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

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
		text = "Controls";
		x = makeWidth(text, m);
		y = makeHeight(70, m);

		g2d.drawString(text, x, y);

//		draw options
		m = g2d.getFontMetrics(optionFont);
		g2d.setFont(optionFont);

		for (int i = 0; i < options.length; i++) {
			text = options[i];

			if (i == 4) {
				y = makeHeight(140 + i * 40, m);
				g2d.setColor(Color.GREEN);
			} else {
				y = makeHeight(140 + i * 27, m);
				g2d.setColor(Color.WHITE);
			}

			x = makeWidth(text, m);

			g2d.drawString(text, x, y);
		}
	}

	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			sm.setState(StateManager.MENUSTATE);
		}
	}

	@Override
	public void keyReleased(int k) {
	}
}
