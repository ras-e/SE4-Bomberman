package dk.sdu.mmmi.bomberman;

import com.badlogic.gdx.*;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Game implements ApplicationListener {

    /*
    TiledMap tiledMap;
    TiledMapTileLayer layoutlayer2, layoutlayer3;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer renderer;
    private Texture player;
    private SpriteBatch batch;
    float Speed = 50;
    float playerx = 70;
    float playery = 50;
    */

   public Game() {
        init();
    }

    public void init() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Asteroids";
        cfg.width = 800;
        cfg.height = 600;
        cfg.resizable = false;

        new LwjglApplication(this, cfg);
    }

    @Override
    public void create() {
       /*
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1470,1340 );
        camera.update();
        tiledMap = new TmxMapLoader().load("LargeMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        player = new Texture("cat.png");
        batch = new SpriteBatch();
        layoutlayer2 = (TiledMapTileLayer)tiledMap.getLayers().get(1);
        layoutlayer3 = (TiledMapTileLayer)tiledMap.getLayers().get(2);
        */
    }

    @Override
    public void render() {
      /*  renderer.setView(camera);
        renderer.render();
        batch.begin();

        camera.update();
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            // Test if key is working
            // System.out.println("W");
            playery+= Gdx.graphics.getDeltaTime()*Speed;

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            // Test if key is working
            // System.out.println("S");
            playery-= Gdx.graphics.getDeltaTime()*Speed;
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
        batch.draw(player, playerx , playery);
        batch.end(); */
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize(int width, int height) {
    }
}
