package br.com.planks.game.sprites;


import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Sprite {
	
	protected Integer x;
	
	protected Integer y;
	
	protected Boolean visible;
	
	public Sprite() {
		setVisible(true);
	}

	public abstract void draw(Graphics g, Integer viewportWidth, Integer viewportHeight);
	
	public abstract Rectangle getSurroundingRectangle();
	
	public boolean isCollidedWith(Sprite anotherSprite) {
		if(this == anotherSprite) {
			return false;
		}
		if(!anotherSprite.isVisible()) {
			return false;
		}
		return getSurroundingRectangle().intersects(anotherSprite.getSurroundingRectangle());
	}

	protected abstract void checkForCollisions(Sprite[] allObjects);

	public Boolean isVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}