package dk.sdu.mmmi.bomberman.common.data.entityparts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.utils.AssetsJarFileResolver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


public class TexturePart implements EntityPart{
    private Texture texture;
    private SpriteBatch batch;
    private Sprite sprite;
    private float x;
    private float y;


    /**
     * jarUrl contains the path from our target folder to the location of the desired image
     * as the code currently is, all images have to stored in our Common resources folder.
     *
     * @param fileName
     */
    public TexturePart(String fileName) {
        String jarUrl = java.nio.file.Paths.get(new File("assets").getAbsolutePath()/*"dk.sdu.mmmi.bomberman.OSGiCommon_1.0.0.SNAPSHOT.jar","maps"*/,fileName).toString();
//        AssetsJarFileResolver jfhr = new AssetsJarFileResolver();
//        AssetManager am = new AssetManager(jfhr);
//        am.load(jarUrl, Texture.class);
//
////        assetManager.load(jarUrl, Texture.class);
////        assetManager.finishLoading();
//
//        texture = am.get(jarUrl, Texture.class);
//        am.finishLoading();
//
//        sprite = new Sprite(texture);
        AssetManager am = new AssetManager();
        am.load(jarUrl, Texture.class);
        am.finishLoading();
        texture = am.get(jarUrl, Texture.class);
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
