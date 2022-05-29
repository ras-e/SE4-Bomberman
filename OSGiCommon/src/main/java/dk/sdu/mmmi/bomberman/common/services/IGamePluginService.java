package dk.sdu.mmmi.bomberman.common.services;

import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;

public interface IGamePluginService {
    /**
     * Responsible for starting game plugins
     */
    void start(GameData gameData, World world);

    /**
     * Responsible for stopping game plugins
     */
    void stop(GameData gameData, World world);
}
