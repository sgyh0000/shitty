package club.itguys.shitty.beans;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

/**
 * @author sgyh
 */
public interface BeanFactory {

    <T> BeanDefination<T> initBean(Class<T> tClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

}
