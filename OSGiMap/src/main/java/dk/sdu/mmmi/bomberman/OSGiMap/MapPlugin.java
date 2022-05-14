package dk.sdu.mmmi.bomberman.OSGiMap;


import dk.sdu.mmmi.bomberman.commonmap.Map;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.TiledMapPart;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;

public class MapPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        Entity tiledMap = new Map();
        tiledMap.add(new TiledMapPart("/assets/ColMap.tmx"));
        world.addEntity(tiledMap);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(tiledMap);
    }
}
