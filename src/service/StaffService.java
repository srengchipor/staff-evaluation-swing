package service;

import dto.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Staff Service Interface
 * Defines operations available to staff users (limited access)
 */
public interface StaffService {

    // ============================================
    // Profile Management (Read Only)
    // ============================================
    StaffResponse getMyProfile(Long userId) throws SQLException;

    StaffResponse getStaffProfile(Long staffId) throws SQLException;

    // ============================================
    // View Organizational Structure (Read Only)
    // ============================================
    DepartmentResponse getMyDepartment(Long staffId) throws SQLException;

    OfficeResponse getMyOffice(Long staffId) throws SQLException;

    PositionResponse getMyPosition(Long staffId) throws SQLException;

    StaffResponse getMyLeader(Long staffId) throws SQLException;

    List<StaffResponse> getMyTeamMembers(Long leaderId) throws SQLException;

    // ============================================
    // View Evaluation Assignments (Assigned to Me)
    // ============================================
    List<AssignStaffEvaluationResponse> getMyEvaluationAssignments(Long staffId, Long periodId) throws SQLException;

    List<AssignStaffEvaluationResponse> getMyPendingEvaluations(Long staffId, Long periodId) throws SQLException;

    List<AssignStaffEvaluationResponse> getMyCompletedEvaluations(Long staffId, Long periodId) throws SQLException;

    AssignStaffEvaluationResponse getEvaluationAssignmentDetails(Long assignmentId, Long staffId) throws SQLException;

    // ============================================
    // View Who I Need to Evaluate
    // ============================================
    List<StaffResponse> getStaffIAmEvaluating(Long evaluatorStaffId, Long periodId) throws SQLException;

    List<AssignStaffEvaluationListResponse> getMyEvaluationTasks(Long evaluatorStaffId, Long assignmentId) throws SQLException;

    boolean isAssignedToEvaluate(Long evaluatorStaffId, Long targetStaffId, Long periodId) throws SQLException;

    // ============================================
    // View Evaluation Points/Criteria
    // ============================================
    List<EvaluationPointResponse> getEvaluationPoints() throws SQLException;

    EvaluationPointResponse getEvaluationPointById(Long evaluationPointId) throws SQLException;

    // ============================================
    // Submit Evaluation Scores
    // ============================================
    String submitEvaluationScore(Long assignStaffEvaluationListId, Double score,
                                 Long evaluatorStaffId) throws SQLException;

    String updateEvaluationScore(Long scoreId, Double score, Long evaluatorStaffId) throws SQLException;

    boolean canSubmitScore(Long evaluationListId, Long evaluatorStaffId) throws SQLException;

    // ============================================
    // View My Submitted Scores
    // ============================================
    List<AseAssignScoreResponse> getMySubmittedScores(Long evaluatorStaffId, Long periodId) throws SQLException;

    List<AseAssignScoreResponse> getMyScoresForStaff(Long evaluatorStaffId, Long targetStaffId,
                                                     Long periodId) throws SQLException;

    AseAssignScoreResponse getScoreById(Long scoreId, Long evaluatorStaffId) throws SQLException;

    boolean hasSubmittedScore(Long evaluationListId, Long evaluatorStaffId) throws SQLException;

    // ============================================
    // Self-Evaluation (Special Case)
    // ============================================
    List<AssignStaffEvaluationResponse> getMySelfEvaluationAssignments(Long staffId, Long periodId) throws SQLException;

    boolean canEvaluateSelf(Long staffId, Long periodId) throws SQLException;

    String submitSelfEvaluationScore(Long assignStaffEvaluationListId, Double score,
                                     Long staffId) throws SQLException;

    // ============================================
    // View My Evaluation Results (Limited)
    // ============================================
    EvaluationSummaryResponse getMyEvaluationSummary(Long staffId, Long periodId) throws SQLException;

    Double getMyAverageScore(Long staffId, Long periodId) throws SQLException;

    Integer getMyRankInDepartment(Long staffId, Long periodId) throws SQLException;

    Integer getMyRankInOffice(Long staffId, Long periodId) throws SQLException;

    // ============================================
    // Period Information (Read Only)
    // ============================================
    List<PeriodResponse> getActivePeriods() throws SQLException;

    PeriodResponse getCurrentPeriod() throws SQLException;

    PeriodResponse getPeriodById(Long periodId) throws SQLException;

    // ============================================
    // Evaluation Progress Tracking
    // ============================================
    EvaluationProgressResponse getMyEvaluationProgress(Long staffId, Long periodId) throws SQLException;

    Integer getCompletedEvaluationCount(Long evaluatorStaffId, Long periodId) throws SQLException;

    Integer getPendingEvaluationCount(Long evaluatorStaffId, Long periodId) throws SQLException;

    Double getEvaluationCompletionPercentage(Long evaluatorStaffId, Long periodId) throws SQLException;

    // ============================================
    // Validation Methods
    // ============================================
    boolean isValidScoreRange(Double score, Long evaluationPointId) throws SQLException;

    boolean canAccessEvaluation(Long staffId, Long assignmentId) throws SQLException;

    boolean isEvaluationPeriodActive(Long periodId) throws SQLException;
}
