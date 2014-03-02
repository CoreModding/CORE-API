package info.coremodding.api.io;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * @author James Load jars at runtime
 */
public class ClassPathHack {

    private List<Class<?>> parameters;

    /**
     * @param f      The file to add
     * @param method The method name
     * @return The return value of the method
     * @throws IOException
     */
    public Object addFile(File f, String method) throws IOException {
        return addURL(f.toURI().toURL(), method);
    }

    /**
     * @param s      The file to add
     * @param method The method name
     * @return The return value of the method
     * @throws IOException
     */
    public Object addFile(String s, String method) throws IOException {
        File f = new File(s);
        return addFile(f, method);
    }

    /**
     * @param obj The parameter to add
     * @return The class (for stacked methods)
     */
    public ClassPathHack addParameter(Object obj) {
        this.parameters.add((Class<?>) obj);
        return this;
    }

    /**
     * @param u       The URL to add
     * @param methodn The method name
     * @return The return value of the method
     * @throws IOException
     */
    public Object addURL(URL u, String methodn) throws IOException {
        @SuppressWarnings("resource")
        URLClassLoader sysloader = (URLClassLoader) ClassLoader
                .getSystemClassLoader();
        Class<URLClassLoader> sysclass = URLClassLoader.class;

        try {
            Method method = sysclass.getDeclaredMethod(methodn,
                    (Class[]) this.parameters.toArray());
            method.setAccessible(true);
            Object obj = method.invoke(sysloader, u);
            sysloader.close();
            return obj;
        } catch (Throwable t) {
            t.printStackTrace();
            sysloader.close();
            throw new IOException(
                    "Error, could not add URL to system classloader");
        }
    }
}