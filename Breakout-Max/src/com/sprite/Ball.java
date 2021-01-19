package com.sprite;

import javax.swing.ImageIcon;

import com.main.Commons;
import com.map.AudioPlayer;

public class Ball extends Sprite {

	private double xDir;
	private double yDir;
	private double speedMultiplier = 1;
	
	private boolean changeDirection;
	
	private AudioPlayer bounceSFX;

	@Override
	protected void loadImage() {
		ImageIcon ii = new ImageIcon("Resource/Sprites/ball.png");
		image = ii.getImage();
	}

	private void resetState() {
		x = Commons.INIT_BALL_X;
		y = Commons.INIT_BALL_Y;
	}

	public void setXDir(double x) {
		changeDirection = true;
		xDir = x;
	}

	public void setYDir(double y) {
		changeDirection = true;
		yDir = y;
	}

	public double getYDir() {
		return yDir;
	}

	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}

	public Ball() {
		super();
		xDir = 0;
		yDir = 1;
		
		bounceSFX = new AudioPlayer("/Music/ballcollide.wav");
		changeDirection = false;
		resetState();
	}

	public void move() {
		x += xDir * speedMultiplier;
		y += yDir * speedMultiplier;

		if (x == speedMultiplier) {
			setXDir(1);
		} else if (x == Commons.WIDTH - (getImageWidth() * speedMultiplier)) {
			setXDir(-1);
		}
		if (y == getImageHeight() * speedMultiplier) {
			setYDir(1);
		}
		
		if (changeDirection) {
			bounceSFX.play();
			changeDirection = false;
		}
	}
	
	public void moveNorth() {
		yDir = Math.abs(yDir) * -1;
    }
    public void moveSouth() {
    	yDir = Math.abs(yDir);
    }
    public void moveWest() {
    	xDir = Math.abs(xDir) * -1;
    }
    public void moveEast() {
    	xDir = Math.abs(xDir);
    }
}