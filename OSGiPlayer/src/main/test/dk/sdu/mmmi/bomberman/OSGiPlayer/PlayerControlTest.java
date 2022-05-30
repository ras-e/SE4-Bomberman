package dk.sdu.mmmi.bomberman.OSGiPlayer;

import com.badlogic.gdx.Game;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameKeys;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.bomberman.commonplayer.Player;
import org.junit.jupiter.api.BeforeEach;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mockito.Mock;

class PlayerTest {
    World world;
    GameData gameData;

    @BeforeEach
    public void setup(){
        world = new World();
        gameData = new GameData();
        gameData.setDelta(1);
        gameData.setDisplayHeight(100);
        gameData.setDisplayWidth(100);
    }


    @Test
    public void movementTest() {
        PlayerPlugin playerPlugin = new PlayerPlugin();
        playerPlugin.start(gameData, world);
        Entity testEntity = world.getEntities(Player.class).get(0);
        PositionPart positionPart = testEntity.getPart(PositionPart.class);
        float currentY = positionPart.getY();
        float currentX = positionPart.getX();
        MovingPart movingPart = testEntity.getPart(MovingPart.class);
        gameData.getKeys().setKey(0, true);
        movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
        movingPart.process(gameData, testEntity);
        float newX = positionPart.getY();
        assertNotEquals(currentY, newX);

    }


}