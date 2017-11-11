package se.andolf.lift.auth.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Thomas on 2017-06-23.
 */
public class FileUtils {

    public static String read(String fileName){

        final ClassLoader classLoader = FileUtils.class.getClassLoader();
        try {
            return IOUtils.toString(classLoader.getResourceAsStream(fileName), Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
