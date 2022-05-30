package dk.sdu.mmmi.bomberman.enemy;

import dk.sdu.mmmi.bomberman.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.bomberman.commonenemy.Enemy;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.LifePart;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;


public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;
    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        enemy = createBomeberman(gameData);
        world.addEntity(enemy);
    }

    private Entity createBomeberman(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 2000;
        float maxSpeed = 2000;
        float rotationSpeed = 5;
        float x = 32;
        float y = 32;
        float radians = 3.1415f / 2;

        Entity enemy = new Enemy();
        enemy.setRadius(8);
        enemy.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemy.add(new PositionPart(x, y, radians));
        enemy.add(new LifePart(1));

        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }
}

