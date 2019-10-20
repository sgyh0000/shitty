package club.itguys.shitty.beans;

import club.itguys.shitty.reflection.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 获取自定义注解处理
 *
 * @author sgyh
 */
public class DefaultInitializer implements Initializer {

    private Processor injectionProcessor = new InjectionProcessor();

    @Override
    public void init(Map<String, Object> configuration, Map<Class<? extends Annotation>, BeanFactory> beanInitializerMap) {
        try {
            if (configuration != null && configuration.size() != 0) {
                configuration.forEach(BeanContext::setConfiguration);
            }
            if (beanInitializerMap != null && beanInitializerMap.size() != 0) {
                beanInitializerMap.forEach(BeanContext::setBeanInitializer);
            }

            String basePackage = (String) BeanContext.getConfiguration("basePackage");
            List<Class<?>> classes;
            for (Map.Entry<Class<? extends Annotation>, BeanFactory> beanFactoryEntry : BeanContext.getAllBeanInitializers()) {
                classes = Reflections.getAnnotationClassUnderPackage(basePackage, beanFactoryEntry.getKey());
                for (Class clazz : classes) {
                    BeanDefinationMap.addBeanDefination(beanFactoryEntry.getValue().initBean(clazz));
                }
            }

            injectionProcessor.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(String basePackage) {
        init(basePackage, null);
    }

    @Override
    public void init(String basePackage, Map<Class<? extends Annotation>, BeanFactory> beanInitializerMap) {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put("basePackage", basePackage);
        init(configuration, beanInitializerMap);
    }
}
