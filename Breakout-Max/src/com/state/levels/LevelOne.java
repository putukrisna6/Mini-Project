package com.state.levels;

import com.sprite.Brick;
import com.state.LevelState;
import com.state.StateManager;

public class LevelOne extends LevelState {

	public LevelOne(StateManager sm, int numOfBricks) {
		super(sm, numOfBricks);
		
		int k = 0;
		for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[k] = new Brick(
                				j * 40 + 30, 
                				i * 10 + 50
                			);
                k++;
            }
        }
	}

}
