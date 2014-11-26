package com.brian.Stick.level.button;

import com.brian.Stick.graphics.Screen;
import com.brian.Stick.graphics.Sprite;
import com.brian.Stick.input.Mouse;
import com.brian.Stick.level.Image;

public class Button extends Image {

	public int x, y;
	public Sprite sprite;
	public Sprite spriteDepressed;

	public static Button buttonStart = new StartButton(Sprite.buttonStart, Sprite.buttonStartDepressed);

	public Button(Sprite sprite, Sprite spriteDepressed) {
		this.sprite = sprite;
		this.spriteDepressed = spriteDepressed;
		clickable = true;
	}

	public void render(int x, int y, Screen screen, Mouse mouse) {
		if (Mouse.getX() >= x && Mouse.getX() <= x + sprite.width && Mouse.getY() >= y && Mouse.getY() <= y + sprite.height) screen
				.renderButton(x << 4, y << 4, sprite);
		else screen.renderButton(x << 4, y << 4, spriteDepressed);
	}

}
