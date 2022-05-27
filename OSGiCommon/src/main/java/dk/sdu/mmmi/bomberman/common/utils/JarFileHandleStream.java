package dk.sdu.mmmi.bomberman.common.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.files.FileHandleStream;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcs
 */
public class JarFileHandleStream extends FileHandleStream {

    private JarFile jarFile = null;
    private String jarRelResDir;
    private String jarFilePath;

    /**
     *
     * @param path
     *
     *
     */
    public JarFileHandleStream(String path) {
        super(path);
        try {
            String[] args = path.split("!");
            jarRelResDir = args[1].substring(1);

            jarFilePath = args[0];
            jarFile = new JarFile(jarFilePath);
        } catch (IOException ex) {
            Logger.getLogger(JarFileHandleStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public InputStream read() {

        InputStream is = null;
        try {
            is = jarFile.getInputStream(jarFile.getEntry(jarRelResDir));
        } catch (IOException ex) {
            Logger.getLogger(JarFileHandleStream.class.getName()).log(Level.SEVERE, null, ex);
        }

        return is;
    }

    @Override
    public OutputStream write(boolean overwrite) {
        return super.write(overwrite);
    }

    @Override
    public FileHandle parent() {
        File file = new File(jarRelResDir);
        String parent = file.getParent();
        parent = parent.replace('\\','/');
        String path = jarFilePath + "!/" + parent;
        return new JarFileHandleStream(path);
    }

    @Override
    public byte[] readBytes() {
        InputStream input = read();
        try {
            return StreamUtils.copyStreamToByteArray(input, estimateLength());
        } catch (IOException ex) {
            throw new GdxRuntimeException("Error reading file: " + this, ex);
        } finally {
            StreamUtils.closeQuietly(input);
        }
    }

    @Override
    public FileHandle sibling(String name) {
        final FileHandle parent = parent();
        return parent.child(name);
    }

    private int estimateLength () {
        int length = (int)length();
        return length != 0 ? length : 512;
    }
}
