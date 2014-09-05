package com.brian.Stick.graphics;

import java.util.Random;

import com.brian.Stick.entity.projectile.Projectile;
import com.brian.Stick.level.tile.PathTile;
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

	public void renderTile(int xp, int yp, Tile tile) {
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
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
				int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	// direction should use letters NESW
	@SuppressWarnings("unused")
	public void renderPath(int xp, int yp) {
		for (int y = 0; y < Tile.grass.sprite.SIZE; y++) {//y is usually 16; sprite size
			int ya = y + yp;
			for (int x = 0; x < Tile.grass.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -Tile.grass.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = Tile.grass.sprite.pixels[x + y * Tile.grass.sprite.SIZE];
			}
		}// renders the grass block in preparation to render path
		if (false) switch ("/*PathTile.getDirection()*/") {
			case "NS":
				for (int y = 0; y < Tile.grass.sprite.SIZE; y++) {
					int ya = y + yp;
					for (int x = 0; x < Tile.grass.sprite.SIZE; x++) {
						int xa = x + xp;
						if (xa < -Tile.grass.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
						if (xa < 0) xa = 0;
						pixels[xa + ya * width] = Tile.grass.sprite.pixels[x + y * Tile.grass.sprite.SIZE];
					}
				}
				break;
			case "EW":
				break;
			case "NE":
				break;
			case "NW":
				break;
			case "SE":
				break;
			case "SW":
				break;
			default:

		}
	}

	public void renderButton(int xp, int yp, Sprite sprite) {
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * 32];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	/*
	 * public void setOffset(int xOffset, int yOffset) { this.xOffset = xOffset;
	 * this.yOffset = yOffset; }
	 */

}
