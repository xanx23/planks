package br.com.planks.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

	private Boolean movingTop;
	private Boolean movingBottom;
	private Boolean movingLeft;
	private Boolean movingRight;

	public GameKeyListener() {
		movingTop = false;
		movingBottom = false;
		movingLeft = false;
		movingRight = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			movingTop = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			movingBottom = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			movingLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			movingRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			movingTop = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			movingBottom = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			movingLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			movingRight = false;
		}
	}

	protected Boolean isMovingTop() {
		return movingTop;
	}

	protected Boolean isMovingBottom() {
		return movingBottom;
	}

	protected Boolean isMovingLeft() {
		return movingLeft;
	}

	protected Boolean isMovingRight() {
		return movingRight;
	}
}