package club.itguys.shitty.db;

import club.itguys.shitty.beans.BeanDefination;
import club.itguys.shitty.beans.BeanFactory;
import club.itguys.shitty.beans.DefaultBeanDefination;
import club.itguys.shitty.beans.anno.Repository;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.sql.Connection;

/**
 * @author sgyh
 */
public class RepositoryBeanFactory implements BeanFactory {
    @Override
    public <T> BeanDefination<T> initBean(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        T target = constructor.newInstance();
        Field connectionField = null;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Type type = field.getGenericType();
            if ("java.sql.Connection".equals(type.getTypeName())) {
                connectionField = field;
            }
        }
        if (connectionField == null) {
            throw new NullPointerException("There is no field which type is java.sql.Connection defined in class " + clazz.getName());
        }
        connectionField.setAccessible(true);
        Connection connection = (Connection) connectionField.get(target);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();

        ConnectionReleaseHandler automaticReleaseConnection = new ConnectionReleaseHandler(target);

        automaticReleaseConnection.setConnection(ConnectionManager.getConnectionPool(), connection);

        T proxy = (T) Proxy.newProxyInstance(classLoader, interfaces, automaticReleaseConnection);

        Annotation annotation = clazz.getDeclaredAnnotation(Repository.class);
        String beanName = ((Repository) annotation).name();

        return new DefaultBeanDefination<>(beanName, proxy, clazz);
    }
}
