package br.com.planks.game.sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import br.com.planks.resources.GameResources;

public class Plank extends Sprite {
	
	private Image background;
	
	private Color color;
	
	private Integer height;
	
	private Integer width;

	public Plank(Color color, Integer height, Integer width) {
		this.color = color;
		this.height = height;
		this.width = width;
		
		x = 0;
		y = 0;
		
		background = GameResources.plankBackground();
	}

	public Plank() {
		this(Color.GRAY, 150, 50);
	}

	@Override
	public void draw(Graphics g, Integer viewportWidth, Integer viewportHeight) {
		if (x < 0) {
			x = 0;
		} else if (x > viewportWidth - (width + 1)) {
			x = viewportWidth - (width + 1);
		}
		
		if (y < 0) {
			y = 0;
		} else if (y > viewportHeight - (height + 1)) {
			y = viewportHeight - (height + 1);
		}
		
		if(background != null) {
			g.drawImage(background, x, y, width, height, null);
		} else {
			g.setColor(color);
			g.fillRect(x, y, width, height);
		}
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}

	public void move(Integer viewportWidth, Integer viewportHeight, Boolean top, Boolean bottom, Boolean left, Boolean right) {	
		if(left && x > 0 && !right) {
			x -= 3;
		}
		
		if(right && x < viewportWidth - (width + 1) && !left) {
			x += 3;
		}
		
		if(top && y > 0 && !bottom) {
			y -= 3;
		}
		
		if(bottom && y < viewportHeight - (height + 1) && !top) {
			y += 3;
		}
	}

	@Override
	public Rectangle getSurroundingRectangle() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void checkForCollisions(Sprite[] allObjects) {
		for (Sprite another : allObjects) {
			if (isCollidedWith(another) && another instanceof Ball) {
				Ball ball = (Ball) another;
				if (isTopBorderCollision(another)) {
					ball.sendTop();
				} else if (isBottomBorderCollision(another)) {
					ball.sendBottom();
				}
				if (isLeftBorderCollision(another)) {
					ball.sendLeft();
				} else if (isRightBorderCollision(another)) {
					ball.sendRight();
				}
			}
		}
	}
	
	private boolean isTopBorderCollision(Sprite another) {
		Rectangle top = new Rectangle(x, y, width, 1);
		return top.intersects(another.getSurroundingRectangle());
	}

	private boolean isBottomBorderCollision(Sprite another) {
		Rectangle bottom = new Rectangle(x, y + height, width, 1);
		return bottom.intersects(another.getSurroundingRectangle());
	}
	
	private boolean isLeftBorderCollision(Sprite another) {
		Rectangle left = new Rectangle(x, y, 1, height);
		return left.intersects(another.getSurroundingRectangle());
	}
	
	private boolean isRightBorderCollision(Sprite another) {
		Rectangle right = new Rectangle(x + width, y, 1, height);
		return right.intersects(another.getSurroundingRectangle());
	}
}