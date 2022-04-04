package dk.sdu.mmmi.bomberman.common.data.entityparts;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;

/**
 *
 * @author madsfalken
 */
public class TiledMapPart implements EntityPart {

    private String srcPath;

    /**
     * Image width
     */
    private float width;

    /**
     * Image height
     */
    private float height;

    /**
     * Creates a new texture part with a given source path
     *
     * @param srcPath String
     */
    public TiledMapPart(String srcPath) {
        this.srcPath = srcPath;
        this.width = 20;
        this.height = 20;
    }

    /**
     * gets path to texture source
     *
     * @return String
     */
    public String getSrcPath() {
        return srcPath;
    }

    /**
     * Sets the path to the texture source
     *
     * @param srcPath String
     */
    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}