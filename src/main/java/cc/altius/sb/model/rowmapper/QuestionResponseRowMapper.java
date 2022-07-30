/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.Question;
import cc.altius.sb.model.QuestionResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author yogesh
 */
public class QuestionResponseRowMapper implements RowMapper<QuestionResponse>{

    @Override
    public QuestionResponse mapRow(ResultSet rs, int i) throws SQLException {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setQuestionOptionValue(rs.getString("question_option_value"));
        questionResponse.setQuestionOptionAdditionalValue(rs.getString("question_option_additional_value"));
        questionResponse.setEndedQuestion(rs.getString("ended_question"));
        questionResponse.setStartedQuestion(rs.getString("started_question"));
        
        Question question = new Question();
        question.setQuestionId(rs.getInt("QUESTION_ID"));
        question.setQuestionName(rs.getString("QUESTION_NAME"));
        question.setSortOrder(rs.getInt("SORT_ORDER"));
        questionResponse.setQuestion(question);
        return questionResponse;
    }

    
    
}
