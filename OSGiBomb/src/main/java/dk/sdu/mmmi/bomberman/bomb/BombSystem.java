package dk.sdu.mmmi.bomberman.bomb;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.LifePart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.bomberman.common.services.IEntityProcessingService;
import dk.sdu.mmmi.bomberman.commonbomb.Bomb;
import dk.sdu.mmmi.bomberman.commonbomb.BombSPI;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class BombSystem implements IEntityProcessingService, BombSPI {


    @Override
    public void process(GameData gameData, World world) {

        for(Entity bomb : world.getEntities(Bomb.class)) {
            PositionPart positionPart = bomb.getPart(PositionPart.class);
            MovingPart movingPart = bomb.getPart(MovingPart.class);
            TimerPart timerPart = bomb.getPart(TimerPart.class);
            movingPart.setUp(true);
            if (timerPart.getExpiration() < 0) {
                world.removeEntity(bomb);
            }
            timerPart.process(gameData, bomb);
            movingPart.process(gameData, bomb);
            positionPart.process(gameData, bomb);
        }
    }

    @Override
    public Entity createBomb(Entity bomberman, GameData gameData) {
        PositionPart shooterPos = bomberman.getPart(PositionPart.class);
        MovingPart shooterMovingPart = bomberman.getPart(MovingPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float radians = shooterPos.getRadians();
        float dt = gameData.getDelta();
        float speed = 350;

        Entity bomb = new Bomb();
        bomb.setRadius(2);

        float bx = (float) cos(radians) * bomberman.getRadius() * bomberman.getRadius();
        float by = (float) sin(radians) * bomberman.getRadius() * bomberman.getRadius();


        bomb.add(new PositionPart(bx + x, by + y, radians));
        bomb.add(new LifePart(1));
        bomb.add(new MovingPart(0, 50000, speed, 5));
        bomb.add(new TimerPart(1));

        bomb.setShapeX(new float[2]);
        bomb.setShapeY(new float[2]);

        return bomb;
    }
}
