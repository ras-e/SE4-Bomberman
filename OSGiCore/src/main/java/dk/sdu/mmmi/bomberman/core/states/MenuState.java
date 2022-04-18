package dk.sdu.mmmi.bomberman.core.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.bomberman.Game;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;

import java.util.ArrayList;

public class MenuState extends GameState {

    private SpriteBatch sb;
    private ShapeRenderer sr;

    private BitmapFont titleFont;
    private BitmapFont font;

    private final String title = "Bomberman";

    private int currentItem;
    private String[] menuOpt;

    public MenuState(dk.sdu.mmmi.bomberman.core.states.StateManager StateManager, Game game, World world, GameData gameData) {
        super(StateManager, game, world, gameData);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}
