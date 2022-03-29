package dk.sdu.mmmi.bomberman.common.data.entityparts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;

public class MovingPart implements EntityPart {

    private float delta = Gdx.graphics.getDeltaTime();
    private float speed;
    private boolean up, down, left, right;

    public MovingPart(float speed) {
        this.speed = speed;
    }

    //The sets check whether or not a corresponding key has been pressed
    public void setUp(boolean up) {
        if (up == Gdx.input.isKeyPressed(Input.Keys.W)){
            this.up = up;
        }
    }

    public void setDown(boolean down) {
        if (down == Gdx.input.isKeyPressed(Input.Keys.S)){
            this.down = down;
        }
    }

    public void setLeft(boolean left) {
        if (left == Gdx.input.isKeyPressed(Input.Keys.D)){
            this.left = left;
        }
    }

    public void setRight(boolean right) {
        if (right == Gdx.input.isKeyPressed(Input.Keys.A)){
            this.right = right;
        }
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        speed = 50f;
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();

        //essentially if the directions are true the process calculate their now position

        if (up) {
            y += delta * speed;
        }
        if (down) {
            y -= delta * speed;
        }
        if (right) {
            x -= delta * speed;
        }
        if (left) {
            x -= delta * speed;
        }
        //tells the PositionPart where the entities new position should be
        positionPart.setX(x);
        positionPart.setY(y);
    }
}
