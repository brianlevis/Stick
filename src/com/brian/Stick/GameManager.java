package com.brian.Stick;

import java.util.ArrayList;
import java.util.List;

import com.brian.Stick.graphics.Screen;
import com.brian.Stick.level.Level;

public class GameManager {

	private List<Level> levels = new ArrayList<Level>();
	private String[] levelList = { "00Menu", "01Valley" };

	public GameManager(Screen screen) {
		loadLevels(screen);
	}

	public void loadLevels(Screen screen) {
		//levels.add(new Level("/levels/" + levelList[0] + ".png", screen));
		for (int i = 1; i < levelList.length; i++)
			levels.add(new Level("/levels/" + levelList[i] + ".png", screen));
	}

	public void startMenu() {
		
	}

	public void startLevel() {
		
	}

	public void update() {
	}
	
	public void render(Screen screen) {
		for (Level level : levels)
			level.render(screen);
	}

}
