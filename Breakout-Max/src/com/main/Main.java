package com.main;

import javax.swing.JFrame;

public class Main {
	// add a static game panel so that it can be accessed by other classes	
	public static MainPanel panel = new MainPanel();

	public static void main(String[] args) {
		JFrame window = new JFrame("Breakout Max");

		window.setContentPane(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

}
