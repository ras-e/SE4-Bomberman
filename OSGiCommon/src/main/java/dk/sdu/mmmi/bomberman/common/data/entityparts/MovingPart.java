package dk.sdu.mmmi.bomberman.common.data.entityparts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;

public class MovingPart implements EntityPart {
    private float deceleration;
    private float acceleration = 25;
    private float maxSpeed = 75;
    private float rotationSpeed;
    private boolean left, right, up, down;

    public MovingPart(float deceleration, float acceleration, float maxSpeed, float rotationSpeed) {
        this.deceleration = deceleration;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
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

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void controls(Entity entity, float dt){
        PositionPart part = entity.getPart(PositionPart.class);
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            float accele = part.getY()+acceleration*dt;
            if (accele>maxSpeed){
                part.setY(accele);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            float accele = part.getX()+acceleration*dt;
            if (accele>maxSpeed){
                part.setX(accele);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            float accele = part.getY()-acceleration*dt;
            if (accele>maxSpeed){
                part.setY(accele);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            float accele = part.getX()-acceleration*dt;
            if (accele>maxSpeed){
                part.setX(accele);
            }
        }
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        final float dt = Gdx.graphics.getDeltaTime()*2000;
        controls(entity,dt);
    }
}
