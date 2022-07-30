/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.QuestionType;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author yogesh
 */
public class QuestionTypeRowMapper implements RowMapper<QuestionType>{

    @Override
    public QuestionType mapRow(ResultSet rs, int i) throws SQLException {
        QuestionType qt = new QuestionType();
        qt.setQuestionTypeId(rs.getInt("QUESTION_TYPE_ID"));
        qt.setQuestionTypeName(rs.getString("QUESTION_TYPE_NAME"));
        return qt;
                
    }
    
    
    
}
