package dk.sdu.mmmi.bomberman.OSGiPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.LifePart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.bomberman.common.services.IEntityProcessingService;

/**
 *
 * @author jcs
 */
public class PlayerControlSystem implements IEntityProcessingService {

    //private final static AssetLoader assetLoader = AssetLoader.getInstance(MODULE_NAME);
    private static final float acceleration = 150F;
    private static final float maxAcceleration = 75F;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            LifePart lifePart = player.getPart(LifePart.class);

            final float delta = gameData.getDelta();

            float x = positionPart.getX();
            float y = positionPart.getY();

            controls(player, delta);

            positionPart.process(gameData, player);
            lifePart.process(gameData, player);

            updateShape(player);
        }
    }

    public void controls(Entity player, float delta){
        PositionPart part = player.getPart(PositionPart.class);

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            part.setY(part.getY() + 10F * delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            part.setX(part.getX() - 10F * delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            part.setY(part.getY() - 10F * delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            part.setX(part.getX() + 10F * delta);
        }
    }

    private void updateShape(Entity entity) {
        float radius = entity.getRadius();
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = 5;

        shapex[0] = (float) (x + Math.cos(radians) * radius);
        shapey[0] = (float) (y + Math.sin(radians) * radius);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * radius);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * radius);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * radius * 0.625);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * radius * 0.625);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * radius);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * radius);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
