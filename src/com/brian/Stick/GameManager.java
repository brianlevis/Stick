package com.brian.Stick;

import java.util.ArrayList;
import java.util.List;

import com.brian.Stick.graphics.Screen;
import com.brian.Stick.input.Keyboard;
import com.brian.Stick.input.Mouse;
import com.brian.Stick.level.Level;
import com.brian.Stick.level.button.Button;
import com.brian.Stick.level.button.StartButton;

public class GameManager {

	private List<Level> levels = new ArrayList<Level>();
	private String[] levelList = { "00Valley", "01Cliffs" };
	private Button buttonStart = new StartButton();

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
		System.out.println("Starting \"" + level.name + "\" level...");

	}

	public void update() {
		if (!started) started = buttonStart.update();
		else {
			// errything else
		}
		
	}

	public void render() {
		levels.get(currentLevel).render(screen);
		if (!started) 
			buttonStart.render(screen, mouse);
		else {
			// errything else
		}
	}

}
