package com.brian.Stick;

import java.util.ArrayList;
import java.util.List;

import com.brian.Stick.graphics.Screen;
import com.brian.Stick.level.Level;
import com.brian.Stick.level.Start;

public class GameManager {

	private List<Level> levels = new ArrayList<Level>();
	private String[] levelList = { "00Menu", "01Valley" };
	
	private int currentLevel = 0;
	private Screen screen;
	
	public GameManager(Screen screen) {
		this.screen = screen;
		startMenu();
		loadLevels();
	}

	public void startMenu() {
		levels.add(new Start("/levels/" + levelList[0] + ".png", screen));
	}
	
	public void loadLevels() {
		for (int i = 1; i < levelList.length; i++)
			levels.add(new Level("/levels/" + levelList[i] + ".png", screen));
	}

	public void startLevel() {
		
	}

	public void update() {
		
	}
	
	public void render() {
		levels.get(currentLevel).render(screen);
	}

}
