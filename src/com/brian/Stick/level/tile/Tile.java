package com.brian.Stick.level.tile;

import com.brian.Stick.graphics.Screen;
import com.brian.Stick.graphics.Sprite;
import com.brian.Stick.level.Level;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	public boolean solid = false;
	
	public static Tile grass = new Tile(Sprite.grass);
	public static Tile flower = new Tile(Sprite.flower);
	public static Tile rock = new SolidTile(Sprite.rock);
	public static Tile pathNS = new PathTile(Sprite.pathNS);
	public static Tile pathEW = new PathTile(Sprite.pathEW);
	public static Tile pathSE = new PathTile(Sprite.pathSE);
	public static Tile pathSW = new PathTile(Sprite.pathSW);
	public static Tile pathNE = new PathTile(Sprite.pathNE);
	public static Tile pathNW = new PathTile(Sprite.pathNW);
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public final static int col_grass = 0xff00ff21;
	public final static int col_flower = 0xffffd800;
	public final static int col_rock = 0xff808080;
	public final static int col_pathNS = 0xff000000;
	public final static int col_pathNE = 0xff000001;
	public final static int col_pathEW = 0xff000002;
	public final static int col_pathSE = 0xff000003;
	public final static int col_pathSW = 0xff000004;
	public final static int col_pathNW = 0xff000005;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen, Level level) {
		screen.renderTile(x << 4, y << 4, this, level);
	}

}
