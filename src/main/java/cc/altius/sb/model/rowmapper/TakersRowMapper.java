/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.QuestionOption;
import cc.altius.sb.model.Taker;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class TakersRowMapper implements RowMapper<Taker>{

    @Override
    public Taker mapRow(ResultSet rs, int i) throws SQLException {
        Taker taker=new Taker();
        taker.setTakerId(rs.getInt("TAKER_ID"));
        taker.setFirstName(rs.getString("FIRST_NAME"));
        taker.setLastName(rs.getString("LAST_NAME"));
        taker.setEmail(rs.getString("EMAIL"));
        taker.setPhone(rs.getString("MOBILE_NO"));
        taker.setAge(rs.getInt("AGE"));
        taker.setGender(rs.getInt("GENDER_ID"));
        taker.setCreatedDate(rs.getDate("CREATED_DATE"));
        taker.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        return taker;
    }
    
}
