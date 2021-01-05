package com.sprite;

import java.util.List;

import com.sprite.drops.*;

public abstract class Brick extends Sprite {
	private boolean isDestroyed;
	private char dropId;
	public static int BRICK_WIDTH = 40;
	public static int BRICK_HEIGHT = 10;
	public static int BRICK_X_OFFSET = 10;
	public static int BRICK_Y_OFFSET = 50;

	@Override
	protected abstract void loadImage();

	public Brick(int x, int y, char dropId) {
		super();
		this.x = x;
		this.y = y;
		this.dropId = dropId; // need to add check id

		isDestroyed = false;
	}

	public void dropModifier(List<Drop> d) {
		if (this.dropId == 'd')
			d.add(new Debuff(this.x + 10, this.y));
		else if (this.dropId == 'b')
			d.add(new Buff(this.x + 10, this.y));
	}

	public boolean getIsDestroyed() {
		return isDestroyed;
	}

	public void setIsDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public char getDropId() {
		return dropId;
	}

	public void setDropId(char dropId) {
		this.dropId = dropId;
	}
	
	public void breaks(List<Drop> d) {
		this.setIsDestroyed(true);
		this.dropModifier(d);
	}
}
