package dk.sdu.mmmi.bomberman.OSGiExplosion;

import dk.sdu.mmmi.bomberman.OSGiCommonBomb.entities.Explosion;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;

public class ExplosionPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Explosion.class) {
                world.removeEntity(e);
            }
        }
    }
}
