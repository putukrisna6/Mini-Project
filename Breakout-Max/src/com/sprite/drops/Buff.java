package com.sprite.drops;

import javax.swing.ImageIcon;

import com.map.AudioPlayer;
import com.sprite.Drop;

public class Buff extends Drop {
	public Buff(double x, double y) {
		super(x, y);
	}

	@Override
	protected void loadImage() {
		ImageIcon ii = new ImageIcon("Resource/Sprites/Drops/drop-good.png");
		image = ii.getImage();
	}

	@Override
	protected void setSFX() {
		picked = new AudioPlayer("/Music/pickgoodalt.wav");
	}
}
