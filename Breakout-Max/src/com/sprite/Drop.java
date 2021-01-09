package com.sprite;

import java.util.Random;

import com.main.Commons;
import com.map.AudioPlayer;

public abstract class Drop extends Sprite {
	
	protected AudioPlayer picked;
	
	private int yDir;

	private boolean visible;

	public Drop(double x, double y) {
		super();
		this.x = x;
		this.y = y;

		visible = true;
		setSFX();
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
		picked.play();
	}

	@Override
	public String toString() {
		return "Drop";
	}
	
	protected abstract void setSFX();
}
