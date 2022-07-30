/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.QuestionTestType;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author yogesh
 */
public class TestRowMapper implements  RowMapper<QuestionTestType>{

    @Override
    public QuestionTestType mapRow(ResultSet rs, int i) throws SQLException {
        QuestionTestType test = new QuestionTestType();
        test.setQuestionTestTypeId(rs.getInt("TEST_TYPE_ID"));
        test.setQuestionTestTypeName(rs.getString("TEST_TYPE_DESC"));
        return test;
    }
    
    
}
