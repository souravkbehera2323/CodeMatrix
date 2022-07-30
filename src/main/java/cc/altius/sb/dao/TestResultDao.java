/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao;

import cc.altius.matrixCodeVerse.model.TestSession;
import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.model.DTO.TakerNoOfQuestionAttempted;
import cc.altius.sb.model.Question;
import cc.altius.sb.model.QuestionResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yogesh
 */
public interface TestResultDao {

    List<TestSession> getTakerSessionResultList(CustomUserDetails curUser, int flag);

    public List<QuestionResponse> getExportRawReport(int takerId);

    public List<Integer> getWisdomTakerListReport();

    public List<Question> getQuestionsSetCompletedList(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional);
    
    public List<Question> getQuestionsSetPendingList(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional);
    
    public List<Map<String, Object>> getQuestionsSetCompletedListCount(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional);
    
    public List<Map<String, Object>> getQuestionsSetPendingListCount(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional);
    
    public List<TakerNoOfQuestionAttempted> getNoOfQuestionAttemptedByTaker(int takerId);
    public int updateNoOfQuestionAttemptedByTaker(int active, int takerId);
}
