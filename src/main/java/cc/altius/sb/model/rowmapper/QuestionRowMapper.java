/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.Question;
import cc.altius.sb.model.QuestionAspect;
import cc.altius.sb.model.QuestionType;
import cc.altius.sb.model.QuestionTestType;
import cc.altius.sb.model.QuestionDimension;
import cc.altius.sb.model.QuestionTrait;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author yogesh
 */
public class QuestionRowMapper implements RowMapper<Question>{

    @Override
    public Question mapRow(ResultSet rs, int i) throws SQLException {
        Question question = new Question();
        question.setQuestionId(rs.getInt("QUESTION_ID"));
        question.setQuestionName(rs.getString("QUESTION_NAME"));
        question.setSortOrder(rs.getInt("SORT_ORDER"));
        
        QuestionDimension questionDimension = new QuestionDimension();
        questionDimension.setDimensionName(rs.getString("DIMENSION_NAME"));
        question.setQuestionDimension(questionDimension);
        
        QuestionTrait questionTrait= new QuestionTrait();
        questionTrait.setTraitName(rs.getString("TRAIT_NAME"));
        question.setQuestionTrait(questionTrait);
        
        QuestionAspect questionAspect = new QuestionAspect();
        questionAspect.setAspectName(rs.getString("ASPECT_NAME"));
        question.setQuestionAspect(questionAspect);
        
        question.setIsReverseKey(rs.getBoolean("IS_REVERSE_KEY"));
        question.setActive(rs.getBoolean("ACTIVE"));
        
        QuestionType questionType = new QuestionType();
        questionType.setQuestionTypeId(rs.getInt("QUESTION_TYPE_ID"));
        questionType.setQuestionTypeName(rs.getString("QUESTION_TYPE_NAME"));
        question.setQuestionType(questionType);
        
        QuestionTestType test = new QuestionTestType();
        test.setQuestionTestTypeId(rs.getInt("TEST_TYPE_ID"));
        test.setQuestionTestTypeName(rs.getString("TEST_TYPE_DESC"));
        question.setQuestionTestType(test);
        
        return question;
    }
    
}
