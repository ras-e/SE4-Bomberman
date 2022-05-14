package dk.sdu.mmmi.bomberman.OSGiExplosion;

import dk.sdu.mmmi.bomberman.OSGiCommonBomb.entities.Explosion;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.services.IEntityProcessingService;

public class ExplosionProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity b : world.getEntities(Explosion.class)) {

        }
    }
}
