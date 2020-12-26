package com.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import com.main.Commons;
import com.map.Background;

public class MenuState extends State {

	private Background bg;
	private int currentChoice = 0;
	private String[] options = {
				"Play", "Controls", "Options", "Quit"
			};

	private Font font;
	
	public MenuState(StateManager sm) {
		super(sm);
		
		try {
			bg = new Background("/Backgrounds/mainbg.png", 1);
			bg.setVector(0, 0);
			
			font = new Font("Minecraftia", Font.PLAIN, 16);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() {}
	@Override
	public void update() {
//		bg.update();
//		uncomment to make the bg move
	}
	@Override
	public void draw(Graphics2D g2d) {
		bg.draw(g2d);
		g2d.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON
			);
		
		FontMetrics metrics;
		String text;
		int x;
		int y;

//		draw menu
		metrics = g2d.getFontMetrics(font);
		g2d.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g2d.setColor(Color.RED);
			}
			else {
				g2d.setColor(Color.WHITE);
			}
			text = options[i];
			x = makeWidth(text, metrics);
			y = makeHeight(170 + i * 27, metrics);
			
			g2d.drawString(text, x, y);
		}
	}
	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice < 0) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	@Override
	public void keyReleased(int k) {	}
	
	private void select() {
		if (currentChoice == 0) {
			sm.setState(StateManager.SELECTSTATE);
		}
		else if (currentChoice == 1) {
			sm.setState(StateManager.HELPSTATE);
		}
		else if (currentChoice == 2) {
			sm.setState(StateManager.OPTIONSTATE);
		}
		else if (currentChoice == 3) {
			System.exit(0);
		}
	}
	
	private int makeWidth(String text, FontMetrics m) {
		return (Commons.WIDTH / 2) - (m.stringWidth(text) / 2);
	}
	private int makeHeight(int y, FontMetrics m) {
		return (y - m.getHeight() / 2) + m.getAscent();
	}

}
