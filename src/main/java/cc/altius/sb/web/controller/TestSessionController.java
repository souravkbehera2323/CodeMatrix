/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.matrixCodeVerse.model.QuestionResponseBasic;
import cc.altius.matrixCodeVerse.model.TestSession;
import cc.altius.maven.AllAltiusUtil;
import cc.altius.sb.model.Taker;
import cc.altius.sb.service.RegisterService;
import cc.altius.sb.service.TestResultService;
import cc.altius.sb.service.TestSessionsService;
import cc.altius.sb.service.UserService;
import static cc.altius.maven.AllAltiusUtil.fetchData;
//import cc.altius.utils.DateUtils;
//import cc.altius.utils.SessionUtils;
//import cc.altius.utils.SessionUtils;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author akil
 */
@Controller
public class TestSessionController {

    @Autowired
    private TestSessionsService testSessionService;
    @Autowired
    private TestResultService testResultService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserService userService;

    //taker see questions for test
    @RequestMapping(value = "/testSession/take.htm", method = RequestMethod.GET)
    public String getTestSession(@RequestParam(value = "developer", required = false, defaultValue = "0") Integer developerValue, ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("developerValue", developerValue);
        String createdDate = AllAltiusUtil.fetchData("createdDate", request, session, AllAltiusUtil.getCurrentDateString(AllAltiusUtil.EST, AllAltiusUtil.YMDHMS));

        String lastName = AllAltiusUtil.fetchData("lastName", request, session, "");
        String emailId = AllAltiusUtil.fetchData("emailId", request, session, "");
        String password = AllAltiusUtil.fetchData("password", request, session, "");
        if ((emailId == null || emailId.equals("")) && (lastName == null || lastName.equals(""))) {
            long generateNo = generateRandom(0);
            lastName = "" + generateNo;
            session.setAttribute("lastName", lastName);
            emailId = generateNo + "@gmail.com";
            session.setAttribute("emailId", emailId);
            Taker t = new Taker();
            t.setLastName("" + generateNo);
            t.setFirstName("" + generateNo);
            t.setEmail(generateNo + "@gmail.com");
            t.setGender(1);
            t.setPassword("pass");
            int takerId = this.registerService.addTaker(t, "1");
            TestSession testSession = new TestSession();
            int i = this.registerService.addTestSession(testSession, takerId);
        }

        TestSession ts = this.testSessionService.getTestSessionData(lastName, emailId);
        ts.setCurrentQuestion(this.testSessionService.getNextQuestion(ts.getTestSessionId()));
        // ts.setPopulationStats(this.testSessionService.getPopulationStatsForQuestionFromLiveData(ts.getCurrentQuestion().getQuestionId(), ts.getTestSessionId(), "testSession"));
        if (ts.getCurrentQuestion().getSortOrder() == 76) { // The user has reached the 13th Question check if he has registered if not we need to force him to register
            if (this.userService.isTakerRegistered(ts.getTaker().getTakerId()) == false) {
                return "redirect:/takers/register.htm?lastNameId=" + ts.getTaker().getLastName();
            }
        }

        int noOfQuestionsAnswered = this.testSessionService.noOfQuestionAnswered(ts.getTestSessionId());
        if (ts.getCurrentQuestion().getSortOrder() == 76) { // The user has reached the 13th Question check if he has registered if not we need to force him to register
            if (this.userService.isTakerRegistered(ts.getTaker().getTakerId())== false) {
                return "redirect:/takers/register.htm?lastNameId=" + ts.getTaker().getLastName();
            }
        }
        ts.setNoOfQuestionsAnswered(noOfQuestionsAnswered);
        model.addAttribute("noOfQuestionAnswered", noOfQuestionsAnswered);
        int maxSortOrder = this.testSessionService.getLastQuestionId();
        if (ts.getCurrentQuestion().getSortOrder() == 0) {
            return "redirect:/testSession/complete.htm";
        } else {
            if (ts.getCurrentQuestion().getDimension().getId() == 6 && ts.getCurrentQuestion().getTrait().getId() == 57) {
                maxSortOrder = 23;
            } else if (ts.getCurrentQuestion().getDimension().getId() == 6 && ts.getCurrentQuestion().getTrait().getId() == 58) {
                maxSortOrder = 43;
            } else {
                maxSortOrder = maxSortOrder - 65;
            }
            model.addAttribute("percentage", ((noOfQuestionsAnswered) * 100) / maxSortOrder);
            String takerLabel = this.testSessionService.getLabelByNumberOfQuestions(ts.getNoOfQuestionsAnswered());
            model.addAttribute("takerLabel", takerLabel);
            ts.setQuestionStartedTime(System.currentTimeMillis() / 1000);

            putData("testSession", session, ts);

            if ((lastName != null) && (emailId != null)) {
                model.addAttribute("testSession", ts);
                if ((ts.getNoOfQuestionsAnswered() == 0 && ts.getCurrentQuestion().getDimension().getId() < 6)
                        || (ts.getTaker().isMedicalProfessional() && ts.getNoOfQuestionsAnswered() == 0 && ts.getCurrentQuestion().getDimension().getId() < 6)) {
                    List<Map<String, Object>> result = this.testResultService.getTestResultForCovid(ts.getTaker().getTakerId(), model);
                    model.addAttribute("insightResult", result);
                    model.addAttribute("resultSize", result.size());
                }
                return "/testSession/take";
            } else {
                // Not found so go back to resume and re-enter the name and email

                return "redirect:/takers/resume.htm";
            }
        }
    }

    @RequestMapping(value = "/testSession/take.htm", method = RequestMethod.POST)
    public String postTestSession(@RequestParam(value = "developer", required = false) Integer developerValue, ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if (developerValue == null) {
            model.addAttribute("developerValue", 0);
        } else {
            model.addAttribute("developerValue", developerValue);
        }
        String lastName = AllAltiusUtil.fetchData("lastName", request, session, "");
        String emailId = AllAltiusUtil.fetchData("emailId", request, session, "");
        String password = AllAltiusUtil.fetchData("password", request, session, "");

        TestSession ts = (TestSession) fetchData("testSession", session, null);
        putData("testSession", session, ts);
        String[] selectedOptions = null;
        int questionTypeId = ServletRequestUtils.getIntParameter(request, "currentQuestion.questionTypeId", 0);
        int sortOrder = ServletRequestUtils.getIntParameter(request, "currentQuestion.sortOrder", -1);
        int questionId = ServletRequestUtils.getIntParameter(request, "currentQuestion.questionId", 0);
        int takerId = ServletRequestUtils.getIntParameter(request, "taker.takerId", 0);
        if (sortOrder == 3) {
            String covid = ServletRequestUtils.getStringParameter(request, "covid", "");
            Taker taker = new Taker();

            taker.setFirstName(lastName);
            taker.setLastName(lastName);
            taker.setEmail(emailId);
            taker.setPassword(password);
            if (covid != null && !"".equals(covid) && !"0".equals(covid)) {
                taker.setMedicalProfessional(true);
                taker.setCovid(covid);
                this.testSessionService.updateTestSessionForMedicalProferssion(24, ts.getTestSessionId());
            } else {
                this.testSessionService.updateTestSessionForMedicalProferssion(sortOrder, ts.getTestSessionId());
            }
            this.registerService.addTaker(taker, lastName);
            ts.setCurrentQuestion(this.testSessionService.getNextQuestion(ts.getTestSessionId()));
//            ts.setPopulationStats(this.testSessionService.getPopulationStatsForQuestionFromLiveData(ts.getCurrentQuestion().getQuestionId(), ts.getTestSessionId(), "testSession"));
            putData("testSession", session, ts);
        } else {
            if (ts.getCurrentQuestion().getQuestionId() != questionId) {
                return "redirect:/testSession/take.htm";
            }
            if (questionTypeId == 5) {
                String[] multiSelected = ServletRequestUtils.getStringParameters(request, "questionOptionId");
                selectedOptions = multiSelected;
            } else {
                String data = ServletRequestUtils.getStringParameter(request, "questionOptionId", "");
                selectedOptions = data.split(",");
            }
            String[] questionResponseValue = new String[selectedOptions.length];

            int count = 0;
            for (String s : selectedOptions) {
                switch (questionTypeId) {
                    case 1:// Radio

                        String radioScore = this.testSessionService.getQuestionValueByQuestionOptionIdRadio(selectedOptions[0]);
                        double score = Double.parseDouble(selectedOptions[0]);
                        QuestionResponseBasic qrbQ = new QuestionResponseBasic();
                        qrbQ.setId(questionId);
                        qrbQ.setScore(score);
                        ts.getCurrentStats().addResponseForQuestion(qrbQ);

                        QuestionResponseBasic qrbD = new QuestionResponseBasic();
                        qrbD.setId(ts.getCurrentQuestion().getDimension().getId());
                        qrbD.setScore(score);
                        ts.getCurrentStats().addResponseForDimension(qrbD);

                        QuestionResponseBasic qrbT = new QuestionResponseBasic();
                        qrbT.setId(ts.getCurrentQuestion().getTrait().getId());
                        qrbT.setScore(score);
                        ts.getCurrentStats().addResponseForTrait(qrbT);

                        QuestionResponseBasic qrbA = new QuestionResponseBasic();
                        qrbA.setId(ts.getCurrentQuestion().getAspect().getId());
                        qrbA.setScore(score);
                        ts.getCurrentStats().addResponseForAspect(qrbA);

                        questionResponseValue[count] = radioScore;

//                        ts.setPopulationStats(this.testSessionService.getPopulationStatsForQuestionFromLiveData(ts.getCurrentQuestion().getQuestionId(), ts.getTestSessionId(), "testSession"));
//                       
//                        Map<String, NormalDistStats> stats = new HashMap<>();
//                        NormalDistStats traitStats = new NormalDistStats();
//                        traitStats.setId(ts.getCurrentQuestion().getTrait().getId());
//                        traitStats.setCurrentMean(ts.getCurrentStats().getStats(PopulationStatsGroup.TRAIT_STATS, ts.getCurrentQuestion().getTrait().getId()).getMean());
//                        traitStats.setScore(ts.getCurrentStats().getStats(PopulationStatsGroup.TRAIT_STATS, ts.getCurrentQuestion().getTrait().getId()).getTotalScore());
//                        double Traitmean = 0,
//                         TraitstdDev = 0;
//                        if (ts.getPopulationStats().getStats(PopulationStatsGroup.TRAIT_STATS, ts.getCurrentQuestion().getTrait().getId()) != null) {
//                            Traitmean = ts.getPopulationStats().getStats(PopulationStatsGroup.TRAIT_STATS, ts.getCurrentQuestion().getTrait().getId()).getMean();
//                            TraitstdDev = ts.getPopulationStats().getStats(PopulationStatsGroup.TRAIT_STATS, ts.getCurrentQuestion().getTrait().getId()).getStdDeviation();
//                        }
//                        traitStats.setPopulationMean(Traitmean);
//                        traitStats.setPopulationStdDev(TraitstdDev);
//                        stats.put("trait", traitStats);
//                        NormalDistStats aspectStats = new NormalDistStats();
//                        aspectStats.setId(ts.getCurrentQuestion().getAspect().getId());
//                        aspectStats.setCurrentMean(ts.getCurrentStats().getStats(PopulationStatsGroup.ASPECT_STATS, ts.getCurrentQuestion().getAspect().getId()).getMean());
//                        aspectStats.setScore(ts.getCurrentStats().getStats(PopulationStatsGroup.ASPECT_STATS, ts.getCurrentQuestion().getAspect().getId()).getMean());
//                        double Aspectmean = 0,
//                         AspectDev = 0;
//                        if (ts.getPopulationStats().getStats(PopulationStatsGroup.ASPECT_STATS, ts.getCurrentQuestion().getAspect().getId()) != null) {
//                            Aspectmean = ts.getPopulationStats().getStats(PopulationStatsGroup.ASPECT_STATS, ts.getCurrentQuestion().getAspect().getId()).getMean();
//                            AspectDev = ts.getPopulationStats().getStats(PopulationStatsGroup.ASPECT_STATS, ts.getCurrentQuestion().getAspect().getId()).getStdDeviation();
//                        }
//                        aspectStats.setPopulationMean(Aspectmean);
//                        aspectStats.setPopulationStdDev(AspectDev);
//                        stats.put("aspect", aspectStats);
//                        model.addAttribute("normalDistStats", stats);
                        break;
                    case 4:// Order
                    case 5:// Multi
                        questionResponseValue[count] = this.testSessionService.getQuestionOptionValueByQuestionOptionIdMultSelectQue(ts.getCurrentQuestion().getQuestionId(), s);
                        break;
                    case 2:// Text
                    case 3:// TextArea
                    default:
                        questionResponseValue[count] = s;
                        break;
                }
                count++;
            }
            ts.setCurrentQuestionResponse(questionResponseValue);
            ts.setQuestionEndedTime(System.currentTimeMillis() / 1000);
            this.testSessionService.saveQuestionResponses(ts, ts.getCurrentQuestion(), selectedOptions, ts.getCurrentQuestionResponse());
            int noOfQuestionsAnswered = this.testSessionService.noOfQuestionAnswered(ts.getTestSessionId());
            ts.setNoOfQuestionsAnswered(noOfQuestionsAnswered);
            ts.setCurrentQuestion(this.testSessionService.getNextQuestion(ts.getTestSessionId()));
            putData("testSession", session, ts);
        }
        String takerLabel = this.testSessionService.getLabelByNumberOfQuestions(ts.getNoOfQuestionsAnswered());
        model.addAttribute("takerLabel", takerLabel);

        int maxSortOrder = this.testSessionService.getLastQuestionId();
        if (ts.getCurrentQuestion().getDimension().getId() == 6 && ts.getCurrentQuestion().getTrait().getId() == 57) {
            maxSortOrder = 23;
        } else if (ts.getCurrentQuestion().getDimension().getId() == 6 && ts.getCurrentQuestion().getTrait().getId() == 58) {
            maxSortOrder = 43;
        } else {
            maxSortOrder = maxSortOrder - 65;
        }

        model.addAttribute("percentage", (ts.getNoOfQuestionsAnswered() * 100) / maxSortOrder);
        if (sortOrder == 75) {
            return "redirect:/takers/register.htm?lastNameId=" + lastName;
        }
        if (sortOrder == 65) {
            //return "redirect:/takers/register.htm?lastNameId=" + lastName;
        }
        if (sortOrder == 24) {
            this.testSessionService.updateTestSessionForMedicalProferssion(65, ts.getTestSessionId());
//            return "redirect:/takers/register.htm?lastNameId=" + lastName;
        }

        if (ts.getCurrentQuestion().getDimension().getId() != 6 && (maxSortOrder + 65) == (sortOrder)) {
            return "redirect:/testSession/complete.htm";
        } else {
            model.addAttribute("testSession", ts);
            return "redirect:/testSession/take.htm";

        }
    }

    private static Object fetchData(String variableName, HttpSession session, Object defaultValue) {
        Object tmpValue = (session.getAttribute(variableName) == null ? defaultValue : (Object) session.getAttribute(variableName));
        putData(variableName, session, tmpValue);
        return tmpValue;
    }

    private static void putData(String variableName, HttpSession session, Object value) {
        session.setAttribute(variableName, value);
    }
    //taker attend the all question after show the complete msg

    @RequestMapping(value = "/testSession/complete.htm", method = RequestMethod.GET)
    public String getTestSessionsComplete(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String lastName = AllAltiusUtil.fetchData("lastName", request, session, "");
        String emailId = AllAltiusUtil.fetchData("emailId", request, session, "");
        TestSession ts = this.testSessionService.getTestSessionData(lastName, emailId);
        model.addAttribute("testSession", ts);
        return "/testSession/complete";
    }

    public long generateRandom(int prefix) {
        Random rand = new Random();
        long x = (long) (rand.nextDouble() * 100000000000000L);
        String s = String.valueOf(prefix) + String.format("%05d", x);
        return Long.valueOf(s);
    }
}
