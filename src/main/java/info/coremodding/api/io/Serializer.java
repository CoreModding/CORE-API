package info.coremodding.api.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author James
 *         The serializer utility
 */
public class Serializer
{
    
    /**
     * @param bytes
     *            The bytes to deserialize
     * @return The object from the bytes
     * @throws IOException
     *             Something screwed up
     * @throws ClassNotFoundException
     *             Something screwed up
     */
    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException
    {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
    
    /**
     * @param obj
     *            The object to serialize
     * @return The bytes from the object
     * @throws IOException
     *             Something screwed up
     */
    public static byte[] serialize(Object obj) throws IOException
    {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }
}
