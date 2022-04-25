package dk.sdu.mmmi.bomberman.core.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dk.sdu.mmmi.bomberman.Game;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;

public final class StateManager {

    // current game state
    private GameState gameState;
    private final Game game;
    private GameData gameData;
    private World world;

    public static final int MENU = 0;
    public static final int PLAY = 1;

    public StateManager(Game game, World world, GameData gameData) {
        this.game = game;
        this.world = world;
        this.gameData = gameData;
        //setState(MENU);

    }
/*
    public void setState(int state) {
        if (gameState != null) {
            gameState.dispose();
        }
        if (state == MENU) {
            gameState = new MenuState(this, game, world, gameData);
        }
        if (state == PLAY) {
            gameState = new PlayState(this, game, world, gameData);
        }
    }
*/
    public void update(float dt) {
        gameState.update(dt);
    }

    public void render(SpriteBatch spriteBatch) {
        gameState.render(spriteBatch);
    }
}

//}
