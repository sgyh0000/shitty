package club.itguys.shitty.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author sgyh
 */
public class TestDaoImpl implements TestDao {

    private Connection connection;

    @Override
    public void testSQL(String sql) {
        try {
            connection = ConnectionManager.getConnectionPool().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
