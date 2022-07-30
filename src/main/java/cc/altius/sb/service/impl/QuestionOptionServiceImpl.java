/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.service.impl;

import cc.altius.sb.dao.QuestionOptionDao;
import cc.altius.sb.model.QuestionOption;
import cc.altius.sb.service.QuestionOptionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class QuestionOptionServiceImpl implements QuestionOptionService {

    @Autowired
    private QuestionOptionDao questionOptionDao;

  
    public int addQuestionOption(QuestionOption questionOption) {
        return this.questionOptionDao.addQuestionOption(questionOption);
    }

    @Override
    public List<QuestionOption>  getQuestionOptionsList() {
     return this.questionOptionDao.getQuestionOptionsList();    }

    @Override
    public QuestionOption getQuestionOptionByquestionOptionId(int questionOptionId) {
        return this.questionOptionDao.getQuestionOptionByquestionOptionId(questionOptionId);
    }

    @Override
    public int updateQuestionOption(QuestionOption questionOption) {
        return this.questionOptionDao.updateQuestionOption(questionOption);
    }

}
