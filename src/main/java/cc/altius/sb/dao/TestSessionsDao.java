/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao;

import cc.altius.matrixCodeVerse.model.DarkFactorCalculatorDTO;
import cc.altius.matrixCodeVerse.model.Question;
import cc.altius.matrixCodeVerse.model.PopulationStatsGroup;
import cc.altius.matrixCodeVerse.model.TestSession;
import cc.altius.matrixCodeVerse.model.TestSessionWithQuestionList;
import cc.altius.sb.model.TestSessions;
import java.util.List;
import java.util.Map;

/**
 *
 * @author altius
 */
public interface TestSessionsDao {

    public TestSession getTestSessionData(String lastName, String emailId);

    public PopulationStatsGroup getPopulationStatsForQuestionFromLiveData(int questionId, int testSessionId, String type);

    public int saveQuestionResponses(TestSession testSession, Question currentQuestion, String[] selectedOptions, String[] selectedOptionValues);

    public int addQuestionResponses(int takerId, int testSessionId, int questionId, int testId, String outputData, String questionValue, String createdDate, String modifiedDate, long startedQuestion, long endedQuestion, String outputType);

    public int noOfQuestionAnswered(int testSessionId);

    public int getLastQuestionId();

    public int getDimensionIdBySortOrder(int sortOrder);

    public int getTraitIdByQuestionId(int sortOrder);

    public int getAspectIdByQuestionId(int sortOrder);

    public int getCountForAspect();

    public int getCountForDimension();

    public int getCountForTrait();

    public double getQuestionOptionValueByQuestionOptionId(int questionId, String questionOptionId);

    public Map<String,Object> getQuestionOptionValueByQuestionOptionId1(int questionId, String questionOptionId);
        
    public List<TestSessions> getPrivateDossierPdf(int takerId);

    public List<TestSessions> getExtremismDimensionData(int takerId);

    public List<Map<String, Object>> getCognitionDimensionData(int takerId, int trait);

    public List<Map<String, Object>> getQuestionOptionList(int takerId, int trait);

    public List<Map<String, Object>> getBehaviourQuestion3(int testSessionId);

    public Map<String, Object> getBehaviourQuestion2(int testSessionId);

    public String getBehaviourMasterySum(int testSessionId);

    public List<Map<String, Object>> getBehaviourQuestion1(int testSessionId);

    public String getBehaviouMindLikertSum(int testSessionId);

    public Map<String, Object> getBehaviourQuestion4(int testSessionId);

    public String getBhaviourAbstinenceSum(int testSessionId);

    public List<Map<String, Object>> getMentalImprovementActivities_QL2(int testSessionId);

    public Map<String, Object> getTendenciesTestPercForQList(int testSessionId);

    public Map<String, Object> getTendenciesTestPercForUList(int testSessionId);

    public Map<String, Object> getTendenciesTestPercForOList(int testSessionId);

    public int getTendenciesTestPercForRList(int testSessionId);

    public int getTendenciesTestPercForWithRList(int testSessionId);

    public List<Double> getPopAverageForAspect(int aspectId);

    public List<Double> getPopAspectScoreList(int sortOrder);

    public TestSessionWithQuestionList getTestSessionAllQueData(int takerId);

    public List<Map<String, Object>> getSelfActualizationData(int testSessionId);

    public Map<String, Object> getSatisfactionWithLifeScale(int testSessionId);

    public Map<String, Object> getLonelinessData(int testSessionId);

    public List<Map<String, Object>> getUCLAquestions(int testSessionId);

    public List<Map<String, Object>> getSelfRsesList(int testSessionId);

    public List<Map<String, Object>> getANSPTraitSum(int testSessionId, int is_reverse_key);

    public List<DarkFactorCalculatorDTO> getDARKTraitSum(int testSessionId, int _reverse_key);

    public void updateTestSession(int sortOrder, int testSessionId, int questionTypeId);

    public void updateTestSessionForMedicalProferssion(int sortOrder, int testSessionId);

    public String getQuestionOptionValueByQuestionOptionIdMultSelectQue(int questionId, String s);

    public String getQuestionValueByQuestionOptionIdRadio(String questionOptionId);

    public TestSessionWithQuestionList getTestSessionAllCovidQueDataForResult(int userId, int dimensionId);

    public String getLabelByNumberOfQuestions(int numberOfQuestions);

    public int deleteLastSorder();

    public TestSession getTestSessionDataByUserId(int userId);

    public PopulationStatsGroup getPopulationStatsForCovidQuestion(int questionId, int testSessionId, String type);

    public int getLastQuestionIdWithoutCovidQue();

    public String getDescByPercentile(double percentile, int parameterType, int parameterValue);

    public List<Map<String, Object>> getValueHealthAndDevelopmentGraph(int testSessionId);

    public Question getCurrentQuestionData(int testSessionId);

    public int getAnpsTotalScore(int traitId, int takerId);

    public Question getNextQuestion(int testSessionId);

    public PopulationStatsGroup getPopulationStatsForQuestionFromSavedData(int questionId, int testSessionId, String pdf);

    public void updatePopulationStatsInDatabase();
}
