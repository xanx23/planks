package br.com.planks;

import java.awt.Canvas;

import javax.swing.JFrame;

import br.com.planks.game.GameKeyListener;
import br.com.planks.game.GameThread;

public class PlanksFrame extends JFrame {

	private static final long serialVersionUID = 3944070327799633376L;
	
	private Canvas canvas;
	
	private GameKeyListener keyListener;

	public PlanksFrame() {
		initComponent();
		prepareDrawingCanvas();
		startGame();
	}

	private void initComponent() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		keyListener = new GameKeyListener();
	}

	private void prepareDrawingCanvas() {		
		canvas = new Canvas();
		canvas.setSize(1000, 550);
		canvas.setIgnoreRepaint(true);
		canvas.addKeyListener(keyListener);
		
		add(canvas);
		pack();
		setLocationRelativeTo(null);
		canvas.createBufferStrategy(2);
	}

	private void startGame() {
		Thread thread = new GameThread(canvas, keyListener);
		thread.start();
	}
}