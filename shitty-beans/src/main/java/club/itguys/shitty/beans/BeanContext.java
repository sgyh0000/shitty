package club.itguys.shitty.beans;

import club.itguys.shitty.beans.anno.Bean;
import club.itguys.shitty.beans.anno.Component;
import club.itguys.shitty.beans.anno.Repository;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sgyh
 */
public final class BeanContext {

    private static final Map<Class<? extends Annotation>, BeanInitializer> BEAN_INITIALIZER_MAP = new ConcurrentHashMap<>();

    private static final Map<String, Object> CONFIGURATION = new ConcurrentHashMap<>();

    static {
        BeanInitializer defaultBeanInitializer = new DefaultBeanInitializer();
        BEAN_INITIALIZER_MAP.put(Bean.class, defaultBeanInitializer);
        BEAN_INITIALIZER_MAP.put(Component.class, defaultBeanInitializer);
        BEAN_INITIALIZER_MAP.put(Repository.class, defaultBeanInitializer);
    }

    public static void setBeanInitializer(Class<? extends Annotation> annotationClass, BeanInitializer beanInitializer) {
        BEAN_INITIALIZER_MAP.put(annotationClass, beanInitializer);
    }

    public static BeanInitializer getBeanInitializer(Class<? extends Annotation> annotationClass) {
        BeanInitializer beanInitializer = BEAN_INITIALIZER_MAP.get(annotationClass);
        if (beanInitializer == null) {
            throw new NullPointerException("NULL");
        }
        return beanInitializer;
    }

    public static Collection<BeanInitializer> getAllBeanInitializer() {
        return BEAN_INITIALIZER_MAP.values();
    }

    public static void setConfiguration(String key, Object value) {
        CONFIGURATION.put(key, value);
    }

    public static Object getConfiguration(String key) {
        return CONFIGURATION.get(key);
    }

}
