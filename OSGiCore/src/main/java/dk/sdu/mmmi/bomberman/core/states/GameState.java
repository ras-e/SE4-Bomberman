package dk.sdu.mmmi.bomberman.core.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.mmmi.bomberman.Game;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;

public abstract class GameState {

    private final Game game;
    private final World world;
    private final GameData gameData;
    private final OrthographicCamera cam = new OrthographicCamera();
    private final StateManager StateManager;

    public GameState(StateManager StateManager, Game game, World world, GameData gameData) {
        this.game = game;
        this.world = world;
        this.gameData = gameData;
        this.StateManager = StateManager;
        init();
    }

    public abstract void init();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch spriteBatch);

    public abstract void handleInput();

    public abstract void dispose();

    public Game getGame() {
        return game;
    }

    public World getWorld() {
        return world;
    }

    public GameData getGameData() {
        return gameData;
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public StateManager getStateManager() {
        return StateManager;
    }

}
}
