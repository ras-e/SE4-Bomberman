package dk.sdu.mmmi.bomberman.common.data.entityparts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;

public class SpritePart implements EntityPart{

    private SpriteBatch batch;
    private Texture sprite;

    public SpritePart(Texture sprite) {
        this.sprite = sprite;
        batch = new SpriteBatch();
    }


    @Override
    public void process(GameData gameData, Entity entity) {
        batch.begin();
        batch.draw(getSprite(),64 ,64 );
        batch.end();
    }

    public Texture getSprite() {
        return sprite;
    }
}
