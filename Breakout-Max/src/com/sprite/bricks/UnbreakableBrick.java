package com.sprite.bricks;

import javax.swing.ImageIcon;

import com.sprite.Brick;

public class UnbreakableBrick extends Brick {

	public UnbreakableBrick(int x, int y, char dropId) {
		super(x, y, dropId);
	}

	@Override
	public void setIsDestroyed(boolean isDestroyed) {
		// do nothing
	}

	@Override
	protected void loadImage() {
		ImageIcon ii = new ImageIcon("Resource/Sprites/Bricks/unbreakable.png");
		image = ii.getImage();
	}

}
