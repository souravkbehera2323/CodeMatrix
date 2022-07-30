/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.matrixCodeVerse.model.NormalDistStats;
import cc.altius.matrixCodeVerse.model.NormalDistribution;
import cc.altius.matrixCodeVerse.model.PopulationStatsGroup;
import cc.altius.matrixCodeVerse.model.QuestionResponseBasic;
import cc.altius.matrixCodeVerse.model.TestSessionWithQuestionList;
import static cc.altius.maven.AllAltiusUtil.round;
import cc.altius.sb.service.TestSessionsService;
//
//import static cc.altius.utils.StringUtils.round;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import cc.altius.utils.DateUtils;
import java.util.LinkedList;

/**
 *
 * @author altius
 */
//showing comprehensiveDossier
@Controller
public class ComprehensiveDossierController {

    @Autowired
    private TestSessionsService testSessionsService;

    @RequestMapping(value = "/pdf/comprehensiveDossier.htm", method = RequestMethod.GET)
    public String getPdfFromSavedData(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "takerId", required = false) int takerId) throws IOException, InterruptedException, ServletRequestBindingException {
        TestSessionWithQuestionList ts = this.testSessionsService.getTestSessionAllQueData(takerId);
        double avatarLevelNormalDistribution1 = 0, avatarLevelNormalDistribution2 = 0, avatarLevelNormalDistribution3 = 0, avatarLevelNormalDistribution4 = 0, avatarLevelNormalDistribution5 = 0, avatarLevelNormalDistribution6 = 0, avatarLevelNormalDistribution7 = 0,
                avatarLevelNormalDistribution8 = 0, avatarLevelNormalDistribution9 = 0, avatarLevelNormalDistribution10 = 0, avatarLevelNormalDistribution11 = 0,
                avatarLevelNormalDistribution12 = 0, avatarLevelNormalDistribution13 = 0, avatarLevelNormalDistribution14 = 0, avatarLevelNormalDistribution15 = 0;

        double AnpsPlayPercentage = 0, AnpsSeekingPercenatge = 0, AnpsCarePercentile = 0, AnpsFearPercentile = 0, AnpsAngerPercentile = 0,
                AnpsSadnessPercentile = 0, AnpsSpiritualityPercentile = 0, AnpsLustPercentile = 0;
        double darkfactorEgoismPercentile = 0, darkfactorMachiavellianismPercentile = 0, darkfactorNarcissismPercentile = 0, darkfactorPsychopathyPercentile = 0, darkfactorMoraldisengagementPercentile = 0, darkfactorPsychologicalPercentile = 0, darkfactorSelfInterestPercentile = 0, darkfactorSpitefulnessPercentile = 0, darkfactorSadismPercentile = 0;
        double wholeheartedTraitPercentile = 0, uclaTraitPercentile = 0, rsesTraitPercentile = 0, slAspectPercentile = 0, scAspectPercentile = 0;
        Map<Integer, Object> questionWiseNDList = new HashMap<>();
        Map<String, Object> uclaDataMap = new HashMap<>();
        int count = 0;
       // System.out.println("startTime-->"+DateUtils.getCurrentDateObject(DateUtils.IST));
        for (int i = 0; i < ts.getCurrentQuestion().size(); i++) {
            if (ts.getCurrentQuestion().get(i).getQuestionTypeId() == 1) {
                 double  score = this.testSessionsService.getQuestionOptionValueByQuestionOptionId(ts.getCurrentQuestion().get(i).getQuestionId(), ts.getCurrentQuestion().get(i).getQuestionOptionList().get(0).getQuestionOptionId() + "");
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

                ts.setPopulationStats(this.testSessionsService.getPopulationStatsForQuestionFromLiveData(ts.getCurrentQuestion().get(i).getQuestionId(), ts.getTestSessionId(), "pdf"));
                if ((ts.getCurrentQuestion().get(i).getDimension().getId() == 2)) {
                    model.addAttribute("extremismDimension", ts.getCurrentQuestion().get(i).getDimension().getName());
                    model.addAttribute("extremismDimensionND", ts.getNormalDistributionForPopulationString(ts.getCurrentQuestion().get(i).getDimension().getId(), PopulationStatsGroup.DIMENSION_STATS));
                }
                if ((ts.getCurrentQuestion().get(i).getDimension().getId() == 1) || (ts.getCurrentQuestion().get(i).getDimension().getId() == 3) || (ts.getCurrentQuestion().get(i).getTrait().getId() == 35) || (ts.getCurrentQuestion().get(i).getQuestionId() == 42)) {
                    questionWiseNDList.put(ts.getCurrentQuestion().get(i).getQuestionId(), getExtremismDimensionData(i, model, ts));
                }

                if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 1)) {
                    model.addAttribute("agreeablenessTrait", ts.getCurrentQuestion().get(i).getTrait().getName());
                    model.addAttribute("agreeablenessTraitData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution1 = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 1)) {
                    model.addAttribute("compassion", ts.getCurrentQuestion().get(i).getAspect().getName());
                    model.addAttribute("compassionData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution2 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 2)) {
                    model.addAttribute("politeness", ts.getCurrentQuestion().get(i).getAspect().getName());
                    model.addAttribute("politenessData", getExtremismDimensionData(i, model, ts));
                    model.addAttribute("politenessTraitND", ts.getNormalDistributionForPopulationString(ts.getCurrentQuestion().get(i).getAspect().getId(), PopulationStatsGroup.ASPECT_STATS));
                    if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution3 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 2)) {
                    model.addAttribute("conscientiousnessTrait", ts.getCurrentQuestion().get(i).getTrait().getName());
                    model.addAttribute("conscientiousnessTraitData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution4 = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 3)) {
                    model.addAttribute("industriousness", ts.getCurrentQuestion().get(i).getAspect().getName());
                    model.addAttribute("industriousnessData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution5 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 4)) {
                    model.addAttribute("orderliness", ts.getCurrentQuestion().get(i).getAspect().getName());
                    model.addAttribute("orderlinessData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution6 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 3)) {
                    model.addAttribute("extroversion", ts.getCurrentQuestion().get(i).getTrait().getName());
                    model.addAttribute("extroversionData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution7 = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 5)) {
                    model.addAttribute("enthusiasm", ts.getCurrentQuestion().get(i).getAspect().getName());
                    model.addAttribute("enthusiasmData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution8 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 6)) {
                    model.addAttribute("assertiveness", ts.getCurrentQuestion().get(i).getAspect().getName());
                    model.addAttribute("assertivenessData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution9 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 4)) {
                    model.addAttribute("neuroticism", ts.getCurrentQuestion().get(i).getTrait().getName());
                    model.addAttribute("neuroticismData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution10 = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 7)) {
                    model.addAttribute("withdrawal", ts.getCurrentQuestion().get(i).getAspect().getName());
                    model.addAttribute("withdrawalData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution11 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 8)) {
                    model.addAttribute("volatility", ts.getCurrentQuestion().get(i).getAspect().getName());
                    model.addAttribute("volatilityData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution12 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 5)) {
                    model.addAttribute("opennesstoExperience", ts.getCurrentQuestion().get(i).getTrait().getName());
                    model.addAttribute("opennesstoExperienceData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution13 = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                }

                if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 9)) {
                    model.addAttribute("intellect", ts.getCurrentQuestion().get(i).getAspect().getName());
                    model.addAttribute("intellectData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution14 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                    }
                }
                if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 10)) {

                    model.addAttribute("openness", ts.getCurrentQuestion().get(i).getAspect().getName());
                    model.addAttribute("opennessData", getExtremismDimensionData(i, model, ts));
                    if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                        avatarLevelNormalDistribution15 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                    }
                }
                //ANSP CALCULATOR
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 46) {
                    AnpsPlayPercentage = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    model.addAttribute("AnpsPlayScore",  getExtremismDimensionData(i, model, ts).get("trait").getScore());
//                    model.addAttribute("AnpsPlayScore", testSessionsService.getAnpsTotalScore(ts.getCurrentQuestion().get(i).getTrait().getId(), ts.getTaker().getTakerId()));
                    model.addAttribute("AnpsPlayAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("AnpsPlayPercentage", AnpsPlayPercentage);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 42) {
                    AnpsSeekingPercenatge = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
//                    model.addAttribute("AnpsSeekingScore", testSessionsService.getAnpsTotalScore(ts.getCurrentQuestion().get(i).getTrait().getId(), ts.getTaker().getTakerId()));
                    model.addAttribute("AnpsSeekingScore", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("AnpsSeekingAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("AnpsSeekingPercenatge", AnpsSeekingPercenatge);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 44) {
                    AnpsCarePercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
//                    model.addAttribute("AnpsCareScore", testSessionsService.getAnpsTotalScore(ts.getCurrentQuestion().get(i).getTrait().getId(), ts.getTaker().getTakerId()));
                    model.addAttribute("AnpsCareScore", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("AnpsCareAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("AnpsCarePercentile", AnpsCarePercentile);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 43) {
                    AnpsFearPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
//                    model.addAttribute("AnpsFearScore", testSessionsService.getAnpsTotalScore(ts.getCurrentQuestion().get(i).getTrait().getId(), ts.getTaker().getTakerId()));
                    model.addAttribute("AnpsFearScore", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("AnpsFearAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("AnpsFearPercentile", AnpsFearPercentile);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 45) {
                    AnpsAngerPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
//                    model.addAttribute("AnpsAngerScore", testSessionsService.getAnpsTotalScore(ts.getCurrentQuestion().get(i).getTrait().getId(), ts.getTaker().getTakerId()));
                    model.addAttribute("AnpsAngerScore", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("AnpsAngerAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("AnpsAngerPercentile", AnpsAngerPercentile);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 47) {
                    AnpsSadnessPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
//                    model.addAttribute("AnpsSadnessScore", testSessionsService.getAnpsTotalScore(ts.getCurrentQuestion().get(i).getTrait().getId(), ts.getTaker().getTakerId()));
                    model.addAttribute("AnpsSadnessScore", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("AnpsSadnessAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("AnpsSadnessPercentile", AnpsSadnessPercentile);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 48) {
                    AnpsSpiritualityPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
//                    model.addAttribute("AnpsSpiritualityScore", testSessionsService.getAnpsTotalScore(ts.getCurrentQuestion().get(i).getTrait().getId(), ts.getTaker().getTakerId()));
                    model.addAttribute("AnpsSpiritualityScore", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("AnpsSpiritualityAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("AnpsSpiritualityPercentile", AnpsSpiritualityPercentile);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 49) {
                    AnpsLustPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
//                    model.addAttribute("AnpsLustScore", testSessionsService.getAnpsTotalScore(ts.getCurrentQuestion().get(i).getTrait().getId(), ts.getTaker().getTakerId()));
                    model.addAttribute("AnpsLustScore", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("AnpsLustAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("AnpsLustPercentile", AnpsLustPercentile);
                }

                // DARK FACTOR
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 11) {
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        darkfactorEgoismPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                    model.addAttribute("darkfactorEgoismTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("darkfactorEgoismAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("darkfactorEgoismPercentile", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 12) {
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        darkfactorMachiavellianismPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                    model.addAttribute("darkfactorMachiavellianismAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("darkfactorMachiavellianismtotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("darkfactorMachiavellianismPercentile", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 13) {
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        darkfactorNarcissismPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                    model.addAttribute("darkfactorNarcissismAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("darkfactorNarcissismTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("darkfactorNarcissismPercentile", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());

                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 15) {
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        darkfactorPsychopathyPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                    model.addAttribute("darkfactorPsychopathyAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("darkfactorPsychopathyTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("darkfactorPsychopathyPercentile", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());

                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 17) {
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        darkfactorMoraldisengagementPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                    model.addAttribute("darkfactorMoralDisengagementAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("darkfactorMoralDisengagementTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("darkfactorMoraldisengagementPercentile", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 18) {
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        darkfactorPsychologicalPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                    model.addAttribute("darkfactorPsychologicalAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("darkfactorPsychologicalTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("darkfactorPsychologicalPercentile", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 19) {
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        darkfactorSelfInterestPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                    model.addAttribute("darkfactorSelfInterestAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("darkfactorSelfInterestTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("darkfactorSelfInterestPercentile", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 20) {
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        darkfactorSpitefulnessPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                    model.addAttribute("darkfactorSpitefulnessAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("darkfactorSpitefulnessTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("darkfactorSpitefulnessPercentile", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 41) {
                    if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                        darkfactorSadismPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    }
                    model.addAttribute("darkfactorSadismAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("darkfactorSadismTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("darkfactorSadismPercentile", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 36) {
                    model.addAttribute("wholeTraitAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("wholeTraitTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("wholeheartedTraitND", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                    wholeheartedTraitPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 19 && ts.getCurrentQuestion().get(i).getTrait().getId() == 36) {
                    model.addAttribute("senseOfSelfMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("senseOfSelfTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("senseOfSelfND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());

                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 20 && ts.getCurrentQuestion().get(i).getTrait().getId() == 36) {
                    model.addAttribute("thinkingMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("thinkingTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("thinkingND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());

                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 21 && ts.getCurrentQuestion().get(i).getTrait().getId() == 36) {
                    model.addAttribute("actionMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("actionTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("actionND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());

                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 14) {
                    model.addAttribute("selfActualizationMean", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("selfActualizationTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("selfActualizationND", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 13 && ts.getCurrentQuestion().get(i).getTrait().getId() == 14) {
                    model.addAttribute("depthMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("depthTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("depthND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());

                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 11 && ts.getCurrentQuestion().get(i).getTrait().getId() == 14) {
                    model.addAttribute("acceptanceMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("acceptanceTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("acceptanceND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());

                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 12 && ts.getCurrentQuestion().get(i).getTrait().getId() == 14) {
                    model.addAttribute("impactMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("impactTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("impactND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());

                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 14 && ts.getCurrentQuestion().get(i).getTrait().getId() == 14) {
                    model.addAttribute("individualityMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("individualityTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("individualityND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());

                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 39) {
                    uclaDataMap.put(ts.getCurrentQuestion().get(i).getName(), getExtremismDimensionData(i, model, ts));
                    model.addAttribute("uclaTraitMean", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("uclaTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("uclaTraitND", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                    uclaTraitPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                }

                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 22 && ts.getCurrentQuestion().get(i).getTrait().getId() == 39) {
                    model.addAttribute("lTraitMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("lTraitTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("lTraitND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 16) {
                    model.addAttribute("rsesTraitMean", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("rsesTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("rsesTraitND", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                    rsesTraitPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 15 && ts.getCurrentQuestion().get(i).getTrait().getId() == 16) {
                    model.addAttribute("slAspectMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("slAspectTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("slAspectND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());
                    slAspectPercentile = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 16 && ts.getCurrentQuestion().get(i).getTrait().getId() == 16) {
                    model.addAttribute("scAspectMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("scAspectTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("scAspectND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());
                    scAspectPercentile = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 38) {
                    model.addAttribute("swlsTraitMean", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("swlsTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("swlsTraitND", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 40) {
                    model.addAttribute("BDIAverage", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("BDITotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("BDIND", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 35) {
                    model.addAttribute("connectionMean", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("connectionTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("connectionND", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 22) {
                    model.addAttribute("mindTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("mindNd", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 24) {
                    model.addAttribute("abstinenceTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("abstinenceNd", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 25) {
                    model.addAttribute("masteryTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("masteryNd", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 18) {
                    model.addAttribute("romanticRelationShipMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("romanticRelationShipAspectTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("romanticRelationShipAspectND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 39) {
                    model.addAttribute("coOpScoreMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("coOpScoreTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("coOpScoreND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());
                }
            }
            model.addAttribute("testSessions", ts);
        }
        //System.out.println("stopTime-->"+DateUtils.getCurrentDateObject(DateUtils.IST));
        //Description Module for Extremism
        List<String> extremismDescList = new LinkedList<>();
        String desc;
        if (avatarLevelNormalDistribution1 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution1, 1, 1);
            extremismDescList.add("<b>Agreeableness</b>: " + desc);
        }

        if (avatarLevelNormalDistribution2 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution2, 2, 1);
            extremismDescList.add("<b>Compassion</b>: " + desc);
        }
        if (avatarLevelNormalDistribution3 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution3, 2, 2);
            extremismDescList.add("<b>Politeness</b>: " + desc);
        }
        if (avatarLevelNormalDistribution4 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution4, 1, 2);
            extremismDescList.add("<b>Conscientiousness</b>: " + desc);
        }
        if (avatarLevelNormalDistribution5 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution5, 2, 3);
            extremismDescList.add("<b>Industriousness</b>: " + desc);
        }
        if (avatarLevelNormalDistribution6 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution6, 2, 4);
            extremismDescList.add("<b>Orderliness</b>: " + desc);
        }
        if (avatarLevelNormalDistribution7 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution7, 1, 3);
            extremismDescList.add("<b>Extroversion</b>: " + desc);
        }
        if (avatarLevelNormalDistribution8 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution8, 2, 5);
            extremismDescList.add("<b>Enthusiasm</b>: " + desc);
        }
        if (avatarLevelNormalDistribution9 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution9, 2, 6);
            extremismDescList.add("<b>Assertiveness</b>: " + desc);
        }
        if (avatarLevelNormalDistribution10 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution10, 1, 4);
            extremismDescList.add("<b>Neuroticism</b>: " + desc);
        }
        if (avatarLevelNormalDistribution11 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution11, 2, 7);
            extremismDescList.add("<b>Withdrawal</b>: " + desc);
        }
        if (avatarLevelNormalDistribution12 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution12, 2, 8);
            extremismDescList.add("<b>Volatility</b>: " + desc);
        }
        if (avatarLevelNormalDistribution13 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution13, 1, 5);
            extremismDescList.add("<b>Openness to Experience</b>: " + desc);
        }
        if (avatarLevelNormalDistribution14 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution14, 2, 9);
            extremismDescList.add("<b>Intellect</b>: " + desc);
        }
        if (avatarLevelNormalDistribution15 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution15, 2, 10);
            extremismDescList.add("<b>Openness</b>: " + desc);
        }

        //Description Module for ANPS
        List<String> anpsDescList = new LinkedList<>();
        if (AnpsPlayPercentage > 0) {
            desc = this.testSessionsService.getDescByPercentile(AnpsPlayPercentage, 1, 46);
            anpsDescList.add("<b>Play</b>: " + desc);
        }
        if (AnpsSeekingPercenatge > 0) {
            desc = this.testSessionsService.getDescByPercentile(AnpsSeekingPercenatge, 1, 42);
            anpsDescList.add("<b>Seeking</b>: " + desc);
        }
        if (AnpsCarePercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(AnpsCarePercentile, 1, 44);
            anpsDescList.add("<b>Care</b>: " + desc);
        }
        if (AnpsFearPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(AnpsFearPercentile, 1, 43);
            anpsDescList.add("<b>Fear</b>: " + desc);
        }
        if (AnpsAngerPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(AnpsAngerPercentile, 1, 45);
            anpsDescList.add("<b>Anger</b>: " + desc);
        }
        if (AnpsSadnessPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(AnpsSadnessPercentile, 1, 47);
            anpsDescList.add("<b>Sadness</b>: " + desc);
        }
        if (AnpsSpiritualityPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(AnpsSpiritualityPercentile, 1, 48);
            anpsDescList.add("<b>Spirituality</b>: " + desc);
        }
        if (AnpsLustPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(AnpsLustPercentile, 1, 49);
            anpsDescList.add("<b>Lust</b>: " + desc);
        }

        model.addAttribute("anpsDescList", anpsDescList);
        model.addAttribute("extremismDescList", extremismDescList);
        model.addAttribute("uclaDataMap", uclaDataMap);
        model.addAttribute("questionWiseNDList", questionWiseNDList);

        //Description Module for Disposition
        List<String> dispositionDescList = new LinkedList<>();
        if (wholeheartedTraitPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(wholeheartedTraitPercentile, 1, 36);
            dispositionDescList.add("<b>Satisfaction With Life</b>: " + desc);
        }
        if (uclaTraitPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(uclaTraitPercentile, 1, 39);
            dispositionDescList.add("<b>Loneliness</b>: " + desc);
        }
        if (rsesTraitPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(rsesTraitPercentile, 1, 16);
            dispositionDescList.add("<b>Self-Esteem</b>: " + desc);
        }
        if (slAspectPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(slAspectPercentile, 2, 15);
            dispositionDescList.add("<b>Self-Liking</b>: " + desc);
        }
        if (scAspectPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(scAspectPercentile, 2, 16);
            dispositionDescList.add("<b>Self-Competence</b>: " + desc);
        }
        model.addAttribute("dispositionDescList", dispositionDescList);

        // Value dimension
        List<Map<String, Object>> valueHealthAndDevelopmentGraphList = this.testSessionsService.getValueHealthAndDevelopmentGraph(ts.getTestSessionId());

        List<Object> valueHealthAndDevelopmentGraphSubList = new ArrayList<>();
        for (int i = 0; i < valueHealthAndDevelopmentGraphList.size(); i++) {
            valueHealthAndDevelopmentGraphSubList.add(0);
        }
        int kk = 0;
        for (Map<String, Object> map : valueHealthAndDevelopmentGraphList) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                String aa = value + "";
                valueHealthAndDevelopmentGraphSubList.set(kk, aa);
                kk++;
            }
        }
        StringBuilder valueHealthAndDevelopmentGraphData = new StringBuilder();
        for (int i = 0; i < valueHealthAndDevelopmentGraphList.size(); i++) {
            valueHealthAndDevelopmentGraphData.append("'").append(valueHealthAndDevelopmentGraphSubList.get(i)).append("',");
        }
        if (valueHealthAndDevelopmentGraphData.length() > 0) {
            valueHealthAndDevelopmentGraphData.setLength(valueHealthAndDevelopmentGraphData.length() - 1);
        }
        model.addAttribute("valueHealthAndDevelopmentGraph", valueHealthAndDevelopmentGraphData);

        List<Map<String, Object>> forcedValueShapeGraphDataForQue = this.testSessionsService.getCognitionDimensionData(takerId, 17);
        List<Map<String, Object>> forcedValueShapeGraphForQueOption = this.testSessionsService.getQuestionOptionList(takerId, 17);
        try {
            approachList1(forcedValueShapeGraphDataForQue, model, takerId, forcedValueShapeGraphForQueOption, "forcedValueShapeGraph");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> sourcesOfValidationGraphDataForQue = this.testSessionsService.getCognitionDimensionData(takerId, 18);
        List<Map<String, Object>> sourcesOfValidationGraphForQueOption = this.testSessionsService.getQuestionOptionList(takerId, 18);
        try {
            approachList1(sourcesOfValidationGraphDataForQue, model, takerId, sourcesOfValidationGraphForQueOption, "sourcesOfValidationGraph");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // cognition dimension
        List<Map<String, Object>> problemSolvingApproachDataForQue = this.testSessionsService.getCognitionDimensionData(takerId, 37);
        List<Map<String, Object>> problemSolvingApproachForQueOption = this.testSessionsService.getQuestionOptionList(takerId, 37);
        try {
            approachList1(problemSolvingApproachDataForQue, model, takerId, problemSolvingApproachForQueOption, "problemSolvingApproach");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> collaborativeApproachDataForQue = this.testSessionsService.getCognitionDimensionData(takerId, 38);
        List<Map<String, Object>> questionOptionsCollaborativeAppro = this.testSessionsService.getQuestionOptionList(takerId, 38);
        try {
            approachList1(collaborativeApproachDataForQue, model, takerId, questionOptionsCollaborativeAppro, "collabrorativeApproach");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> treatResponseApproachDataForQue = this.testSessionsService.getCognitionDimensionData(takerId, 39);
        List<Map<String, Object>> questionOptionstreatResponseAppro = this.testSessionsService.getQuestionOptionList(takerId, 39);
        try {
            approachList1(treatResponseApproachDataForQue, model, takerId, questionOptionstreatResponseAppro, "treatResponseApproach");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //behaviour dimension
        Map<String, Object> tendenciesTestPercForQ = this.testSessionsService.getTendenciesTestPercForQList(ts.getTestSessionId());
        model.addAttribute("tendenciesTestPercForQRation", tendenciesTestPercForQ.get("ratio"));
        model.addAttribute("tendenciesTestPercForQPercentage", tendenciesTestPercForQ.get("percent"));
        Map<String, Object> tendenciesTestPercForU = this.testSessionsService.getTendenciesTestPercForUList(ts.getTestSessionId());
        model.addAttribute("tendenciesTestPercForURation", tendenciesTestPercForU.get("ratio"));
        model.addAttribute("tendenciesTestPercForUPercentage", tendenciesTestPercForU.get("percent"));
        Map<String, Object> tendenciesTestPercForO = this.testSessionsService.getTendenciesTestPercForOList(ts.getTestSessionId());
        model.addAttribute("tendenciesTestPercForORation", tendenciesTestPercForO.get("ratio"));
        model.addAttribute("tendenciesTestPercForOPercentage", tendenciesTestPercForO.get("percentage"));
        int tendenciesTestPercForR = this.testSessionsService.getTendenciesTestPercForRList(ts.getTestSessionId());
        int tendenciesTestPercForWithR = this.testSessionsService.getTendenciesTestPercForWithRList(ts.getTestSessionId());
        float tendenciesTestPerc = 0;
        float ratio = 0, percentage = 0;
        if (tendenciesTestPercForWithR != 0) {
            tendenciesTestPerc = tendenciesTestPercForR + tendenciesTestPercForWithR;
            ratio = (float) round((tendenciesTestPerc / 60), 4);
            percentage = (float) round((ratio * 100), 2);
        }
        model.addAttribute("tendenciesTestPercForRRation", ratio);
        model.addAttribute("tendenciesTestPercForRPercentage", percentage);

//        String mindLinkertSum = this.testSessionsService.getBehaviouMindLikertSum(ts.getTestSessionId());
//        String masterySum = this.testSessionsService.getBehaviourMasterySum(ts.getTestSessionId());
//        String abstinenceSum = this.testSessionsService.getBhaviourAbstinenceSum(ts.getTestSessionId());
        List<Map<String, Object>> question1 = this.testSessionsService.getBehaviourQuestion1(ts.getTestSessionId());
        Map<String, Object> question2 = this.testSessionsService.getBehaviourQuestion2(ts.getTestSessionId());
        List<Map<String, Object>> question3 = this.testSessionsService.getBehaviourQuestion3(ts.getTestSessionId());
        Map<String, Object> question4 = this.testSessionsService.getBehaviourQuestion4(ts.getTestSessionId());

//        model.addAttribute("mindLinkertSum", mindLinkertSum);
//        model.addAttribute("masterySum", masterySum);
//        model.addAttribute("abstinenceSum", abstinenceSum);
        if (question1 != null) {
            model.addAttribute("question1Name", "What are you doing to work on your body?");
            model.addAttribute("question1Value1", question1);
        }

        if (question2 != null) {
            model.addAttribute("question2Name", question2.get("QUESTION_NAME"));
            model.addAttribute("question2Value1", question2.get("QUESTION_OPTION_VALUE"));
            model.addAttribute("question2Value2", question2.get("QUESTION_OPTION_ADDITIONAL_VALUE"));
        }
        if (question3 != null) {
         model.addAttribute("question3Value", question3);    
        }
        if (question4 != null) {
            model.addAttribute("question4Name", question4.get("QUESTION_NAME"));
            model.addAttribute("question4Value1", question4.get("QUESTION_OPTION_VALUE"));
            model.addAttribute("question4Value2", question4.get("QUESTION_OPTION_ADDITIONAL_VALUE"));
        }

        List<Map<String, Object>> mentalImproveActivitiesQL2 = this.testSessionsService.getMentalImprovementActivities_QL2(ts.getTestSessionId());

        List<Object> mentalImproveListForGraph = new ArrayList<>();
        for (int i = 0; i < mentalImproveActivitiesQL2.size(); i++) {
            mentalImproveListForGraph.add(0);
        }
        int k = 0;
        for (Map<String, Object> map : mentalImproveActivitiesQL2) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                String aa = value + "";
                mentalImproveListForGraph.set(k, aa);
                k++;
            }
        }
        StringBuilder mentalImproveListForGraphData = new StringBuilder();
        for (int i = 0; i < mentalImproveActivitiesQL2.size(); i++) {
            mentalImproveListForGraphData.append("'").append(mentalImproveListForGraph.get(i)).append("',");
        }
        if (mentalImproveListForGraphData.length() > 0) {
            mentalImproveListForGraphData.setLength(mentalImproveListForGraphData.length() - 1);
        }
        model.addAttribute("mentalImproveListForGraph", mentalImproveListForGraphData);

        List<Map<String, Object>> selfActualizationData = this.testSessionsService.getSelfActualizationData(ts.getTestSessionId());
        double selfTraitSum = 0;
        for (Map<String, Object> map : selfActualizationData) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().equals("TraitSum")) {
                    selfTraitSum = round(((double) selfTraitSum + (double) entry.getValue()) / 80 * 100, 2);
                }
            }
        }
        model.addAttribute("selfTraitSum", selfTraitSum);
        model.addAttribute("selfActualizationData", selfActualizationData);
        Map<String, Object> satisfactionWithLifeScale = this.testSessionsService.getSatisfactionWithLifeScale(ts.getTestSessionId());
        if ((satisfactionWithLifeScale.get("aspectPercentile") != null) && (satisfactionWithLifeScale.get("compositeSWLSscore") != null)) {
            model.addAttribute("aspectPercentile", satisfactionWithLifeScale.get("aspectPercentile"));
            double satisfactionWithLifeScalePercetile = round(NormalDistribution.stats_cdf_normal((double) satisfactionWithLifeScale.get("compositeSWLSscore"), 23.50, 6.40, 1) * 100, 2);
            model.addAttribute("satisfactionWithLifeScalePercetile", satisfactionWithLifeScalePercetile);
        }
        Map<String, Object> lonelinessData = this.testSessionsService.getLonelinessData(ts.getTestSessionId());
        if (lonelinessData.get("lonelinessPercentile") != null && lonelinessData.get("compositeLonelinessScore") != null) {
            model.addAttribute("lonelinessPercentile", lonelinessData.get("lonelinessPercentile"));
            model.addAttribute("compositeLonelinessScore", lonelinessData.get("compositeLonelinessScore"));
            double lonelinessOverallPercentile = round(NormalDistribution.stats_cdf_normal((double) lonelinessData.get("lonelinessPercentile"), 35.40, 19.20, 1) * 100, 2);
            model.addAttribute("lonelinessOverallPercentile", lonelinessOverallPercentile);
        }

        //DEATHPROOF FACTOR
        //Agreeableness
        double agreeblenessScoreLess50 = avatarLevelNormalDistribution1 * (-1);
        double agreeblenessAbsoluteValOfScore = Math.abs(agreeblenessScoreLess50);
        double aggreeblenessAdjustment = 0, aggreeblenessThresholdWeighttings = 0;
        if (avatarLevelNormalDistribution1 > 50) {
            aggreeblenessAdjustment = -49;
        }
        if (avatarLevelNormalDistribution1 < 51) {
            aggreeblenessAdjustment = -50;
        }
        if (avatarLevelNormalDistribution1 > 3.5) {
            aggreeblenessThresholdWeighttings = 1;
        }
        if (avatarLevelNormalDistribution1 < 3.49) {
            aggreeblenessThresholdWeighttings = 1.5;
        }
        double aggreeblenessMultiplierAdjustedPerVal = agreeblenessScoreLess50 * 1 * aggreeblenessThresholdWeighttings;
        //Compassion
        double compassionMultiplier = 0, compassionAdjustment = 0, compassionThresholdWeighttings = 0, compassionTotal = 0, compassionScoreLess50;

        if (avatarLevelNormalDistribution2 > 50) {
            compassionThresholdWeighttings = 1;
            compassionAdjustment = -49;

        }
        if (avatarLevelNormalDistribution2 < 51) {
            compassionThresholdWeighttings = -1.25;
            compassionAdjustment = -50;
        }
        if (avatarLevelNormalDistribution2 > 50) {
            compassionMultiplier = 2;
        }
        if (avatarLevelNormalDistribution2 < 50.01) {
            compassionMultiplier = 1;
        }
        if (avatarLevelNormalDistribution2 > 90) {
            compassionTotal = 0.58;
        }
        if (avatarLevelNormalDistribution2 < 90.01) {
            compassionTotal = 1;
        }
        compassionScoreLess50 = avatarLevelNormalDistribution2 + compassionAdjustment;
        double compassionAbsoluteValOfScore = Math.abs(compassionScoreLess50);
        double compassionMultiplierAdjustedPerVal = compassionAbsoluteValOfScore * compassionMultiplier * compassionTotal * compassionThresholdWeighttings;
        //Politeness
        double politenessOriginalScore = avatarLevelNormalDistribution3, politenessAdjustment = 0;
        if (avatarLevelNormalDistribution3 > 50) {
            politenessAdjustment = -49;

        }
        if (avatarLevelNormalDistribution3 < 51) {
            politenessAdjustment = -50;
        }
        double politenessScoreLess50 = (politenessOriginalScore * -1);
        double politenessMultiplierAdjustedPerVal = politenessScoreLess50 * 1;

        //Conscientiousness
        double conscientiousnessOriginalScore = avatarLevelNormalDistribution4,
                conscientiousnessAdjustment = 0, conscientiousnessAdjustmentThresholdWeightings = 0, conscientiousnessTotal = 0;
        double conscientiousnessScoreLess50 = 0, conscientiousnessAbsoluteValOfScore = 0, conscientiousnessMultiplierAdjustedPerVal = 0;
        if (conscientiousnessOriginalScore > 50) {
            conscientiousnessAdjustment = -49;
            conscientiousnessAdjustmentThresholdWeightings = 1;
        }
        if (conscientiousnessOriginalScore < 51) {
            conscientiousnessAdjustment = -50;
            conscientiousnessAdjustmentThresholdWeightings = -2;
        }
        if (conscientiousnessOriginalScore > 90) {
            conscientiousnessTotal = 1.2;
        }
        if (conscientiousnessOriginalScore < 90.01) {
            conscientiousnessTotal = 1;
        }

        conscientiousnessScoreLess50 = conscientiousnessOriginalScore + conscientiousnessAdjustment;
        conscientiousnessAbsoluteValOfScore = Math.abs(conscientiousnessScoreLess50);
        conscientiousnessMultiplierAdjustedPerVal = conscientiousnessAbsoluteValOfScore * 1.25 * conscientiousnessAdjustmentThresholdWeightings * conscientiousnessTotal;
        //Industriousness
        double industriousnessOriginalScore = avatarLevelNormalDistribution5;
        double industriousnessAdjustment = 0, industriousnessThresholdWeightings = 0, industriousnessTotal = 0;
        if (industriousnessOriginalScore > 50) {
            industriousnessAdjustment = -49;
            industriousnessThresholdWeightings = 1;
        }
        if (industriousnessOriginalScore < 51) {
            industriousnessAdjustment = -50;
            industriousnessThresholdWeightings = -2;
        }

        if (industriousnessOriginalScore > 90) {
            industriousnessTotal = 1.5;
        }
        if (industriousnessOriginalScore < 90.01) {
            industriousnessTotal = 1;
        }
        double IndustriousnessScoreLess50 = industriousnessOriginalScore + industriousnessAdjustment;
        double IndustriousnessAbsoluteValOfScore = Math.abs(IndustriousnessScoreLess50);
        double IndustriousnessMultiplierAdjustedPerVal = IndustriousnessAbsoluteValOfScore * 1.25 * industriousnessThresholdWeightings * industriousnessTotal;
        //Orderliness
        double OrderlinessOriginalScore = avatarLevelNormalDistribution6, OrderlinessAdjustment = 0,
                OrderlinessTotal = 0, OrderlinessOutOf = 0;

        if (OrderlinessOriginalScore > 50) {
            OrderlinessAdjustment = -49;
        }
        if (OrderlinessOriginalScore < 51) {
            OrderlinessAdjustment = -50;
        }

        if (OrderlinessOriginalScore > 71.01) {
            OrderlinessTotal = 0.3;
        }
        if (OrderlinessOriginalScore < 71.02) {
            OrderlinessTotal = 1;
        }

        if (OrderlinessOriginalScore > 15.99) {
            OrderlinessOutOf = 1;
        }
        if (OrderlinessOriginalScore < 16) {
            OrderlinessOutOf = -1;
        }
        double OrderlinessScoreLess50 = OrderlinessOriginalScore + OrderlinessAdjustment;
        double orderlinessAbsoluteValOfScore = Math.abs(OrderlinessScoreLess50);
        double OrderlinessMultiplierAdjustedPerVal = orderlinessAbsoluteValOfScore * 1 * OrderlinessTotal * OrderlinessOutOf;
        //Extraversion
        double ExtroversionOriginalScore = avatarLevelNormalDistribution7;
        double ExtroversionAdjustment = 0, ExtroversionThresholdWeightings = 0;
        double EtroversionTotal = 0;
        if (ExtroversionOriginalScore > 50) {
            ExtroversionAdjustment = -49;
            ExtroversionThresholdWeightings = 1;
        }
        if (ExtroversionOriginalScore < 51) {
            ExtroversionAdjustment = -50;
            ExtroversionThresholdWeightings = -2;
        }

        if (ExtroversionOriginalScore > 90) {
            EtroversionTotal = 1.5;
        }
        if (ExtroversionOriginalScore < 90.01) {
            EtroversionTotal = 1;
        }
        double ExtroversionScoreLess50 = ExtroversionOriginalScore + ExtroversionAdjustment;
        double ExtroversionAbsoluteValOfScore = Math.abs(ExtroversionScoreLess50);
        double ExtroversionMultiplierAdjustedPerVal = ExtroversionAbsoluteValOfScore * 1 * ExtroversionThresholdWeightings * EtroversionTotal;
        //Enthusiasm
        double EnthusiasmOriginalScore = avatarLevelNormalDistribution8, EnthusiasmAdjustment = 0,
                EnthusiasmThresholdWeightings = 0, EnthusiasmTotal = 0;

        if (EnthusiasmOriginalScore > 50) {
            EnthusiasmAdjustment = -49;
            EnthusiasmThresholdWeightings = 1;
        }
        if (EnthusiasmOriginalScore < 51) {
            EnthusiasmAdjustment = -50;
            EnthusiasmThresholdWeightings = -2;
        }

        if (EnthusiasmOriginalScore > 88.99) {
            EnthusiasmTotal = 1.5;
        }
        if (EnthusiasmOriginalScore < 89) {
            EnthusiasmTotal = 1;
        }
        double EnthusiasmScoreLess50 = EnthusiasmOriginalScore + EnthusiasmAdjustment;
        double EnthusiasmAbsoluteValOfScore = Math.abs(EnthusiasmScoreLess50);
        double EnthusiasmMultiplierAdjustedPerVal = EnthusiasmAbsoluteValOfScore * 1 * EnthusiasmThresholdWeightings * EnthusiasmTotal;
        //Assertiveness
        double AssertivenessOriginalScore = avatarLevelNormalDistribution9, AssertivenessAdjustment = 0,
                AssertivenessThresholdWeightings = 0, AssertivenessTotal = 0, AsserativeScoreLess50 = 0,
                AssertivenessAbsoluteValOfScore = 0, AssertivenessMultiplierAdjustedPerVal;

        if (AssertivenessOriginalScore > 50) {
            AssertivenessAdjustment = -49;
            AssertivenessThresholdWeightings = 1;
        }
        if (AssertivenessOriginalScore < 51) {
            AssertivenessAdjustment = -50;
            AssertivenessThresholdWeightings = -2;
        }

        if (AssertivenessOriginalScore > 90) {
            AssertivenessTotal = 1.5;
        }
        if (AssertivenessOriginalScore < 90.01) {
            AssertivenessTotal = 1;
        }
        AsserativeScoreLess50 = AssertivenessOriginalScore + AssertivenessAdjustment;
        AssertivenessAbsoluteValOfScore = Math.abs(AsserativeScoreLess50);
        AssertivenessMultiplierAdjustedPerVal = AssertivenessAbsoluteValOfScore * 1 * AssertivenessThresholdWeightings * AssertivenessTotal;
        //Neuroticism
        double NeuroticismOriginalScore = avatarLevelNormalDistribution10;
        double NeuroticismScoreLess50 = NeuroticismOriginalScore * -1;
        double NeuroticismMultiplierAdjustedPerVal = NeuroticismScoreLess50 * 1;
        //Withdrawal
        double WithdrawalOriginalScore = avatarLevelNormalDistribution11;;
        double WithdrawalScoreLess50 = WithdrawalOriginalScore * -1;
        double WithdrawalMultiplierAdjustedPerVal = WithdrawalScoreLess50 * 1;
        //Volatility
        double Volatilitymultiplier = 1;
        double VolatilityOriginalScore = avatarLevelNormalDistribution12;;
        double VolatilityScoreLess50 = VolatilityOriginalScore * -1;
        double VolatilityMultiplierAdjustedPerVal = VolatilityScoreLess50 * Volatilitymultiplier;
        //Openness
        double OpennessExperienceMultiplier = 1.5;
        double OpennessExperienceMultiplierOriginalScore = avatarLevelNormalDistribution13, OpennessExperienceAdjustment = 0,
                OpennessExperienceTotal = 0, OpennessExperiencethresholdWeightings = 0;

        if (OpennessExperienceMultiplierOriginalScore > 50) {
            OpennessExperienceAdjustment = -49;
        }
        if (OpennessExperienceMultiplierOriginalScore < 51) {
            OpennessExperienceAdjustment = -50;
        }

        if (OpennessExperienceMultiplierOriginalScore > 50) {
            OpennessExperienceTotal = 1;
        }
        if (OpennessExperienceMultiplierOriginalScore < 50.01) {
            OpennessExperienceTotal = -2;
        }
        if (OpennessExperienceMultiplierOriginalScore > 84.9) {
            OpennessExperiencethresholdWeightings = 4;
        }
        if (OpennessExperienceMultiplierOriginalScore < 84.91) {
            OpennessExperiencethresholdWeightings = 0.67;
        }
        double OpennessExperiencethresholdScoreLess50 = OpennessExperienceMultiplierOriginalScore + OpennessExperienceAdjustment;
        double OpennessExperiencethresholdAbsoluteValOfScore = Math.abs(OpennessExperiencethresholdScoreLess50);
        double OpennessExperiencethresholdMultiplierAdjustedPerVal = OpennessExperiencethresholdAbsoluteValOfScore * OpennessExperienceMultiplier * OpennessExperienceTotal * OpennessExperiencethresholdWeightings;
        //Intellect
        double IntellectMultiplier = 1.5, IntellectThresholdWeightings = 0, IntellectScoreLess50, IntellectAbsoluteValOfScore;
        double IntellectOriginalScore = avatarLevelNormalDistribution14, IntellectAdjustment = 0, IntellectTotal = 0;

        if (IntellectOriginalScore > 50) {
            IntellectAdjustment = -49;
        }
        if (IntellectOriginalScore < 51) {
            IntellectAdjustment = -50;
        }

        if (IntellectOriginalScore > 50) {
            IntellectTotal = 1;
        }
        if (IntellectOriginalScore < 50.01) {
            IntellectTotal = -2;
        }

        if (IntellectOriginalScore > 89) {
            IntellectThresholdWeightings = 2.5;
        }
        if (IntellectOriginalScore < 90) {
            IntellectThresholdWeightings = .67;
        }
        IntellectScoreLess50 = IntellectOriginalScore + IntellectAdjustment;
        IntellectAbsoluteValOfScore = Math.abs(IntellectScoreLess50);

        double IntellectMultiplierAdjustedPerVal = IntellectAbsoluteValOfScore * IntellectMultiplier * IntellectThresholdWeightings * IntellectTotal;
        //Openness
        double OpennessMultiplier = 1, OpennessAdjustment = 0, OpennessTotal = 0, OpennessThresholdWeightings = 0;
        double OpennessOriginalScore = avatarLevelNormalDistribution15;
        if (OpennessOriginalScore > 50) {
            OpennessAdjustment = -49;
        }
        if (OpennessOriginalScore < 51) {
            OpennessAdjustment = -50;
        }
        if (OpennessOriginalScore > 50) {
            OpennessTotal = 1;
        }
        if (OpennessOriginalScore < 50.01) {
            OpennessTotal = -1.5;
        }

        if (OpennessOriginalScore > 90) {
            OpennessThresholdWeightings = 1.5;
        }
        if (OpennessOriginalScore < 90.01) {
            OpennessThresholdWeightings = 1;
        }
        double OpennessScoreLess50 = OpennessOriginalScore + OpennessAdjustment;
        double OpennessAbsoluteValOfScore = Math.abs(OpennessScoreLess50);

        double OpennessMultiplierAdjustedPerVal = OpennessAbsoluteValOfScore * OpennessMultiplier * OpennessThresholdWeightings * OpennessTotal;
        double DpfactorTotal
                = aggreeblenessMultiplierAdjustedPerVal + compassionMultiplierAdjustedPerVal + politenessMultiplierAdjustedPerVal
                + conscientiousnessMultiplierAdjustedPerVal + IndustriousnessMultiplierAdjustedPerVal
                + OrderlinessMultiplierAdjustedPerVal + ExtroversionMultiplierAdjustedPerVal + EnthusiasmMultiplierAdjustedPerVal
                + AssertivenessMultiplierAdjustedPerVal + NeuroticismMultiplierAdjustedPerVal + WithdrawalMultiplierAdjustedPerVal
                + VolatilityMultiplierAdjustedPerVal + OpennessExperiencethresholdMultiplierAdjustedPerVal
                + IntellectMultiplierAdjustedPerVal + OpennessMultiplierAdjustedPerVal;
        double DpfactorScore = round((DpfactorTotal / 675) * 100, 2);
        double DpfactorAvatarLevel = round(((DpfactorScore + 145) / 290) * 100, 2);
        //Wisdom
        if (DpfactorTotal != 0.0) {
            model.addAttribute("AvtarLevel", DpfactorAvatarLevel);
            model.addAttribute("DeathProofScore", DpfactorScore);
        }

        //  DARK FACTOR TRAITS: 
        //DRAGON FACTOR
        //'egoism'
        double dragonfactorTotal = 0;

        double dragonfactorEgoismOriginal = darkfactorEgoismPercentile, dragonfactorEgoismAdjustment = 0;
        if (dragonfactorEgoismOriginal > 50) {
            dragonfactorEgoismAdjustment = -49;
        }
        if (dragonfactorEgoismOriginal < 51) {
            dragonfactorEgoismAdjustment = -50;
        }

        double dragonfactorEgoismLess50 = dragonfactorEgoismOriginal + dragonfactorEgoismAdjustment;
        double dragonfactorEgoismAbsolute = Math.abs(dragonfactorEgoismLess50);
        double dragonfactorEgoismAdjustedVal = dragonfactorEgoismAbsolute * 1;
        dragonfactorTotal = dragonfactorTotal + dragonfactorEgoismAdjustedVal;
        // Start machiavellianism
        double dragonfactorMachiavellianismOriginal = darkfactorMachiavellianismPercentile;
        double dragonfactorMachiavellianismAdjustment = 0, dragonfactorMachiavellianismTotal = 0, dragonfactorMachiavellianismThresholdWeighting = 0;
        if (dragonfactorMachiavellianismOriginal > 50) {
            dragonfactorMachiavellianismAdjustment = -49;
            dragonfactorMachiavellianismTotal = 1;
        }
        if (dragonfactorMachiavellianismOriginal < 51) {
            dragonfactorMachiavellianismAdjustment = -50;
            dragonfactorMachiavellianismTotal = -2;
        }
        if (dragonfactorMachiavellianismOriginal > 81) {
            dragonfactorMachiavellianismThresholdWeighting = 4;
        }
        if (dragonfactorMachiavellianismOriginal < 82) {
            dragonfactorMachiavellianismThresholdWeighting = .67;
        }
        double dragonfactorMachiavellianismLess50 = dragonfactorMachiavellianismOriginal + dragonfactorMachiavellianismAdjustment;
        double dragonfactorMachiavellianismAbsolute = Math.abs(dragonfactorMachiavellianismLess50);
        double dragonfactorMachiavellianismAdjustedVal = dragonfactorMachiavellianismAbsolute * 1.5 * dragonfactorMachiavellianismTotal * dragonfactorMachiavellianismThresholdWeighting;
        dragonfactorTotal = dragonfactorTotal + dragonfactorMachiavellianismAdjustedVal;
// Start narcissism
        double dragonfactorNarcissismOriginal = darkfactorNarcissismPercentile,
                dragonfactorNarcissismAdjustment = 0, dragonfactorNarcissismTotal = 0, dragonfactorNarcissismThresholdWeightings = 0;
        if (dragonfactorNarcissismOriginal > 50) {
            dragonfactorNarcissismAdjustment = -49;
            dragonfactorNarcissismTotal = 1;
        }
        if (dragonfactorNarcissismOriginal < 51) {
            dragonfactorNarcissismAdjustment = -50;
            dragonfactorNarcissismTotal = -2;
        }
        if (dragonfactorNarcissismOriginal > 89) {
            dragonfactorNarcissismThresholdWeightings = 2.5;
        }
        if (dragonfactorNarcissismOriginal < 90) {
            dragonfactorNarcissismThresholdWeightings = .67;
        }
        double dragonfactorNarcissismLess50 = dragonfactorNarcissismOriginal + dragonfactorNarcissismAdjustment;
        double dragonfactorNarcissismAbsolute = Math.abs(dragonfactorNarcissismLess50);
        double dragonfactorNarcissismAdjustedVal = dragonfactorNarcissismAbsolute * 1.5 * dragonfactorNarcissismTotal * dragonfactorNarcissismThresholdWeightings;
        dragonfactorTotal = dragonfactorTotal + dragonfactorNarcissismAdjustedVal;

        // Start psychopathy
        double dragonfactorPsychopathyOriginal = darkfactorPsychopathyPercentile;
        double dragonfactorPsychopathyAdjustment = 0, dragonfactorPsychopathyTotal = 0, dragonfactorPsychopathyThresholdWeghtings = 0;
        if (dragonfactorPsychopathyOriginal > 50) {
            dragonfactorPsychopathyAdjustment = -49;
            dragonfactorPsychopathyTotal = 1;
        }
        if (dragonfactorPsychopathyOriginal < 51) {
            dragonfactorPsychopathyAdjustment = -50;
            dragonfactorPsychopathyTotal = -2;
        }
        if (dragonfactorPsychopathyOriginal > 89) {
            dragonfactorPsychopathyThresholdWeghtings = 2.5;
        }
        if (dragonfactorPsychopathyOriginal < 90) {
            dragonfactorPsychopathyThresholdWeghtings = .67;
        }
        double dragonfactorPsychopathyLess50 = dragonfactorPsychopathyOriginal + dragonfactorPsychopathyAdjustment;
        double dragonfactorPsychopathyAbsolute = Math.abs(dragonfactorPsychopathyLess50);
        double dragonfactorPsychopathyAdjustedVal = dragonfactorPsychopathyAbsolute * 1.5 * dragonfactorPsychopathyTotal * dragonfactorPsychopathyThresholdWeghtings;
        dragonfactorTotal = dragonfactorTotal + dragonfactorPsychopathyAdjustedVal;

        // Start moral_disengagement
        double dragonfactorMoralDisengagementOriginal = darkfactorMoraldisengagementPercentile;
        double dragonfactorMoralDisengagementAdjustment = 0, dragonfactorMoralDisengagementThresholdWeightings = 0;
        if (dragonfactorMoralDisengagementOriginal > 50) {
            dragonfactorMoralDisengagementAdjustment = -49;
        }
        if (dragonfactorMoralDisengagementOriginal < 51) {
            dragonfactorMoralDisengagementAdjustment = -50;
        }
        if (dragonfactorMoralDisengagementOriginal > 50) {
            dragonfactorMoralDisengagementThresholdWeightings = 1;
        }
        if (dragonfactorMoralDisengagementOriginal < 51) {
            dragonfactorMoralDisengagementThresholdWeightings = -2;
        }
        double dragonfactorMoralDisengagementLess50 = dragonfactorMoralDisengagementOriginal + dragonfactorMoralDisengagementAdjustment;
        double dragonfactorMoralDisengagementAbsolute = Math.abs(dragonfactorMoralDisengagementLess50);
        double dragonfactorMoralDisengagementAdjustedVal = dragonfactorMoralDisengagementAbsolute * 1.04 * dragonfactorMoralDisengagementThresholdWeightings;
        dragonfactorTotal = dragonfactorTotal + dragonfactorMoralDisengagementAdjustedVal;

        // Start psychological_entitlement
        double dragonfactorPsychologicalEntitlementOriginal = darkfactorPsychologicalPercentile;
        double dragonfactorPsychologicalAdjustment = 0, dragonfactorPsychologicalthresholdWeightings = 0, dragonfactorPsychologicalTotal = 0;

        if (dragonfactorPsychologicalEntitlementOriginal > 50) {
            dragonfactorPsychologicalAdjustment = -49;
            dragonfactorPsychologicalTotal = 1.5;
        }
        if (dragonfactorPsychologicalEntitlementOriginal < 51) {
            dragonfactorPsychologicalAdjustment = -50;
            dragonfactorPsychologicalTotal = -2;
        }
        if (dragonfactorPsychologicalEntitlementOriginal > 93) {
            dragonfactorPsychologicalthresholdWeightings = .05;
        }
        if (dragonfactorPsychologicalEntitlementOriginal < 93) {
            dragonfactorPsychologicalthresholdWeightings = 1;
        }
        double dragonfactorPsychologicalEntitlementLess50 = dragonfactorPsychologicalEntitlementOriginal + dragonfactorPsychologicalAdjustment;
        double dragonfactorPsychologicalEntitlementAbsolute = Math.abs(dragonfactorPsychologicalEntitlementLess50);
        double dragonfactorPsychologicalEntitlementAdjustedVal = dragonfactorPsychologicalEntitlementAbsolute * 1.25 * dragonfactorPsychologicalTotal * dragonfactorPsychologicalthresholdWeightings;
        dragonfactorTotal = dragonfactorTotal + dragonfactorPsychologicalEntitlementAdjustedVal;

        // Start Self-Interest
        double dragonfactorSelfInterestOriginal = darkfactorSelfInterestPercentile;
        double dragonfactorSelfInterestAdjustment = 0;
        if (dragonfactorSelfInterestOriginal > 50) {
            dragonfactorSelfInterestAdjustment = -49;
        }
        if (dragonfactorSelfInterestOriginal < 51) {
            dragonfactorSelfInterestAdjustment = -50;
        }
        double dragonfactorSelfInterestLess50 = dragonfactorSelfInterestOriginal + dragonfactorSelfInterestAdjustment;
        double dragonfactorSelfInterestAbsolute = Math.abs(dragonfactorSelfInterestLess50);
        double dragonfactorSelfInterestAdjustedVal = dragonfactorSelfInterestAbsolute * 1;
        dragonfactorTotal = dragonfactorTotal + dragonfactorSelfInterestAdjustedVal;
// End Self-Interest

// Start spitefulness
        double dragonfactorSpitefulnessOriginal = darkfactorSpitefulnessPercentile;
        double dragonfactorSpitefulnessAdjustment = 0, dragonfactorSpitefulnessThresholdWeightings = 0;
        if (dragonfactorSpitefulnessOriginal > 50) {
            dragonfactorSpitefulnessAdjustment = -49;
        }
        if (dragonfactorSpitefulnessOriginal < 51) {
            dragonfactorSpitefulnessAdjustment = -50;
        }
        if (dragonfactorSpitefulnessOriginal > 50) {
            dragonfactorSpitefulnessThresholdWeightings = -4;
        }
        if (dragonfactorSpitefulnessOriginal < 51) {
            dragonfactorSpitefulnessThresholdWeightings = 2.5;
        }
        double dragonfactorSpitefulnessLess50 = dragonfactorSpitefulnessOriginal + dragonfactorSpitefulnessAdjustment;
        double dragonfactorSpitefulnessAbsolute = Math.abs(dragonfactorSpitefulnessLess50);
        double dragonfactorSpitefulnessAdjustedVal = dragonfactorSpitefulnessAbsolute * 1 * dragonfactorSpitefulnessThresholdWeightings;
        dragonfactorTotal = dragonfactorTotal + dragonfactorSpitefulnessAdjustedVal;
// End spitefulness

//sadism
        double dragonfactorSadismOriginal = darkfactorSadismPercentile;
        double dragonfactorSadismAdjustment = 0, dragonfactorSadismOrignal, dragonfactorSadismThresholdWeightings = 0;
        if (dragonfactorSadismOriginal > 50) {
            dragonfactorSadismAdjustment = -49;
        }
        if (dragonfactorSadismOriginal < 51) {
            dragonfactorSadismAdjustment = -50;
        }
        if (dragonfactorSadismOriginal > 50) {
            dragonfactorSadismThresholdWeightings = 1;
        }
        if (dragonfactorSadismOriginal < 51) {
            dragonfactorSadismThresholdWeightings = -2;
        }
        double dragonfactorSadismLess50 = dragonfactorSadismOriginal + dragonfactorSadismAdjustment;
        double dragonfactorSadismAbsolute = Math.abs(dragonfactorSadismLess50);
        double dragonfactorSadismAdjustedVal = dragonfactorSadismAbsolute * 1.25 * dragonfactorSadismThresholdWeightings;
        dragonfactorTotal = dragonfactorTotal + dragonfactorSadismAdjustedVal;
        dragonfactorTotal = round(dragonfactorTotal, 2);

        double darkTotal = darkfactorEgoismPercentile + darkfactorMachiavellianismPercentile + darkfactorNarcissismPercentile
                + +darkfactorPsychopathyPercentile + darkfactorMoraldisengagementPercentile + darkfactorPsychologicalPercentile + darkfactorSelfInterestPercentile + darkfactorSpitefulnessPercentile + darkfactorSadismPercentile;
        double darkfactorLeverageRatio = round(dragonfactorTotal / darkTotal, 2);
        if (darkTotal != 0.0) {
            model.addAttribute("darkfactorTotal", darkTotal);
            model.addAttribute("dragonfactorTotal", dragonfactorTotal);
            model.addAttribute("darkfactorLeverageRatio", darkfactorLeverageRatio);
        }

        //Description Module for dark Factor
        List<String> dFactorDescList = new LinkedList<>();
        if (darkfactorEgoismPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(darkfactorEgoismPercentile, 1, 11);
            dFactorDescList.add("<b>Egoism</b>: " + desc);
        }
        if (darkfactorMachiavellianismPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(darkfactorMachiavellianismPercentile, 1, 12);
            dFactorDescList.add("<b>Machiavellianism</b>: " + desc);
        }
        if (darkfactorNarcissismPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(darkfactorNarcissismPercentile, 1, 13);
            dFactorDescList.add("<b>Narcissism</b>: " + desc);
        }
        if (darkfactorPsychopathyPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(darkfactorPsychopathyPercentile, 1, 15);
            dFactorDescList.add("<b>Psychopathy</b>: " + desc);
        }
        if (darkfactorMoraldisengagementPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(darkfactorMoraldisengagementPercentile, 1, 17);
            dFactorDescList.add("<b>Moral Disengagement</b>: " + desc);
        }
        if (darkfactorPsychologicalPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(darkfactorPsychologicalPercentile, 1, 18);
            dFactorDescList.add("<b>Psychological Entitlement</b>: " + desc);
        }
        if (darkfactorSelfInterestPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(darkfactorSelfInterestPercentile, 1, 19);
            dFactorDescList.add("<b>Self-Interest</b>: " + desc);
        }
        if (darkfactorSpitefulnessPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(darkfactorSpitefulnessPercentile, 1, 20);
            dFactorDescList.add("<b>Spitefulness</b>: " + desc);
        }
        if (darkfactorSadismPercentile > 0) {
            desc = this.testSessionsService.getDescByPercentile(darkfactorSadismPercentile, 1, 41);
            dFactorDescList.add("<b>Sadism</b>: " + desc);
        }

        model.addAttribute("dFactorDescList", dFactorDescList);

        return "/pdf/comprehensiveDossier";

    }

    private void approachList1(List<Map<String, Object>> questionOptionIdList, ModelMap model, int takerId, List<Map<String, Object>> questionResponseOptionIdList, String dd) {
        int questionIdList1[] = new int[questionOptionIdList.size()];
        int qIdList1 = 0, i;
        for (Map<String, Object> data : questionOptionIdList) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                Object value = entry.getValue();
                String aa = value + "";
                questionIdList1[qIdList1] = Integer.parseInt(aa);
                qIdList1++;
            }
        }
        int questionIdList2[] = new int[questionResponseOptionIdList.size()];
        i = 0;
        for (Map<String, Object> map : questionResponseOptionIdList) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                String aa = value + "";
                questionIdList2[i] = Integer.parseInt(aa);
                i++;
            }
        }
        int sortOrderList[] = new int[questionOptionIdList.size()], j = 0, k = 0;

        for (i = 0; i < questionOptionIdList.size(); i++) {
            for (j = 0; j < questionResponseOptionIdList.size(); j++) {
                if (questionIdList2[i] == questionIdList1[j]) {
                    sortOrderList[k] = j + 1;
                    k++;
                    break;
                }
            }
        }
        StringBuilder answer = new StringBuilder();

        for (int jj = 0; jj < sortOrderList.length; jj++) {
            switch (sortOrderList[jj]) {
                case 0:
                    answer.append("'").append(0).append("',");
                    break;
                case 1:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 2:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 3:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 4:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 5:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 6:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 7:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 8:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 9:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 10:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 11:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 12:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 13:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
                case 14:
                    answer.append("'").append(answerOrderToValue(sortOrderList[jj], questionOptionIdList.size())).append("',");
                    break;
            }
        }
        if (answer.length() > 0) {
            answer.setLength(answer.length() - 1);
        }
        if (dd.equals("problemSolvingApproach")) {
            model.addAttribute("problemSolvingList", answer);
        }
        if (dd.equals("collabrorativeApproach")) {
            model.addAttribute("collaborativeApproachList", answer);
        }
        if (dd.equals("treatResponseApproach")) {
            model.addAttribute("treatResponseApproachList", answer);
        }
        if (dd.equals("forcedValueShapeGraph")) {
            model.addAttribute("forcedValueShapeGraph", answer);
        }
        if (dd.equals("sourcesOfValidationGraph")) {
            model.addAttribute("sourcesOfValidationGraph", answer);
        }
    }

    public double answerOrderToValue(int current, int totalCount) {
        double totalCountPlus = totalCount + 1;
        return totalCountPlus - current;
    }

    private Map<String, NormalDistStats> getExtremismDimensionData(int i, ModelMap model, TestSessionWithQuestionList ts) {
        Map<String, NormalDistStats> stats = new HashMap<>();
        NormalDistStats traitStats = new NormalDistStats();
        traitStats.setId(ts.getCurrentQuestion().get(i).getTrait().getId());
        traitStats.setName(ts.getCurrentQuestion().get(i).getTrait().getName());
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
        aspectStats.setName(ts.getCurrentQuestion().get(i).getAspect().getName());
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
        quesStats.setName(ts.getCurrentQuestion().get(i).getName());
        stats.put("ques", quesStats);
        return stats;
    }

}
