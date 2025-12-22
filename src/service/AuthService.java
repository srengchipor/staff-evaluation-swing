package service;

import java.sql.SQLException;

public interface AuthService {

    boolean login(String username, String password) throws SQLException;

}
