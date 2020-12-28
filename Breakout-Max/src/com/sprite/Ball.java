package com.sprite;

import javax.swing.ImageIcon;

import com.main.Commons;


public class Ball extends Sprite {
	
	private int xDir;
	private int yDir;
	private int speedMultiplier = 1;
	
	@Override
	protected void loadImage() {
		ImageIcon ii = new ImageIcon("Resource/Sprites/ball.png");
		image = ii.getImage();
	}
	private void resetState() {
		x = Commons.INIT_BALL_X;
		y = Commons.INIT_BALL_Y;
	}
	
	public void setXDir(int x) {
		xDir = x * speedMultiplier;
	}
	public void setYDir(int y) {
		yDir = y * speedMultiplier;
	}
	public int getYDir() {
		return yDir;
	}
	public void setSpeedMultiplier(int speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}
	
	public Ball() {
		super();
		xDir = 1;
		yDir = -1;

		resetState();
	}
	public void move() {
		x += xDir;
		y += yDir;
		
		if (x == 0) {
			setXDir(1);
		}
		if (x == Commons.WIDTH - getImageWidth()) {
			setXDir(-1);
		}
		if (y == 0) {
			setYDir(1);
		}
	}
}