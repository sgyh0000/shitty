package club.itguys.shitty.beans;

import club.itguys.shitty.beans.anno.Injection;
import club.itguys.shitty.reflection.Reflections;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * {@link club.itguys.shitty.beans.anno.Injection}
 * Injection注解的处理器, 主要作用: 将Bean注入, 若指定name, 按照name注入, 若没有, 根据class注入
 * @author sgyh
 */
public class InjectionProcessor implements Processor {

    @Override
    public void process() throws Exception {
        Map<Field, Injection> injectionMap = Reflections.getAnnotatedFields((String) BeanContext.getConfiguration("basePackage"), Injection.class);

        injectionMap.forEach((field, injection) -> {

            Class type = field.getDeclaringClass();
            BeanDefination beanDefination = BeanDefinationMap.getBeanDefination(type);

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
