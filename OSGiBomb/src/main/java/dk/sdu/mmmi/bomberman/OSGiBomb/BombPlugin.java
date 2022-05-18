package dk.sdu.mmmi.bomberman.OSGiBomb;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;
import dk.sdu.mmmi.bomberman.OSGiCommonBomb.services.BombSPI;

public class BombPlugin implements IGamePluginService {

    private final BombSPI bomb = new NormalBomb();

    @Override
    public void start(GameData gameData, World world) {
            gameData.addEntity(bomb);
    }

    @Override
    public void stop(GameData gameData, World world) {

        // removes all bombs
        for (Entity entity : world.getEntities(Bomb.class)) {
            world.removeEntity(entity);
        }

        gameData.removeEntity(bomb);
    }
}
