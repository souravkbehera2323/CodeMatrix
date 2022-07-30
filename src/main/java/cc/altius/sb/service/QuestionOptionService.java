/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.service;

import cc.altius.sb.model.QuestionOption;
import java.util.List;

/**
 *
 * @author akil
 */
public interface QuestionOptionService {

    public int addQuestionOption(QuestionOption questionOption);

    public List<QuestionOption>  getQuestionOptionsList();

    public QuestionOption getQuestionOptionByquestionOptionId(int questionOptionId);

    public int updateQuestionOption(QuestionOption questionOption);

}
