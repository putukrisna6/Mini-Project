package com.state.levels;

import com.state.LevelState;
import com.state.StateManager;

public class Level_2 extends LevelState {

	public Level_2(StateManager sm, int rows, int columns) {
		super(sm, rows, columns);

		fillConfiguration(0, 0, getRows() - 1, getColumns() - 1, 1);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if ((((i + 1) % 2 == 0) && (j == 1 || j == 5)) || (i == 3 && j == 3)) {
					fillConfiguration(i, j, 0);
				}

				else if (i == 3 && j % 2 == 0) {
					fillConfiguration(i, j, 11);
				}

				else if ((i == 0 || i == 6) && (j + 1) % 2 == 0) {
					fillConfiguration(i, j, 10);
				}
			}
		}

		translateConfiguration();
	}

}
