package info.coremodding.api.render;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

public class RenderUtils {
	
	public static String getOpenGLVersion() {
		return glGetString(GL_VERSION);
	}
	
	public static String[] getOpenGLVersionSplit() {
		return getOpenGLVersion().split(".");
	}
	
	public static int getOpenGLMajorVersion() {
		return Integer.valueOf(getOpenGLVersionSplit()[0]);
	}
	
	public static int getOpenGLMinorVersion() {
		return Integer.valueOf(getOpenGLVersionSplit()[1]);
	}
}
