package dk.sdu.mmmi.bomberman.OSGiCommonBomb.parts;

import dk.sdu.mmmi.bomberman.OSGiCommonBomb.services.BombSPI;
import dk.sdu.mmmi.bomberman.common.data.Entity;
import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.entityparts.EntityPart;

public class InventoryPart implements EntityPart {

    private BombSPI currentBomb;

    @Override
    public void process(GameData gameData, Entity entity) {public void setCurrentWeapon(BombSPI bomb) {
            this.currentBomb = bomb;
        }

    }
}
