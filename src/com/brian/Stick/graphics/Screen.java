package com.brian.Stick.graphics;

import java.util.Random;

import com.brian.Stick.entity.projectile.Projectile;
import com.brian.Stick.level.Level;
import com.brian.Stick.level.tile.Tile;

public class Screen {

	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	// public int xOffset, yOffset;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private Random random = new Random();
	
	

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; // 0 - 50,399 = 50,400

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
			tiles[0] = 0;
		}

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	
	//this method is to be called on every tile ONCE per level
	public void renderTile(int xp, int yp, Tile tile, Level level) {
		for (int y = 0; y < tile.sprite.height; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.width; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.width || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				level.currentMapPixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.width];
			}
		}
	}
	
	public void renderLevel(Level level) {
		pixels = level.currentMapPixels.clone();
	}

	public void renderProjectile(int xp, int yp, Projectile p) {
		// xp -= xOffset;
		// yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSprite().height];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderButton(int xp, int yp, Sprite button) {
		for (int y = 0; y < button.height; y++) {
			int ya = y + yp;
			for (int x = 0; x < button.width; x++) {
				int xa = x + xp;
				//if (xa < -button.width || xa >= width || ya < 0 || ya >= height) break;
				//if (xa < 0) xa = 0;
				int col = button.pixels[x + y * button.width];
				if (col != 0xffff00ff) 
					pixels[xa + ya * width] = col;
			}
		}
	}

	/*
	 * public void setOffset(int xOffset, int yOffset) { this.xOffset = xOffset;
	 * this.yOffset = yOffset; }
	 */

}
