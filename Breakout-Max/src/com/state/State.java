package com.state;

import java.awt.FontMetrics;
import java.awt.Graphics2D;

import com.main.Commons;
import com.map.AudioPlayer;

public abstract class State {
	protected StateManager sm;
	protected AudioPlayer bgMusic;

	public State(StateManager sm) {
		this.sm = sm;
	}

	public abstract void init();

	public abstract void update();

	public abstract void draw(Graphics2D g2d);

	public abstract void keyPressed(int k);

	public abstract void keyReleased(int k);

	public void close() {
		if (bgMusic != null) {
			bgMusic.close();
		}
	}
	
	public int makeWidth(String text, FontMetrics m) {
		return (Commons.WIDTH / 2) - (m.stringWidth(text) / 2);
	}

	public int makeHeight(int y, FontMetrics m) {
		return (y - m.getHeight() / 2) + m.getAscent();
	}
}
