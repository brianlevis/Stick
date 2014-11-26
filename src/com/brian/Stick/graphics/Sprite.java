package com.brian.Stick.graphics;

public class Sprite {

	public final int width, height;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite buttonStart = new Sprite(256, 84, 0, 2, SpriteSheet.buttons);
	public static Sprite buttonStartDepressed = new Sprite(256, 84, 0, 7, SpriteSheet.buttons);

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite pathNS = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite pathEW = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite pathSE = new Sprite(16, 2, 1, SpriteSheet.tiles);
	public static Sprite pathSW = new Sprite(16, 3, 1, SpriteSheet.tiles);
	public static Sprite pathNE = new Sprite(16, 4, 1, SpriteSheet.tiles);
	public static Sprite pathNW = new Sprite(16, 5, 1, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		width = height = size;
		pixels = new int[width * height];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color) {
		width = height = size;
		pixels = new int[width * height];
		setColour(color);
	}

	private void setColour(int colour) {
		for (int i = 0; i < height * width; i++) {
			pixels[i] = colour;
		}
	}

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
