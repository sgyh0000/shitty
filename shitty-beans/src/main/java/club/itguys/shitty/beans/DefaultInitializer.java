package club.itguys.shitty.beans;

import club.itguys.shitty.beans.anno.Bean;
import club.itguys.shitty.beans.anno.Component;
import club.itguys.shitty.beans.anno.Repository;
import club.itguys.shitty.reflection.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sgyh
 */
public class DefaultInitializer implements Initializer {

    private InjectionProcessor injectionProcessor = new InjectionProcessor();

    @Override
    public void init(Map<String, Object> configuration, Map<Class<? extends Annotation>, BeanInitializer> beanInitializerMap) {
        try {
            if (configuration != null && configuration.size() != 0) {
                configuration.forEach(BeanContext::setConfiguration);
            }
            if (beanInitializerMap != null && beanInitializerMap.size() != 0) {
                beanInitializerMap.forEach(BeanContext::setBeanInitializer);
            }

            String basePackage = (String) BeanContext.getConfiguration("basePackage");
            List<Class<?>> classes = Reflections.getAnnotationClassUnderPackage(basePackage, Bean.class);
            BeanInitializer beanInitializer = BeanContext.getBeanInitializer(Bean.class);
            for (Class<?> clazz : classes) {
                BeanDefinationMap.addBeanDefination(beanInitializer.initBean(clazz));
            }

            classes = Reflections.getAnnotationClassUnderPackage(basePackage, Component.class);
            beanInitializer = BeanContext.getBeanInitializer(Component.class);
            for (Class<?> clazz : classes) {
                BeanDefinationMap.addBeanDefination(beanInitializer.initBean(clazz));
            }

            classes = Reflections.getAnnotationClassUnderPackage(basePackage, Repository.class);
            beanInitializer = BeanContext.getBeanInitializer(Repository.class);
            for (Class<?> clazz : classes) {
                BeanDefinationMap.addBeanDefination(beanInitializer.initBean(clazz));
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
    public void init(String basePackage, Map<Class<? extends Annotation>, BeanInitializer> beanInitializerMap) {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put("basePackage", basePackage);
        init(configuration, beanInitializerMap);
    }
}
