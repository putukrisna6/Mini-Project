package com.sprite.bricks;

import javax.swing.ImageIcon;

import com.sprite.Brick;

public class MultipleHitBrick extends Brick {

	private int hitCount;
	private int maxHit;

	public MultipleHitBrick(int x, int y, char dropId) {
		super(x, y, dropId);
		this.hitCount = 0;
		this.maxHit = 2;
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getMaxHit() {
		return maxHit;
	}

	public void setHitCount(int hitCount) {
		if (hitCount <= this.maxHit)
			this.hitCount = hitCount;
	}

	public void setMaxHit(int maxHit) {
		this.maxHit = maxHit;
	}

	public void crack() {
		ImageIcon cracked = new ImageIcon("Resource/Sprites/Bricks/normal.png");
		this.setImage(cracked.getImage());
	}

	@Override
	protected void loadImage() { // UNSOLVED: change to cracked
		ImageIcon ii = new ImageIcon("Resource/Sprites/Bricks/multihit.png");

		image = ii.getImage();
	}

}
