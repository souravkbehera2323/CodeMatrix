/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.QuestionOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class QuestionOptionsListRowMapper implements RowMapper<QuestionOption> {

    @Override
    public QuestionOption mapRow(ResultSet rs, int i) throws SQLException {
        QuestionOption questionOption = new QuestionOption();
        questionOption.setQuestionOptionId(rs.getInt("QUESTION_OPTION_ID"));
        questionOption.setQuestionOptionName(rs.getString("QUESTION_OPTION_NAME"));
        questionOption.setQuestionValue(rs.getString("QUESTION_VALUE"));
        questionOption.setLabelValue(rs.getString("LABEL_VALUE"));
        questionOption.setLabelPosition(rs.getString("LABEL_POSITION"));
        questionOption.setSortOrder(rs.getInt("SORT_ORDER"));
        questionOption.setActive(rs.getBoolean("ACTIVE"));

        return questionOption;
    }

}
