package com.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import com.main.Commons;
import com.map.Background;

public class LevelSelectState extends State {
	
	private Background bg;
	
//	Title Font
	private Font titleFont;

//	Options Font
	private Font levelFont;
	private String[] levels = {
			"Stage 1", "Stage 2", "Stage 3", "Back to Menu"
		};
	private int currLevel = 0;

//	Constructor
	public LevelSelectState(StateManager sm) {
		super(sm);
		
		try {
			bg = new Background("/Backgrounds/selectbg.png", 1);
			bg.setVector(0, 0);
			
			titleFont = new Font("04b", Font.PLAIN, 24);
			levelFont = new Font("Minecraftia", Font.PLAIN, 14);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() { }
	@Override
	public void update() {	}
	@Override
	public void draw(Graphics2D g2d) {
		bg.draw(g2d);
		
		g2d.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON
			);
		
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
		
		for (int i = 0; i < levels.length; i++) {
			if (i == currLevel) {
				g2d.setColor(Color.RED);
			}
			else {
				g2d.setColor(Color.WHITE);
			}
			
			y = makeHeight(140 + i * 27, m);

			if (i == 3) {
				y = makeHeight(140 + i * 40, m);
			}
			
			text = levels[i];
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
			currLevel--;
			if (currLevel < 0) {
				currLevel = levels.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currLevel++;
			if (currLevel == levels.length) {
				currLevel = 0;
			}
		}
		
	}
	@Override
	public void keyReleased(int k) {}
	
	private void select() {
		if (currLevel == 0) {
			sm.setState(StateManager.LEVEL1STATE);
		}
		else if (currLevel == 1) {
			sm.setState(StateManager.LEVEL2STATE);
		}
		else if (currLevel == 2) {
			
		}
		else if (currLevel == 3) {
			sm.setState(StateManager.MENUSTATE);
		}
	}
	
	private int makeWidth(String text, FontMetrics m) {
		return (Commons.WIDTH / 2) - (m.stringWidth(text) / 2);
	}
	private int makeHeight(int y, FontMetrics m) {
		return (y - m.getHeight() / 2) + m.getAscent();
	}
}
