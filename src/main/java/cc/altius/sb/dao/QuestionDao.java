/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao;

import cc.altius.sb.exception.DataStoreException;
import cc.altius.sb.model.Question;
import cc.altius.sb.model.QuestionAspect;
import cc.altius.sb.model.QuestionDimension;
import cc.altius.sb.model.QuestionTrait;
import cc.altius.sb.model.QuestionType;
import cc.altius.sb.model.QuestionTestType;
import java.util.List;

/**
 *
 * @author yogesh
 */
public interface QuestionDao {

    public int addQuestion(Question question);
    
    public List<QuestionType> getQuestionTypeList();
    
    public List<Question> getQuestionList();
    
    public List<QuestionTestType> getTestList();
    
    public List<QuestionDimension> getDimensionList();
    
    public List<QuestionTrait> getTraitList();
    
    public List<QuestionAspect> getAspectList();
    
    public Question getQuestionByQuestionId(int questionId);
    
    public int updateQuestion(Question question) throws DataStoreException;
}
