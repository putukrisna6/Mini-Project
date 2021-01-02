package com.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.main.Commons;

public class Background {
	private BufferedImage image;

	private double x;
	private double y;
	private double dx;
	private double dy;

	private double moveScale;

//	Constructor
	public Background(String s, double ms) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

//	getters and setters
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % Commons.WIDTH;
		this.y = (y * moveScale) % Commons.HEIGHT;
	}

	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void update() {
		x += dx;
		y += dy;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int) x, (int) y, null);

		if (x < 0) {
			g2d.drawImage(image, (int) x + Commons.WIDTH, (int) y, null);
		}
		if (x > 0) {
			g2d.drawImage(image, (int) x - Commons.WIDTH, (int) y, null);
		}
	}
}
