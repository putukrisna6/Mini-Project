package com.state.levels;

import com.state.LevelState;
import com.state.StateManager;

public class Level_10 extends LevelState {

	public Level_10(StateManager sm, int rows, int columns) {
		super(sm, rows, columns);

		fillConfiguration(0, 0, getRows() - 1, getColumns() - 1, 1);
		fillConfiguration(1, 1, 5, 5, 2);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (((i == 0 || i == 6) && (j == 0 || j == 6)) || (i == 3 && ((j + 1) % 2 == 0))) {
					fillConfiguration(i, j, 3);
				} else if (((i == 2 || i == 4) && j == 3) || (i == 3 && (j == 2 || j == 4))) {
					fillConfiguration(i, j, 11);
				} else if ((i == 0 && (j == 2 || j == 4)) || (((i + 1) % 2 == 0) && (j == 0 || j == 6))
						|| (i == 6 && ((j + 1) % 2 == 0))) {
					fillConfiguration(i, j, 10);
				}
			}
		}

		translateConfiguration();
	}

}
