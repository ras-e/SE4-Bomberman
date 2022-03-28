package dk.sdu.mmmi.bomberman.OSGiPlayer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.mmmi.bomberman.OSGiCommonPlayer.Player;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.LifePart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {
    private String entityID;
    private float radius = 20f;

    @Override
    public void start(GameData gameData, World world) {
        Entity player = createPlayer(gameData);
        entityID = world.addEntity(player);
        for (IGamePluginService plugin : gameData.getGamePlugins()){
            plugin.start(gameData, world);
        }
    }

    private Entity createPlayer(GameData gameData){
        Entity player = new Player();

        float speed = 200;
        float x = 64;
        float y = 64;
        int life = 3;
        player.add(new LifePart(life));
        player.add(new MovingPart(speed));
        player.add(new PositionPart(x, y));

        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
    }
}
