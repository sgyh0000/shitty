package club.itguys.shitty.mvc.anno;

import java.lang.annotation.*;

/**
 * @author sgyh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.PARAMETER
})
public @interface Param {

    String name();

    boolean isRequired() default false;

    String defaultValue() default "";

}
