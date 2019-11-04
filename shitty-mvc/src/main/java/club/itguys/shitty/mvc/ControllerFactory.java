package club.itguys.shitty.mvc;

import club.itguys.shitty.beans.BeanDefination;
import club.itguys.shitty.beans.BeanFactory;
import club.itguys.shitty.beans.DefaultBeanDefination;
import club.itguys.shitty.beans.anno.Bean;
import club.itguys.shitty.beans.anno.Component;
import club.itguys.shitty.beans.anno.Repository;
import club.itguys.shitty.mvc.anno.Controller;
import club.itguys.shitty.mvc.anno.Path;
import club.itguys.shitty.mvc.exceptions.IncorrectPathException;
import club.itguys.shitty.mvc.mapping.MvcContext;
import club.itguys.shitty.mvc.mapping.PathResolverImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author sgyh
 */
public class ControllerFactory implements BeanFactory {
    @Override
    public <T> BeanDefination<T> initBean(Class<T> tClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = tClass.getDeclaredConstructor();
        T t = constructor.newInstance();

        Controller controller = tClass.getAnnotation(Controller.class);
        String beanName = controller.name();

        BeanDefination<T> beanDefination = new DefaultBeanDefination<>(beanName, t, tClass);
        registerMapping(beanDefination);
        return beanDefination;
    }

    private void registerMapping(BeanDefination beanDefination) {
        Class clazz = beanDefination.getBeanClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Path path = method.getAnnotation(Path.class);
            if (path != null) {
                String pathStr = path.path();
                if (!pathStr.startsWith("/")) {
                    throw new IncorrectPathException("The correct path should be start with /");
                }
                MvcContext.addResolver(pathStr, new PathResolverImpl(method, beanDefination));
            }
        }
    }
}
