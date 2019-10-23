package club.itguys.shitty.mvc.anno;

import club.itguys.shitty.mvc.core.HttpMethod;

import java.lang.annotation.*;

/**
 * @author sgyh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface Path {

    String path();

    HttpMethod method() default HttpMethod.ALL;

}
