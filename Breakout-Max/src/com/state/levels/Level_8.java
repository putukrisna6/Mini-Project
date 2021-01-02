package com.state.levels;

import com.sprite.bricks.MultipleHitBrick;
import com.sprite.bricks.NormalBrick;
import com.sprite.bricks.UnbreakableBrick;
import com.state.LevelState;
import com.state.StateManager;

public class Level_8 extends LevelState {

	public Level_8(StateManager sm, int rows, int columns) {
		super(sm, rows, columns);

		fillConfiguration(0, 0, getRows() - 1, getColumns() - 1, 2);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (((i == 0 || i == 6) && (j == 0 || j == 6)) || (i == 3 && j == 3)) {
					fillConfiguration(i, j, 11);
				} else if ((i == 1 || i == 5) && ((j + 1) % 2 == 0)) {
					fillConfiguration(i, j, 10);
				} else if (((i == 1 || i == 5) && (j == 2 || j == 4)) || ((i == 2 || i == 4) && (j == 1 || j == 5))) {
					fillConfiguration(i, j, 1);
				} else if (i == 3 && (j == 1 || j == 5)) {
					fillConfiguration(i, j, 3);
				}
			}
		}

		translateConfiguration();
	}

}