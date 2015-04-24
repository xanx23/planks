package br.com.planks;

import java.awt.EventQueue;

public class Planks {

	public static void main(String[] args) {
		EventQueue.invokeLater(new PlanksRunnable());
	}

	private static class PlanksRunnable implements Runnable {

		public void run() {
			PlanksFrame frame = new PlanksFrame();
			frame.setVisible(true);
		}
	}
}