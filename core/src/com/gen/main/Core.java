package com.gen.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gen.grid.MapGrid;

public class Core extends ApplicationAdapter {
	
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;
	private MapGrid map;
	
	@Override
	public void create () {
		
		Gdx.graphics.setDisplayMode(640, 480, false);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
		
		
		spriteBatch = new SpriteBatch();
		
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		
		map = new MapGrid(100, 101);
		map.generateMap();
		
	}
	
	public void cameraController(float speed){
		if(Gdx.input.isKeyPressed(Keys.W)){
			camera.translate(0, speed);
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			camera.translate(0, -speed);
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			camera.translate(-speed, 0);
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			camera.translate(speed, 0);
		}
		if(Gdx.input.isKeyPressed(Keys.Q)){
			camera.zoom += speed / 1000.0f;
		}
		if(Gdx.input.isKeyPressed(Keys.E)){
			camera.zoom -= speed / 1000.0f;
		}
		if(Gdx.input.isKeyPressed(Keys.F)){
			camera.viewportHeight = map.getHeight() * map.getCellSize();
			camera.viewportWidth = map.getWidth() * map.getCellSize();
			camera.position.set((map.getWidth() * map.getCellSize()) / 2, (map.getHeight() * map.getCellSize()) / 2, 0);
		}
	}
	
	@Override
	public void render () {
		
		camera.update();
		cameraController(5);
		spriteBatch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		map.update();
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		spriteBatch.begin();
		
		spriteBatch.end();
		
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		shapeRenderer.begin();
		map.render(shapeRenderer);
		shapeRenderer.end();
		
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
}
