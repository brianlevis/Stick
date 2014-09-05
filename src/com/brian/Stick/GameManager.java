package com.brian.Stick;

import java.util.ArrayList;
import java.util.List;

import com.brian.Stick.graphics.Screen;
import com.brian.Stick.level.Level;

public class GameManager {

	private List<Level> levels = new ArrayList<Level>();
	private String[] levelList = {"00Menu", "01Valley"};
	
	public GameManager() {
		loadLevels();
	}
	
	public void loadLevels(){
		for(int i = 0; i < levelList.length; i++) levels.add(new Level("/levels/" + levelList[i] + ".png"));
	}

	public void update() {		
	}
	
	public void render(Screen screen) {
		for(Level level : levels) level.render(screen); 
	}

}
