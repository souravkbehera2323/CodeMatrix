/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.service.impl;

import cc.altius.matrixCodeVerse.model.DarkFactorCalculatorDTO;
import cc.altius.matrixCodeVerse.model.Question;
import cc.altius.matrixCodeVerse.model.PopulationStatsGroup;
import cc.altius.matrixCodeVerse.model.TestSession;
import cc.altius.matrixCodeVerse.model.TestSessionWithQuestionList;
import cc.altius.sb.dao.TestSessionsDao;
import cc.altius.sb.model.TestSessions;
import cc.altius.sb.service.TestSessionsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class TestSessionsServiceImpl implements TestSessionsService {

    @Autowired
    private TestSessionsDao testSessionsDao;

    @Override
    public TestSession getTestSessionData(String lastName, String emailId) {
        return this.testSessionsDao.getTestSessionData(lastName, emailId);
    }

    @Override
    public PopulationStatsGroup getPopulationStatsForQuestionFromLiveData(int questionId, int testSessionId, String type) {
        return this.testSessionsDao.getPopulationStatsForQuestionFromLiveData(questionId, testSessionId, type);
    }

   @Override
    public PopulationStatsGroup getPopulationStatsForQuestionFromSavedData(int questionId, int testSessionId, String type) {
        return this.testSessionsDao.getPopulationStatsForQuestionFromSavedData(questionId, testSessionId, type);
    }
    @Override
    public int saveQuestionResponses(TestSession testSession, Question currentQuestion, String[] selectedOptions, String[] selectedOptionValues) {
        return this.testSessionsDao.saveQuestionResponses(testSession, currentQuestion, selectedOptions, selectedOptionValues);
    }


    @Override
    public int addQuestionResponses(int takerId, int testSessionId, int questionId, int testId, String outputData, String questionValue, String createdDate, String modifiedDate, long startedQuestion, long endedQuestion, String outputType) {
        return this.testSessionsDao.addQuestionResponses(takerId, testSessionId, questionId, testId, outputData, questionValue, createdDate, modifiedDate, startedQuestion, endedQuestion, outputType);
    }

    @Override
    public int noOfQuestionAnswered(int testSessionId) {
        return this.testSessionsDao.noOfQuestionAnswered(testSessionId);
    }

    @Override
    public int getLastQuestionId() {
        return this.testSessionsDao.getLastQuestionId();
    }

    @Override
    public int getDimensionIdBySortOrder(int sortOrder) {
        return this.testSessionsDao.getDimensionIdBySortOrder(sortOrder);
    }

    @Override
    public double getQuestionOptionValueByQuestionOptionId(int questionId, String questionOptionId) {
        return this.testSessionsDao.getQuestionOptionValueByQuestionOptionId(questionId, questionOptionId);
    }

    @Override
    public int getTraitIdByQuestionId(int sortOrder) {
        return this.testSessionsDao.getTraitIdByQuestionId(sortOrder);
    }

    @Override
    public int getAspectIdByQuestionId(int sortOrder) {
        return this.testSessionsDao.getAspectIdByQuestionId(sortOrder);
    }

    @Override
    public int getCountForAspect() {
        return this.testSessionsDao.getCountForAspect();
    }

    @Override
    public int getCountForDimension() {
        return this.testSessionsDao.getCountForDimension();
    }

    @Override
    public int getCountForTrait() {
        return this.testSessionsDao.getCountForTrait();
    }


    @Override
    public List<TestSessions> getPrivateDossierPdf(int takerId) {
        return this.testSessionsDao.getPrivateDossierPdf(takerId);
    }

    @Override
    public List<TestSessions> getExtremismDimensionData(int takerId) {
        return this.testSessionsDao.getExtremismDimensionData(takerId);
    }

    @Override
    public List<Map<String, Object>> getCognitionDimensionData(int takerId, int trait) {
        return this.testSessionsDao.getCognitionDimensionData(takerId, trait);
    }

    @Override
    public List<Map<String, Object>> getQuestionOptionList(int takerId, int trait) {
        return this.testSessionsDao.getQuestionOptionList(takerId, trait);
    }

    @Override
    public String getBehaviouMindLikertSum(int testSessionId) {
        return this.testSessionsDao.getBehaviouMindLikertSum(testSessionId);
    }

    @Override
    public List<Map<String, Object>> getBehaviourQuestion1(int testSessionId) {
        return this.testSessionsDao.getBehaviourQuestion1(testSessionId);
    }

    @Override
    public String getBehaviourMasterySum(int testSessionId) {
        return this.testSessionsDao.getBehaviourMasterySum(testSessionId);
    }

    @Override
    public Map<String, Object> getBehaviourQuestion2(int testSessionId) {
        return this.testSessionsDao.getBehaviourQuestion2(testSessionId);
    }

    @Override
    public List<Map<String, Object>>  getBehaviourQuestion3(int testSessionId) {
        return this.testSessionsDao.getBehaviourQuestion3(testSessionId);
    }

    @Override
    public Map<String, Object> getBehaviourQuestion4(int testSessionId) {
        return this.testSessionsDao.getBehaviourQuestion4(testSessionId);
    }

    @Override
    public String getBhaviourAbstinenceSum(int testSessionId) {
        return this.testSessionsDao.getBhaviourAbstinenceSum(testSessionId);
    }

    @Override
    public List<Map<String, Object>> getMentalImprovementActivities_QL2(int testSessionId) {
        return this.testSessionsDao.getMentalImprovementActivities_QL2(testSessionId);
    }

    @Override
    public Map<String, Object> getTendenciesTestPercForQList(int testSessionId) {
        return this.testSessionsDao.getTendenciesTestPercForQList(testSessionId);
    }

    @Override
    public Map<String, Object> getTendenciesTestPercForUList(int testSessionId) {
        return this.testSessionsDao.getTendenciesTestPercForUList(testSessionId);
    }

    @Override
    public Map<String, Object> getTendenciesTestPercForOList(int testSessionId) {
        return this.testSessionsDao.getTendenciesTestPercForOList(testSessionId);
    }

    @Override
    public int getTendenciesTestPercForRList(int testSessionId) {
        return this.testSessionsDao.getTendenciesTestPercForRList(testSessionId);
    }

    @Override
    public int getTendenciesTestPercForWithRList(int testSessionId) {
        return this.testSessionsDao.getTendenciesTestPercForWithRList(testSessionId);
    }

    @Override
    public List<Double> getPopAverageForAspect(int aspectId) {
        return this.testSessionsDao.getPopAverageForAspect(aspectId);
    }

    @Override
    public List<Double> getPopAspectScoreList(int sortOrder) {
        return this.testSessionsDao.getPopAspectScoreList(sortOrder);
    }

    @Override
    public List<Map<String, Object>> getQuestionOptionList(String takerId, int trait) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TestSessionWithQuestionList getTestSessionAllQueData(int takerId) {
        return this.testSessionsDao.getTestSessionAllQueData(takerId);
    }

    @Override
    public TestSessionWithQuestionList getTestSessionAllCovidQueDataForResult(int userId, int dimensionId) {
        return this.testSessionsDao.getTestSessionAllCovidQueDataForResult(userId, dimensionId);
    }

    @Override
    public List<Map<String, Object>> getSelfActualizationData(int testSessionId) {
        return this.testSessionsDao.getSelfActualizationData(testSessionId);
    }

    @Override
    public Map<String, Object> getSatisfactionWithLifeScale(int testSessionId) {
        return this.testSessionsDao.getSatisfactionWithLifeScale(testSessionId);
    }

    @Override
    public Map<String, Object> getLonelinessData(int testSessionId) {
        return this.testSessionsDao.getLonelinessData(testSessionId);
    }

    @Override
    public List<Map<String, Object>> getUCLAquestions(int testSessionId) {
        return this.testSessionsDao.getUCLAquestions(testSessionId);
    }

    @Override
    public List<Map<String, Object>> getSelfRsesList(int testSessionId) {
        return this.testSessionsDao.getSelfRsesList(testSessionId);
    }

    @Override
    public List<Map<String, Object>> getANSPTraitSum(int testSessionId, int is_reverse_key) {
        return this.testSessionsDao.getANSPTraitSum(testSessionId, is_reverse_key);
    }

    @Override
    public List<DarkFactorCalculatorDTO> getDARKTraitSum(int testSessionId, int is_reverse_key) {
        return this.testSessionsDao.getDARKTraitSum(testSessionId, is_reverse_key);
    }

    @Override
    public void updateTestSession(int sortOrder, int testSessionId, int questionTypeId) {
        this.testSessionsDao.updateTestSession(sortOrder, testSessionId, questionTypeId);
    }

    @Override
    public void updateTestSessionForMedicalProferssion(int sortOrder, int testSessionId) {
        this.testSessionsDao.updateTestSessionForMedicalProferssion(sortOrder, testSessionId);
    }

    @Override
    public String getQuestionOptionValueByQuestionOptionIdMultSelectQue(int questionId, String s) {
        return this.testSessionsDao.getQuestionOptionValueByQuestionOptionIdMultSelectQue(questionId, s);
    }

    @Override
    public String getQuestionValueByQuestionOptionIdRadio(String questionOptionId) {
        return this.testSessionsDao.getQuestionValueByQuestionOptionIdRadio(questionOptionId);
    }

    @Override
    public String getLabelByNumberOfQuestions(int numberOfQuestions) {
        return this.testSessionsDao.getLabelByNumberOfQuestions(numberOfQuestions);
    }

    @Override
    public int deleteLastSorder() {
        return this.testSessionsDao.deleteLastSorder();
    }

    @Override
    public TestSession getTestSessionDataByUserId(int userId) {
        return this.testSessionsDao.getTestSessionDataByUserId(userId);
    }

    @Override
    public PopulationStatsGroup getPopulationStatsForCovidQuestion(int questionId, int testSessionId, String type) {
        return this.testSessionsDao.getPopulationStatsForCovidQuestion(questionId, testSessionId, type);
    }

    @Override
    public int getLastQuestionIdWithoutCovidQue() {
        return this.testSessionsDao.getLastQuestionIdWithoutCovidQue();
    }

    @Override
    public String getDescByPercentile(double percentile, int parameterType, int parameterValue) {
        return this.testSessionsDao.getDescByPercentile(percentile, parameterType, parameterValue);
    }

    @Override
    public List<Map<String, Object>> getValueHealthAndDevelopmentGraph(int testSessionId) {
         return this.testSessionsDao.getValueHealthAndDevelopmentGraph(testSessionId);
    }

    @Override
    public Question getCurrentQuestionData(int testSessionId) {
         return this.testSessionsDao.getCurrentQuestionData(testSessionId);
    }

    @Override
    public int getAnpsTotalScore(int traitId, int takerId) {
         return this.testSessionsDao.getAnpsTotalScore(traitId,takerId);
    }


    @Override
    public Question getNextQuestion(int testSessionId) {
         return this.testSessionsDao.getNextQuestion(testSessionId);
    }
     @Override
    public void updatePopulationStatsInDatabase() {
       this.testSessionsDao.updatePopulationStatsInDatabase();
    }
}
