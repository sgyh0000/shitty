package club.itguys.shitty.db;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.sql.Connection;

/**
 * @author sgyh
 */
public class BeanUtils {

    public static <T> T getBean(Class<T> clazz) throws Exception {
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
        return proxy;
    }

}
