/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.sb.exception.DataStoreException;
import cc.altius.sb.model.Question;
import cc.altius.sb.model.QuestionOption;
import cc.altius.sb.service.QuestionOptionService;
import cc.altius.sb.service.QuestionService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author yogesh
 */
//showing question crud
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionOptionService optionService;

    @RequestMapping(value = "/question/questionAdd.htm", method = RequestMethod.GET)
    public String addQuestion(ModelMap modelMap, HttpServletRequest httpServletRequest) {
        Question question = new Question();
        modelMap.addAttribute("question", question);
        modelMap.addAttribute("questionTypeList", this.questionService.getQuestionTypeList());
        modelMap.addAttribute("dimensionList",this.questionService.getDimensionList());
        modelMap.addAttribute("traitList",this.questionService.getTraitList());
        modelMap.addAttribute("aspectList",this.questionService.getAspectList());
        modelMap.addAttribute("questionOptionList", this.optionService.getQuestionOptionsList());
        modelMap.addAttribute("testList", this.questionService.getTestList());
        return "question/questionAdd";
    }

    @RequestMapping(value = "/question/questionAdd.htm", method = RequestMethod.POST)
    public String addQuestionPost(@ModelAttribute("question") Question question, Errors errors, ModelMap modelMap, HttpServletRequest request) throws UnsupportedEncodingException {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            question = null;
            return "redirect:/question/listQuestion.htm?msg=" + URLEncoder.encode("Action cancelled", "UTF-8");
        } else {
            int questionId = 0;
            boolean isReverseKey = ServletRequestUtils.getBooleanParameter(request, "isReverseKey", false);
            question.setIsReverseKey(isReverseKey);
            int[] questionOptions = ServletRequestUtils.getIntParameters(request, "questionOptions");
            List<QuestionOption> questionOptionList = new LinkedList<>();
            for (int r : questionOptions) {
                QuestionOption rObj = new QuestionOption();
                rObj.setQuestionOptionId(r);
                questionOptionList.add(rObj);
            }
            question.setQuestionOptions(questionOptionList);
            questionId = this.questionService.addQuestion(question);
            if (questionId == 0) {
                return "question/questionAdd";
            } else {
                return "redirect:/question/listQuestion.htm?msg=" + URLEncoder.encode("Question addedd successfully", "UTF-8");
            }
        }
    }

    @RequestMapping(value = "/question/listQuestion.htm", method = RequestMethod.GET)
    public String shoQuestionListPage(ModelMap model) {
        model.addAttribute("questionList", this.questionService.getQuestionList());
        return "question/questionList";
    }

    @RequestMapping(value = "/question/editQuestion.htm", method = RequestMethod.GET)
    public String showEditQuestion(@RequestParam(value = "questionId", required = true) int questionId, ModelMap model, HttpServletResponse response) {
        Question question = this.questionService.getQuestionByQuestionId(questionId);
        model.addAttribute("question", question);
        model.addAttribute("questionTypeList", this.questionService.getQuestionTypeList());
        model.addAttribute("questionTypeList", this.questionService.getQuestionTypeList());
        model.addAttribute("dimensionList",this.questionService.getDimensionList());
        model.addAttribute("traitList",this.questionService.getTraitList());
        model.addAttribute("aspectList",this.questionService.getAspectList());
        model.addAttribute("questionOptionList", this.optionService.getQuestionOptionsList());
        model.addAttribute("testList", this.questionService.getTestList());
        return "question/editQuestion";
    }

    @RequestMapping(value = "/question/submitQuestion.htm", method = RequestMethod.POST)
    public String showEditQuestionPost(@ModelAttribute("question") Question question, Errors errors,ModelMap model, HttpServletRequest request) throws DataStoreException, UnsupportedEncodingException, ServletRequestBindingException {    
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);      
        if (cancel != null) {
            question = null;
            return "redirect:/question/listQuestion.htm?msg=msg.actionCancelled";
        } else {
            int[] questionOptions = ServletRequestUtils.getIntParameters(request, "questionOptions");
            List<QuestionOption> questionOptionList = new LinkedList<>();
            for (int r : questionOptions) {
                QuestionOption rObj = new QuestionOption();
                rObj.setQuestionOptionId(r);
                questionOptionList.add(rObj);
            }
            question.setQuestionOptions(questionOptionList);            
            int rows = this.questionService.updateQuestion(question);
            if (rows == 0) {
                String error = "error.saveFailed";
                return "redirect:/question/listQuestion?error=" + error;
            } else {
                return "redirect:/question/listQuestion.htm?msg=" + URLEncoder.encode("Question addedd successfully", "UTF-8");
            }
        }
    }
}
