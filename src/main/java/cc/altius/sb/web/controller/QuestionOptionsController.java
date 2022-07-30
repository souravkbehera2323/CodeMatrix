/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.sb.model.CustomUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cc.altius.sb.model.QuestionOption;
import cc.altius.sb.service.QuestionOptionService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author altius
 */
// showing question option crud
@Controller
public class QuestionOptionsController {

    @Autowired
    private QuestionOptionService questionOptionService;

    @RequestMapping(value = "/questionOptions/addQuestionOptions.htm", method = RequestMethod.GET)
    public String showQuestionOptionPage(ModelMap model, HttpServletRequest request) {
        QuestionOption questionOption = new QuestionOption();
        model.addAttribute("questionOption", questionOption);
        return "questionOptions/addQuestionOptions";
    }

    @RequestMapping(value = "questionOptions/addQuestionOptions.htm", method = RequestMethod.POST)
    public String addQuestionsOptions(@ModelAttribute("questionOption") QuestionOption questionOption, Errors errors, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        int qOID = this.questionOptionService.addQuestionOption(questionOption);
        model.addAttribute("questionOption", questionOption);
        if (qOID == 0) {
            errors.rejectValue("name", "name.failed", "Failed to add Question Options");
            return "questionOptions/addQuestionOptions";
        } else {
            return "redirect:/questionOptions/listQuestionOptions.htm?msg=" + URLEncoder.encode("QuestionOptions addedd successfully", "UTF-8");
        }
    }

    @RequestMapping(value = "/questionOptions/listQuestionOptions.htm", method = RequestMethod.GET)
    public String showQuestionOptionList(ModelMap model) {
        model.addAttribute("questionOptionsList", this.questionOptionService.getQuestionOptionsList());
        return "questionOptions/listQuestionOptions";
    }

    @RequestMapping(value = "/questionOptions/editQuestionOptions.htm", method = RequestMethod.GET)
    public String showEditQuestionOption(@RequestParam(value = "questionOptionId", required = true) int questionOptionId, ModelMap model) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        QuestionOption questionOption = this.questionOptionService.getQuestionOptionByquestionOptionId(questionOptionId);
        model.addAttribute("questionOptionId", questionOptionId);
        model.addAttribute("questionOption", questionOption);
        return "/questionOptions/editQuestionOptions";
    }

    @RequestMapping(value = "/questionOptions/editQuestionOptions.htm", method = RequestMethod.POST)
    public String postEditQuestionOption(@ModelAttribute("questionOption") QuestionOption questionOption, Errors errors, ModelMap model, HttpServletRequest request) {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            questionOption = null;
            return "redirect:/question/listQuestion.htm?msg=msg.actionCancelled";
        } else {
            int questionOptionId = this.questionOptionService.updateQuestionOption(questionOption);
            if (questionOptionId == 0) {
                String error = "error.saveFailed";
                return "redirect:/question/listQuestion?error=" + error;
            } else {
                return "redirect:listQuestionOptions.htm?msg=msg.questionOptionsUpdatedSuccessfully";
            }
        }
    }

}
