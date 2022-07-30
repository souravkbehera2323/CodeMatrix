/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.webService;

import cc.altius.matrixCodeVerse.model.NormalDistStats;
import cc.altius.matrixCodeVerse.model.PopulationStatsGroup;
import cc.altius.matrixCodeVerse.model.QuestionResponseBasic;
import cc.altius.matrixCodeVerse.model.Taker;
import cc.altius.matrixCodeVerse.model.TestSessionWithQuestionList;
import cc.altius.sb.model.ResponseFormat;
import cc.altius.sb.service.RegisterService;
import cc.altius.sb.service.TestSessionsService;
import cc.altius.sb.utils.LogUtils;
import com.google.gson.Gson;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author altius
 */

//codeoflife send the takerId for this API we produce result to the taker and send result back to the codeoflife.
@RestController
@RequestMapping("/api")
public class WebServiceController extends SpringBeanAutowiringSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegisterService registerService;
    @Autowired
    private TestSessionsService sessionService;
    private String apiToken = "Y@g4007e$eem";

    @PostMapping(value = "/getTaker", produces = "application/json;charset=UTF-8") //    @ApiOperation(value = "Check UserName and Password and AppToken",
    public ResponseEntity<Taker> getTaker(@RequestHeader(value = "phpToken") String phpToken,
            @RequestBody String content) throws ServletRequestBindingException {
        logger.info(LogUtils.buildStringForSystemLog("content=====>" + content));
        ResponseFormat responseFormat = new ResponseFormat();
        try {
            Map<String, Object> responseMap = null;
            if (phpToken.equals(this.apiToken)) {
                if (content.length() <= 2) {
                    responseFormat.setStatus("failed");
                    responseFormat.setFailedReason("Body contains no information");
                    return new ResponseEntity(responseFormat, HttpStatus.UNAUTHORIZED);
                }
                Gson gson = new Gson();
                Taker taker = gson.fromJson(content, Taker.class);
                logger.info(LogUtils.buildStringForSystemLog("registraionScore Object Recived  :" + taker));
                int takerId = taker.getTakerId();
                TestSessionWithQuestionList ts = this.sessionService.getTestSessionAllQueData(takerId);
                Map<String, Map<String, NormalDistStats>> data = new HashMap<>();
                String[] questionResponseValue = new String[ts.getCurrentQuestion().size()];
                int count = 0;
                for (int i = 0; i < ts.getCurrentQuestion().size(); i++) {
                    double score = this.sessionService.getQuestionOptionValueByQuestionOptionId(ts.getCurrentQuestion().get(i).getQuestionId(), ts.getCurrentQuestion().get(i).getQuestionOptionList().get(0).getQuestionOptionId() + "");
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
                    ts.setPopulationStats(this.sessionService.getPopulationStatsForQuestionFromLiveData(ts.getCurrentQuestion().get(i).getQuestionId(), ts.getTestSessionId(), "pdf"));

                    if (ts.getCurrentQuestion().get(i).getQuestionTypeId() == 1) {
                        ts.setPopulationStats(this.sessionService.getPopulationStatsForQuestionFromLiveData(ts.getCurrentQuestion().get(i).getQuestionId(), ts.getTestSessionId(), "pdf"));
                        if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 1)) {
                            data.put("agreeablenessTraitData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 1)) {
                            data.put("compassionData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 2)) {
                            data.put("politenessData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 2)) {
                            data.put("conscientiousnessTraitData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 3)) {
                            data.put("industriousnessData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 4)) {
                            data.put("orderlinessData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 3)) {
                            data.put("extroversionData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 5)) {
                            data.put("enthusiasmData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 6)) {
                            data.put("assertivenessData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 4)) {
                            data.put("neuroticismData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 7)) {
                            data.put("withdrawalData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 8)) {
                            data.put("volatilityData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 5)) {
                            data.put("opennesstoExperienceData", getExtremismDimensionData(i, ts, takerId));
                        }

                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 9)) {
                            data.put("intellectData", getExtremismDimensionData(i, ts, takerId));
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 10)) {
                            data.put("opennessData", getExtremismDimensionData(i, ts, takerId));
                        }
                    }
                }
                if (takerId != 0) {
                    return new ResponseEntity(data, HttpStatus.OK);
                } else {
                    responseFormat.setStatus("failed");
                    responseFormat.setFailedReason("error occured while insertion");
                    return new ResponseEntity(responseFormat, HttpStatus.UNAUTHORIZED);
                }

            } else {
                responseFormat.setStatus("failed");
                responseFormat.setFailedReason("INVALID_APP_TOKEN");
                responseFormat.setFailedValue(99);
                return new ResponseEntity(responseFormat, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseFormat.setStatus("failed");
            responseFormat.setFailedReason("Invalid information try" + e.getMessage());
            return new ResponseEntity(responseFormat, HttpStatus.UNAUTHORIZED);
        }
    }

    private Map<String, NormalDistStats> getExtremismDimensionData(int i, TestSessionWithQuestionList ts, int takerId) {
        Map<String, NormalDistStats> stats = new HashMap<>();
        NormalDistStats traitStats = new NormalDistStats();
        traitStats.setCurrentMean(ts.getCurrentStats().getStats(PopulationStatsGroup.TRAIT_STATS, ts.getCurrentQuestion().get(i).getTrait().getId()).getMean());
        traitStats.setScore(ts.getCurrentStats().getStats(PopulationStatsGroup.TRAIT_STATS, ts.getCurrentQuestion().get(i).getTrait().getId()).getTotalScore());
        double Traitmean = 0, TraitstdDev = 0;
        if (ts.getPopulationStats().getStats(PopulationStatsGroup.TRAIT_STATS, ts.getCurrentQuestion().get(i).getTrait().getId()) != null) {
            Traitmean = ts.getPopulationStats().getStats(PopulationStatsGroup.TRAIT_STATS, ts.getCurrentQuestion().get(i).getTrait().getId()).getMean();
            TraitstdDev = ts.getPopulationStats().getStats(PopulationStatsGroup.TRAIT_STATS, ts.getCurrentQuestion().get(i).getTrait().getId()).getStdDeviation();
        }
        traitStats.setPopulationMean(Traitmean);
        traitStats.setPopulationStdDev(TraitstdDev);
        stats.put("trait", traitStats);
        NormalDistStats aspectStats = new NormalDistStats();
        aspectStats.setId(ts.getCurrentQuestion().get(i).getAspect().getId());
        aspectStats.setCurrentMean(ts.getCurrentStats().getStats(PopulationStatsGroup.ASPECT_STATS, ts.getCurrentQuestion().get(i).getAspect().getId()).getMean());
        aspectStats.setScore(ts.getCurrentStats().getStats(PopulationStatsGroup.ASPECT_STATS, ts.getCurrentQuestion().get(i).getAspect().getId()).getTotalScore());
        double Aspectmean = 0, AspectDev = 0;
        if (ts.getPopulationStats().getStats(PopulationStatsGroup.ASPECT_STATS, ts.getCurrentQuestion().get(i).getAspect().getId()) != null) {
            Aspectmean = ts.getPopulationStats().getStats(PopulationStatsGroup.ASPECT_STATS, ts.getCurrentQuestion().get(i).getAspect().getId()).getMean();
            AspectDev = ts.getPopulationStats().getStats(PopulationStatsGroup.ASPECT_STATS, ts.getCurrentQuestion().get(i).getAspect().getId()).getStdDeviation();
        }
        aspectStats.setPopulationMean(Aspectmean);
        aspectStats.setPopulationStdDev(AspectDev);
        stats.put("aspect", aspectStats);
        return stats;
    }

}
