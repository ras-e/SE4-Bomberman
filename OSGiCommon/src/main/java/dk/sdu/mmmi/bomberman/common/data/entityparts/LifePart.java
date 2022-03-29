package dk.sdu.mmmi.bomberman.common.data.entityparts;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;

public class LifePart implements EntityPart{
    private boolean dead = false;
    private int life;
    private boolean isHit = false;

    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        //When hit 1 life is retracted from total life points and kills the entity when life is <= 0
        if (isHit) {
            life =- 1;
            isHit = false;
        }
        if (life <= 0) {
            dead = true;
        }
    }
}
