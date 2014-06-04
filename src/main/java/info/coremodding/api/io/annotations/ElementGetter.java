package info.coremodding.api.io.annotations;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;

/**
 * @author James
 *         Gets elements marked with an annotation value
 */
public class ElementGetter
{
    
    /**
     * @param scanDir
     *            The directory to scan for jars
     * @param annotation
     *            The annotation to use
     * @return All constructors in that jar with the given annotation
     * @throws Exception
     *             Something mucked up.
     */
    public static Set<Constructor<?>> getConstructorElements(String scanDir, Annotation annotation) throws Exception
    {
        Set<Constructor<?>> _entries = new HashSet<>();
        if (new File(scanDir).isDirectory())
        {
            File[] files = new File(scanDir).listFiles();
            Reflections reflections = new Reflections();
            for (File file : files)
            {
                if (file.exists())
                {
                    if (file.getName().endsWith(".cjar"))
                    {
                        reflections.scan(file.toURI().toURL());
                        _entries.addAll((Collection<? extends Constructor<?>>) reflections.getConstructorsAnnotatedWith(annotation));
                    }
                }
            }
        }
        return _entries;
    }
    
    /**
     * @param scanDir
     *            The directory to scan for jars
     * @param annotation
     *            The annotation to use
     * @return All fields in that jar with the given annotation
     * @throws Exception
     *             Something mucked up.
     */
    public static Set<Field> getFieldElements(String scanDir, Annotation annotation) throws Exception
    {
        Set<Field> _entries = new HashSet<>();
        if (new File(scanDir).isDirectory())
        {
            File[] files = new File(scanDir).listFiles();
            Reflections reflections = new Reflections();
            for (File file : files)
            {
                if (file.exists())
                {
                    if (file.getName().endsWith(".cjar"))
                    {
                        reflections.scan(file.toURI().toURL());
                        _entries.addAll(reflections.getFieldsAnnotatedWith(annotation));
                    }
                }
            }
        }
        return _entries;
    }
    
    /**
     * @param scanDir
     *            The directory to scan for jars
     * @param annotation
     *            The annotation to use
     * @return All methods in that jar with the given annotation
     * @throws Exception
     *             Something mucked up.
     */
    public static Set<Method> getMethodElements(String scanDir, Annotation annotation) throws Exception
    {
        Set<Method> _entries = new HashSet<>();
        if (new File(scanDir).isDirectory())
        {
            File[] files = new File(scanDir).listFiles();
            Reflections reflections = new Reflections();
            for (File file : files)
            {
                if (file.exists())
                {
                    if (file.getName().endsWith(".cjar"))
                    {
                        reflections.scan(file.toURI().toURL());
                        _entries.addAll(reflections.getMethodsAnnotatedWith(annotation));
                    }
                }
            }
        }
        return _entries;
    }
    
    /**
     * @param scanDir
     *            The directory to scan for jars
     * @param annotation
     *            The annotation to use
     * @return All types in that jar with the given annotation
     * @throws Exception
     *             Something mucked up.
     */
    public static Set<Type> getTypeElements(String scanDir, Annotation annotation) throws Exception
    {
        Set<Type> _entries = new HashSet<>();
        if (new File(scanDir).isDirectory())
        {
            File[] files = new File(scanDir).listFiles();
            Reflections reflections = new Reflections();
            for (File file : files)
            {
                if (file.exists())
                {
                    if (file.getName().endsWith(".cjar"))
                    {
                        reflections.scan(file.toURI().toURL());
                        _entries.addAll(reflections.getTypesAnnotatedWith(annotation));
                    }
                }
            }
        }
        return _entries;
    }
}
