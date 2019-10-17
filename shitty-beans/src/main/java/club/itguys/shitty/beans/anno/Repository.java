package club.itguys.shitty.beans.anno;

import java.lang.annotation.*;

/**
 * @author sgyh
 */
@Target({
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Repository {

    String name() default "";

}
