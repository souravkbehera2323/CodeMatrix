/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.Question;
import cc.altius.sb.model.QuestionAspect;
import cc.altius.sb.model.QuestionDimension;
import cc.altius.sb.model.QuestionOption;
import cc.altius.sb.model.QuestionTrait;
import cc.altius.sb.model.QuestionType;
import cc.altius.sb.model.QuestionTestType;
import cc.altius.sb.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author yogesh
 */
public class QuestionResultSetExtractor implements ResultSetExtractor<Question> {

    @Override
    public Question extractData(ResultSet rs) throws SQLException, DataAccessException {
        Question question = new Question();
        boolean isFirst = true;
        while (rs.next()) {
            if (isFirst) {
                question.setQuestionId(rs.getInt("QUESTION_ID"));
                question.setQuestionName(rs.getString("QUESTION_NAME"));
                question.setSortOrder(rs.getInt("SORT_ORDER"));
                question.setDescription(rs.getString("DESCRIPTION"));
                question.setIsReverseKey(rs.getBoolean("IS_REVERSE_KEY"));
                question.setActive(rs.getBoolean("ACTIVE"));
                question.setDescToggle(rs.getBoolean("DESCIPTION_TOGGLE"));
                        
                QuestionTestType test = new QuestionTestType();
                test.setQuestionTestTypeId(rs.getInt("TEST_TYPE_ID"));
                test.setQuestionTestTypeName(rs.getString("TEST_TYPE_DESC"));
                question.setQuestionTestType(test);
                
                QuestionType questionType = new QuestionType();
                questionType.setQuestionTypeId(rs.getInt("QUESTION_TYPE_ID"));
                questionType.setQuestionTypeName(rs.getString("QUESTION_TYPE_NAME"));
                question.setQuestionType(questionType);

                QuestionTrait questionTrait = new QuestionTrait();
                questionTrait.setTraitId(rs.getInt("TRAIT_ID"));
                questionTrait.setTraitName(rs.getString("TRAIT_NAME"));
                question.setQuestionTrait(questionTrait);

                QuestionAspect questionAspect = new QuestionAspect();
                questionAspect.setAspectId(rs.getInt("ASPECT_ID"));
                questionAspect.setAspectName(rs.getString("ASPECT_NAME"));
                question.setQuestionAspect(questionAspect);

                QuestionDimension questionDimension = new QuestionDimension();
                questionDimension.setDimensionId(rs.getInt("DIMENSION_ID"));
                questionDimension.setDimensionName(rs.getString("DIMENSION_NAME"));
                question.setQuestionDimension(questionDimension);

                question.setQuestionOptions(new LinkedList<>());

            }
            QuestionOption qOption = new QuestionOption();
            qOption.setQuestionOptionId(rs.getInt("QUESTION_OPTION_ID"));
            qOption.setQuestionOptionName(rs.getString("QUESTION_OPTION_NAME"));
            question.getQuestionOptions().add(qOption);
            isFirst = false;

        }
        if (isFirst) {
            return null;
        } else {
            return question;
        }
    }
}
