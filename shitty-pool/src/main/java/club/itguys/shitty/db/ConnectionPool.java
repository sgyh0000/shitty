package club.itguys.shitty.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author sgyh
 */
public interface ConnectionPool {

    Connection getConnection() throws SQLException, InterruptedException;

    void freeConnection(Connection connection);

    /**
     * 最大连接数
     * @return
     */
    int getMaxiumSize();

    /**
     * 实际的连接数
     * @return
     */
    int getSize();

    /**
     * 空闲连接数
     * @return
     */
    int getFreeSize();

    /**
     * Fixable重写
     * @param maxium
     */
    void setSize(int maxium);

}
