package dk.sdu.mmmi.bomberman.common.data.entityparts;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;

public class CirclePart implements EntityPart {

    private float centreX;
    private float centreY;
    private float radius;

    public CirclePart(float centreX, float centreY, float radius) {
        this.centreX = centreX;
        this.centreY = centreY;
        this.radius = radius;
    }

    @Override
    public void process(GameData gameData, Entity entity) { }

    // Returns the x-coordinate of the circle's centre
    public float getCentreX() {
        return centreX;
    }

    //Sets the x-coordinate of the circle's centre
    public void setCentreX(float centreX) {
        this.centreX = centreX;
    }

    //Returns the y-coordinate of the circle's centre
    public float getCentreY() {
        return centreY;
    }

    //Sets the y-coordinate of the circle's centre
    public void setCentreY(float centreY) {
        this.centreY = centreY;
    }

    //Returns the circle's radius
    public float getRadius() {
        return radius;
    }

    //Sets the circle's radius
    public void setRadius(float radius) {
        this.radius = radius;
    }
}
