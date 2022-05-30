package dk.sdu.mmmi.bomberman.OSGiPlayer;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.LifePart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;
import dk.sdu.mmmi.bomberman.commonplayer.Player;


public class PlayerPlugin implements IGamePluginService {
    private Entity player;
    public PlayerPlugin() {
    }
    @Override
    public void start(GameData gameData, World world) {
        player = createPlayer(gameData);
        world.addEntity(player);
    }
    private Entity createPlayer(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 2000;
        float maxSpeed = 2000;
        float rotationSpeed = 5;
        float x = 248;
        float y = 248;
        float radians = 3.1415f / 2;

        Entity player = new Player();
        player.setRadius(8);
        player.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        player.add(new PositionPart(x, y, radians));
        player.add(new LifePart(1));
        
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
