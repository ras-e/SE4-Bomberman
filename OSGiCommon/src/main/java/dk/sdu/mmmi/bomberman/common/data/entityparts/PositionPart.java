package dk.sdu.mmmi.bomberman.common.data.entityparts;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;

public class PositionPart implements EntityPart {
    private float x;
    private float y;
    private float radians;

    // sets the x and y coordinate for the entity
    public PositionPart(float x, float y, float radians) {
        this.setX(x);
        this.setY(y);
        this.radians = radians;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadians() {
        return radians;
    }

    public void setX(float newX) {
        this.x = newX;
    }

    public void setY(float newY) {
        this.y = newY;
    }

    public void setPosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    @Override
    public void process(GameData gameData, Entity entity) {

    }
}