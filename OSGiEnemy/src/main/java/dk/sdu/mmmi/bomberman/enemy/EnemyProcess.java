package dk.sdu.mmmi.bomberman.enemy;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.LifePart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.bomberman.commonenemy.Enemy;
import dk.sdu.mmmi.bomberman.common.services.IEntityProcessingService;

public class EnemyProcess implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Enemy.class)){

            //sets the parts for the entity, so it can be updated
            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            LifePart lifePart = entity.getPart(LifePart.class);


        //sets the processes for the entity
        positionPart.process(gameData, entity);
        movingPart.process(gameData, entity);
        lifePart.process(gameData, entity);

        //Checks whether the player is dead and consequently removes them if so
        if (lifePart.isDead()){
            world.removeEntity(entity);
            gameData.setPVictory(true);
        }
    }
}
}