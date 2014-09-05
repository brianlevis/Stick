package com.brian.Stick.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite path = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite pathNS = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite pathEW = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite pathSE = new Sprite(16, 2, 1, SpriteSheet.tiles);
	public static Sprite pathSW = new Sprite(16, 3, 1, SpriteSheet.tiles);
	public static Sprite pathNE = new Sprite(16, 4, 1, SpriteSheet.tiles);
	public static Sprite pathNW = new Sprite(16, 5, 1, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColour(color);
	}

	private void setColour(int colour) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = colour;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
