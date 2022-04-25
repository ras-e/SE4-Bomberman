package dk.sdu.mmmi.bomberman.core.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.bomberman.Game;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.GameKeys;
import dk.sdu.mmmi.bomberman.common.data.World;

import java.util.ArrayList;

public class MenuState extends GameState{

    private SpriteBatch sb;
    private ShapeRenderer sr;

    private BitmapFont titleFont;
    private BitmapFont font;

    private final String title = "Bomberman";

    //For drawing the menuOptions
    private int current;
    private String[] menuOpt;

    public MenuState(StateManager StateManager, Game game, World world, GameData gameData) {
        super(StateManager, game, world, gameData);
    }

    @Override
    public void init() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator
                (Gdx.files.internal("fonts/Hyperspace Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        //Size of font
        parameter.size = 80;
        //Sets the titleFont to use the custom font
        titleFont = generator.generateFont(parameter);

        menuOpt = new String[]{
                "Play",
                "Quit",
        };
    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        getGame().getTextureSpriteBatch().setProjectionMatrix(getGame().getCam().combined);
        getGame().getTextureSpriteBatch().begin();
        sb.begin();

        // draw title
        titleFont.draw(
                getGame().getTextureSpriteBatch(),
                title,
                20,
                300
        );

        // draw menu
        for (int i = 0; i < menuOpt.length; i++) {
            if (current == i) {
                font.setColor(Color.RED);
            } else {
                font.setColor(Color.WHITE);
            }
            font.draw(
                    getGame().getTextureSpriteBatch(),
                    menuOpt[i],
                    20,
                    180 - 35 * i
            );
        }
        getGame().getTextureSpriteBatch().end();
    }

    @Override
    public void handleInput() {
        if(GameKeys.isPressed(GameKeys.UP)) {
            if(current > 0) {
                current--;
            }
        }
        if(GameKeys.isPressed(GameKeys.DOWN)) {
            if(current < menuOpt.length - 1) {
                current++;
            }
        }
        if(GameKeys.isPressed(GameKeys.ENTER)) {
            select();
        }


    }

    private void select() {
        // play
        if (current == 0) {
            getStateManager().setState(StateManager.PLAY);
        } // high scores
        else if (current == 1) {
            Gdx.app.exit();
        /*else if (current == 1) {
            getStateManager().setState(StateManager.HELP);
        } else if (current == 2) {
            getStateManager().setState(StateManager.HIGHSCORE);
        } else if (current == 4) {
            Gdx.app.exit();
        }*/
        }
    }

    @Override
    public void dispose() {
            titleFont.dispose();
            font.dispose();
        }
    }

