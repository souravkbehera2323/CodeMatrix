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
import cc.altius.sb.model.AspectScore;
import cc.altius.sb.model.DimensionScore;
import cc.altius.sb.model.TestSessions;
import cc.altius.sb.model.TraitScore;
import cc.altius.sb.service.TestSessionsService;
//import static cc.altius.utils.StringUtils.round;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import static cc.altius.utils.StringUtils.round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author yogesh
 */
//display private dossier
@Controller
public class PdfController {

    @Autowired
    private TestSessionsService testSessionsService;

    @RequestMapping(value = "/pdf/newPdf.htm", method = RequestMethod.GET)
    public String newPdfGet(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "takerId", required = false) int takerId) throws IOException, InterruptedException, ServletRequestBindingException {
        TestSessionWithQuestionList ts = this.testSessionsService.getTestSessionAllQueData(takerId);
        double avatarLevelNormalDistribution1 = 0, avatarLevelNormalDistribution2 = 0, avatarLevelNormalDistribution3 = 0, avatarLevelNormalDistribution4 = 0, avatarLevelNormalDistribution5 = 0, avatarLevelNormalDistribution6 = 0, avatarLevelNormalDistribution7 = 0,
                avatarLevelNormalDistribution8 = 0, avatarLevelNormalDistribution9 = 0, avatarLevelNormalDistribution10 = 0, avatarLevelNormalDistribution11 = 0,
                avatarLevelNormalDistribution12 = 0, avatarLevelNormalDistribution13 = 0, avatarLevelNormalDistribution14 = 0, avatarLevelNormalDistribution15 = 0,
                darkfactorEgoismPercentile = 0, darkfactorMachiavellianismPercentile = 0, darkfactorNarcissismPercentile = 0, darkfactorPsychopathyPercentile = 0, darkfactorMoraldisengagementPercentile = 0, darkfactorPsychologicalPercentile = 0, darkfactorSelfInterestPercentile = 0, darkfactorSpitefulnessPercentile = 0, darkfactorSadismPercentile = 0;
        String[] questionResponseValue = new String[ts.getCurrentQuestion().size()];
        Map<Integer, Map<String, NormalDistStats>> extendedDossierForAspectList = new HashMap<>();
        Map<Integer, Map<String, NormalDistStats>> extendedDossierForTraitList = new HashMap<>();
        int count = 0;
        List<String> extremismDescList = new LinkedList<>();
        for (int i = 0; i < ts.getCurrentQuestion().size(); i++) {

            if (ts.getCurrentQuestion().get(i).getQuestionTypeId() == 1) {
                double score = this.testSessionsService.getQuestionOptionValueByQuestionOptionId(ts.getCurrentQuestion().get(i).getQuestionId(), ts.getCurrentQuestion().get(i).getQuestionOptionList().get(0).getQuestionOptionId() + "");
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
                ts.setPopulationStats(this.testSessionsService.getPopulationStatsForQuestionFromLiveData(ts.getCurrentQuestion().get(i).getQuestionId(), ts.getTestSessionId(), "pdf"));

                if ((ts.getCurrentQuestion().get(i).getTrait().getName() != null) && (!ts.getCurrentQuestion().get(i).getTrait().getName().equals("")) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 1) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 2)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 3) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 4)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 5) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 46) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 42) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 44)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 43) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 45) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 47) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 48) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 49)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 11) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 12) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 13) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 15) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 17)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 18) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 19) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 20)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 41) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 36) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 14) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 39) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 16)) {
                    extendedDossierForTraitList.put(ts.getCurrentQuestion().get(i).getTrait().getId(), getExtremismDimensionData(i, model, ts));
                }
                if ((ts.getCurrentQuestion().get(i).getAspect().getName() != null) && (!ts.getCurrentQuestion().get(i).getAspect().getName().equals("")) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 1) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 2)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 3) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 4)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 5) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 46) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 42) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 44)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 43) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 45) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 47) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 48) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 49)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 11) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 12) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 13) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 15) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 17)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 18) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 19) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 20)
                        && (ts.getCurrentQuestion().get(i).getTrait().getId() != 41) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 36) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 14) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 39) && (ts.getCurrentQuestion().get(i).getTrait().getId() != 16)) {
                    extendedDossierForAspectList.put(ts.getCurrentQuestion().get(i).getAspect().getId(), getExtremismDimensionData(i, model, ts));
                }
                if ((ts.getCurrentQuestion().get(i).getDimension().getId() == 2)) {
                    model.addAttribute("extremismDimension", ts.getCurrentQuestion().get(i).getDimension().getName());
                    model.addAttribute("extremismDimensionND", ts.getNormalDistributionForPopulationString(ts.getCurrentQuestion().get(i).getDimension().getId(), PopulationStatsGroup.DIMENSION_STATS));
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
                    double AnpsPlayScore = getExtremismDimensionData(i, model, ts).get("trait").getScore();
                    double AnpsPlayAverage = getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean();
                    double AnpsPlayPercentage = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();

                    model.addAttribute("AnpsPlayScore", AnpsPlayScore);
                    model.addAttribute("AnpsPlayAverage", AnpsPlayAverage);
                    model.addAttribute("AnpsPlayPercentage", AnpsPlayPercentage);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 42) {
                    double AnpsSeekingScore = getExtremismDimensionData(i, model, ts).get("trait").getScore();
                    double AnpsSeekingAverage = getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean();
                    double AnpsSeekingPercenatge = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    model.addAttribute("AnpsSeekingScore", AnpsSeekingScore);
                    model.addAttribute("AnpsSeekingAverage", AnpsSeekingAverage);
                    model.addAttribute("AnpsSeekingPercenatge", AnpsSeekingPercenatge);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 44) {
                    double AnpsCareScore = getExtremismDimensionData(i, model, ts).get("trait").getScore();
                    double AnpsCareAverage = getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean();
                    double AnpsCarePercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    model.addAttribute("AnpsCareScore", AnpsCareScore);
                    model.addAttribute("AnpsCareAverage", AnpsCareAverage);
                    model.addAttribute("AnpsCarePercentile", AnpsCarePercentile);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 43) {
                    double AnpsFearScore = getExtremismDimensionData(i, model, ts).get("trait").getScore();
                    double AnpsFearAverage = getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean();
                    double AnpsFearPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    model.addAttribute("AnpsFearScore", AnpsFearScore);
                    model.addAttribute("AnpsFearAverage", AnpsFearAverage);
                    model.addAttribute("AnpsFearPercentile", AnpsFearPercentile);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 45) {
                    double AnpsAngerScore = getExtremismDimensionData(i, model, ts).get("trait").getScore();
                    double AnpsAngerAverage = getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean();
                    double AnpsAngerPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    model.addAttribute("AnpsAngerScore", AnpsAngerScore);
                    model.addAttribute("AnpsAngerAverage", AnpsAngerAverage);
                    model.addAttribute("AnpsAngerPercentile", AnpsAngerPercentile);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 47) {
                    double AnpsSadnessScore = getExtremismDimensionData(i, model, ts).get("trait").getScore();
                    double AnpsSadnessAverage = getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean();
                    double AnpsSadnessPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    model.addAttribute("AnpsSadnessScore", AnpsSadnessScore);
                    model.addAttribute("AnpsSadnessAverage", AnpsSadnessAverage);
                    model.addAttribute("AnpsSadnessPercentile", AnpsSadnessPercentile);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 48) {
                    double AnpsSpiritualityScore = getExtremismDimensionData(i, model, ts).get("trait").getScore();
                    double AnpsSpiritualityAverage = getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean();
                    double AnpsSpiritualityPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    model.addAttribute("AnpsSpiritualityScore", AnpsSpiritualityScore);
                    model.addAttribute("AnpsSpiritualityAverage", AnpsSpiritualityAverage);
                    model.addAttribute("AnpsSpiritualityPercentile", AnpsSpiritualityPercentile);
                }
                if (ts.getCurrentQuestion().get(i).getTrait().getId() == 49) {
                    double AnpsLustScore = getExtremismDimensionData(i, model, ts).get("trait").getScore();
                    double AnpsLustAverage = getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean();
                    double AnpsLustPercentile = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                    model.addAttribute("AnpsLustScore", AnpsLustScore);
                    model.addAttribute("AnpsLustAverage", AnpsLustAverage);
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
                    model.addAttribute("uclaTraitMean", getExtremismDimensionData(i, model, ts).get("trait").getCurrentMean());
                    model.addAttribute("uclaTotal", getExtremismDimensionData(i, model, ts).get("trait").getScore());
                    model.addAttribute("uclaTraitND", getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution());
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
                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 15 && ts.getCurrentQuestion().get(i).getTrait().getId() == 16) {
                    model.addAttribute("slAspectMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("slAspectTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("slAspectND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());
                }
                if (ts.getCurrentQuestion().get(i).getAspect().getId() == 16 && ts.getCurrentQuestion().get(i).getTrait().getId() == 16) {
                    model.addAttribute("scAspectMean", getExtremismDimensionData(i, model, ts).get("aspect").getCurrentMean());
                    model.addAttribute("scAspectTotal", getExtremismDimensionData(i, model, ts).get("aspect").getScore());
                    model.addAttribute("scAspectND", getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution());
                }

            }
            model.addAttribute("testSessions", ts);
        }
        //Description Module
        String desc;
        if (avatarLevelNormalDistribution1 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution1, 1, 1);
            extremismDescList.add("<b>Agreeableness</b> : " + desc);
        }

        if (avatarLevelNormalDistribution2 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution2, 2, 1);
            extremismDescList.add("<b>Compassion</b> : " + desc);
        }
        if (avatarLevelNormalDistribution3 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution3, 2, 2);
            extremismDescList.add("<b>Politeness</b> : " + desc);
        }
        if (avatarLevelNormalDistribution4 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution4, 1, 2);
            extremismDescList.add("<b>Conscientiousness</b> : " + desc);
        }
        if (avatarLevelNormalDistribution5 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution5, 2, 3);
            extremismDescList.add("<b>Industriousness</b> : " + desc);
        }
        if (avatarLevelNormalDistribution6 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution6, 2, 4);
            extremismDescList.add("<b>Orderliness</b> : " + desc);
        }
        if (avatarLevelNormalDistribution7 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution7, 1, 3);
            extremismDescList.add("<b>Extroversion</b> : " + desc);
        }
        if (avatarLevelNormalDistribution8 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution8, 2, 5);
            extremismDescList.add("<b>Enthusiasm</b> : " + desc);
        }
        if (avatarLevelNormalDistribution9 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution9, 2, 6);
            extremismDescList.add("<b>Assertiveness</b> : " + desc);
        }
        if (avatarLevelNormalDistribution10 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution10, 1, 4);
            extremismDescList.add("<b>Neuroticism</b> : " + desc);
        }
        if (avatarLevelNormalDistribution11 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution11, 2, 7);
            extremismDescList.add("<b>Withdrawal</b> : " + desc);
        }
        if (avatarLevelNormalDistribution12 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution12, 2, 8);
            extremismDescList.add("<b>Volatility</b> : " + desc);
        }
        if (avatarLevelNormalDistribution13 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution13, 1, 5);
            extremismDescList.add("<b>Openness to Experience</b> : " + desc);
        }
        if (avatarLevelNormalDistribution14 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution14, 2, 9);
            extremismDescList.add("<b>Intellect</b> : " + desc);
        }
        if (avatarLevelNormalDistribution15 > 0) {
            desc = this.testSessionsService.getDescByPercentile(avatarLevelNormalDistribution15, 2, 10);
            extremismDescList.add("<b>Openness</b> : " + desc);
        }
        model.addAttribute("extremismDescList", extremismDescList);
        model.addAttribute("extendedDossierList", extendedDossierForAspectList);
        model.addAttribute("extendedDossierForTraitList", extendedDossierForTraitList);

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

        String mindLinkertSum = this.testSessionsService.getBehaviouMindLikertSum(ts.getTestSessionId());
        String masterySum = this.testSessionsService.getBehaviourMasterySum(ts.getTestSessionId());
        String abstinenceSum = this.testSessionsService.getBhaviourAbstinenceSum(ts.getTestSessionId());
        List<Map<String, Object>> question1 = this.testSessionsService.getBehaviourQuestion1(ts.getTestSessionId());
        Map<String, Object> question2 = this.testSessionsService.getBehaviourQuestion2(ts.getTestSessionId());
        List<Map<String, Object>> question3 = this.testSessionsService.getBehaviourQuestion3(ts.getTestSessionId());
        Map<String, Object> question4 = this.testSessionsService.getBehaviourQuestion4(ts.getTestSessionId());

        model.addAttribute("mindLinkertSum", mindLinkertSum);
        model.addAttribute("masterySum", masterySum);
        model.addAttribute("abstinenceSum", abstinenceSum);
        if (question1 != null) {
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
        for (int i = 0; i < 11; i++) {
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
        for (int i = 0; i < 11; i++) {
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
        return "/pdf/newPdf";

    }

    @RequestMapping(value = "/pdf/newPdf.htm", method = RequestMethod.POST)
    public String newPdfPost(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "takerId", required = false) int takerId) throws ServletRequestBindingException {
        model.addAttribute("takerId", takerId);
        String savePath = "/home/projects";//PDF file Generate Path
        String fileName = "er.pdf"; //Pdf file name
        File download = new File(savePath, fileName);
        int takerId1 = ServletRequestUtils.getIntParameter(request, "takerId");
        try {
            Process process = printApp(takerId, download.getPath());
            int status = process.waitFor();
            if (status == 0) {
                response.setContentType("application/pdf");
                response.setContentLength((int) download.length());
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment;filename=\"%s\"", download.getName());
                StreamUtils.copy(new FileInputStream(download), response.getOutputStream());
            } else {
                // do something if it fails.
            }

        } catch (IOException ioe) {
            // Do something to handle exception
        } catch (InterruptedException ie) {
            // Do something to handle exception
        }
        return "pdf/newPdf";
    }

    public Process printApp(int id, String pdf) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/pdf/newPdf.htm?takerId=14"));
        File f = new File("/home/projects/bcc.html");
        List<String> arrayList = new LinkedList<>();
        arrayList.add("test1");
        arrayList.add("test2");

        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {

            if (line.contains("<%JAVA%>")) {
                bw.write(" <strong>Test Taker:</strong>" + arrayList.get(0) + "<br>");
            } else {
                bw.write(line);
            }
            i++;
            bw.newLine();
        }
        br.close();
        bw.close();

        String urlPath = "/home/projects/bcc.html";
//        urlPath += "/genApp.htm?id=" + 1;//generate url to execute wkhtmltopdf 
        String wxpath = "wkhtmltopdf";//the path where wkhtmltopdf located  

        String command = wxpath + " " + urlPath + " " + pdf;
        return Runtime.getRuntime().exec(command);
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
        return stats;
    }

    @RequestMapping(value = "/pdf/adminPrivateDossier.htm", method = RequestMethod.GET)
    public String newPdfAdminPrivateDossier(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "takerId", required = false) int takerId) throws IOException, InterruptedException, ServletRequestBindingException {
        List<TestSessions> testSessions = this.testSessionsService.getPrivateDossierPdf(takerId);
        model.addAttribute("testSessions", testSessions);
        if (testSessions.size() > 0) {
            List<TestSessions> extremismDimensionData = this.testSessionsService.getExtremismDimensionData(takerId);
            List<TraitScore> tList = new ArrayList<TraitScore>();
            List<AspectScore> aList = new ArrayList<AspectScore>();
            List<DimensionScore> dList = new ArrayList<DimensionScore>();
            if (aList == null || aList.isEmpty()) {
                int a = testSessionsService.getCountForAspect();
                aList = new LinkedList<>();
                for (int i = 1; i <= a; i++) {
                    aList.add(new AspectScore(i));
                }
            }
            if (tList == null || tList.isEmpty()) {
                int t = testSessionsService.getCountForTrait();
                tList = new LinkedList<>();
                for (int i = 1; i <= t; i++) {
                    tList.add(new TraitScore(i));
                }
            }
            if (dList == null || dList.isEmpty()) {
                int a = testSessionsService.getCountForDimension();
                dList = new LinkedList<>();
                for (int i = 1; i <= a; i++) {
                    dList.add(new DimensionScore(i));
                }
            }
            //Extremeism dimension
            for (TestSessions data : extremismDimensionData) {
                int traitId = this.testSessionsService.getTraitIdByQuestionId(data.getQuestionResponses().getQuestion().getSortOrder());
                int dimensionId = this.testSessionsService.getDimensionIdBySortOrder(data.getQuestionResponses().getQuestion().getSortOrder());
                int aspectId = this.testSessionsService.getAspectIdByQuestionId(data.getQuestionResponses().getQuestion().getSortOrder());
                List<Double> aspectScoresPop = this.testSessionsService.getPopAverageForAspect(aspectId);
                double questioOptionValue = this.testSessionsService.getQuestionOptionValueByQuestionOptionId(data.getQuestionResponses().getQuestion().getQuestionId(), "" + data.getQuestionResponses().getQuestionOption().getQuestionOptionId());
                DimensionScore tmp = new DimensionScore(dimensionId);
                int index = dList.indexOf(tmp);
                dList.get(index).addScore(questioOptionValue, data.getQuestionResponses().getQuestion().getQuestionDimension().getDimensionName());

                if (aspectId != 0) {
                    AspectScore aspectTemp = new AspectScore(aspectId);
                    int aspectIndex = aList.indexOf(aspectTemp);
                    aList.get(aspectIndex).addAspectScore(questioOptionValue, data.getQuestionResponses().getQuestion().getQuestionAspect().getAspectName(), data.getQuestionResponses().getQuestion().getQuestionTrait().getTraitName());
                }
                TraitScore traitTemp = new TraitScore(traitId);
                int traitIndex = tList.indexOf(traitTemp);
                tList.get(traitIndex).addTraitScore(questioOptionValue, data.getQuestionResponses().getQuestion().getQuestionTrait().getTraitName());
                session.setAttribute("tList", tList);
                session.setAttribute("dList", dList);
                session.setAttribute("aList", aList);

                model.addAttribute("tList", tList);
                model.addAttribute("dList", dList);
                model.addAttribute("aList", aList);
                model.addAttribute("testSessions", testSessions);
            }
            List<Object> arrayList = new ArrayList<>();
            for (int k = 0; k < 15; k++) {
                arrayList.add(0);
            }

            int kk = 0;
            for (int k = 0; k < tList.size(); k++) {
                arrayList.set(kk, tList.get(k));
                kk = kk + 3;
                if (kk == 15) {
                    break;
                }
            }

            int jj = 0;
            for (int k = 0; k < arrayList.size(); k++) {
                if (arrayList.get(k).equals(0)) {
                    arrayList.set(k, aList.get(jj));
                    jj++;
                }
            }
            List<Object> list = new ArrayList<>();
            for (int k = 0; k < arrayList.size(); k++) {
                list.add(arrayList.get(k));
            }
            model.addAttribute("list", list);
            return "/pdf/adminPrivateDossier";
        } else {
            return null;
        }
    }
}
