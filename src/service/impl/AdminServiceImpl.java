package service.impl;

import dbconfig.DbConnection;
import dto.*;
import service.AdminService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Override
    public String registerCompany(String name, String address, String phone, String email, String adminUser, String adminPassword) throws SQLException {

        String sql = """
                INSERT INTO companies (name, address, phone, email, admin_user, admin_password)\s
                VALUES (?, ?, ?, ?, ?, ?);""";

        try (Connection connection = DbConnection.getInstance()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, address);
                ps.setString(3, phone);
                ps.setString(4, email);
                ps.setString(5, adminUser);
                ps.setString(6, adminPassword);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
//                    System.out.println("Company registered successfully");
                    return "Company registered successfully!\n\nYou can now login with your admin credentials.";
                }

            }


        }

        throw new SQLException("Failed to register company");
    }

    @Override
    public String updateCompany(Long companyId, String name, String address, String phone, String email, String adminUser, String adminPassword) throws SQLException {
        return "";
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
    public List<UserResponse> getAllUsers(Long companyId) throws SQLException {
        return List.of();
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
    public String createStaff(String name, String sex, Date dateOfBirth, String placeOfBirth, String currentAddress, String phone, String email, Long leaderId, Long departmentId, Long officeId, Long positionId, Long createdByUserId) throws SQLException {
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
    public List<StaffResponse> getAllStaff(Long userId) throws SQLException {
        return List.of();
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


}
