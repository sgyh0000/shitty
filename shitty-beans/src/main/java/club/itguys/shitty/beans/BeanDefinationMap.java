package club.itguys.shitty.beans;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sgyh
 */
public final class BeanDefinationMap {

    private static final Map<String, BeanDefination> BEAN_NAME_DEFINATION_MAP = new ConcurrentHashMap<>(256);

    private static final Map<Class, BeanDefination> BEAN_TYPE_DEFINATION_MAP = new ConcurrentHashMap<>(256);

    public static BeanDefination getBeanDefination(String name) {
        BeanDefination beanDefination = BEAN_NAME_DEFINATION_MAP.get(name);
        if (beanDefination == null) {
            throw new NullPointerException("no such bean named: " + name);
        }
        return beanDefination;
    }

    public static Object getBean(String name) {
        BeanDefination beanDefination = getBeanDefination(name);
        return beanDefination.getBean();
    }

    public static void addBeanDefination(BeanDefination beanDefination) {
        String beanName = beanDefination.getBeanName();
        Class type = beanDefination.getBeanClass();
        if (BEAN_NAME_DEFINATION_MAP.get(beanName) != null) {
            throw new UnsupportedOperationException("The bean named " + beanName + " has exists");
        }
        if (BEAN_TYPE_DEFINATION_MAP.get(type) != null) {
            throw new UnsupportedOperationException("The bean type " + type + " has exists");
        }
        if (!"".equals(beanDefination.getBeanName()) || beanDefination.getBeanName() != null) {
            BEAN_NAME_DEFINATION_MAP.put(beanDefination.getBeanName(), beanDefination);
        }
        BEAN_TYPE_DEFINATION_MAP.put(type, beanDefination);
    }

    public static BeanDefination getBeanDefination(Class type) {
        BeanDefination beanDefination = BEAN_TYPE_DEFINATION_MAP.get(type);
        if (beanDefination == null) {
            throw new NullPointerException("no such type bean: " + type);
        }
        return beanDefination;
    }

    public static Object getBean(Class type) {
        BeanDefination beanDefination = getBeanDefination(type);
        return beanDefination.getBean();
    }

}
