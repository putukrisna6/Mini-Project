package com.state.levels;

import com.state.LevelState;
import com.state.StateManager;

public class Level_6 extends LevelState {

	public Level_6(StateManager sm, int rows, int columns) {
		super(sm, rows, columns);

		fillConfiguration(0, 0, getRows() - 1, getColumns() - 1, 1);
		fillConfiguration(2, 2, 4, 4, 2);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if ((i == 1 || i == 5) && (j == 0 || j == 6)) {
					fillConfiguration(i, j, 2);
				} else if (i == 3 && j == 3) {
					fillConfiguration(i, j, 10);
				} else if (((i == 1 || i == 5) && ((j + 1) % 2 == 0)) || (i == 3 && (j == 1 || j == 5))) {
					fillConfiguration(i, j, 0);
				} else if ((i == 0 && (j == 2 || j == 4)) || (i == 3 && (j == 0 || j == 6))) {
					fillConfiguration(i, j, 3);
				} else if ((i == 0 || i == 2 || i == 4) && (j == 0 || j == 6)) {
					fillConfiguration(i, j, 11);
				}
			}
		}

		translateConfiguration();
	}

}
