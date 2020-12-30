package com.sprite;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.main.Commons;

public class Paddle extends Sprite {
	private int dx;
	private int moveSpeedLeft = -2;
	private int moveSpeedRight = 2;	
	
	@Override
	protected void loadImage() {
		ImageIcon ii = new ImageIcon("Resource/Sprites/Paddles/paddle.png");
		image = ii.getImage();
	}
	
	private void resetState() {
		x = Commons.INIT_PADDLE_X;
		y = Commons.INIT_PADDLE_Y;
	}
	
//	Constructor
	public Paddle() {
		super();
		resetState();
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
		if (key == KeyEvent.VK_LEFT) {
			dx = moveSpeedLeft;
		}
		else if (key == KeyEvent.VK_RIGHT) {
			dx = moveSpeedRight;
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
		this.moveSpeedLeft *= multiplier;
		this.moveSpeedRight *= multiplier;
	}
	
	public void setToLong() {
		ImageIcon ii = new ImageIcon("Resource/Sprites/Paddles/paddle-long.png");
		image = ii.getImage();
		getImageDimensions();
	}
	public void setToShort() {
		ImageIcon ii = new ImageIcon("Resource/Sprites/Paddles/paddle-short.png");
		image = ii.getImage();
		getImageDimensions();
	}

}
