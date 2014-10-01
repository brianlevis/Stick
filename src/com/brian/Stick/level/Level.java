package com.brian.Stick.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.brian.Stick.entity.Entity;
import com.brian.Stick.entity.projectile.Projectile;
import com.brian.Stick.graphics.Screen;
import com.brian.Stick.level.button.Button;
import com.brian.Stick.level.tile.PathTile;
import com.brian.Stick.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	public String name;

	private int[] stickPath;

	private List<Button> buttons = new ArrayList<Button>();
	private List<Entity> entities = new ArrayList<Entity>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<PathTile> path = new ArrayList<PathTile>();
	
	public int[] currentMapPixels = new int[640 * 480];

	public Level(String path, Screen screen) {
		this.name = path.substring("/levels/".length(), path.length() - 4);
		loadLevel(path, screen);
	}

	protected void loadLevel(String path, Screen screen) {
		try {
			System.out.println("Loading level file at " + path + "...");
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
			getPath(tiles);
			loadLevelPixels(screen);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error! Could not load level file at " + path + "!");
		}
	}
	
	public void generateButton(Button button){
		//WIP
		//buttons
	}
	
	public void destroyButton(){
		
	}

	public void getPath(int[] tiles) {
		int pathTiles = 0;
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				if (tiles[x + y * width] >= Tile.col_pathNS && tiles[x + y * width] <= Tile.col_pathNW) {
					/*
					 * System.out.println("Detected path tile #" + pathTiles +
					 * " at (" + x + ", " + y + "). Type: " + tiles[x + y *
					 * width]);
					 */
					pathTiles++;
				}
		stickPath = new int[pathTiles];

		try {
			for (int y = 0; y < height; y++)
				if (tiles[y * width] >= Tile.col_pathNS && tiles[y * width] <= Tile.col_pathNW) stickPath[0] = y * width;
			System.out.println("Starting path at (" + stickPath[0] % height + ", " + stickPath[0] / width + ").");
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Error! Could not find path start!");
		}

		try {
			int[] surrounding = new int[3];
			for (int i = 0; i < 3; i++)
				surrounding[i] = stickPath[0] + width * (i - 1) + 1;
			for (int i = 0; i < 3; i++) {
				if (tiles[surrounding[i]] >= Tile.col_pathNS && tiles[surrounding[i]] <= Tile.col_pathNW) {
					stickPath[1] = surrounding[i];
					System.out.println("Continuing path to (" + stickPath[1] % width + ", " + stickPath[1] / width + ").");
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Error! Could not trace path from start to finish!");
		}
		try {
			for (int l = 2; l < stickPath.length - 1; l++) {
				int[] surrounding = new int[8];
				for (int i = 0; i < 3; i++)
					surrounding[i] = stickPath[l - 1] - width + i - 1;
				surrounding[3] = stickPath[l - 1] - 1;
				surrounding[4] = stickPath[l - 1] + 1;
				for (int i = 5; i < 8; i++)
					surrounding[i] = stickPath[l - 1] + width + i - 6;
				for (int i = 0; i < 8; i++) {
					if (tiles[surrounding[i]] >= Tile.col_pathNS && tiles[surrounding[i]] <= Tile.col_pathNW
							&& surrounding[i] != stickPath[l - 2]) {
						stickPath[l] = surrounding[i];
						System.out.println("Continuing path to (" + stickPath[l] % width + ", " + stickPath[l] / width
								+ ").");
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Error! Could not trace path from start to finish!");
		}

		try {
			for (int y = 0; y < height; y++)
				if (tiles[y * width + width - 1] >= Tile.col_pathNS && tiles[y * width + width - 1] <= Tile.col_pathNW) stickPath[0] = y
						* width;
			System.out.println("Starting path at (" + stickPath[stickPath.length - 1] % height + ", "
					+ stickPath[stickPath.length - 1] / width + ").");
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Error! Could not find path start!");
		}
	}

	public void loadLevelPixels(Screen screen) {
		int x1 = (screen.width + 16) >> 4;
		int y1 = (screen.height + 16) >> 4;

		for (int y = 0; y < y1; y++) {
			for (int x = 0; x < x1; x++) {
				getTile(x, y).render(x, y, screen, this);
			}
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		// for (int i = 0; i < projectiles.size(); i++) {
		// projectiles.get(i).render(screen);
		// }
	}

	public void render(Screen screen) {
		screen.renderLevel(this);

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		// for (int i = 0; i < projectiles.size(); i++) {
		// projectiles.get(i).render(screen);
		// }
	}

	public void add(Entity e) {
		entities.add(e);
	}

	// public void addProjectile(Projectile p) {
	// projectiles.add(p);
	// }

	// Grass = 0xFF00FF00
	// Flower = 0xFFFFFF00
	// Rock = 0xFF7F7F00
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_grass) return Tile.grass;
		if (tiles[x + y * width] == Tile.col_flower) return Tile.flower;
		if (tiles[x + y * width] == Tile.col_rock) return Tile.rock;
		if (tiles[x + y * width] == Tile.col_pathNS) return Tile.pathNS;
		if (tiles[x + y * width] == Tile.col_pathEW) return Tile.pathEW;
		if (tiles[x + y * width] == Tile.col_pathSE) return Tile.pathSE;
		if (tiles[x + y * width] == Tile.col_pathSW) return Tile.pathSW;
		if (tiles[x + y * width] == Tile.col_pathNE) return Tile.pathNE;
		if (tiles[x + y * width] == Tile.col_pathNW) return Tile.pathNW;
		return Tile.voidTile;
	}

}
