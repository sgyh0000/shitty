package club.itguys.shitty.beans;

import club.itguys.shitty.beans.anno.Bean;
import club.itguys.shitty.beans.anno.Component;
import club.itguys.shitty.beans.anno.Repository;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FIXME 不应显示指定各个注解的factory, 改为try to get, 为null时再使用默认factory
 *
 * TODO {@link club.itguys.shitty.beans.Processor} 的配置获取
 * 
 * @author sgyh
 */
public final class BeanContext {

    private static final Map<Class<? extends Annotation>, BeanFactory> BEAN_FACTORY_MAP = new ConcurrentHashMap<>();

    private static final Map<String, Object> CONFIGURATION = new ConcurrentHashMap<>();

    static {
        BeanFactory defaultBeanFactory = new DefaultBeanFactory();
        BEAN_FACTORY_MAP.put(Bean.class, defaultBeanFactory);
        BEAN_FACTORY_MAP.put(Component.class, defaultBeanFactory);
        BEAN_FACTORY_MAP.put(Repository.class, defaultBeanFactory);
    }

    public static void setBeanInitializer(Class<? extends Annotation> annotationClass, BeanFactory beanFactory) {
        BEAN_FACTORY_MAP.put(annotationClass, beanFactory);
    }

    public static BeanFactory getBeanInitializer(Class<? extends Annotation> annotationClass) {
        BeanFactory beanFactory = BEAN_FACTORY_MAP.get(annotationClass);
        if (beanFactory == null) {
            throw new NullPointerException("NULL");
        }
        return beanFactory;
    }

    public static Collection<BeanFactory> getAllBeanInitializer() {
        return BEAN_FACTORY_MAP.values();
    }

    public static void setConfiguration(String key, Object value) {
        CONFIGURATION.put(key, value);
    }

    public static Object getConfiguration(String key) {
        return CONFIGURATION.get(key);
    }

    public static Set<Map.Entry<Class<? extends Annotation>, BeanFactory>> getAllBeanInitializers() {
        return BEAN_FACTORY_MAP.entrySet();
    }

}
