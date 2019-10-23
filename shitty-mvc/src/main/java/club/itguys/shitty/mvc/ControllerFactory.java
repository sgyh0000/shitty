package club.itguys.shitty.mvc;

import club.itguys.shitty.beans.BeanDefination;
import club.itguys.shitty.beans.BeanFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author sgyh
 */
public class ControllerFactory implements BeanFactory {
    @Override
    public <T> BeanDefination<T> initBean(Class<T> tClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return null;
    }
}
