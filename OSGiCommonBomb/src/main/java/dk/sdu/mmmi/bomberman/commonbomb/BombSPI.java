package dk.sdu.mmmi.bomberman.commonbomb;

import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;

public interface BombSPI {
    Entity createBomb(Entity e, GameData gameData);
}
