package info.coremodding.api.plugin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Info
{
    public @interface Init
    {
    }
    
    public @interface PostInit
    {
    }
    
    public @interface PreInit
    {
    }
    
    String name();
    
    String version();
    boolean wantsPostInit() default false;
    boolean wantsPreInit() default false;
}
