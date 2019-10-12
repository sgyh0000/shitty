package club.itguys.shitty.db;

import java.sql.Connection;

/**
 * @author sgyh
 */
public interface AutomaticReleaseConnection {

    void setConnection(ConnectionPool connectionPool, Connection connection);

}
