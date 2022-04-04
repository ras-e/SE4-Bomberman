package dk.sdu.mmmi.bomberman.common.data.entityparts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;

public class TexturePart implements EntityPart{
    private Texture texture;
    private SpriteBatch batch;
    private float x;
    private float y;

    public TexturePart(String fileName, float x, float y){
        texture = new Texture(Gdx.files.internal(fileName));
        batch = new SpriteBatch();
        this.x = x;
        this.y = y;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        batch.begin();
        batch.draw(texture, x, y);
        batch.end();
    }
}
