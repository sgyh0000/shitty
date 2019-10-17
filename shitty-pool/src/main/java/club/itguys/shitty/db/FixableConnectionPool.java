package club.itguys.shitty.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author sgyh
 */
public class FixableConnectionPool implements ConnectionPool {

    private DataSource dataSource;

    private int size;
    private int initSize;
    private int maxiumSize;

    private final Object lock = new Object();

    private Queue<Connection> freeConnection;

    protected FixableConnectionPool(int initSize, int maxiumSize, DataSource dataSource) throws SQLException {
        this.initSize = initSize;
        this.size = initSize;
        this.maxiumSize = maxiumSize;
        this.dataSource = dataSource;
        freeConnection = new LinkedList<>();
        for (int i = 0; i < initSize; i++) {
            freeConnection.offer(dataSource.getConnection());
        }
    }

    @Override
    public Connection getConnection() throws SQLException, InterruptedException {
        Connection connection;
        if (freeConnection.size() > 0) { // 有空闲连接
            synchronized (lock) {
                connection = pollConnection();
            }
        } else if (size < maxiumSize) { // 无空闲连接，但连接并未达到最大，扩容
            synchronized (lock) {
                connection = addConnection();
            }
        } else { // 连接达到最大且无空闲，等待其他连接释放
            synchronized (lock) {
                lock.wait();
                connection = pollConnection();
            }
        }
        return connection;
    }

    private Connection pollConnection() {
        return freeConnection.poll();
    }

    private Connection addConnection() throws SQLException {
        size++;
        return dataSource.getConnection();
    }

    @Override
    public int getMaxiumSize() {
        return maxiumSize;
    }

    @Override
    public void freeConnection(Connection connection) {
        synchronized (lock) {
            freeConnection.offer(connection);
            if (size == maxiumSize && getFreeSize() == 1) {
                lock.notifyAll();
            }
        }

        System.out.println("这会有输出的");

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getFreeSize() {
        return freeConnection.size();
    }

    @Override
    public void setSize(int maxium) {
        this.maxiumSize = maxium;
    }

}
