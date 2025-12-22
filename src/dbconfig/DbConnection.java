package dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getInstance() {

        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/staff_evaluation_db",
                    "admin",
                    "admin123"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
