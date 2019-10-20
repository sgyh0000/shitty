package club.itguys.shitty.beans;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author sgyh
 */
public interface Initializer {

    void init(Map<String, Object> configuration, Map<Class<? extends Annotation>, BeanFactory> beanInitializerMap);

    void init(String basePackage);

    void init(String basePackage, Map<Class<? extends Annotation>, BeanFactory> beanInitializerMap);

}
