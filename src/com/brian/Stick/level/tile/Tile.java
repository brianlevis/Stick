package com.brian.Stick.level.tile;

import com.brian.Stick.graphics.Screen;
import com.brian.Stick.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new RegularTile(Sprite.grass);
	public static Tile flower = new RegularTile(Sprite.flower);
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
	public final static int col_path = 0xff000000;
	//Everything below this point is condemned to die
	public final static int col_pathNS = 0xff020202;
	public final static int col_pathEW = 0xff000000;
	public final static int col_pathSE = 0xff050505;
	public final static int col_pathSW = 0xff070707;
	public final static int col_pathNE = 0xff0A0A0A;
	public final static int col_pathNW = 0xff0C0C0C;
	//End list of the condemned
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
	}
	
	public boolean solid() {
		return false;
	}

}
