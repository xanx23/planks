package br.com.planks.game;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import br.com.planks.game.sprites.Ball;
import br.com.planks.game.sprites.Plank;
import br.com.planks.game.sprites.Sprite;

public class GameThread extends Thread {
	
	private Canvas canvas;

	private BufferStrategy strategy;
	
	private GameKeyListener keyListener;

	public GameThread(Canvas canvas, GameKeyListener keyListener) {
		this.canvas = canvas;
		this.keyListener = keyListener;
		this.strategy = canvas.getBufferStrategy();
		
		canvas.requestFocus();
	}

	@Override
	public void run() {
		while (true) {
			refresh();
		}
	}
	
	private void refresh() {
		Sprite allSprites[] = new Sprite[2];
		Ball ball = new Ball();
		Plank plank = new Plank();
		allSprites[0] = ball;
		allSprites[1] = plank;
		
		while (true) {		
			Graphics g = getNextGraphicsBuffer();
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());	
			
			ball.move(null, canvas.getWidth(), canvas.getHeight());
			ball.draw(g, canvas.getWidth(), canvas.getHeight());
			
			plank.move(canvas.getWidth(), canvas.getHeight(), keyListener.isMovingTop(), keyListener.isMovingBottom(), keyListener.isMovingLeft(), keyListener.isMovingRight());
			plank.checkForCollisions(allSprites);
			plank.draw(g, canvas.getWidth(), canvas.getHeight());
			
			g.setColor(Color.RED);
			
			g.dispose();
			strategy.show();
			
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Graphics2D getNextGraphicsBuffer() {
		Graphics2D graphics = (Graphics2D) strategy.getDrawGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		return graphics;
	}
}