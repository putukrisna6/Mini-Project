package com.state;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class StateManager {
	private ArrayList<State> states;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int SELECTSTATE = 1;
	public static final int OPTIONSTATE = 2;
	
	public StateManager() {
		states = new ArrayList<State>();
		
		currentState = 0;
		states.add(new MenuState(this));
		states.add(new LevelSelectState(this));
		states.add(new OptionState(this));
	}
	
	public void setState(int state) {
		currentState = state;
		states.get(currentState).init();
	}
	public void update() {
		states.get(currentState).update();
	}
	public void draw(Graphics2D g2d) {
		states.get(currentState).draw(g2d);
	}
	public void keyPressed(int k) {
		states.get(currentState).keyPressed(k);
	}
	public void keyReleased(int k) {
		states.get(currentState).keyReleased(k);
	}
}
