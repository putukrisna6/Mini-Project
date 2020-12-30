package com.state.levels;

import com.sprite.bricks.NormalBrick;
import com.state.LevelState;
import com.state.StateManager;

public class LevelThree extends LevelState {

	private final int rows = 10;
	private final int cols = 5;
	
	public LevelThree(StateManager sm, int numOfBricks) {
		super(sm, numOfBricks);
		int k = 0;
		
//		TODO - implement this better please :c
		for (int i = 0; i < rows / 2; i++) {
            for (int j = 0; j < cols; j++) {
                bricks[k] = new NormalBrick(
                				j * 40 + 50, 
                				i * 10 + 50
                			);
                if (i == 0 || i == 4) {
                	if (j == 0 || j == 1) {
                		bricks[k].setIsDestroyed(true);
                	}
                	else if (j == 3 || j == 4) {
                		bricks[k].setIsDestroyed(true);
                	}
                }
                else if (i == 1 || i == 3) {
                	if (j == 0 || j == 4) {
                		bricks[k].setIsDestroyed(true);
                	}
                }
                k++;
            }
        }
		for (int i = 5; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                bricks[k] = new NormalBrick(
                				j * 40 + 50, 
                				i * 10 + 50
                			);
                if (i == 5 || i == 9) {
                	if (j == 0 || j == 1) {
                		bricks[k].setIsDestroyed(true);
                	}
                	else if (j == 3 || j == 4) {
                		bricks[k].setIsDestroyed(true);
                	}
                }
                else if (i == 6 || i == 8) {
                	if (j == 0 || j == 4) {
                		bricks[k].setIsDestroyed(true);
                	}
                }
                k++;
            }
        }
	}

}
