package dk.sdu.mmmi.bomberman;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import dk.sdu.mmmi.bomberman.common.utils.AssetsJarFileResolver;
import dk.sdu.mmmi.bomberman.core.managers.GameInputProcessor;

import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Game implements ApplicationListener {

    TiledMap tiledMap;
    OrthographicCamera cam;
    OrthogonalTiledMapRenderer renderer;
    public World world = new World();
    GameData gameData = new GameData();
    private SpriteBatch textureSpriteBatch;
    private static Queue<Runnable> gdxThreadTasks = new LinkedList<>();
    private static final List<IEntityProcessingService> entityProcessorList = new CopyOnWriteArrayList<>();
    private static final List<IGamePluginService> gamePluginList = new CopyOnWriteArrayList<>();
    private static List<IPostEntityProcessingService> postEntityProcessorList = new CopyOnWriteArrayList<>();
    private ShapeRenderer sr;

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
        cam.setToOrtho(false, 832, 704);
        cam.update();
        sr = new ShapeRenderer();
        String[] mapFiles = {"assets/ColMap.tmx", "assets/plain.tsx", "assets/Shadow.tsx", "assets/[64x64] Dungeon Bricks Plain.png", "assets/[64x64] Dungeon Bricks Shadow.png"};
        FileLoader.loadFiles(mapFiles, getClass());
        tiledMap = new TmxMapLoader().load(mapFiles[0]);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        for (Runnable plug : gdxThreadTasks) {
            Thread thread = new Thread(plug);
            thread.run();
        }
    }


    @Override
    public void render() {
        renderer.setView(cam);
        renderer.render();
        cam.update();
        update();
        draw();
        gameData.getKeys().update();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : entityProcessorList) {
            entityProcessorService.process(gameData, world);
        }

        // Post Update
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessorList) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    public void draw() {
        for (Entity entity : world.getEntities()) {

            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                 i < shapex.length;
                 j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
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
        gdxThreadTasks.add(() -> plugin.start(gameData, world));

    }

    public void removeGamePluginService(IGamePluginService plugin) {
        this.gamePluginList.remove(plugin);
        gdxThreadTasks.add(() -> plugin.stop(gameData, world));
    }

    public SpriteBatch getTextureSpriteBatch() {
        return textureSpriteBatch;
    }

    public OrthographicCamera getCam() {
        return cam;
    }
}
