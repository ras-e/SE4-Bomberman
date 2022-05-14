package dk.sdu.mmmi.bomberman.OSGiCommonBomb.services;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;

public interface BombSPI {

    /**
     * Get the name of the specific Bomb
     * @return
     */
    String getName();


    /**
     * Get the path to the icon of the Bomb
     * @return
     */
    String getIconPath();


    /**
     * Place a bomb of the specific type.
     * @param actor
     * @param gameData
     * @param explosionPower
     * @param world
     */
    void createBomb(Entity actor, GameData gameData, float explosionPower, World world);
}

