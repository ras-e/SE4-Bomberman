package dk.sdu.mmmi.bomberman.OSGiPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.mmmi.bomberman.OSGiCommonPlayer.Player;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.LifePart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.TexturePart;
import dk.sdu.mmmi.bomberman.common.services.IEntityProcessingService;

public class PlayerProcess implements IEntityProcessingService {
    private Texture texture;
    private Sprite sprite;
    private SpriteBatch batch;
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Player.class)){

            //sets the parts for the entity, so it can be updated
            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            LifePart lifePart = entity.getPart(LifePart.class);
            TexturePart texturePart = entity.getPart(TexturePart.class);

            //tells the movingpart, the entitypart, when a key is pressed
            movingPart.setUp(Gdx.input.isKeyPressed(Input.Keys.W));
            movingPart.setDown(Gdx.input.isKeyPressed(Input.Keys.S));
            movingPart.setLeft(Gdx.input.isKeyPressed(Input.Keys.A));
            movingPart.setRight(Gdx.input.isKeyPressed(Input.Keys.D));

            //sets the processes for the entity
            positionPart.process(gameData, entity);
            movingPart.process(gameData, entity);
            lifePart.process(gameData, entity);
            texturePart.process(gameData, entity);


            //Checks whether the player is dead and consequently removes them if so
            if (lifePart.isDead()){
                world.removeEntity(entity);
                gameData.setEVictory(true);
            }
        }
    }
}
