package dk.sdu.mmmi.bomberman;

import com.badlogic.gdx.*;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.World;

public class Game implements ApplicationListener {

    public World world;
    TiledMap tiledMap;
    OrthographicCamera cam;
    OrthogonalTiledMapRenderer renderer;
    Texture texture;
    SpriteBatch batch;


   public Game() {
        init();
    }

    public void init() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Bomberman";
        cfg.width = 800;
        cfg.height = 600;
        cfg.resizable = false;

        new LwjglApplication(this, cfg);
    }

    @Override
    public void create() {
       cam = new OrthographicCamera();
       //viewportwidth and -height matches the exact height and width of the map. Do you change the size of the map, change here too
       cam.setToOrtho(false, 832, 704);
       cam.update();
       tiledMap = new TmxMapLoader().load("/home/janpe20/Desktop/SE4-Bomberman/OSGiCore/src/main/resources/assets/smallMap.tmx");
       renderer = new OrthogonalTiledMapRenderer(tiledMap);
       //texture = new Texture(Gdx.files.internal("/home/janpe20/Desktop/SE4-Bomberman/OSGiCore/src/main/resources/assets/jens.png").file().getAbsolutePath());
       batch = new SpriteBatch();
    }

    @Override
    public void render() {
       renderer.setView(cam);
       renderer.render();
       batch.begin();
       batch.draw(texture,64,64,64,64);
       cam.update();
       batch.end();

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
