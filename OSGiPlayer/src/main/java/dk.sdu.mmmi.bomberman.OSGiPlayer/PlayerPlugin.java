package dk.sdu.mmmi.bomberman.OSGiPlayer;

import com.badlogic.gdx.graphics.Texture;
import dk.sdu.mmmi.bomberman.common.CatFileHandler;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.*;
import dk.sdu.mmmi.bomberman.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;

//add a texture/sprite in each module that handles their texture
//and make sure the texture can be reached in the process class



public class PlayerPlugin implements IGamePluginService {
    private String entityID;
    private float radius = 20f;

    private Entity player;

    public PlayerPlugin(){}

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayer(gameData);
        world.addEntity(player);
    }

    private Entity createPlayer(GameData gameData){
        player = new Player();
        float speed = 200;
        float x = 64;
        float y = 64;
        int life = 1;
        Texture upSprite = new Texture(new CatFileHandler("/assets/cat.png", this.getClass()));
        player.add(new SpritePart(upSprite));
        player.add(new LifePart(life));
        player.add(new MovingPart(speed));
        player.add(new PositionPart(x, y));

        //player.add(new SpritePart(new Texture(new FileHandler("/assets/cat.png", Player.class))));
        //player.add(new TexturePart("jens.png"));
        //Texture playerSprite = new Texture(new FileHandler("/assets/cat.png", Player.class));
        //player.add(new SpritePart(playerSprite));
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(entityID);
    }
}
