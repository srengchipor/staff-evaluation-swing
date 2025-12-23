package service.impl;

import dbconfig.DbConnection;
import service.AuthService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthServiceImpl implements AuthService {
    @Override
    public String login(String username, String password) throws SQLException {

        try (Connection connection = DbConnection.getInstance()) {

            // Query should include user_group to differentiate between ADMIN and STAFF
            String sql = "SELECT user_group, company_id FROM users WHERE username = ? AND password = ? AND status = 'YES'";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {

                    // Check if user exists
                    if (!rs.next()) {
                        return "INVALID_CREDENTIALS";
                    }

                    // Get user_group and company_id
                    String userGroup = rs.getString("user_group");
                    Long companyId = rs.getLong("company_id");

                    // Check if company_id is null (wasNull() must be called after getLong)
                    boolean isCompanyIdNull = rs.wasNull();

                    // Return role based on user_group and company_id
                    if (isCompanyIdNull) {
                        // Super admin (no company association)
                        return "SUPER_ADMIN";
                    } else if ("ADMIN".equals(userGroup)) {
                        // Company admin
                        return "ADMIN";
                    } else if ("STAFF".equals(userGroup)) {
                        // Regular staff
                        return "STAFF";
                    } else {
                        return "UNKNOWN_ROLE";
                    }
                }
            }
        }
    }

}
