package dk.sdu.mmmi.bomberman.enemy;

import dk.sdu.mmmi.bomberman.commonenemy.Enemy;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.LifePart;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;


public class EnemyPlugin implements IGamePluginService {

    private String enemyID;
    //amountOfEnemies depends on game-level stage
    private int amountOfEnemies;

    public EnemyPlugin() {
        amountOfEnemies = 1;
    }

    public EnemyPlugin(int amountOfEnemies) {
        this.amountOfEnemies = amountOfEnemies;

    }
    //TODO: Add entityparts, same as the player. Omit texturepart for now though
    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the game world (x Bombermans)
        for (int i = 0; i < amountOfEnemies; i++) {
            Entity enemy = createBomeberman(gameData);
            enemyID = world.addEntity(enemy);
        }
    }

    private Entity createBomeberman(GameData gameData) {
        Entity enemyBomberman = new Enemy();

        float deacceleration = 10;
        float acceleration = 2000;
        float maxSpeed = 2000;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = 3.1415f / 2;
        enemyBomberman.add(new LifePart(1));
      // enemyBomberman.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
       // enemyBomberman.add(new PositionPart(x, y, radians));


        return enemyBomberman;
    }
    @Override
    public void stop(GameData gameData, World world) {
        //Remove entities from game world
       // for (Entity enemyBomberman : world.getEntities(Enemy.class)) {
     //       world.removeEntity(enemyBomberman);
        }
    }

