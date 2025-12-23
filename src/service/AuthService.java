package service;

import java.sql.SQLException;

public interface AuthService {

    String login(String username, String password) throws SQLException;

}
