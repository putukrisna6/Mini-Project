package com.sprite;

import javax.swing.ImageIcon;

import com.main.Commons;

public class Drop extends Sprite {

//	Probably will have a lot in common with the Ball class
	
	private int yDir;
	
	private boolean visible;
	
	public Drop(int x, int y) {
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
	protected void loadImage() {
		ImageIcon ii = new ImageIcon("Resource/Sprites/pickuptemp.png");
		image = ii.getImage();		
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
}
