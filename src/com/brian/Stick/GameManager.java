package com.brian.Stick;

import java.util.ArrayList;
import java.util.List;

import com.brian.Stick.graphics.Screen;
import com.brian.Stick.input.Keyboard;
import com.brian.Stick.input.Mouse;
import com.brian.Stick.level.Level;

public class GameManager {

	private List<Level> levels = new ArrayList<Level>();
	private String[] levelList = { "00Valley", "01Cliffs" };
	
	private int currentLevel = 0;
	private Screen screen;
	private Keyboard keyboard;
	private Mouse mouse;
	
	private boolean started;
	
	public GameManager(Screen screen, Keyboard keyboard, Mouse mouse) {
		this.screen = screen;
		this.keyboard = keyboard;
		this.mouse = mouse;
		
		loadLevels();
		startLevel(levels.get(0));
	}
	
	public void loadLevels() {
		for (int i = 0; i < levelList.length; i++)
			levels.add(new Level("/levels/" + levelList[i] + ".png", screen));
	}

	public void startLevel(Level level) {
		started = false;
		System.out.println("Starting level");
	}

	public void update() {
		
	}
	
	public void render() {
		levels.get(currentLevel).render(screen);
		//levels.get(currentLevel).rend
	}

}
