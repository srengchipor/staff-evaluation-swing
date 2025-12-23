package service;

import dto.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Admin Service Interface
 * Defines all administrative operations for the Staff Evaluation System
 */
public interface AdminService {

    // ============================================
    // Company Management
    // ============================================
    String registerCompany(String name, String address, String phone, String email,
                           String adminUser, String adminPassword) throws SQLException;

    String updateCompany(Long companyId, String name, String address, String phone,
                         String email, String adminUser, String adminPassword) throws SQLException;

    List<CompanyResponse> getAllCompanies() throws SQLException;

    List<CompanyNameAndIdResponse> getAllCompanyName() throws SQLException;

    CompanyResponse getCompanyById(Long companyId) throws SQLException;

    String activateCompany(Long companyId) throws SQLException;

    String deactivateCompany(Long companyId) throws SQLException;

    // ============================================
    // User Management
    // ============================================
    String createUser(Long companyId, String username, String password, String description,
                      String userGroup) throws SQLException;

    String updateUser(Long userId, String username, String password, String description,
                      String userGroup, String status) throws SQLException;

    UserResponse getUserById(Long userId) throws SQLException;

    List<UserResponse> getAllUsers() throws SQLException;

    List<UserResponse> getUsersByGroup(Long companyId, String userGroup) throws SQLException;

    String activateUser(Long userId) throws SQLException;

    String deactivateUser(Long userId) throws SQLException;

    String deleteUser(Long userId) throws SQLException;

    // ============================================
    // Department Management
    // ============================================
    String createDepartment(String name, Long userId) throws SQLException;

    String updateDepartment(Long departmentId, String name, Long userId) throws SQLException;

    DepartmentResponse getDepartmentById(Long departmentId) throws SQLException;

    List<DepartmentResponse> getAllDepartments(Long userId) throws SQLException;

    String deleteDepartment(Long departmentId) throws SQLException;

    // ============================================
    // Office Management
    // ============================================
    String createOffice(String name, Long userId) throws SQLException;

    String updateOffice(Long officeId, String name, Long userId) throws SQLException;

    OfficeResponse getOfficeById(Long officeId) throws SQLException;

    List<OfficeResponse> getAllOffices(Long userId) throws SQLException;

    String deleteOffice(Long officeId) throws SQLException;

    // ============================================
    // Position Management
    // ============================================
    String createPosition(String name, Long userId) throws SQLException;

    String updatePosition(Long positionId, String name, Long userId) throws SQLException;

    PositionResponse getPositionById(Long positionId) throws SQLException;

    List<PositionResponse> getAllPositions(Long userId) throws SQLException;

    String deletePosition(Long positionId) throws SQLException;

    // ============================================
    // Period Management
    // ============================================
    String createPeriod(String code, Date fromDate, Date toDate, Long createdByUserId) throws SQLException;

    String updatePeriod(Long periodId, String code, Date fromDate, Date toDate,
                        String status) throws SQLException;

    PeriodResponse getPeriodById(Long periodId) throws SQLException;

    List<PeriodResponse> getAllPeriods(Long userId) throws SQLException;

    List<PeriodResponse> getActivePeriods(Long userId) throws SQLException;

    String activatePeriod(Long periodId) throws SQLException;

    String closePeriod(Long periodId) throws SQLException;

    // ============================================
    // Staff Management
    // ============================================
    String createStaff(
            long companyId,

            // login account
            String username,
            String password,

            // staff profile
            String name,
            String sex,
            LocalDate dateOfBirth,
            String placeOfBirth,
            String currentAddress,
            String phone,
            String email,

            Long leaderId,
            long departmentId,
            long officeId,
            long positionId,

            long createdByUserId
    )
    throws SQLException;

    String updateStaff(Long staffId, String name, String sex, Date dateOfBirth,
                       String placeOfBirth, String currentAddress, String phone, String email,
                       Long leaderId, Long departmentId, Long officeId,
                       Long positionId) throws SQLException;

    StaffResponse getStaffById(Long staffId) throws SQLException;

    List<StaffResponse> getAllStaff() throws SQLException;

    List<StaffResponse> getStaffByDepartment(Long departmentId) throws SQLException;

    List<StaffResponse> getStaffByOffice(Long officeId) throws SQLException;

    List<StaffResponse> getStaffByPosition(Long positionId) throws SQLException;

    List<StaffResponse> getStaffByLeader(Long leaderId) throws SQLException;

    String deleteStaff(Long staffId) throws SQLException;

    // ============================================
    // Evaluation Point Management
    // ============================================
    String createEvaluationPoint(String name, Double scoreRangeFrom, Double scoreRangeTo,
                                 Long createdByUserId) throws SQLException;

    String updateEvaluationPoint(Long evaluationPointId, String name, Double scoreRangeFrom,
                                 Double scoreRangeTo) throws SQLException;

    EvaluationPointResponse getEvaluationPointById(Long evaluationPointId) throws SQLException;

    List<EvaluationPointResponse> getAllEvaluationPoints(Long userId) throws SQLException;

    String deleteEvaluationPoint(Long evaluationPointId) throws SQLException;

    // ============================================
    // Evaluation Assignment Management
    // ============================================
    String createEvaluationAssignment(Long periodId, Long assignByStaffId, Long forStaffId,
                                      Date assignDate, String description,
                                      Long createdByUserId) throws SQLException;

    String updateEvaluationAssignment(Long assignmentId, Long periodId, Long assignByStaffId,
                                      Long forStaffId, Date assignDate,
                                      String description) throws SQLException;

    AssignStaffEvaluationResponse getEvaluationAssignmentById(Long assignmentId) throws SQLException;

    List<AssignStaffEvaluationResponse> getEvaluationAssignmentsByPeriod(Long periodId) throws SQLException;

    List<AssignStaffEvaluationResponse> getEvaluationAssignmentsForStaff(Long staffId) throws SQLException;

    String deleteEvaluationAssignment(Long assignmentId) throws SQLException;

    // ============================================
    // Evaluation List Management
    // ============================================
    String addStaffToEvaluationList(Long assignStaffEvaluationId,
                                    Long evaluationStaffId) throws SQLException;

    String removeStaffFromEvaluationList(Long evaluationListId) throws SQLException;

    List<AssignStaffEvaluationListResponse> getEvaluationListByAssignment(Long assignmentId) throws SQLException;

    List<StaffResponse> getEvaluatorsForStaff(Long assignmentId) throws SQLException;

    // ============================================
    // Reporting & Analytics
    // ============================================
    List<BestStaffReportResponse> getBestStaffByOffice(Long periodId, Long officeId) throws SQLException;

    List<BestStaffReportResponse> getBestStaffByDepartment(Long periodId, Long departmentId) throws SQLException;

    List<BestStaffReportResponse> getBestStaffOverall(Long periodId) throws SQLException;

    List<EvaluationSummaryResponse> getEvaluationSummaryByStaff(Long staffId, Long periodId) throws SQLException;

    List<EvaluationSummaryResponse> getEvaluationSummaryByDepartment(Long departmentId, Long periodId) throws SQLException;

    List<EvaluationSummaryResponse> getEvaluationSummaryByOffice(Long officeId, Long periodId) throws SQLException;

    EvaluationStatisticsResponse getEvaluationStatistics(Long periodId) throws SQLException;

    // ============================================
    // Score Management (Admin View Only)
    // ============================================
    List<AseAssignScoreResponse> getAllScoresByAssignment(Long assignmentId) throws SQLException;

    List<AseAssignScoreResponse> getAllScoresForStaff(Long staffId, Long periodId) throws SQLException;

    Double getAverageScoreForStaff(Long staffId, Long periodId) throws SQLException;



    List<String> getDepartmentName() throws SQLException;
    List<String> getOfficeName() throws SQLException;
    List<String> getPositionName() throws SQLException;

}