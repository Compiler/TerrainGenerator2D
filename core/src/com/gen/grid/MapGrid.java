package com.gen.grid;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * 
 * MapGrid is a class used for creating a grid of true and false, this is used to
 * tell if an object is there(true) or not(false)
 * This can be modified to have a grid that expands and/or a grid of objects
 * to give more depth to properties
 * 
 * @author Luke Roche
 *
 */
public class MapGrid {
	
	
	
	private int width, height;
	private int biomeCount;
	private int mountainCount;
	private Node vertex[];
	
	private final int cell;
	
	private Node[][] map;
	
	/**
	 * Default properties for MapGrid
	 * Width = 100
	 * Height = 100;
	 * Cell size = 25
	 * mountain count = random
	 */
	public MapGrid(){
		this.cell = 25;
		this.map = new Node[this.width][this.height];
	}
	
	/**
	 * @param width of map (how many cells on x-axis * cell size)
	 * @param height of map (how many cells on y-axis * cell size)
	 */
	public MapGrid(int width, int height){
		this.width = width;
		this.height = height;
		this.cell = 25;
		this.map = new Node[this.width][this.height];
	}
	
	/**
	 * @param width of map (how many cells on x-axis * cell size)
	 * @param height of map (how many cells on y-axis * cell size)
	 * @param cellSize of each cell in grid
	 */
	public MapGrid(int width, int height, int cellSize){
		this.width = width;
		this.height = height;
		this.cell = cellSize;
		this.map = new Node[this.width][this.height];
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				map[x][y] = null;
			}
		}
	}
	
	
	public void generateMap(){
		System.out.println(mountainCount = MathUtils.random(1, 1));
		vertex = new Node[mountainCount];
		int xSpacer = (width / mountainCount) / 2;
		for(int i = 0; i < vertex.length; i++){
			int xPos = MathUtils.random(0, width - 1);
			int yPos = MathUtils.random(5, height - 1);
			vertex[i] = new Node(xPos, yPos);
			Node vert = new Node(vertex[i].x, vertex[i].y);
			vert.isVertex = true;
			map[vertex[i].x][vertex[i].y] =vert;
		}
		for(int i = 0; i < vertex.length; i++){
			int slope = 1;
			for(int y = (int) vertex[i].y, x = 0; y > 0; y --, x += slope){
				
				int xLeft = (int) (vertex[i].x - x >= width ? width - 1 : vertex[i].x - x <= 0 ? 0 : vertex[i].x - x);
				int xRight = (int) (vertex[i].x + x >= width ? width - 1 : vertex[i].x + x <= 0 ? 0 : vertex[i].x + x);
				Node tmpNodeLeft = new Node(xLeft, y);
				Node tmpNodeRight = new Node(xRight, y);
				
				if(map[(int) vertex[i].x][y] == null)
					map[(int) vertex[i].x][y] = new Node(vertex[i].x, y);
				
			}
			
			
		}
		ArrayList<Node> hillLeft = new ArrayList<Node>();
		
		for(int i = 0; i < vertex.length; i++){
			int minWidth = vertex[i].y / 6;
			int maxWidth = vertex[i].y;
			int width = MathUtils.random(minWidth, maxWidth);
			
			int bottomXLeft = vertex[i].x - width < 0 ? 0 : vertex[i].x - width;
			hillLeft.add(new Node(bottomXLeft, 0));
			
			minWidth = (vertex[i].x - bottomXLeft) / 6;
			maxWidth = (vertex[i].x - bottomXLeft) / 2;
			
			width = MathUtils.random(minWidth, maxWidth);
			bottomXLeft = vertex[i].x - width < 0 ? 0 : vertex[i].x - width;
			
			hillLeft.add(new Node(bottomXLeft, MathUtils.random(vertex[i].y / 6, vertex[i].y - 1)));
			
		}
		//connect them
		//for(int i = 0; i < )
		
		for(Node n : hillLeft){
			map[n.x][n.y] = n;
		}
		
		
	}
	
	
	
	
	public void update(){
		
	}
	
	public void render(ShapeRenderer render){
		
		for(int x = 0; x < width * cell; x += cell){
			for(int y = 0; y < height * cell; y += cell){
				render.setColor(.35f, .35f, .35f, .15f);
				
				if(map[x / cell][y / cell] != null){
					if(map[x/cell][y/cell].isVertex){
						render.setColor(0, 1, 0, 1);
					}else if(map[x/cell][y/cell].isIntersection)
						render.setColor(1, 0, 0, 1f);
					else
						render.setColor(0.545f, 0.27f, 0.0745f, 1f);
						
				}
				
				render.rect(x, y, cell, cell);
			}
		}
		
		
	}
	
	public int getWidth(){ return this.width; }
	public int getHeight(){ return this.height; }
	public int getCellSize(){ return this.cell; }
	
	
	
	
	
	
	private void sortVertices(){
		int index = 0;
		int tmp = (int) vertex[index].x;
		Vector2[] tmpVert = new Vector2[vertex.length];
		for(int i = 0; i < vertex.length; i++){
			if(index == i){
				continue;
			}
			System.out.println(i);
		}
		
	}
}
