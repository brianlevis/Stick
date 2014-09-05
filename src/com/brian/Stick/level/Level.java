package com.brian.Stick.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.brian.Stick.entity.Entity;
import com.brian.Stick.entity.projectile.Projectile;
import com.brian.Stick.graphics.Screen;
import com.brian.Stick.level.tile.PathTile;
import com.brian.Stick.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	public String name;

	private List<Entity> entities = new ArrayList<Entity>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<PathTile> path = new ArrayList<PathTile>();

	public Level(String path) {
		this.name = path.substring("/levels/".length(), path.length() - 4);
		loadLevel(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
			getPath(tiles);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
	}

	public void getPath(int[] tiles) {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				if (tiles[x + y * width] == Tile.col_grass) System.out.println(tiles[x + y * width]);

	}

	public void render(Screen screen) {
		int x1 = (screen.width + 16) >> 4;
		int y1 = (screen.height + 16) >> 4;

		for (int y = 0; y < y1; y++) {
			for (int x = 0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}

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
		if (tiles[x + y * width] == Tile.col_path) return Tile.rock;
		if (tiles[x + y * width] == Tile.col_pathNS) return Tile.pathNS;
		if (tiles[x + y * width] == Tile.col_pathEW) return Tile.pathEW;
		if (tiles[x + y * width] == Tile.col_pathSE) return Tile.pathSE;
		if (tiles[x + y * width] == Tile.col_pathSW) return Tile.pathSW;
		if (tiles[x + y * width] == Tile.col_pathNE) return Tile.pathNE;
		if (tiles[x + y * width] == Tile.col_pathNW) return Tile.pathNW;
		return Tile.voidTile;
	}

}
