package club.itguys.shitty.beans;

import club.itguys.shitty.beans.anno.Bean;
import club.itguys.shitty.beans.anno.Component;
import club.itguys.shitty.beans.anno.Repository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author sgyh
 */
public class DefaultBeanInitializer implements BeanInitializer {
    @Override
    public <T> BeanDefination<T> initBean(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        T t = constructor.newInstance();

        Annotation[] annotations = clazz.getDeclaredAnnotations();
        String beanName = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Bean) {
                Bean bean = (Bean) annotation;
                beanName = bean.name();
                break;
            }
            if (annotation instanceof Component) {
                Component component = (Component) annotation;
                beanName = component.name();
                break;
            }
            if (annotation instanceof Repository) {
                Repository repository = (Repository) annotation;
                beanName = repository.name();
                break;
            }
        }
        return new DefaultBeanDefination<>(beanName, t, clazz);
    }
}
