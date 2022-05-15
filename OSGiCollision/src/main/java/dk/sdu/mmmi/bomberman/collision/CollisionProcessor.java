package dk.sdu.mmmi.bomberman.collision;


import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;
import dk.sdu.mmmi.bomberman.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.TiledMapPart;
import dk.sdu.mmmi.bomberman.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.bomberman.commonmap.Tile;
import dk.sdu.mmmi.bomberman.commonplayer.Player;

public class CollisionProcessor implements IPostEntityProcessingService {

    private Map map;
    MapObjects objects;
    MapObjects border;


    @Override
    public void process(GameData gameData, World world) {
        if (world == null) {
            throw new IllegalArgumentException("World defined as null");
        }
        if (map == null) {
            for (Entity Tile : world.getEntities(Tile.class)) {
                TiledMapPart tiledMap = Tile.getPart(TiledMapPart.class);
                map = new TmxMapLoader().load(tiledMap.getSrcPath());
                objects = map.getLayers().get("BarrelLayer").getObjects();
                border = map.getLayers().get("Walls").getObjects();
            }
        }
        //TODO: Damage logic (Bomb), additionally add logic for rectangle so that moving through bomb is not possible.

        //Cannot move through walls using rectangle overlaps
        for (Entity player : world.getEntities(Player.class)) {
            MovingPart movingPart = player.getPart(MovingPart.class);
            PositionPart positionPart = player.getPart(PositionPart.class);

            for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
                Rectangle rectangle = rectangleObject.getRectangle();
                Rectangle playerRectangle = new Rectangle(positionPart.getX(), positionPart.getY(), 35, 35);

                if (Intersector.overlaps(rectangle, playerRectangle)) {
                    movingPart.setIsInWalls(true);
                }
            }
            for (RectangleMapObject rectangleObject : border.getByType(RectangleMapObject.class)) {
                Rectangle rectangle = rectangleObject.getRectangle();
                Rectangle playerRectangle = new Rectangle(positionPart.getX(), positionPart.getY(), 35, 35);

                if (Intersector.overlaps(rectangle, playerRectangle)) {
                    movingPart.setIsInWalls(true);
                }
            }

            if (!movingPart.isInWalls()) {
                movingPart.setLastX(positionPart.getX());
                movingPart.setLastY(positionPart.getY());
            }
        }
    }
}
