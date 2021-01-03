package com.state.levels;

import com.state.LevelState;
import com.state.StateManager;

public class Level_1 extends LevelState {

	public Level_1(StateManager sm, int rows, int columns) {
		super(sm, rows, columns);

		fillConfiguration(0, 0, getRows() - 1, getColumns() - 1, 1);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (((i == 1 || i == 5) && ((j + 1) % 2 == 0)) || (i == 3 && j % 2 == 0)) {
					fillConfiguration(i, j, 11);
				}
			}
		}

		translateConfiguration();
	}

}
