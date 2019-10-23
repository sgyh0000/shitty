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
 *
 * TODO {@link club.itguys.shitty.beans.Processor} 的配置获取
 * 
 * @author sgyh
 */
public final class BeanContext {

    private static final Map<Class<? extends Annotation>, BeanFactory> BEAN_FACTORY_MAP = new ConcurrentHashMap<>();

    private static final Map<Class<? extends Annotation>, Processor> PROCESSOR_MAP = new ConcurrentHashMap<>();

    private static final Map<String, Object> CONFIGURATION = new ConcurrentHashMap<>();

    private static final BeanFactory DEFAULT_BEAN_FACTORY = new DefaultBeanFactory();

    public static void setBeanInitializer(Class<? extends Annotation> annotationClass, BeanFactory beanFactory) {
        BEAN_FACTORY_MAP.put(annotationClass, beanFactory);
    }

    public static BeanFactory getBeanInitializer(Class<? extends Annotation> annotationClass) {
        BeanFactory beanFactory = BEAN_FACTORY_MAP.get(annotationClass);
        if (beanFactory == null) {
            return DEFAULT_BEAN_FACTORY;
        }
        return beanFactory;
    }

    public static Collection<BeanFactory> getAllBeanFactory() {
        return BEAN_FACTORY_MAP.values();
    }

    public static void setConfiguration(String key, Object value) {
        CONFIGURATION.put(key, value);
    }

    public static Object getConfiguration(String key) {
        return CONFIGURATION.get(key);
    }

    public static Set<Map.Entry<Class<? extends Annotation>, BeanFactory>> getAllBeanFactoryEntry() {
        return BEAN_FACTORY_MAP.entrySet();
    }

    public static void setProcessor(Class<? extends Annotation> annotationClass, Processor processor) {
        PROCESSOR_MAP.put(annotationClass, processor);
    }

    public static Processor getProcessor(Class<? extends Annotation> annotationClass) {
        Processor processor = PROCESSOR_MAP.get(annotationClass);
        if (processor == null) {
            throw new NullPointerException("Processor is null");
        }
        return processor;
    }

    public static Set<Map.Entry<Class<? extends Annotation>, Processor>> getAllProcessorEntry() {
        return PROCESSOR_MAP.entrySet();
    }

    public static Collection<Processor> getAllProcessors() {
        return PROCESSOR_MAP.values();
    }

}
