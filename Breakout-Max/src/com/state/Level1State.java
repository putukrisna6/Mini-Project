package com.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import com.main.Commons;
import com.main.Main;
import com.map.Background;
import com.sprite.*;

public class Level1State extends State {
	private boolean inGame = true;
	private int score = 0;
	private String message = "Game Over";
	Background bg;
	
//	Game Sprite
	private Ball ball;
	private Paddle paddle;
	private Brick[] bricks;
	
	public Level1State(StateManager sm) {
		super(sm);
		try {
			bg = new Background("/Backgrounds/levelbg.png", 1);
			bg.setVector(0, 0);
//			
//			titleFont = new Font("04b", Font.PLAIN, 24);
//			levelFont = new Font("Minecraftia", Font.PLAIN, 14);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		bricks = new Brick[Commons.N_OF_BRICKS];
		
		ball = new Ball();
		paddle = new Paddle();
		
		int k = 0;
		for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[k] = new Brick(
                				j * 40 + 30, 
                				i * 10 + 50
                			);
                k++;
            }
        }
	}

	@Override
	public void init() {}
	@Override
	public void update() {
		doGameCycle();
	}
	@Override
	public void draw(Graphics2D g2d) {
		bg.draw(g2d);
		
		g2d.setRenderingHint(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON
                );
        g2d.setRenderingHint(
        			RenderingHints.KEY_RENDERING,
        			RenderingHints.VALUE_RENDER_QUALITY
        		);
        
        if (inGame) {
        	drawObjects(g2d);
        }
        else {
        	gameFinished(g2d);
        }
	}
	
	private void drawObjects(Graphics2D g2d) {
		g2d.drawImage(
				ball.getImage(), 
				ball.getX(), 
				ball.getY(),
				ball.getImageWidth(),
				ball.getImageHeight(), 
				Main.panel
			);
		g2d.drawImage(
				paddle.getImage(), 
				paddle.getX(), 
				paddle.getY(),
                paddle.getImageWidth(), 
                paddle.getImageHeight(), 
                Main.panel
			);
		
		for (int i = 0; i < Commons.N_OF_BRICKS; i++) {
			if (!bricks[i].getIsDestroyed()) {
				g2d.drawImage(bricks[i].getImage(), bricks[i].getX(),
	                        bricks[i].getY(), bricks[i].getImageWidth(),
	                        bricks[i].getImageHeight(), null);
			}
		}
		
		 g2d.setColor(Color.GRAY);
	        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
			g2d.drawString(String.valueOf(score), 10, 30);
		
	}
	private void gameFinished(Graphics2D g2d) {
		Font font = new Font("04b", Font.PLAIN, 24);
		FontMetrics fontMetrics = g2d.getFontMetrics(font);

        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString(message,
                (Commons.WIDTH - fontMetrics.stringWidth(message)) / 2,
                Commons.WIDTH / 2);
        
        Font bottomFont = new Font("Minecraftia", Font.PLAIN, 10);
        fontMetrics = g2d.getFontMetrics(bottomFont);
        
        g2d.setColor(Color.BLACK);
        g2d.setFont(bottomFont);
        g2d.drawString("Press Enter to Continue",
                (Commons.WIDTH - fontMetrics.stringWidth("Press Enter to Continue")) / 2,
                30 + Commons.WIDTH / 2);
	}

	@Override
	public void keyPressed(int k) {
		paddle.keyPressed(k);
		
		if (!inGame) {
			if (k == KeyEvent.VK_ENTER) {
				sm.setState(StateManager.SELECTSTATE);
			}
		}
	}
	@Override
	public void keyReleased(int k) {
		paddle.keyReleased(k);
	}
	
	private void doGameCycle() {
		ball.move();
		paddle.move();
		checkCollision();
	}
	private void stopGame() {
		inGame = false;
	}
	private void checkCollision() {
		  if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) {
	            stopGame();
	        }

	        for (int i = 0, j = 0; i < Commons.N_OF_BRICKS; i++) {

	            if (bricks[i].getIsDestroyed()) {

	                j++;
	            }

	            if (j == Commons.N_OF_BRICKS) {
	                message = "Victory";
	                stopGame();
	            }
	        }

	        if ((ball.getRect()).intersects(paddle.getRect())) {

	            int paddleLPos = (int) paddle.getRect().getMinX();
	            int ballLPos = (int) ball.getRect().getMinX();

	            int first = paddleLPos + 8;
	            int second = paddleLPos + 16;
	            int third = paddleLPos + 24;
	            int fourth = paddleLPos + 32;

	            if (ballLPos < first) {

	                ball.setXDir(-1);
	                ball.setYDir(-1);
	            }

	            if (ballLPos >= first && ballLPos < second) {

	                ball.setXDir(-1);
	                ball.setYDir(-1 * ball.getYDir());
	            }

	            if (ballLPos >= second && ballLPos < third) {

	                ball.setXDir(0);
	                ball.setYDir(-1);
	            }

	            if (ballLPos >= third && ballLPos < fourth) {

	                ball.setXDir(1);
	                ball.setYDir(-1 * ball.getYDir());
	            }

	            if (ballLPos > fourth) {

	                ball.setXDir(1);
	                ball.setYDir(-1);
	            }
	        }

	        for (int i = 0; i < Commons.N_OF_BRICKS; i++) {
	            if ((ball.getRect()).intersects(bricks[i].getRect())) {

	                int ballLeft = (int) ball.getRect().getMinX();
	                int ballHeight = (int) ball.getRect().getHeight();
	                int ballWidth = (int) ball.getRect().getWidth();
	                int ballTop = (int) ball.getRect().getMinY();

	                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
	                Point pointLeft = new Point(ballLeft - 1, ballTop);
	                Point pointTop = new Point(ballLeft, ballTop - 1);
	                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

	                if (!bricks[i].getIsDestroyed()) {
	                    if (bricks[i].getRect().contains(pointRight)) {
	                        ball.setXDir(-1);
	                    } 
	                    else if (bricks[i].getRect().contains(pointLeft)) {
	                    	ball.setXDir(1);
	                    }
	                    if (bricks[i].getRect().contains(pointTop)) {
	                        ball.setYDir(1);
	                    } 
	                    else if (bricks[i].getRect().contains(pointBottom)) {
	                    	ball.setYDir(-1);
	                    }
	                    bricks[i].setIsDestroyed(true);
	                    score++;
	                }
	            }
	        }
	}
}