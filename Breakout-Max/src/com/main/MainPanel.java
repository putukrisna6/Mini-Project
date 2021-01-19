package com.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.state.StateManager;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements Runnable, KeyListener {
//	Thread
	private Thread thread;
	private boolean running;
	private long targetTime = 1000 / Commons.FPS;

//	Image
	private BufferedImage image;
	private Graphics2D g2d;

//	State Manager
	private StateManager sm;

//	Constructor
	public MainPanel() {
		super();
		setPreferredSize(new Dimension(Commons.WIDTH * Commons.SCALE, Commons.HEIGHT * Commons.SCALE));
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void addNotify() {
		super.addNotify();

		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	private void init() {
		image = new BufferedImage(Commons.WIDTH, Commons.HEIGHT, BufferedImage.TYPE_INT_RGB);

		g2d = (Graphics2D) image.getGraphics();
		running = true;

		sm = new StateManager();
	}

//	pass the keyboard inputs to the state manager
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		sm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		sm.keyReleased(e.getKeyCode());
	}

	@Override
	public void run() {
		init();

		long start;
		long elapsed;
		long wait;

		while (running) {
			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;

			if (wait < 0) {
				wait = 5;
			}

			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void update() {
		sm.update();
	}

//	pass the g2d to state manager
	private void draw() {
		sm.draw(g2d);
	}

	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, Commons.WIDTH * Commons.SCALE, Commons.HEIGHT * Commons.SCALE, null);
		g2.dispose();
	}
}
