package club.itguys.shitty.beans;

import java.lang.reflect.Field;

/**
 * name为空时进行type match
 *
 * @author sgyh
 */
public class BeanMatcher {

    public static Object match(String beanName, Field field) {
        if (beanName == null || "".equals(beanName)) {
            return typeMatch(field);
        } else {
            return nameMatch(beanName);
        }
    }

    private static Object nameMatch(String beanName) {
        return BeanDefinationMap.getBean(beanName);
    }

    private static Object typeMatch(Field field) {
        Class type = field.getType();
        return BeanDefinationMap.getBean(type);
    }

}
