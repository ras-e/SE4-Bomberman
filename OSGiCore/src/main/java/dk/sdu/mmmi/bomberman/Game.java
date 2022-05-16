package dk.sdu.mmmi.bomberman;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.services.IEntityProcessingService;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;
import dk.sdu.mmmi.bomberman.common.services.IPostEntityProcessingService;

import dk.sdu.mmmi.bomberman.common.data.Entity;

import dk.sdu.mmmi.bomberman.common.tools.FileLoader;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Game implements ApplicationListener {

    TiledMap tiledMap;
    OrthographicCamera cam;
    OrthogonalTiledMapRenderer renderer;
    public World world = new World();
    GameData gameData = new GameData();
    private SpriteBatch textureSpriteBatch;

    private static final List<IEntityProcessingService> entityProcessorList = new CopyOnWriteArrayList<>();
    private static final List<IGamePluginService> gamePluginList = new CopyOnWriteArrayList<>();
    private static List<IPostEntityProcessingService> postEntityProcessorList = new CopyOnWriteArrayList<>();
    
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

       String[] mapFiles = {"assets/ColMap.tmx", "assets/plain.tsx", "assets/Shadow.tsx", "assets/[64x64] Dungeon Bricks Plain.png", "assets/[64x64] Dungeon Bricks Shadow.png"};
       FileLoader.loadFiles(mapFiles, getClass());
       tiledMap = new TmxMapLoader().load(mapFiles[0]);
       renderer = new OrthogonalTiledMapRenderer(tiledMap);
       //texture = new Texture(Gdx.files.internal("/home/janpe20/Desktop/SE4-Bomberman/OSGiCore/src/main/resources/assets/jens.png").file().getAbsolutePath());

        gameData.getAssetManager().loadAssets();
    }

    @Override
    public void render() {

       renderer.setView(cam);
       renderer.render();
       cam.update();
       update();
    }

    private void update(){
        for (IEntityProcessingService entityProcessorService : entityProcessorList) {
            entityProcessorService.process(gameData, world);
        }

        // Post Update
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessorList) {
            postEntityProcessorService.process(gameData, world);
        }
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

    public void addEntityProcessingService(IEntityProcessingService eps) {
        this.entityProcessorList.add(eps);
    }

    public void removeEntityProcessingService(IEntityProcessingService eps) {
        this.entityProcessorList.remove(eps);
    }

    public void addPostEntityProcessingService(IPostEntityProcessingService eps) {
        postEntityProcessorList.add(eps);
    }

    public void removePostEntityProcessingService(IPostEntityProcessingService eps) {
        postEntityProcessorList.remove(eps);
    }

    public void addGamePluginService(IGamePluginService plugin) {
        this.gamePluginList.add(plugin);
        plugin.start(gameData, world);

    }

    public void removeGamePluginService(IGamePluginService plugin) {
        this.gamePluginList.remove(plugin);
        plugin.stop(gameData, world);
    }

    public SpriteBatch getTextureSpriteBatch() {
        return textureSpriteBatch;
    }

    public OrthographicCamera getCam() {
        return cam;
    }
}
