package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GDXmain extends ApplicationAdapter {
	TiledMap tiledMap;
	TiledMapTileLayer layoutlayer2, layoutlayer3;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer renderer;
	private Texture texture;
	private SpriteBatch batch;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1470,1340 );
		camera.update();
		tiledMap = new TmxMapLoader().load("LargeMap.tmx");
		renderer = new OrthogonalTiledMapRenderer(tiledMap);
		texture = new Texture(Gdx.files.internal("jens.png"));
		batch = new SpriteBatch();
		layoutlayer2 = (TiledMapTileLayer)tiledMap.getLayers().get(1);
		layoutlayer3 = (TiledMapTileLayer)tiledMap.getLayers().get(2);
	}

	@Override
	public void render() {
		renderer.setView(camera);
		renderer.render();
		renderer.getBatch().begin();
/*        renderer.renderTileLayer(layoutlayer2);
        renderer.renderTileLayer(layoutlayer3);*/
		batch.begin();
		batch.draw(texture, 67, 60, 55, 65);
		camera.update();
		renderer.getBatch().end();
		batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}
}