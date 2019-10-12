package club.itguys.shitty.db;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * @author sgyh
 */
public class ConnectionReleaseHandler implements InvocationHandler, AutomaticReleaseConnection {

    private Object target;

    private Connection connection;
    private ConnectionPool connectionPool;

    public ConnectionReleaseHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        connectionPool.freeConnection(connection);
        return result;
    }

    @Override
    public void setConnection(ConnectionPool connectionPool, Connection connection) {
        this.connectionPool = connectionPool;
        this.connection = connection;
    }
}
