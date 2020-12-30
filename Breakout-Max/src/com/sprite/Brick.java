package com.sprite;

public abstract class Brick extends Sprite {
	
	private boolean isDestroyed;
	
	@Override
	protected abstract void loadImage();
	
	public Brick(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		
		isDestroyed = false;
	}
	
	public boolean getIsDestroyed() {
		return isDestroyed;
	}
	public void setIsDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
}
