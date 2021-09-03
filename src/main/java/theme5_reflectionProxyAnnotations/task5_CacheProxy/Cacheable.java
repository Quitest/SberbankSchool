package theme5_reflectionProxyAnnotations.task5_CacheProxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {
    CacheType cacheType() default CacheType.RAM;

    String pathToCache() default "";
}

