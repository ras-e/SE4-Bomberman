package dk.sdu.mmmi.bomberman.common.services;

import dk.sdu.mmmi.bomberman.common.data.GameData;
import dk.sdu.mmmi.bomberman.common.data.World;

public interface IPostEntityProcessingService {

    void process(GameData gameData, World world);
}
