/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import cc.altius.matrixCodeVerse.model.Question;
/**
 *
 * @author akil
 */
public class NextQuestionResultSetExtractor implements ResultSetExtractor<Question>{

    @Override
    public Question extractData(ResultSet rs) throws SQLException, DataAccessException {
        Question q = new Question();
        boolean isFirst = true;
        while(rs.next()) {
            if (isFirst) {
                q.setQuestionId(rs.getInt("QUESTION_ID"));
                q.setSortOrder(rs.getInt("SORT_ORDER"));
                q.setName(rs.getString("QUESTION_NAME"));
                q.setQuestionTypeId(rs.getInt("QUESTION_TYPE_ID"));
                q.setDimension(new Property(rs.getInt("DIMENSION_ID")));
                q.setTrait(new Property(rs.getInt("TRAIT_ID")));
                q.setAspect(new Property(rs.getInt("ASPECT_ID")));
                q.setReverseKey(rs.getBoolean("IS_REVERSE_KEY"));
                q.setDescription(rs.getString("DESCRIPTION"));
                q.setDescToggle(rs.getBoolean("DESCIPTION_TOGGLE"));
            }
            QuestionOption qo = new QuestionOption();
            qo.setQuestionOptionId(rs.getInt("QUESTION_OPTION_ID"));
            qo.setQuestionOptionText(rs.getString("QUESTION_OPTION_NAME"));
//            qo.setQuestionOptionValue(rs.getDouble("QUESTION_VALUE"));
            qo.setQuestionOptionValue(rs.getString("QUESTION_VALUE"));
            q.getQuestionOptionList().add(qo);
            isFirst = false;
        }
        return q;
    }
    
}
