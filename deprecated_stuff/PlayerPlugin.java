package dk.sdu.mmmi.bomberman.OSGiPlayer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.*;
import dk.sdu.mmmi.bomberman.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;
import dk.sdu.mmmi.bomberman.common.utils.AssetsJarFileResolver;

//add a texture/sprite in each module that handles their texture
//and make sure the texture can be reached in the process class


public class PlayerPlugin implements IGamePluginService {
    private String entityID;
    private float radius = 20f;
    private Entity player;

    public PlayerPlugin() {
    }

    private AssetManager assetManager = new AssetManager(new AssetsJarFileResolver(this.getClass()));
    private Texture texture;

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayer(gameData);
        world.addEntity(player);
    }

    private Entity createPlayer(GameData gameData) {
        player = new Player();
        float speed = 200;
        float x = 64;
        float y = 64;
        int life = 1;
        player.add(new LifePart(life));
        player.add(new MovingPart(speed));
        player.add(new PositionPart(x, y));
        player.add(new SpritePart(loadSprite()));
        return player;
    }

    private String loadPlayerAssets() {

        String path = java.nio.file.Paths.get("assets", "jens.png").toString();

        assetManager.load(path, Texture.class);

        assetManager.finishLoading();

        path = path.replace("\\", "/");

        return path;
    }

    protected Texture loadSprite() {
            String jarUrl = loadPlayerAssets();
            final Texture texture = assetManager.get(jarUrl);
        return texture;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(entityID);
    }
}
