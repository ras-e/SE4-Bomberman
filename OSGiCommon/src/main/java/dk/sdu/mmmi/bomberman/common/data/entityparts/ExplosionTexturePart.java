package dk.sdu.mmmi.bomberman.common.data.entityparts;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;

public class ExplosionTexturePart implements EntityPart {

    private int mapCols;
    private int mapRows;
    private String srcPath;

    public ExplosionTexturePart() {

    }

    public ExplosionTexturePart(int mapCols, int mapRows, String srcPath) {
        this.mapCols = mapCols;
        this.mapRows = mapRows;
        this.srcPath = srcPath;
    }

    public int getMapCols() {
        return mapCols;
    }

    public int getMapRows() {
        return mapRows;
    }

    public String getSrcPath() {
        return srcPath;
    }

    @Override
    public void processPart(Entity entity, GameData gameData, World world) {

    }
}
