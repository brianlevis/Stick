package com.brian.Stick.level.button;

import com.brian.Stick.Game;
import com.brian.Stick.graphics.Screen;
import com.brian.Stick.graphics.Sprite;
import com.brian.Stick.input.Mouse;
import com.brian.Stick.level.Image;

public class Button extends Image {

	public int x, y;
	public Sprite sprite;
	public Sprite spriteDepressed;
	private boolean depressed = false;
	private boolean clicked = false;

	public Button(int x, int y, Sprite sprite, Sprite spriteDepressed) {
		this.x = x << 4;
		this.y = y << 4;
		this.sprite = sprite;
		this.spriteDepressed = spriteDepressed;
		clickable = true;
	}

	public void render(Screen screen, Mouse mouse) {
		if (depressed) screen.renderButton(x, y, spriteDepressed);
		else screen.renderButton(x, y, sprite);
	}

	public boolean update() {
		depressed = Mouse.getX() >= x && Mouse.getX() <= x + sprite.width && Mouse.getY() >= y
				&& Mouse.getY() <= y + sprite.height;
		clicked = depressed && Mouse.getButton() != -1;
		return clicked;
	}
}
