package com.main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame("Breakout Max");
		
		window.setContentPane(new MainPanel());
		window.setDefaultCloseOperation(
					JFrame.EXIT_ON_CLOSE
				);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

}
