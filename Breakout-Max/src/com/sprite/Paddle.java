package com.sprite;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.main.Commons;
import com.state.OptionState;

public class Paddle extends Sprite {
	private int dx;
	private int moveSpeedLeft = -2;
	private int moveSpeedRight = 2;

	private double speedMultiplier;

	boolean isInverted;

	@Override
	protected void loadImage() {
		setToNormal();
	}

	private void resetState() {
		x = Commons.INIT_PADDLE_X;
		y = Commons.INIT_PADDLE_Y;
	}

//	Constructor
	public Paddle() {
		super();
		resetState();
		isInverted = false;
		speedMultiplier = 1;
	}

	public void move() {
		x += dx;

		if (x <= 0) {
			x = 0;
		}
		if (x >= Commons.WIDTH - getImageWidth()) {
			x = Commons.WIDTH - getImageWidth();
		}
	}

	public void keyPressed(int key) {
		if (!isInverted) {
			if (key == KeyEvent.VK_LEFT) {
				dx = (int) (moveSpeedLeft * speedMultiplier);
			} else if (key == KeyEvent.VK_RIGHT) {
				dx = (int) (moveSpeedRight * speedMultiplier);
			}
		} else {
			if (key == KeyEvent.VK_RIGHT) {
				dx = (int) (moveSpeedLeft * speedMultiplier);
			} else if (key == KeyEvent.VK_LEFT) {
				dx = (int) (moveSpeedRight * speedMultiplier);
			}
		}
	}

	public void keyReleased(int key) {
		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (key == KeyEvent.VK_RIGHT) {

			dx = 0;
		}
	}

	public void setMoveSpeed(double multiplier) {
		speedMultiplier = multiplier;
	}

	public void setToLong() {
		ImageIcon ii;
		if (OptionState.currSkin == 0) {
			ii = new ImageIcon("Resource/Sprites/Paddles/paddle-long.png");
			image = ii.getImage();
		} else if (OptionState.currSkin == 1) {
			ii = new ImageIcon("Resource/Sprites/Paddles/paddle-green-long.png");
			image = ii.getImage();
		} else {
			ii = new ImageIcon("Resource/Sprites/Paddles/paddle-red-long.png");
			image = ii.getImage();
		}
		getImageDimensions();
	}

	public void setToShort() {
		ImageIcon ii;
		if (OptionState.currSkin == 0) {
			ii = new ImageIcon("Resource/Sprites/Paddles/paddle-short.png");
			image = ii.getImage();
		} else if (OptionState.currSkin == 1) {
			ii = new ImageIcon("Resource/Sprites/Paddles/paddle-green-short.png");
			image = ii.getImage();
		} else {
			ii = new ImageIcon("Resource/Sprites/Paddles/paddle-red-short.png");
			image = ii.getImage();
		}
		getImageDimensions();
	}

	public void setToNormal() {
		ImageIcon ii;
		if (OptionState.currSkin == 0) {
			ii = new ImageIcon("Resource/Sprites/Paddles/paddle.png");
			image = ii.getImage();
		} else if (OptionState.currSkin == 1) {
			ii = new ImageIcon("Resource/Sprites/Paddles/paddle-green.png");
			image = ii.getImage();
		} else {
			ii = new ImageIcon("Resource/Sprites/Paddles/paddle-red.png");
			image = ii.getImage();
		}
		getImageDimensions();
	}

	public void setInverted(boolean bool) {
		isInverted = bool;
	}
}
