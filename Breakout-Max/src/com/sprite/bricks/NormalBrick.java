package com.sprite.bricks;

import javax.swing.ImageIcon;

import com.sprite.Brick;

public class NormalBrick extends Brick {

	public NormalBrick(int x, int y) {
		super(x, y);
	}

	@Override
	protected void loadImage() {
		ImageIcon ii = new ImageIcon("Resource/Sprites/Bricks/normal.png");
		image = ii.getImage();		
	}

}
