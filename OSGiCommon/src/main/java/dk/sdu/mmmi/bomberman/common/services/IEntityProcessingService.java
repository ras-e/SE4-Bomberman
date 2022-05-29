package dk.sdu.mmmi.bomberman.common.services;

import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;

public interface IEntityProcessingService {

    /**
     * Responsible for updating various game logic
     * Entities might employ or need to function
     */
    void process(GameData gameData, World world);
}