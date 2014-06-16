package info.coremodding.api.render;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

/**
 * Gets data about rendering environment
 */
public class RenderUtils
{

    /**
     * @return The OpenGL major version
     */
    public static int getOpenGLMajorVersion()
    {
        return Integer.valueOf(getOpenGLVersionSplit()[0]);
    }

    /**
     * @return The OpenGL minor version
     */
    public static int getOpenGLMinorVersion()
    {
        return Integer.valueOf(getOpenGLVersionSplit()[1]);
    }

    /**
     * @return The OpenGL version
     */
    public static String getOpenGLVersion()
    {
        return glGetString(GL_VERSION);
    }

    /**
     * @return The OpenGL version
     */
    public static String[] getOpenGLVersionSplit()
    {
        return getOpenGLVersion().split(".");
    }
}
