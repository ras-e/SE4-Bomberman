package dk.sdu.mmmi.bomberman.common.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AssetLoader {
    private static final String path = "\\SE4-Bomberman\\OSGiPlayer\\src\\main\\resources\\assets\\";

    public static String getAssetPath(String filename) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + path + filename;
    }
}
