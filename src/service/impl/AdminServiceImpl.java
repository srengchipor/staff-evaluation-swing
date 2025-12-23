package service.impl;

import dbconfig.DbConnection;
import dto.*;
import service.AdminService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Override
    public String registerCompany(
            String name,
            String address,
            String phone,
            String email,
            String adminUser,
            String adminPassword
    ) throws SQLException {

        String insertCompanySql = """
        INSERT INTO companies (
            name, address, phone, email, admin_user, admin_password
        )
        VALUES (?, ?, ?, ?, ?, ?)
        """;

        String insertAdminSql = """
        INSERT INTO users (
            company_id, username, password, description, user_group, status
        )
        VALUES (?, ?, ?, 'Company Administrator', 'ADMIN', 'YES')
        """;

        try (Connection connection = DbConnection.getInstance()) {
            connection.setAutoCommit(false); // 🔥 START TRANSACTION

            try (
                    PreparedStatement companyPs =
                            connection.prepareStatement(insertCompanySql, Statement.RETURN_GENERATED_KEYS);
            ) {
                companyPs.setString(1, name);
                companyPs.setString(2, address);
                companyPs.setString(3, phone);
                companyPs.setString(4, email);
                companyPs.setString(5, adminUser);
                companyPs.setString(6, adminPassword);

                companyPs.executeUpdate();

                long companyId;
                try (ResultSet rs = companyPs.getGeneratedKeys()) {
                    if (!rs.next()) {
                        throw new SQLException("Failed to retrieve company ID");
                    }
                    companyId = rs.getLong(1);
                }

                try (PreparedStatement adminPs =
                             connection.prepareStatement(insertAdminSql)) {

                    adminPs.setLong(1, companyId);
                    adminPs.setString(2, adminUser);
                    adminPs.setString(3, adminPassword); // hash before storing!

                    adminPs.executeUpdate();
                }

                connection.commit(); // ✅ COMMIT
                return "Company registered successfully!\n\nYou can now login with your admin credentials.";

            } catch (SQLException e) {
                connection.rollback(); // ❌ ROLLBACK
                throw e;
            }
        }
    }


    @Override
    public String updateCompany(Long companyId, String name, String address, String phone, String email, String adminUser, String adminPassword) throws SQLException {
        return "";
    }

    @Override
    public List<CompanyResponse> getAllCompanies() throws SQLException {
        String sql = "select * from companies";
        List<CompanyResponse> responses = new ArrayList<>();

        try (Connection connection = DbConnection.getInstance()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        responses.add(
                                new CompanyResponse(
                                        rs.getLong("id"),
                                        rs.getString("name"),
                                        rs.getString("address"),
                                        rs.getString("email"),
                                        rs.getString("phone"),
                                        rs.getString("admin_user"),
                                        rs.getString("status")
                                )
                        );
                    }

                }


            }
        }

        return responses;
    }

    @Override
    public List<CompanyNameAndIdResponse> getAllCompanyName() throws SQLException {
        String sql = "select id, name from companies";
        List<CompanyNameAndIdResponse> responses = new ArrayList<>();

        try (Connection connection = DbConnection.getInstance()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        responses.add(
                                new CompanyNameAndIdResponse(
                                        rs.getLong("id"),
                                        rs.getString("name")
                                )
                        );
                    }
                }
            }
        }

        return responses;
    }

    @Override
    public CompanyResponse getCompanyById(Long companyId) throws SQLException {
        return null;
    }

    @Override
    public String activateCompany(Long companyId) throws SQLException {
        return "";
    }

    @Override
    public String deactivateCompany(Long companyId) throws SQLException {
        return "";
    }

    @Override
    public String createUser(Long companyId, String username, String password, String description, String userGroup) throws SQLException {
        return "";
    }

    @Override
    public String updateUser(Long userId, String username, String password, String description, String userGroup, String status) throws SQLException {
        return "";
    }

    @Override
    public UserResponse getUserById(Long userId) throws SQLException {
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() throws SQLException {
        String sql = "select u.id, u.username, c.name as company, u.description, u.status from users u left join companies c on u.company_id = c.id;";
        List<UserResponse> users = new ArrayList<>();

        try (Connection connection = DbConnection.getInstance()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        users.add(
                                new UserResponse(
                                        rs.getLong("id"),
                                        rs.getString("username"),
                                        rs.getString("company"),
                                        rs.getString("description"),
                                        rs.getString("status")
                                )
                        );

                    }

                }

            }
        }

        return users;
    }

    @Override
    public List<UserResponse> getUsersByGroup(Long companyId, String userGroup) throws SQLException {
        return List.of();
    }

    @Override
    public String activateUser(Long userId) throws SQLException {
        return "";
    }

    @Override
    public String deactivateUser(Long userId) throws SQLException {
        return "";
    }

    @Override
    public String deleteUser(Long userId) throws SQLException {
        return "";
    }

    @Override
    public String createDepartment(String name, Long userId) throws SQLException {
        return "";
    }

    @Override
    public String updateDepartment(Long departmentId, String name, Long userId) throws SQLException {
        return "";
    }

    @Override
    public DepartmentResponse getDepartmentById(Long departmentId) throws SQLException {
        return null;
    }

    @Override
    public List<DepartmentResponse> getAllDepartments(Long userId) throws SQLException {
        return List.of();
    }

    @Override
    public String deleteDepartment(Long departmentId) throws SQLException {
        return "";
    }

    @Override
    public String createOffice(String name, Long userId) throws SQLException {
        return "";
    }

    @Override
    public String updateOffice(Long officeId, String name, Long userId) throws SQLException {
        return "";
    }

    @Override
    public OfficeResponse getOfficeById(Long officeId) throws SQLException {
        return null;
    }

    @Override
    public List<OfficeResponse> getAllOffices(Long userId) throws SQLException {
        return List.of();
    }

    @Override
    public String deleteOffice(Long officeId) throws SQLException {
        return "";
    }

    @Override
    public String createPosition(String name, Long userId) throws SQLException {
        return "";
    }

    @Override
    public String updatePosition(Long positionId, String name, Long userId) throws SQLException {
        return "";
    }

    @Override
    public PositionResponse getPositionById(Long positionId) throws SQLException {
        return null;
    }

    @Override
    public List<PositionResponse> getAllPositions(Long userId) throws SQLException {
        return List.of();
    }

    @Override
    public String deletePosition(Long positionId) throws SQLException {
        return "";
    }

    @Override
    public String createPeriod(String code, Date fromDate, Date toDate, Long createdByUserId) throws SQLException {
        return "";
    }

    @Override
    public String updatePeriod(Long periodId, String code, Date fromDate, Date toDate, String status) throws SQLException {
        return "";
    }

    @Override
    public PeriodResponse getPeriodById(Long periodId) throws SQLException {
        return null;
    }

    @Override
    public List<PeriodResponse> getAllPeriods(Long userId) throws SQLException {
        return List.of();
    }

    @Override
    public List<PeriodResponse> getActivePeriods(Long userId) throws SQLException {
        return List.of();
    }

    @Override
    public String activatePeriod(Long periodId) throws SQLException {
        return "";
    }

    @Override
    public String closePeriod(Long periodId) throws SQLException {
        return "";
    }

    @Override
    public String createStaff(long companyId, String username, String password, String name, String sex, LocalDate dateOfBirth, String placeOfBirth, String currentAddress, String phone, String email, Long leaderId, long departmentId, long officeId, long positionId, long createdByUserId) throws SQLException {
        return "";
    }

    @Override
    public String updateStaff(Long staffId, String name, String sex, Date dateOfBirth, String placeOfBirth, String currentAddress, String phone, String email, Long leaderId, Long departmentId, Long officeId, Long positionId) throws SQLException {
        return "";
    }

    @Override
    public StaffResponse getStaffById(Long staffId) throws SQLException {
        return null;
    }

    @Override
    public List<StaffResponse> getAllStaff() throws SQLException {
        String sql = """
                SELECT
                    s.id,
                    s.name            AS staff_name,
                    d.name            AS department_name,
                    o.name            AS office_name,
                    p.name            AS position_name,
                    s.status
                FROM staffs s
                JOIN departments d   ON s.department_id = d.id
                JOIN offices o ON s.office_id = o.id
                JOIN positions p     ON s.position_id = p.id;
                """;

        List<StaffResponse> staffResponses = new ArrayList<>();

        try (Connection connection = DbConnection.getInstance()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        staffResponses.add(
                                new StaffResponse(
                                        rs.getLong("id"),
                                        rs.getString("staff_name"),
                                        rs.getString("department_name"),
                                        rs.getString("office_name"),
                                        rs.getString("position_name"),
                                        rs.getString("status")
                                )
                        );
                    }
                }
            }
        }

        return staffResponses;
    }

    @Override
    public List<StaffResponse> getStaffByDepartment(Long departmentId) throws SQLException {
        return List.of();
    }

    @Override
    public List<StaffResponse> getStaffByOffice(Long officeId) throws SQLException {
        return List.of();
    }

    @Override
    public List<StaffResponse> getStaffByPosition(Long positionId) throws SQLException {
        return List.of();
    }

    @Override
    public List<StaffResponse> getStaffByLeader(Long leaderId) throws SQLException {
        return List.of();
    }

    @Override
    public String deleteStaff(Long staffId) throws SQLException {
        return "";
    }

    @Override
    public String createEvaluationPoint(String name, Double scoreRangeFrom, Double scoreRangeTo, Long createdByUserId) throws SQLException {
        return "";
    }

    @Override
    public String updateEvaluationPoint(Long evaluationPointId, String name, Double scoreRangeFrom, Double scoreRangeTo) throws SQLException {
        return "";
    }

    @Override
    public EvaluationPointResponse getEvaluationPointById(Long evaluationPointId) throws SQLException {
        return null;
    }

    @Override
    public List<EvaluationPointResponse> getAllEvaluationPoints(Long userId) throws SQLException {
        return List.of();
    }

    @Override
    public String deleteEvaluationPoint(Long evaluationPointId) throws SQLException {
        return "";
    }

    @Override
    public String createEvaluationAssignment(Long periodId, Long assignByStaffId, Long forStaffId, Date assignDate, String description, Long createdByUserId) throws SQLException {
        return "";
    }

    @Override
    public String updateEvaluationAssignment(Long assignmentId, Long periodId, Long assignByStaffId, Long forStaffId, Date assignDate, String description) throws SQLException {
        return "";
    }

    @Override
    public AssignStaffEvaluationResponse getEvaluationAssignmentById(Long assignmentId) throws SQLException {
        return null;
    }

    @Override
    public List<AssignStaffEvaluationResponse> getEvaluationAssignmentsByPeriod(Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public List<AssignStaffEvaluationResponse> getEvaluationAssignmentsForStaff(Long staffId) throws SQLException {
        return List.of();
    }

    @Override
    public String deleteEvaluationAssignment(Long assignmentId) throws SQLException {
        return "";
    }

    @Override
    public String addStaffToEvaluationList(Long assignStaffEvaluationId, Long evaluationStaffId) throws SQLException {
        return "";
    }

    @Override
    public String removeStaffFromEvaluationList(Long evaluationListId) throws SQLException {
        return "";
    }

    @Override
    public List<AssignStaffEvaluationListResponse> getEvaluationListByAssignment(Long assignmentId) throws SQLException {
        return List.of();
    }

    @Override
    public List<StaffResponse> getEvaluatorsForStaff(Long assignmentId) throws SQLException {
        return List.of();
    }

    @Override
    public List<BestStaffReportResponse> getBestStaffByOffice(Long periodId, Long officeId) throws SQLException {
        return List.of();
    }

    @Override
    public List<BestStaffReportResponse> getBestStaffByDepartment(Long periodId, Long departmentId) throws SQLException {
        return List.of();
    }

    @Override
    public List<BestStaffReportResponse> getBestStaffOverall(Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public List<EvaluationSummaryResponse> getEvaluationSummaryByStaff(Long staffId, Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public List<EvaluationSummaryResponse> getEvaluationSummaryByDepartment(Long departmentId, Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public List<EvaluationSummaryResponse> getEvaluationSummaryByOffice(Long officeId, Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public EvaluationStatisticsResponse getEvaluationStatistics(Long periodId) throws SQLException {
        return null;
    }

    @Override
    public List<AseAssignScoreResponse> getAllScoresByAssignment(Long assignmentId) throws SQLException {
        return List.of();
    }

    @Override
    public List<AseAssignScoreResponse> getAllScoresForStaff(Long staffId, Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public Double getAverageScoreForStaff(Long staffId, Long periodId) throws SQLException {
        return 0.0;
    }

    @Override
    public List<String> getDepartmentName() throws SQLException {
        String sql = "select name from departments;";
        List<String> data = new ArrayList<>();

        try (Connection connection = DbConnection.getInstance()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            data.add(rs.getString("name"));
                        }
                }

            }
        }


        return data;
    }

    @Override
    public List<String> getOfficeName() throws SQLException {
        String sql = "select name from offices;";
        List<String> data = new ArrayList<>();

        try (Connection connection = DbConnection.getInstance()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        data.add(rs.getString("name"));
                    }
                }

            }
        }


        return data;
    }

    @Override
    public List<String> getPositionName() throws SQLException {
        String sql = "select name from positions;";
        List<String> data = new ArrayList<>();

        try (Connection connection = DbConnection.getInstance()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        data.add(rs.getString("name"));
                    }
                }

            }
        }


        return data;
    }


}
