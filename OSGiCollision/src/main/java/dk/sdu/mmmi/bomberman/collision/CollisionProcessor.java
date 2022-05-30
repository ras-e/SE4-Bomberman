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
import dk.sdu.mmmi.bomberman.common.data.entityparts.LifePart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.bomberman.common.data.entityparts.TiledMapPart;
import dk.sdu.mmmi.bomberman.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.bomberman.commonmap.Tile;

public class CollisionProcessor implements IPostEntityProcessingService {

    private Map map;
    MapObjects objects;
    MapObjects border;

    @Override
    public void process(GameData gameData, World world) {
        if (world == null) {
            throw new IllegalArgumentException("World defined as null");
        }
        // Retrives the tile layers (walls and objects defined in ColMap map file)
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
        //Entity collision code
        for (Entity entity : world.getEntities()) {
            for (Entity collisionDetection : world.getEntities()) {
                // get parts on entities
                LifePart entityLife = entity.getPart(LifePart.class);
                MovingPart entityMoving = entity.getPart(MovingPart.class);
                PositionPart entityPosition = entity.getPart(PositionPart.class);

                //Loop through the objects and set them as rectangles
                for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
                    Rectangle rectangle = rectangleObject.getRectangle();
                    //Give entity a defined rectangle
                    Rectangle entityRectangle = new Rectangle(entityPosition.getX(), entityPosition.getY(), 35, 35);
                    //If entity overlaps , setIsInWalls to true
                    if (Intersector.overlaps(rectangle, entityRectangle)) {
                        entityMoving.setIsInWalls(true);
                    }
                }
                //Loop through the objects and set them as rectangles
                for (RectangleMapObject rectangleObject : border.getByType(RectangleMapObject.class)) {
                    Rectangle rectangle = rectangleObject.getRectangle();
                    Rectangle entityRectangle = new Rectangle(entityPosition.getX(), entityPosition.getY(), 35, 35);
                    //If entity overlaps , setIsInWalls to true
                    if (Intersector.overlaps(rectangle, entityRectangle)) {
                        entityMoving.setIsInWalls(true);
                    }
                }
                //If in walls, set to last x,y coords
                if (!entityMoving.isInWalls()) {
                    entityMoving.setLastX(entityPosition.getX());
                    entityMoving.setLastY(entityPosition.getY());
                }
            }
        }
    }
}

