package com.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import com.main.Commons;
import com.main.Main;
import com.map.Background;
import com.sprite.*;
import com.sprite.drops.*;

public class LevelState extends State {
	
//	Attributes for basic level stuffs
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
	
//	List of buffs
	HashSet<String> effects;
	String dropEffect;
	
//	Constructor
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
		drops = new ArrayList<Drop>();
		
		dropEffect = null;
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
	
	// draw every sprite	
	private void drawObjects(Graphics2D g2d) {
		// draw ball		
		g2d.drawImage(
				ball.getImage(), 
				ball.getX(), 
				ball.getY(),
				ball.getImageWidth(),
				ball.getImageHeight(), 
				Main.panel
			);
		// draw paddle		
		g2d.drawImage(
				paddle.getImage(), 
				paddle.getX(), 
				paddle.getY(),
                paddle.getImageWidth(), 
                paddle.getImageHeight(), 
                Main.panel
			);
		
		// draw bricks		
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
		
		// draw drops		
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
		
		// draw score string		
		g2d.setColor(Color.GRAY);
	    g2d.setFont(new Font("Verdana", Font.BOLD, 30));
	    g2d.drawString(String.valueOf(score), 10, 30);
		
	    // draw collect drop notification
	    g2d.setColor(Color.BLACK);
	    Font notifFont = new Font("Minecraftia", Font.PLAIN, 15);
	    g2d.setFont(notifFont);
		FontMetrics fontMetrics = g2d.getFontMetrics(notifFont);
	    try {
	    	// don't draw anything if there's nothing to draw	    	
	    	if (dropEffect != null) {
	    		g2d.drawString(
	    						dropEffect, 
	    						(Commons.WIDTH - fontMetrics.stringWidth(dropEffect)) / 2,
	    						Commons.WIDTH / 2);
	    	}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	// if the game is done, draw these strings	
	private void gameFinished(Graphics2D g2d) {
		// Attributes to help to draw the message string
		Font font = new Font("04b", Font.PLAIN, 24);
		FontMetrics fontMetrics = g2d.getFontMetrics(font);
        // draw the string
		g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString(message,
                (Commons.WIDTH - fontMetrics.stringWidth(message)) / 2,
                Commons.WIDTH / 2);
        
        // draw the little message below the main message
        Font bottomFont = new Font("Minecraftia", Font.PLAIN, 10);
        fontMetrics = g2d.getFontMetrics(bottomFont);
        g2d.setColor(Color.BLACK);
        g2d.setFont(bottomFont);
        fontMetrics = g2d.getFontMetrics(bottomFont);
        String cont = "Press Enter to Continue";
        g2d.drawString(cont,
                (Commons.WIDTH - fontMetrics.stringWidth(cont)) / 2,
                30 + Commons.WIDTH / 2);
	}

	// handle keyboard inputs
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
	// check collision between paddle, ball, bricks, and the wall	
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
	    			
	    			// TODO - Future implementation won't have a random chance of getting buff/debuff
	    			Random rand = new Random();
	    			int randInt = rand.nextInt(1000);

	    			if (randInt % 2 == 0) {
	    				drops.add(new Buff(bricks[i].getX() + 10, bricks[i].getY()));
	    			}
	    			else {
	    				drops.add(new Debuff(bricks[i].getX() + 10, bricks[i].getY()));
	    			}
	    		}
	    	}
	    }
	}
	// handles everything from making the drop move, disappear, then when the drop is "collected"	
	private void updateDrops() {
		for (int i = 0; i < drops.size(); i++) {
			Drop drop = drops.get(i);
			
			boolean isGood;
			int effect;
			
			// if the paddle intersects with the drop, "collect" it			
			if (drop.getRect().intersects(paddle.getRect())) {
				if (drop instanceof Buff) {
					isGood = true;
				}
				else {
					isGood = false;
				}
				effect = drop.getEffect();
				
				fetchEffect(isGood, effect);
				
				// wait for 800 millisecond (I think it is 800 millisecond)
				// afterwards set the string back to null so it is no longer visible
				// then stop the timer
				new Timer(800, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	dropEffect = null;
		            	((Timer)e.getSource()).stop();
		            }
		        }).start();
				// don't forget to make the drop disappear
				drop.setVisible(false);
			}
			
			// if the drop is still visible, make it move
			// otherwise, remove the object
			if (drop.isVisible()) {
				drop.move();
			}
			else {
				drops.remove(i);
			}
		}
	}
	private void fetchEffect(boolean isGood, int effect) {
		if (isGood) {
			switch (effect) {
			case BuffList.DOUBLE_PAD_LENGTH: 
				dropEffect = "Longer Paddle!";
				paddle.setToLong();
				return;
			case BuffList.DOUBLE_SCORE:
				// TODO - Hadeh mager banget implementasi timer awokwaokaw
				dropEffect = "Double Score for 5 seconds!";
				return;
			case BuffList.DOUBLE_BALLS:
				dropEffect = "Double Balls!";
				return;
			case BuffList.DOUBLE_PAD_SPEED:
				dropEffect = "Move Faster!";
				paddle.setMoveSpeed(2);
				return;
			default:
				throw new IllegalArgumentException("Unexpected value: " + effect);
			}
		}
		else {
			switch (effect) {
			case DebuffList.HALF_PAD_LENGTH:
				dropEffect = "Chopped Paddle...";
				paddle.setToShort();
				return;
			case DebuffList.HALF_BALL_SPEED:
				dropEffect = "Snail Speed...";
				return;
			case DebuffList.INVERTED_PAD_MOVE:
				dropEffect = "Drunk...";
				return;
			case DebuffList.HALF_PAD_SPEED:
				dropEffect = "Slowed Down...";
				paddle.setMoveSpeed(0.5);
				return;
			default:
				throw new IllegalArgumentException("Unexpected value: " + effect);
			}
		}
	}
}