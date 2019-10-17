package club.itguys.shitty.reflection;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author sgyh
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

}
