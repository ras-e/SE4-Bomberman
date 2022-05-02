package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;


public class TileGame extends ApplicationAdapter {
    TiledMap tiledMap;
    TiledMapTileLayer layoutlayer2, layoutlayer3;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer renderer;
    private Texture player;
    private Texture bomb;
    private Texture explosion;
    private Sprite bombS;
    private Sprite explosionS;
    private Sprite playerS;
    private Rectangle rectangleEX;
    private Rectangle rectanglePL;
    private SpriteBatch batch;
    float Speed = 200;
    float playerx = 70;
    float playery = 50;
    boolean drawSprite = false;
    boolean drawSpriteE = false;
    boolean drawSpriteP = false;


    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1470, 1340);
        camera.update();
        tiledMap = new TmxMapLoader().load("LargeMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        player = new Texture("cat.png");
        playerS = new Sprite(player);
        bomb = new Texture("bomb.png");
        explosion = new Texture("exp.png");
        explosionS = new Sprite(explosion);

        bombS = new Sprite(bomb);
        batch = new SpriteBatch();
        layoutlayer2 = (TiledMapTileLayer) tiledMap.getLayers().get(1);
        layoutlayer3 = (TiledMapTileLayer) tiledMap.getLayers().get(2);


    }

    @Override
    public void render() {
        renderer.setView(camera);
        renderer.render();
        batch.begin();
        camera.update();

        rectangleEX = explosionS.getBoundingRectangle();
        rectanglePL = playerS.getBoundingRectangle();

        if(!drawSpriteP){
            playerS.draw(batch);
        }

        boolean isOverlaping = rectanglePL.overlaps(rectangleEX);
        if(isOverlaping && drawSpriteP){
            System.out.println("Overlapping!");
            batch.end();
        }


        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            bombS.setY(playery);
            bombS.setX(playerx);
            explosionS.setY(bombS.getY());
            explosionS.setX(bombS.getX());
            drawSprite = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            // Test if key is working
            // System.out.println("W");
            playery += Gdx.graphics.getDeltaTime() * Speed;

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            // Test if key is working
            // System.out.println("S");
            playery -= Gdx.graphics.getDeltaTime() * Speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            // Test if key is working
            // System.out.println("A");
            playerx -= Gdx.graphics.getDeltaTime() * Speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            // Test if key is working
            // System.out.println("D");
            playerx += Gdx.graphics.getDeltaTime() * Speed;
        }
        if(drawSprite){
            bombS.draw(batch);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run(){
                            drawSprite = false;
                            drawSpriteE = true;
                        }
                    },
                    3000
            );
        }
        if(drawSpriteE){
            explosionS.draw(batch);
            new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run(){
                        drawSpriteE = false;
                    }
                }, 1000
            );
        }
        batch.draw(player, playerx, playery);
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
