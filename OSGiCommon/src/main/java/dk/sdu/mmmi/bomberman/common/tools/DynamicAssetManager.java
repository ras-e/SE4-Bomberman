package dk.sdu.mmmi.bomberman.common.tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class DynamicAssetManager {

    public final AssetManager assetManager = new AssetManager();

    private ArrayList<String> images = new ArrayList<>();

    public DynamicAssetManager() {
    }

    public void loadAssets(){
        images.add("assets/jens.png");

        FileLoader.loadFiles(images.toArray(new String[images.size()]), getClass());

        loadImages();

        assetManager.finishLoading();
    }

    private void loadImages(){
        for (String image: images){
            assetManager.load(image, Texture.class);
        }
    }

    public Texture getTexture(String filename){
        return assetManager.get(filename, Texture.class);
    }

    public void update(){
        assetManager.update();
    }
}
