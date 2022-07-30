/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.QuestionAspect;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class AspectRowMapper implements RowMapper<QuestionAspect>{

    @Override
    public QuestionAspect mapRow(ResultSet rs, int i) throws SQLException {
        QuestionAspect questionAspect = new QuestionAspect();
        questionAspect.setAspectId(rs.getInt("ASPECT_ID"));
        questionAspect.setAspectName(rs.getString("ASPECT_NAME"));
        return questionAspect;
    }
    
}
