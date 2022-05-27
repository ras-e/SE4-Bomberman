package dk.sdu.mmmi.bomberman.common.utils;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import java.io.File;


public class AssetsJarFileResolver implements FileHandleResolver {

    private Bundle sourceBundle;

    /**
     * Creates a FileHandleResolver that gets a source class that it finds a bundle from. This bundle is used to
     * convert the relative path given to a full jar-file path.
     * NB. It finds the bundle from the bundles classloader, so make sure the constructor is called from the right
     * context.
     * To be certain use the constructor where the sourceBundle can be given as a argument.
     * @param sourceClass The class from the wished bundles class loader.
     */
    public AssetsJarFileResolver(Class sourceClass) {
        sourceBundle = FrameworkUtil.getBundle(sourceClass);
    }

    /**
     * Creates a FileHandleResolver that converts relative paths to a jar-file path using the given bundle.
     * The files are loaded from the given bundle.
     * @param sourceBundle
     */
    public AssetsJarFileResolver(Bundle sourceBundle) {
        this.sourceBundle = sourceBundle;
    }

    @Override
    public FileHandle resolve(String fileName) {
        final String jarPath = getPath(fileName);
        return new JarFileHandleStream(jarPath);
    }

    private String getPath(String relativePath) {
        final String location = sourceBundle.getLocation();
        final String path = location.split(":")[1];

        String jarUrl = java.nio.file.Paths.get(new File("").getAbsolutePath(),
                path + "!", relativePath).toString();
        jarUrl = jarUrl.replace("\\", "/");
        return jarUrl;
    }
}