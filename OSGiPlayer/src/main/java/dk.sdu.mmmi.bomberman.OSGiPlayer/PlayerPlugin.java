package dk.sdu.mmmi.bomberman.OSGiPlayer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.mmmi.bomberman.OSGiCommonPlayer.Player;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {
    private Texture playerAvatar;
    private String entityID;
    private SpriteBatch batch;

    @Override
    public void start(GameData gameData, World world) {
        Entity player = createPlayer(gameData);
        entityID = world.addEntity(player);
    }

    private Entity createPlayer(GameData gameData){
        Entity player = new Player();
        playerAvatar = new Texture("src/main/resources/jens.png");
        float x = 70;
        float y = 50;
        float speed = 50;
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(entityID);
    }
}
