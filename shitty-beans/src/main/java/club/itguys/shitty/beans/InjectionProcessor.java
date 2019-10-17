package club.itguys.shitty.beans;

import club.itguys.shitty.beans.anno.Injection;
import club.itguys.shitty.reflection.Reflections;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author sgyh
 */
public class InjectionProcessor {

    public void process() throws Exception {
        Map<Field, Injection> injectionMap = Reflections.getAnnotatedFields((String) BeanContext.getConfiguration("basePackage"), Injection.class);

        injectionMap.forEach((field, injection) -> {

            Class type = field.getDeclaringClass();
            BeanDefination beanDefination;
            try {
                beanDefination = BeanDefinationMap.getBeanDefination(type);
            } catch (NullPointerException e) {
                e.printStackTrace();
                return;
            }

            if (!field.isAccessible()) {
                field.setAccessible(true);
            }

            String injectionName = injection.name();
            Object injectionBean = BeanMatcher.match(injectionName, field);

            try {
                field.set(beanDefination.getBean(), injectionBean);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

}
