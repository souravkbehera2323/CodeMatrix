/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.service.impl;

import cc.altius.sb.dao.QuestionDao;
import cc.altius.sb.exception.DataStoreException;
import cc.altius.sb.model.Question;
import cc.altius.sb.model.QuestionAspect;
import cc.altius.sb.model.QuestionDimension;
import cc.altius.sb.model.QuestionTrait;
import cc.altius.sb.model.QuestionType;
import cc.altius.sb.model.QuestionTestType;
import cc.altius.sb.service.QuestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yogesh
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public int addQuestion(Question question) {
        return this.questionDao.addQuestion(question);
    }

    @Override
    public List<QuestionType> getQuestionTypeList() {
        return this.questionDao.getQuestionTypeList();
    }

    @Override
    public List<QuestionTestType> getTestList() {
        return this.questionDao.getTestList();
    }

    @Override
    public List<Question> getQuestionList() {
        return this.questionDao.getQuestionList();
    }

    @Override
    public Question getQuestionByQuestionId(int questionId) {
        Question question = this.questionDao.getQuestionByQuestionId(questionId);
        return question;
    }

    @Override
    public int updateQuestion(Question question) throws DataStoreException {
        try {
            return this.questionDao.updateQuestion(question);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public List<QuestionDimension> getDimensionList() {
        return this.questionDao.getDimensionList();
    }

    @Override
    public List<QuestionTrait> getTraitList() {
        return this.questionDao.getTraitList();
    }

    @Override
    public List<QuestionAspect> getAspectList() {
        return this.questionDao.getAspectList();
    }

    
}
