package com.brian.Stick.level.tile;

import com.brian.Stick.graphics.Screen;
import com.brian.Stick.graphics.Sprite;

public class RegularTile extends Tile {

	public RegularTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}
