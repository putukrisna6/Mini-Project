package com.state;

import java.awt.Graphics2D;

public abstract class State {
	protected StateManager sm;
	
	public State (StateManager sm) {
		this.sm = sm;
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g2d);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}
