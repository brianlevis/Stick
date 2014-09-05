package com.brian.Stick.level.tile;

import com.brian.Stick.graphics.Screen;
import com.brian.Stick.graphics.Sprite;

public class PathTile extends Tile {
	
	public PathTile(Sprite sprite) {
		super(sprite);
	}
	
	//public PathTile(int x, int y){

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}