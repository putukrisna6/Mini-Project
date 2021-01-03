package com.state.levels;

import com.state.LevelState;
import com.state.StateManager;

public class Level_7 extends LevelState {

	public Level_7(StateManager sm, int rows, int columns) {
		super(sm, rows, columns);

		fillConfiguration(0, 0, getRows() - 1, getColumns() - 1, 1);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (i == j || i == columns - 1 - j) {
					fillConfiguration(i, j, 0);
				} else if (((i == 2 || i == 4) && (j == 1 || j == 5)) || ((i == 0 || i == 6) && j == 3)) {
					fillConfiguration(i, j, 10);
				} else if ((i == 0 && (j == 2 || j == 4)) || (i == 6 && (j == 1 || j == 5))) {
					fillConfiguration(i, j, 3);
				} else if ((i == 3 && (j == 0 || j == 6)) || ((i == 2 || i == 5) && j == 3)) {
					fillConfiguration(i, j, 11);
				} else if ((i == 0 && (j == 1 || j == 5)) || (i == 1 && (j != 1 && j != 5))
						|| ((i == 5 || i == 6) && (j == 2 || j == 4))) {
					fillConfiguration(i, j, 2);
				}
			}
		}

		translateConfiguration();
	}

}
