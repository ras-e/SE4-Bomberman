package dk.sdu.mmmi.bomberman.common.data.entityparts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;

public class MovingPart implements EntityPart {
    private float deceleration;
    private float acceleration = 2F;
    private float maxSpeed = 0.1F;
    private float rotationSpeed;
    private boolean up;

    //Collision detection
    private float lastX, lastY;
    private boolean isInWalls;

    public MovingPart(float deceleration, float acceleration, float maxSpeed, float rotationSpeed) {
        this.deceleration = deceleration;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed*2;
        this.rotationSpeed = rotationSpeed;
    }

    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }


    public void setUp(boolean up) {
        this.up = up;
    }

    public void controls(Entity entity) {
        PositionPart part = entity.getPart(PositionPart.class);

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            float newCord = part.getY() + 1F;
            part.setY(newCord);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            float newCord = part.getX() + 1F;
            part.setX(newCord);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            float newCord = part.getY() - 1F;
            part.setY(newCord);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            float newCord = part.getX() - 1F;
            part.setX(newCord);
        }
    }


    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        final float dt = Gdx.graphics.getDeltaTime()*10;
        controls(entity);
    }

    public boolean isInWalls() {
        return isInWalls;
    }

    public void setIsInWalls(boolean isInWalls) {
        this.isInWalls = isInWalls;
    }

    public float getLastX() {
        return lastX;
    }

    public void setLastX(float lastX) {
        this.lastX = lastX;
    }

    public float getLastY() {
        return lastY;
    }

    public void setLastY(float lastY) {
        this.lastY = lastY;
    }
}
