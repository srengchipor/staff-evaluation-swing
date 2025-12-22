package service.impl;

import dto.*;
import service.StaffService;

import java.sql.SQLException;
import java.util.List;

public class StaffServiceImpl implements StaffService {
    @Override
    public StaffResponse getMyProfile(Long userId) throws SQLException {
        return null;
    }

    @Override
    public StaffResponse getStaffProfile(Long staffId) throws SQLException {
        return null;
    }

    @Override
    public DepartmentResponse getMyDepartment(Long staffId) throws SQLException {
        return null;
    }

    @Override
    public OfficeResponse getMyOffice(Long staffId) throws SQLException {
        return null;
    }

    @Override
    public PositionResponse getMyPosition(Long staffId) throws SQLException {
        return null;
    }

    @Override
    public StaffResponse getMyLeader(Long staffId) throws SQLException {
        return null;
    }

    @Override
    public List<StaffResponse> getMyTeamMembers(Long leaderId) throws SQLException {
        return List.of();
    }

    @Override
    public List<AssignStaffEvaluationResponse> getMyEvaluationAssignments(Long staffId, Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public List<AssignStaffEvaluationResponse> getMyPendingEvaluations(Long staffId, Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public List<AssignStaffEvaluationResponse> getMyCompletedEvaluations(Long staffId, Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public AssignStaffEvaluationResponse getEvaluationAssignmentDetails(Long assignmentId, Long staffId) throws SQLException {
        return null;
    }

    @Override
    public List<StaffResponse> getStaffIAmEvaluating(Long evaluatorStaffId, Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public List<AssignStaffEvaluationListResponse> getMyEvaluationTasks(Long evaluatorStaffId, Long assignmentId) throws SQLException {
        return List.of();
    }

    @Override
    public boolean isAssignedToEvaluate(Long evaluatorStaffId, Long targetStaffId, Long periodId) throws SQLException {
        return false;
    }

    @Override
    public List<EvaluationPointResponse> getEvaluationPoints() throws SQLException {
        return List.of();
    }

    @Override
    public EvaluationPointResponse getEvaluationPointById(Long evaluationPointId) throws SQLException {
        return null;
    }

    @Override
    public String submitEvaluationScore(Long assignStaffEvaluationListId, Double score, Long evaluatorStaffId) throws SQLException {
        return "";
    }

    @Override
    public String updateEvaluationScore(Long scoreId, Double score, Long evaluatorStaffId) throws SQLException {
        return "";
    }

    @Override
    public boolean canSubmitScore(Long evaluationListId, Long evaluatorStaffId) throws SQLException {
        return false;
    }

    @Override
    public List<AseAssignScoreResponse> getMySubmittedScores(Long evaluatorStaffId, Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public List<AseAssignScoreResponse> getMyScoresForStaff(Long evaluatorStaffId, Long targetStaffId, Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public AseAssignScoreResponse getScoreById(Long scoreId, Long evaluatorStaffId) throws SQLException {
        return null;
    }

    @Override
    public boolean hasSubmittedScore(Long evaluationListId, Long evaluatorStaffId) throws SQLException {
        return false;
    }

    @Override
    public List<AssignStaffEvaluationResponse> getMySelfEvaluationAssignments(Long staffId, Long periodId) throws SQLException {
        return List.of();
    }

    @Override
    public boolean canEvaluateSelf(Long staffId, Long periodId) throws SQLException {
        return false;
    }

    @Override
    public String submitSelfEvaluationScore(Long assignStaffEvaluationListId, Double score, Long staffId) throws SQLException {
        return "";
    }

    @Override
    public EvaluationSummaryResponse getMyEvaluationSummary(Long staffId, Long periodId) throws SQLException {
        return null;
    }

    @Override
    public Double getMyAverageScore(Long staffId, Long periodId) throws SQLException {
        return 0.0;
    }

    @Override
    public Integer getMyRankInDepartment(Long staffId, Long periodId) throws SQLException {
        return 0;
    }

    @Override
    public Integer getMyRankInOffice(Long staffId, Long periodId) throws SQLException {
        return 0;
    }

    @Override
    public List<PeriodResponse> getActivePeriods() throws SQLException {
        return List.of();
    }

    @Override
    public PeriodResponse getCurrentPeriod() throws SQLException {
        return null;
    }

    @Override
    public PeriodResponse getPeriodById(Long periodId) throws SQLException {
        return null;
    }

    @Override
    public EvaluationProgressResponse getMyEvaluationProgress(Long staffId, Long periodId) throws SQLException {
        return null;
    }

    @Override
    public Integer getCompletedEvaluationCount(Long evaluatorStaffId, Long periodId) throws SQLException {
        return 0;
    }

    @Override
    public Integer getPendingEvaluationCount(Long evaluatorStaffId, Long periodId) throws SQLException {
        return 0;
    }

    @Override
    public Double getEvaluationCompletionPercentage(Long evaluatorStaffId, Long periodId) throws SQLException {
        return 0.0;
    }

    @Override
    public boolean isValidScoreRange(Double score, Long evaluationPointId) throws SQLException {
        return false;
    }

    @Override
    public boolean canAccessEvaluation(Long staffId, Long assignmentId) throws SQLException {
        return false;
    }

    @Override
    public boolean isEvaluationPeriodActive(Long periodId) throws SQLException {
        return false;
    }
}
