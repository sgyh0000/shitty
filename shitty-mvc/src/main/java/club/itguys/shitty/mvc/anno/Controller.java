package club.itguys.shitty.mvc.anno;

import java.lang.annotation.*;

/**
 * @author sgyh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.TYPE
})
public @interface Controller {

    String name() default "";

}
