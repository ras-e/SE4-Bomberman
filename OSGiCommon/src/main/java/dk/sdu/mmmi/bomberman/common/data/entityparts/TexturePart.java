package dk.sdu.mmmi.bomberman.common.data.entityparts;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.utils.AssetsJarFileResolver;

import java.io.File;



public class TexturePart implements EntityPart{
    private Texture texture;
    private SpriteBatch batch;
    private Sprite sprite;
    private float x;
    private float y;

    public TexturePart(String fileName) {
        String jarUrl = java.nio.file.Paths.get(new File("").getAbsolutePath(),
                "bundles", "dk.sdu.mmmi.bomberman.OSGiCommon_1.0.0.SNAPSHOT.jar!", "assets", "jens.png").toString();
        AssetsJarFileResolver jfhr = new AssetsJarFileResolver();
//        jfhr.resolve(jarUrl);
        AssetManager am = new AssetManager(jfhr);

        am.load(jarUrl, Texture.class);
        am.finishLoading();

        texture = am.get(jarUrl, Texture.class);

        sprite = new Sprite(texture);

        am.dispose();
    }

    /**
     * Simple draw method
     * It'll start the sprite drawing process with .begin()
     * draw the texture on the coordinates x and y with .draw()
     * and it'll close the sprite drawing process with .end()
     * */
    @Override
    public void process(GameData gameData, Entity entity) {
        batch.begin();
        batch.draw(texture, x, y);
        batch.end();
    }
}
