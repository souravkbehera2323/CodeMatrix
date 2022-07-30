/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.service.impl;

import cc.altius.matrixCodeVerse.model.NormalDistStats;
import cc.altius.matrixCodeVerse.model.PopulationStatsGroup;
import cc.altius.matrixCodeVerse.model.QuestionResponseBasic;
import cc.altius.matrixCodeVerse.model.TestSession;
import cc.altius.matrixCodeVerse.model.TestSessionWithQuestionList;
import cc.altius.sb.dao.TestResultDao;
import cc.altius.sb.framework.GlobalConstants;
import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.model.DTO.TakerNoOfQuestionAttempted;
import cc.altius.sb.model.Question;
import cc.altius.sb.model.QuestionResponse;
import cc.altius.sb.service.TestResultService;
import cc.altius.sb.service.TestSessionsService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 *
 * @author yogesh
 */
@Service
public class TestResultServiceImpl implements TestResultService{

    @Autowired
    private TestResultDao testResultDao;
    
    @Autowired
    private TestSessionsService testSessionsService;
    
    @Override
    public List<TestSession> getTakerSessionResultList(CustomUserDetails curUser, int flag) {
        return this.testResultDao.getTakerSessionResultList(curUser,flag);
    }

    @Override
    public List<QuestionResponse> getExportRawReport(int takerId) {
        return this.testResultDao.getExportRawReport(takerId);
    }

    @Override
    public List<Integer> getWisdomTakerListReport() {
        return this.testResultDao.getWisdomTakerListReport();
    }

    @Override
    public List<Question> getQuestionsSetCompletedList(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional) {
        return this.testResultDao.getQuestionsSetCompletedList(curUser, dimensionId, isMedicalProfessional);
    }

    @Override
    public List<Question> getQuestionsSetPendingList(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional) {
        return this.testResultDao.getQuestionsSetPendingList(curUser, dimensionId, isMedicalProfessional);
    } 
    
    @Override
    public List<Map<String, Object>> getQuestionsSetCompletedListCount(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional) {
        return this.testResultDao.getQuestionsSetCompletedListCount(curUser, dimensionId, isMedicalProfessional);
    }

    @Override
    public List<Map<String, Object>> getQuestionsSetPendingListCount(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional) {
        return this.testResultDao.getQuestionsSetPendingListCount(curUser, dimensionId, isMedicalProfessional);
    }   
    
    @Override
    public List<Map<String, Object>> getTestResultForCovid(int takerId, ModelMap model) {
        TestSessionWithQuestionList ts = this.testSessionsService.getTestSessionAllCovidQueDataForResult(takerId, GlobalConstants.DIMENSION_COVID);        
         List<Map<String, Object>> listDataMap = new LinkedList<>();
        if(ts.getCurrentQuestion()!=null)
        {
        String[] questionResponseValue = new String[ts.getCurrentQuestion().size()];
        int count = 0;
        Map<String, Object> dataMap;
        for (int i = 0; i < ts.getCurrentQuestion().size(); i++) {
            int queTypeId = ts.getCurrentQuestion().get(i).getQuestionTypeId();
            if (queTypeId == GlobalConstants.QUESTION_TYPE_ID_RADIO || queTypeId == GlobalConstants.QUESTION_TYPE_ID_NUMBER) {
                double score = 0.0;
                if (queTypeId == GlobalConstants.QUESTION_TYPE_ID_RADIO) {
                    score = this.testSessionsService.getQuestionOptionValueByQuestionOptionId(ts.getCurrentQuestion().get(i).getQuestionId(), ts.getCurrentQuestion().get(i).getQuestionOptionList().get(0).getQuestionOptionId() + "");
                }
                if (queTypeId == GlobalConstants.QUESTION_TYPE_ID_NUMBER) {
                    try {
                        score = Double.valueOf(ts.getCurrentQuestion().get(i).getQuestionOptionList().get(0).getQuestionOptionValue());                        
                    } catch (Exception e) {
                    }
                }                                
                QuestionResponseBasic qrbQ = new QuestionResponseBasic();
                qrbQ.setId(ts.getCurrentQuestion().get(i).getQuestionId());
                qrbQ.setScore(score);
                ts.getCurrentStats().addResponseForQuestion(qrbQ);

                QuestionResponseBasic qrbD = new QuestionResponseBasic();
                qrbD.setId(ts.getCurrentQuestion().get(i).getDimension().getId());
                qrbD.setScore(score);
                ts.getCurrentStats().addResponseForDimension(qrbD);

                QuestionResponseBasic qrbT = new QuestionResponseBasic();
                qrbT.setId(ts.getCurrentQuestion().get(i).getTrait().getId());
                qrbT.setScore(score);
                ts.getCurrentStats().addResponseForTrait(qrbT);

                QuestionResponseBasic qrbA = new QuestionResponseBasic();
                qrbA.setId(ts.getCurrentQuestion().get(i).getAspect().getId());
                qrbA.setScore(score);
                ts.getCurrentStats().addResponseForAspect(qrbA);

                questionResponseValue[count] = Double.toString(score);
                ts.setCurrentQuestionResponse(questionResponseValue);
                ts.setPopulationStats(this.testSessionsService.getPopulationStatsForCovidQuestion(ts.getCurrentQuestion().get(i).getQuestionId(), ts.getTestSessionId(), "pdf"));

                if ((ts.getCurrentQuestion().get(i).getDimension().getId() == 6)) {

                    
                    if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 57)) {
                        if (getExtremismDimensionData(i, model, ts).get("ques").getNormalDistribution() > 0) {                            
                            dataMap = new HashMap<>();
                            dataMap.put("question", ts.getCurrentQuestion().get(i).getName());
                            dataMap.put("normalDstrValue", Math.floor(getExtremismDimensionData(i, model, ts).get("ques").getNormalDistribution() * 100) / 100);
                            listDataMap.add(dataMap);
                        }
                    }

                    if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 58)) {
                        if (getExtremismDimensionData(i, model, ts).get("ques").getNormalDistribution() > 0) {                            
                            dataMap = new HashMap<>();
                            dataMap.put("question", ts.getCurrentQuestion().get(i).getName());
                            dataMap.put("normalDstrValue", Math.floor(getExtremismDimensionData(i, model, ts).get("ques").getNormalDistribution() * 100) / 100);
                            listDataMap.add(dataMap);
                        }
                    }
                    
                }
            }
        }
        }
        return listDataMap;
    }
    
    private Map<String, NormalDistStats> getExtremismDimensionData(int i, ModelMap model, TestSessionWithQuestionList ts) {
        Map<String, NormalDistStats> stats = new HashMap<>();

        NormalDistStats quesStats = new NormalDistStats();
        quesStats.setCurrentMean(ts.getCurrentStats().getStats(PopulationStatsGroup.QUESTION_STATS, ts.getCurrentQuestion().get(i).getQuestionId()).getMean());
        quesStats.setScore(ts.getCurrentStats().getStats(PopulationStatsGroup.QUESTION_STATS, ts.getCurrentQuestion().get(i).getQuestionId()).getTotalScore());
        double Quesmean = 0, QuesstdDev = 0;
        if (ts.getPopulationStats().getStats(PopulationStatsGroup.QUESTION_STATS, ts.getCurrentQuestion().get(i).getQuestionId()) != null) {
            Quesmean = ts.getPopulationStats().getStats(PopulationStatsGroup.QUESTION_STATS, ts.getCurrentQuestion().get(i).getQuestionId()).getMean();
            QuesstdDev = ts.getPopulationStats().getStats(PopulationStatsGroup.QUESTION_STATS, ts.getCurrentQuestion().get(i).getQuestionId()).getStdDeviation();
        }
        quesStats.setPopulationMean(Quesmean);
        quesStats.setPopulationStdDev(QuesstdDev);
        stats.put("ques", quesStats);
        return stats;
    }

    @Override
    public List<TakerNoOfQuestionAttempted> getNoOfQuestionAttemptedByTaker(int takerId) {
        return this.testResultDao.getNoOfQuestionAttemptedByTaker(takerId);
    }

    @Override
    public int updateNoOfQuestionAttemptedByTaker(int active,int takerId) {
     return this.testResultDao.updateNoOfQuestionAttemptedByTaker(active,takerId);  
    }
    
    
}
