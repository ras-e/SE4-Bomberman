package dk.sdu.mmmi.bomberman.common.data.entityparts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;

public class MovingPart implements EntityPart {

    private float delta = Gdx.graphics.getDeltaTime();
    private float speed;

    public MovingPart(float speed){
        this.speed = speed;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        speed = 50f;
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            y += delta * speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            y -= delta * speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            x -= delta * speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            x -= delta * speed;
        }
        positionPart.setX(x);
        positionPart.setY(y);
    }
}
