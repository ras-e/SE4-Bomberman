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


    /**
     * jarUrl contains the path from our target folder to the location of the desired image
     * as the code currently is, all images have to stored in our Common resources folder.
     *
     * @param fileName
     */
    //TODO:Currently the target path for texture begins at our runner folder, that needs to be changed to the common folder
    public TexturePart(String fileName){
        String jarUrl = java.nio.file.Paths.get(new File("stuff").getAbsolutePath(),
                 fileName).toString();

        AssetsJarFileResolver jfhr = new AssetsJarFileResolver();
        AssetManager am = new AssetManager(jfhr);


//        assetManager.load(jarUrl, Texture.class);
//        assetManager.finishLoading();

        texture = am.get(jarUrl, Texture.class);
        sprite = new Sprite(texture);
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
