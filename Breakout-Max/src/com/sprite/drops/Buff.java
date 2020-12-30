package com.sprite.drops;

import javax.swing.ImageIcon;

import com.sprite.Drop;

public class Buff extends Drop {
	public Buff(int x, int y) {
		super(x, y);
	}

	@Override
	protected void loadImage() {
		ImageIcon ii = new ImageIcon("Resource/Sprites/Drops/drop-good.png");
		image = ii.getImage();
	}
}
