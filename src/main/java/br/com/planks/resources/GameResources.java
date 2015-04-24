package br.com.planks.resources;

import java.awt.Image;

import javax.imageio.ImageIO;

public class GameResources {

	private static Image plankBackground;

	public static Image plankBackground() {
		if (plankBackground == null) {
			try {
				plankBackground = ImageIO.read(GameResources.class.getResource("plank.jpg"));
			} catch (Exception e) {

			}
		}
		return plankBackground;
	}
}