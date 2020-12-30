package com.state.levels;

import com.sprite.bricks.NormalBrick;
import com.state.LevelState;
import com.state.StateManager;

public class LevelTwo extends LevelState {

	private final int rows = 10;
	private final int cols = 5;
	
	public LevelTwo(StateManager sm, int numOfBricks) {
		super(sm, numOfBricks);
		int k = 0;

		for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                bricks[k] = new NormalBrick(
                				j * 40 + 50, 
                				i * 10 + 50
                			);
                if (!(j % 2 == (i % 2))) {
                	bricks[k].setIsDestroyed(true);
                }
                k++;
            }
        }
	}

}
