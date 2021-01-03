package com.state;

import java.awt.Graphics2D;

import com.state.levels.*;

public class StateManager {
//	private ArrayList<State> states;
	private State[] states;
	private int currentState;

	public static final int NUMGAMESTATES = 14;

	public static final int MENUSTATE = 0;
	public static final int SELECTSTATE = 1;
	public static final int HELPSTATE = 2;
	public static final int OPTIONSTATE = 3;
	
	public static final int LEVEL1STATE = 4;
	public static final int LEVEL2STATE = 5;
	public static final int LEVEL3STATE = 6;
	public static final int LEVEL4STATE = 7;
	public static final int LEVEL5STATE = 8;
	public static final int LEVEL6STATE = 9;
	public static final int LEVEL7STATE = 10;
	public static final int LEVEL8STATE = 11;
	public static final int LEVEL9STATE = 12;
	public static final int LEVEL10STATE = 13;

	public StateManager() {
//		states = new ArrayList<State>();

		states = new State[NUMGAMESTATES];

		currentState = MENUSTATE;
		loadState(currentState);
	}

	private void loadState(int state) {
		switch (state) {
		case MENUSTATE:
			states[state] = new MenuState(this);
			return;
		case SELECTSTATE:
			states[state] = new LevelSelectState(this);
			return;
		case HELPSTATE:
			states[state] = new HelpState(this);
			return;
		case OPTIONSTATE:
			states[state] = new OptionState(this);
			return;
		case LEVEL1STATE:
			states[state] = new Level_1(this, 7, 7);
			return;
		case LEVEL2STATE:
			states[state] = new Level_2(this, 7, 7);
			return;
		case LEVEL3STATE:
			states[state] = new Level_3(this, 7, 7);
			return;
		case LEVEL4STATE:
			states[state] = new Level_4(this, 7, 7);
			return;
		case LEVEL5STATE:
			states[state] = new Level_5(this, 7, 7);
			return;
		case LEVEL6STATE:
			states[state] = new Level_6(this, 7, 7);
			return;
		case LEVEL7STATE:
			states[state] = new Level_7(this, 7, 7);
			return;
		case LEVEL8STATE:
			states[state] = new Level_8(this, 7, 7);
			return;
		case LEVEL9STATE:
			states[state] = new Level_9(this, 7, 7);
			return;
		case LEVEL10STATE:
			states[state] = new Level_10(this, 7, 7);
			return;
		default:
			throw new IllegalArgumentException("Unexpected value: " + state);
		}
	}

	private void unloadState(int state) {
		states[state].close();
		states[state] = null;
	}

	public void setState(int state) {
		unloadState(currentState);
		currentState = state;

		loadState(currentState);
	}

	public void update() {
		try {
			states[currentState].update();
		} catch (Exception e) {}
	}

	public void draw(Graphics2D g2d) {
		try {
			states[currentState].draw(g2d);
		} catch (Exception e) {}
	}

	public void keyPressed(int k) {
		states[currentState].keyPressed(k);
	}

	public void keyReleased(int k) {
		states[currentState].keyReleased(k);
	}
}
