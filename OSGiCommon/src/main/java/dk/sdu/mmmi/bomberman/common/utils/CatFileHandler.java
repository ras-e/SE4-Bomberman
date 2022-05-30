package dk.sdu.mmmi.bomberman.common.utils;

import com.badlogic.gdx.files.FileHandle;

import java.io.InputStream;

public class CatFileHandler extends FileHandle {

    protected String resourceName;

    private Class aClass;

    public CatFileHandler(String resourceName, Class aClass){
        super(String.valueOf(aClass.getResource(resourceName)));
        this.resourceName = resourceName;
        this.aClass = aClass;
    }

    @Override
    public InputStream read() {
        return aClass.getResourceAsStream(this.resourceName);
    }
}
