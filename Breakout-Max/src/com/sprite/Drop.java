package com.sprite;

import java.util.Random;

import com.main.Commons;

public abstract class Drop extends Sprite {

	private int yDir;

	private boolean visible;

	public Drop(double x, double y) {
		super();
		this.x = x;
		this.y = y;

		visible = true;
	}

	public void move() {
		y += 1;

		if (y > Commons.HEIGHT) {
			visible = false;
		}
	}

	@Override
	protected abstract void loadImage();

	public int getEffect() {
		Random rand = new Random();
		return rand.nextInt(4);
	}

	public void setYDir(int y) {
		yDir = y;
	}

	public int getYDir() {
		return yDir;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "Drop";
	}
}
