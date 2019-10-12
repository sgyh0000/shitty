package club.itguys.shitty.db;

import javax.sql.DataSource;

/**
 * @author sgyh
 */
public class ConnectionManager {

    private static ConnectionPool connectionPool;
    private static DataSource dataSource;

    static {
        try {
            dataSource = new DefaultDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://rm-2zej45x9z0m1cragqgo.mysql.rds.aliyuncs.com/math", "root", "Sp19990327");
            connectionPool = new FixableConnectionPool(4, 16, dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getConnectionPool() {
        return ConnectionManager.connectionPool;
    }

}
