/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.matrixCodeVerse.model.NormalDistStats;
import cc.altius.matrixCodeVerse.model.PopulationStatsGroup;
import cc.altius.matrixCodeVerse.model.TestSessionWithQuestionList;
import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.model.QuestionResponse;
import cc.altius.sb.service.TestResultService;
import cc.altius.sb.service.TestSessionsService;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cc.altius.matrixCodeVerse.model.QuestionResponseBasic;
import cc.altius.matrixCodeVerse.model.TestSession;
import static cc.altius.maven.AllAltiusUtil.round;
import cc.altius.maven.POICell;
import cc.altius.maven.POIRow;
import cc.altius.maven.POIWorkSheet;
import cc.altius.sb.framework.GlobalConstants;
import cc.altius.sb.model.DTO.TakerNoOfQuestionAttempted;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
//import cc.altius.utils.POI.POICell;
//import cc.altius.utils.POI.POIRow;
//import cc.altius.utils.POI.POIWorkSheet;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
//import static cc.altius.utils.StringUtils.round;

/**
 *
 * @author yogesh
 */
@Controller
public class TestResultController {

    @Autowired
    private TestResultService testResultService;

    @Autowired
    private TestSessionsService testSessionService;
    //showing takers test results

    @RequestMapping(value = "/testResults/view.htm")
    public String testSessionResults(ModelMap map, HttpServletRequest request) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        int flag = ServletRequestUtils.getIntParameter(request, "takerId", 0);
        map.addAttribute("takerResultSessionList", this.testResultService.getTakerSessionResultList(curUser, flag));
        int maxSortOrder = this.testSessionService.getLastQuestionIdWithoutCovidQue();
        map.addAttribute("maxSortOrder", maxSortOrder);
        return "/testResults/view";
    }

    //export Row data
    @RequestMapping(value = "/report/exportRaw.htm", method = RequestMethod.GET)
    public String exportRawReport(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        try {
            int takerId = ServletRequestUtils.getIntParameter(request, "takerId");

            List<QuestionResponse> questionResponseList = this.testResultService.getExportRawReport(takerId);
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=ExportRawReport.xls");
            response.setContentType("application/vnd.ms-excel");
            POIWorkSheet mySheet = new POIWorkSheet(out, "Export Raw report");
            mySheet.setPrintTitle(false);
            POIRow headerRow = new POIRow(POIRow.HEADER_ROW);
            headerRow.addCell("Question Id");
            headerRow.addCell("Question Name");
            headerRow.addCell("Sort Order");
            headerRow.addCell("Question Option Value");
            headerRow.addCell("Question Option Additional Value");
            headerRow.addCell("Started Question");
            headerRow.addCell("Ended Question");

            mySheet.addRow(headerRow);

            for (QuestionResponse data : questionResponseList) {
                POIRow dataRow = new POIRow();
                dataRow.addCell(data.getQuestion().getQuestionId(), POICell.TYPE_INTEGER);
                dataRow.addCell(data.getQuestion().getQuestionName(), POICell.TYPE_TEXT);
                dataRow.addCell(data.getQuestion().getSortOrder(), POICell.TYPE_TEXT);
                dataRow.addCell(data.getQuestionOptionValue(), POICell.TYPE_TEXT);
                dataRow.addCell(data.getQuestionOptionAdditionalValue(), POICell.TYPE_TEXT);
                dataRow.addCell(data.getStartedQuestion(), POICell.TYPE_TEXT);
                dataRow.addCell(data.getEndedQuestion(), POICell.TYPE_TEXT);

                mySheet.addRow(dataRow);
            }
            mySheet.writeWorkBook();
            out.close();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    //showing takers wisdom result
    @RequestMapping(value = "/report/wisdomTakerList.htm", method = RequestMethod.GET)
    public String exportWisdomTakerListReport(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        try {
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=ExportTakerWisdomReport.xls");
            response.setContentType("application/vnd.ms-excel");
            POIWorkSheet mySheet = new POIWorkSheet(out, "Export Raw report");
            mySheet.setPrintTitle(false);

            POIRow headerRow = new POIRow(POIRow.HEADER_ROW);
            headerRow.addCell("Taker Id");
            headerRow.addCell("Wisdom");
            mySheet.addRow(headerRow);
            List<Integer> takerIdList = this.testResultService.getWisdomTakerListReport();
            for (int j = 0; j <= takerIdList.size() - 1; j++) {
                TestSessionWithQuestionList ts = this.testSessionService.getTestSessionAllQueData(takerIdList.get(j));
                double avatarLevelNormalDistribution1 = 0, avatarLevelNormalDistribution2 = 0, avatarLevelNormalDistribution3 = 0, avatarLevelNormalDistribution4 = 0, avatarLevelNormalDistribution5 = 0, avatarLevelNormalDistribution6 = 0, avatarLevelNormalDistribution7 = 0,
                        avatarLevelNormalDistribution8 = 0, avatarLevelNormalDistribution9 = 0, avatarLevelNormalDistribution10 = 0, avatarLevelNormalDistribution11 = 0,
                        avatarLevelNormalDistribution12 = 0, avatarLevelNormalDistribution13 = 0, avatarLevelNormalDistribution14 = 0, avatarLevelNormalDistribution15 = 0;
                String[] questionResponseValue = new String[ts.getCurrentQuestion().size()];
                int count = 0;
                for (int i = 0; i < ts.getCurrentQuestion().size(); i++) {
                    if (ts.getCurrentQuestion().get(i).getQuestionTypeId() == 1) {
                        double score = this.testSessionService.getQuestionOptionValueByQuestionOptionId(ts.getCurrentQuestion().get(i).getQuestionId(), ts.getCurrentQuestion().get(i).getQuestionOptionList().get(0).getQuestionOptionId() + "");
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
                        ts.setPopulationStats(this.testSessionService.getPopulationStatsForQuestionFromLiveData(ts.getCurrentQuestion().get(i).getQuestionId(), ts.getTestSessionId(), "pdf"));

                        if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 1)) {
                            if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution1 = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 1)) {
                            if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution2 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 2)) {
                            if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution3 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 2)) {
                            if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution4 = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 3)) {
                            if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution5 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 4)) {
                            if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution6 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 3)) {
                            if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution7 = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 5)) {
                            if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution8 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 6)) {
                            if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution9 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 4)) {
                            if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution10 = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 7)) {
                            if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution11 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 8)) {
                            if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution12 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getTrait().getId() == 5)) {
                            if (getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution13 = getExtremismDimensionData(i, model, ts).get("trait").getNormalDistribution();
                            }
                        }

                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 9)) {
                            if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution14 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                            }
                        }
                        if ((ts.getCurrentQuestion().get(i).getAspect().getId() == 10)) {
                            if (getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution() > 0) {
                                avatarLevelNormalDistribution15 = getExtremismDimensionData(i, model, ts).get("aspect").getNormalDistribution();
                            }
                        }
                    }
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
                double politenessAbsoluteValOfScore = Math.abs(politenessScoreLess50);
                double politenessMultiplierAdjustedPerVal = politenessAbsoluteValOfScore * 1;

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
                    OrderlinessTotal = .3;
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
                double NeuroticismAbsoluteValOfScore = Math.abs(NeuroticismScoreLess50);
                double NeuroticismMultiplierAdjustedPerVal = NeuroticismAbsoluteValOfScore * 1;
                //Withdrawal
                double WithdrawalOriginalScore = avatarLevelNormalDistribution11;;
                double WithdrawalScoreLess50 = WithdrawalOriginalScore * -1;
                double WithdrawalAbsoluteValOfScore = Math.abs(WithdrawalScoreLess50);
                double WithdrawalMultiplierAdjustedPerVal = WithdrawalAbsoluteValOfScore * 1;
                //Volatility
                double Volatilitymultiplier = 1;
                double VolatilityOriginalScore = avatarLevelNormalDistribution12;;
                double VolatilityScoreLess50 = VolatilityOriginalScore * -1;
                double VolatilityAbsoluteValOfScore = Math.abs(VolatilityScoreLess50);
                double NeuroticismVolatilityMultiplierAdjustedPerVal = VolatilityAbsoluteValOfScore * Volatilitymultiplier;
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
                        + NeuroticismVolatilityMultiplierAdjustedPerVal + OpennessExperiencethresholdMultiplierAdjustedPerVal
                        + IntellectMultiplierAdjustedPerVal + OpennessMultiplierAdjustedPerVal;
                double DpfactorScore = round((DpfactorTotal / 675) * 100, 2);
                double DpfactorAvatarLevel = round(((DpfactorScore + 145) / 290) * 100, 2);
                //Wisdom
                if (DpfactorTotal != 0.0) {
                    model.addAttribute("AvtarLevel", DpfactorAvatarLevel);
                    model.addAttribute("DeathProofScore", DpfactorScore);
                }

                POIRow dataRow = new POIRow();
                dataRow.addCell(takerIdList.get(j), POICell.TYPE_INTEGER);
                dataRow.addCell(DpfactorScore, POICell.TYPE_TEXT);
                mySheet.addRow(dataRow);

            }
            mySheet.writeWorkBook();
            out.close();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

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

    //showing coronavirus complete or pending question list
    @RequestMapping(value = "/testResults/coronavirus.htm", method = RequestMethod.GET)
    public String showCoronavirusResult(ModelMap map) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        TestSession ts = this.testSessionService.getTestSessionDataByUserId(curUser.getUserId());
        map.addAttribute("questionCompletedList", this.testResultService.getQuestionsSetCompletedList(curUser, GlobalConstants.DIMENSION_COVID, ts.getTaker().isMedicalProfessional()));
        map.addAttribute("questionPendingList", this.testResultService.getQuestionsSetPendingList(curUser, GlobalConstants.DIMENSION_COVID, ts.getTaker().isMedicalProfessional()));
        return "/testResults/coronavirus";
    }
    //showing  current taker completed Question count TestType wise

    @RequestMapping(value = "/testResults/questionsSetCompleted.htm", method = RequestMethod.GET)
    public String showQuestionsSetCompletedList(ModelMap map) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        TestSession ts = this.testSessionService.getTestSessionDataByUserId(curUser.getUserId());
        map.addAttribute("questionList", this.testResultService.getQuestionsSetCompletedListCount(curUser, -1, ts.getTaker().isMedicalProfessional()));
        return "/testResults/questionsSetCompleted";
    }

    //showing current taker pending Question count Test Type wise
    @RequestMapping(value = "/testResults/questionsSetPending.htm", method = RequestMethod.GET)
    public String showQuestionsSetPendingList(ModelMap map) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        TestSession ts = this.testSessionService.getTestSessionDataByUserId(curUser.getUserId());
        map.addAttribute("questionList", this.testResultService.getQuestionsSetPendingListCount(curUser, -1, ts.getTaker().isMedicalProfessional()));
        return "/testResults/questionsSetPending";
    }

    @RequestMapping(value = "/testResults/codecore.htm", method = RequestMethod.GET)
    public String showCODEcore(ModelMap map) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "/testResults/codecore";
    }

    //showing current taker corona virus question with normal Distribution
    @RequestMapping(value = "/testResults/coronavirusInsights.htm", method = RequestMethod.GET)
    public String showCoronavirusInsights(ModelMap map) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        TestSession testSession = this.testSessionService.getTestSessionDataByUserId(curUser.getUserId());
        map.addAttribute("isMedicalProfessional", testSession.getTaker().isMedicalProfessional());
        List<Map<String, Object>> listDataMap = this.testResultService.getTestResultForCovid(testSession.getTaker().getTakerId(), map);
        map.addAttribute("dataList", listDataMap);
        return "/testResults/coronavirusInsights";
    }

    //All taker AttemptedQuestion Count
    @RequestMapping(value = "/takers/noOfQuestionAttemptedByTaker.htm", method = RequestMethod.GET)
    public String noOfQuestionAttemptedByTaker(ModelMap modelMap) {
        modelMap.addAttribute("takerAttemptedQuestionsList", this.testResultService.getNoOfQuestionAttemptedByTaker(0));
        return "/testResults/takerAttemptedQuestions";
    }

    @RequestMapping(value = "/takers/editNoOfQuestionAttemptedByTaker.htm", method = RequestMethod.GET)
    public String showNoOfQuestionAttemptedByTaker(@RequestParam(value = "takerId", required = true) int takerId,
            ModelMap model, HttpServletResponse response) {
        List<TakerNoOfQuestionAttempted> takerAttemptedQuestionsList = this.testResultService.getNoOfQuestionAttemptedByTaker(takerId);
        model.addAttribute("takerAttemptedQuestionsList", takerAttemptedQuestionsList.get(0));
        model.addAttribute("status", takerAttemptedQuestionsList.get(0).isActive());
        return "testResults/editNoOfQuestionAttemptedByTaker";
    }

    @RequestMapping(value = "/takers/editNoOfQuestionAttemptedByTaker.htm", method = RequestMethod.POST)
    public String editNoOfQuestionAttemptedByTaker(@ModelAttribute("takerAttemptedQuestionsList") TakerNoOfQuestionAttempted takerAttemptedQuestionsList , Errors errors,ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
        int takerId = ServletRequestUtils.getIntParameter(request, "takerId", 0);
        int active = ServletRequestUtils.getIntParameter(request, "active", 0);
        int rows = this.testResultService.updateNoOfQuestionAttemptedByTaker(active, takerId);
        if (rows == 0) {
            String error = "error.saveFailed";
            return "redirect:/takers/noOfQuestionAttemptedByTaker?error=" + error;
        } else {
            return "redirect:/takers/noOfQuestionAttemptedByTaker.htm?msg=" + URLEncoder.encode("Taker Update successfully", "UTF-8");
        }
    }
    //All taker AttemplalttedQuestion Count in Excel Format

    @RequestMapping(value = "/report/reportNoofTakerAttemptedQuestion.htm", method = RequestMethod.GET)
    public void getreportNoofTakerAttemptedQuestionExcelReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        try {

            List<TakerNoOfQuestionAttempted> reportList = this.testResultService.getNoOfQuestionAttemptedByTaker(0);

            OutputStream out = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=NoofTakerAttemptedQuestion.xls");
            response.setContentType("application/vnd.ms-excel");
            POIWorkSheet mySheet = new POIWorkSheet(out, "Taker Attempted Question");
            mySheet.setPrintTitle(false);
            POIRow headerRow = new POIRow(POIRow.HEADER_ROW);
            headerRow.addCell("First Name");
            headerRow.addCell("Last Name");
            headerRow.addCell("No of Question Taker Attempted");
            headerRow.addCell("Test Started");
            headerRow.addCell("Test Ended");
            headerRow.addCell("Referal");
            headerRow.addCell("Email Id");
            headerRow.addCell("Phone No");
            headerRow.addCell("Zip Code");
            mySheet.addRow(headerRow);

            for (TakerNoOfQuestionAttempted data : reportList) {
                POIRow dataRow = new POIRow();
                dataRow.addCell(data.getFirstName(), POICell.TYPE_AUTO);
                dataRow.addCell(data.getLastName(), POICell.TYPE_TEXT);
                dataRow.addCell(data.getNoOfQuestionAttempted(), POICell.TYPE_TEXT);
                dataRow.addCell(data.getTestStarted(), POICell.TYPE_DATE);
                dataRow.addCell(data.getTestEnded(), POICell.TYPE_DATE);
                dataRow.addCell(data.getReferredBy(), POICell.TYPE_TEXT);
                dataRow.addCell(data.getEmail(), POICell.TYPE_TEXT);
                dataRow.addCell(data.getPhone(), POICell.TYPE_TEXT);
                dataRow.addCell(data.getZipCode(), POICell.TYPE_TEXT);
                mySheet.addRow(dataRow);
            }
            mySheet.writeWorkBook();
            out.close();
            out.flush();
        } catch (IOException io) {
        } catch (Exception e) {
        }
    }

}
