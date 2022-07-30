/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao;

import cc.altius.sb.exception.DataStoreException;
import cc.altius.sb.model.QuestionOption;
import java.util.List;

/**
 *
 * @author altius
 */
public interface QuestionOptionDao {

    public int addQuestionOption(QuestionOption questionOption);

    public List<QuestionOption>  getQuestionOptionsList();

    public QuestionOption getQuestionOptionByquestionOptionId(int questionOptionId);

    public int updateQuestionOption(QuestionOption questionOption);

}
