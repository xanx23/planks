package br.com.planks.game.sprites;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends Sprite {
	
	private Color color;
	
	private Integer size;

	private Double xSpeed = 3.0;

	private Double ySpeed = 3.0;
	
	private Integer actualSpeed;

	public Ball(Color c, Integer size, Integer x, Integer y, Double xSpeed, Double ySpeed, Integer actualSpeed) {
		this.color = c;
		this.size = size;
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.actualSpeed = actualSpeed;
	}

	public Ball(Color color, Integer size) {
		this(color, size, 0, 0, 1d, 1d, 6);
	}
	
	public Ball() {
		this(Color.WHITE, 50);
	}
	
	protected Integer getSize() {
		return size;
	}

	protected void setSize(Integer size) {
		this.size = size;
	}

	public void setPosition(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	public Boolean isReadyToDraw() {
		return !(x == null || y == null || xSpeed == null || ySpeed == null);
	}
	
	public void setDirection(Double xSpeed, Double ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	@Override
	public void draw(Graphics g, Integer viewportWidth, Integer viewportHeight) {
		
		if(!isVisible()) {
			return;
		}
		
		assert isReadyToDraw() : "All ball properties must be set before drawing!";
		
		g.setColor(color);
		g.fillOval(x, y, size, size);	
	}

	/**
	 * Moves and then check for collisions. If there is collision, change the direction.
	 * @param allObjects
	 * @param viewportWidth
	 * @param viewportHeight
	 */
	public void move(Sprite[] allObjects, Integer viewportWidth, Integer viewportHeight) {
		findActualSpeed();
		x += xSpeed.intValue();
		y += ySpeed.intValue();
		if (x <= 0) {
			xSpeed = Math.abs(xSpeed);
			x = 0;
		}
		if (x >= viewportWidth - (size + 1)) {
			xSpeed = -Math.abs(xSpeed);
			x = viewportWidth - (size + 1);
		}
		if (y <= 0) {
			ySpeed = Math.abs(ySpeed);
			y = 0;
		}
		if (y >= viewportHeight - (size + 1)) {
			ySpeed = -Math.abs(ySpeed);
			y = viewportHeight - (size + 1);
		}
		
		checkForCollisions(allObjects);
	}

	private void findActualSpeed() {
		double aux = sqrt((pow(xSpeed, 2) * pow(actualSpeed, 2)) / (pow(xSpeed, 2) + pow(ySpeed, 2)));
		aux = xSpeed > 0 ? aux : -aux;
		ySpeed = (ySpeed * aux) / xSpeed;
		xSpeed = aux;
	}

	@Override
	protected void checkForCollisions(Sprite[] allObjects) {
		
	}
	
	@Override
	public Rectangle getSurroundingRectangle() {
		return new Rectangle(x, y, size, size);
	}

	protected void sendTop() {
		ySpeed = -Math.abs(ySpeed);
	}
	
	protected void sendBottom() {
		ySpeed = Math.abs(ySpeed);
	}
	
	protected void sendRight() {
		xSpeed = Math.abs(xSpeed);
	}
	
	protected void sendLeft() {
		xSpeed = -Math.abs(xSpeed);
	}
}