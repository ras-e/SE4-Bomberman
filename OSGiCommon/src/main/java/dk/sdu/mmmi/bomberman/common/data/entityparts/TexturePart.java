package dk.sdu.mmmi.bomberman.common.data.entityparts;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.tools.DynamicAssetManager;
import dk.sdu.mmmi.bomberman.common.utils.AssetsJarFileResolver;

import java.io.File;



public class TexturePart implements EntityPart{
    private Texture texture;
    private String TextureFileName;
    private SpriteBatch batch;
    private Sprite sprite;
    private float x;
    private float y;

    public TexturePart(String fileName) {
        TextureFileName = fileName;
    }

    /**
     * Simple draw method
     * It'll start the sprite drawing process with .begin()
     * draw the texture on the coordinates x and y with .draw()
     * and it'll close the sprite drawing process with .end()
     * */
    @Override
    public void process(GameData gameData, Entity entity) {
        DynamicAssetManager am = gameData.getAssetManager();
        texture = am.getTexture(TextureFileName);
        sprite = new Sprite(texture);
        batch = new SpriteBatch();
        batch.begin();
        batch.draw(texture, 0, 0, 100, 100);
        batch.end();
    }
}
