package dk.sdu.mmmi.bomberman.bomb;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.bomberman.common.services.IEntityProcessingService;
import dk.sdu.mmmi.bomberman.common.data.entityparts.LifePart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.bomberman.commonbomb.Bomb;

public class BombProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {


        for (Entity b : world.getEntities(Bomb.class)) {
            PositionPart playerPosition = b.getPart(PositionPart.class);
            MovingPart movingpart = b.getPart(MovingPart.class);
            TimerPart timerpart = b.getPart(TimerPart.class);
            LifePart lifepart = b.getPart(LifePart.class);

            float x = playerPosition.getX();
            float y = playerPosition.getY();
            float radians = playerPosition.getRadians();

            Bomb bomb = new Bomb();
           // GameImage img = new GameImage("items/bomb.png", 20, 20);
          //  bomb.setImage(img);

         //   float bx = (float) (x - (image.getWidth() / 2 + 40) * cos(radians));
          //  float by = (float) (y - (image.getWidth() / 2 + 40) * sin(radians));

          //  bomb.add(new PositionPart(bx + (image.getWidth() / 2), by + (image.getHeight() / 2), radians));
            bomb.add(new TimerPart(5));



            timerpart.reduceExpiration(gameData.getDelta());

            //If duration is exceeded, remove the bullet.
         //   if (timerpart.getExpiration() < 0 || movingpart.getIsOut()) {
           //     world.removeEntity(b);
            }
        //    world.addEntity(bomb);
        }
    }

