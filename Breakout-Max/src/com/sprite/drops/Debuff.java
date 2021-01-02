package com.sprite.drops;

import javax.swing.ImageIcon;

import com.sprite.Drop;

public class Debuff extends Drop {
	public Debuff(int x, int y) {
		super(x, y);
	}

	@Override
	protected void loadImage() {
		ImageIcon ii = new ImageIcon("Resource/Sprites/Drops/drop-bad.png");
		image = ii.getImage();
	}
}
