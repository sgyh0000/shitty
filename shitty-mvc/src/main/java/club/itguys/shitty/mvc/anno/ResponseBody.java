package club.itguys.shitty.mvc.anno;

import java.lang.annotation.*;

/**
 * @author sgyh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface ResponseBody {
}
