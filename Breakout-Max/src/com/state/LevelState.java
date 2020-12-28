package com.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.main.Commons;
import com.main.Main;
import com.map.Background;
import com.sprite.*;

public class LevelState extends State {
	
	private boolean inGame = true;
	private int score = 0;
	private String message = "Game Over";
	Background bg;
	
//	Game Sprite
	private Ball ball;
	private Paddle paddle;
	protected Brick[] bricks;
	protected int numOfBricks;
	
	List<Drop> drops;
	
	public LevelState(StateManager sm, int numOfBricks) {
		super(sm);
		try {
			bg = new Background("/Backgrounds/levelbg.png", 1);
			bg.setVector(0, 0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.numOfBricks = numOfBricks;
		
		bricks = new Brick[this.numOfBricks];
		
		ball = new Ball();
		paddle = new Paddle();
		paddle.setMoveSpeed(5);
		drops = new ArrayList<Drop>();
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
		
		for (int i = 0; i < numOfBricks; i++) {
			if (!bricks[i].getIsDestroyed()) {
				g2d.drawImage(
							bricks[i].getImage(), 
							bricks[i].getX(),
							bricks[i].getY(), 
							bricks[i].getImageWidth(),
							bricks[i].getImageHeight(), 
							null
	                    );
			}
		}
		
		try {
			for (Drop drop : drops) {
				g2d.drawImage(
						drop.getImage(), 
						drop.getX(), 
						drop.getY(), 
						drop.getImageHeight(), 
						drop.getImageWidth(), 
						Main.panel);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
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
		if (inGame) {
			if (k == KeyEvent.VK_ESCAPE) {
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
		updateDrops();
	}
	private void stopGame() {
		inGame = false;
	}
	private void checkCollision() {
		if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) {
			stopGame();
	    }

	    for (int i = 0, j = 0; i < numOfBricks; i++) {
	    	if (bricks[i].getIsDestroyed()) {
	    		j++;
	        }

	    	if (j == numOfBricks) {
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
	    
	    for (int i = 0; i < numOfBricks; i++) {
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
	    			
	    			// trying out the Random class
	    			// to be improved upon to implement the random boon/curse mechanism
//	    			Random rand = new Random();
//	    			int randInt = rand.nextInt(1000);
	    			
	    			if (true) {
//	    				System.out.println("it's random! " + randInt);
	    				drops.add(new Drop(bricks[i].getX() + 10, bricks[i].getY()));
	    			}
	    		}
	    	}
	    }
	}
	private void updateDrops() {
		for (int i = 0; i < drops.size(); i++) {
			Drop drop = drops.get(i);
			
			if (drop.getRect().intersects(paddle.getRect())) {
				drop.setVisible(false);
			}
			
			if (drop.isVisible()) {
				drop.move();
			}
			else {
				drops.remove(i);
			}
		}
	}
}