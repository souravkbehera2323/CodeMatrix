/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author akil
 */
public class QuestionResponseBasicRowMapper implements RowMapper<QuestionResponseBasic> {

    private final int type;

    public QuestionResponseBasicRowMapper(int type) {
        this.type = type;
    }

    @Override
    public QuestionResponseBasic mapRow(ResultSet rs, int i) throws SQLException {
        QuestionResponseBasic qrb = new QuestionResponseBasic();
        qrb.setId(rs.getInt("ID"));
        qrb.setScore(rs.getDouble("SCORE"));
        return qrb;
    }

}
