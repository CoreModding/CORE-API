package info.coremodding.api.plugin.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Info 
{
	String name();
	String version();
	boolean wantsPreInit() default false;
	boolean wantsPostInit() default false;
    public @interface PreInit {}
    public @interface Init {}
    public @interface PostInit {}
}
