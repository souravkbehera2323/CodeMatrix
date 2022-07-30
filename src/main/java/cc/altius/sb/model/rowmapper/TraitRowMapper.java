/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.QuestionTrait;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class TraitRowMapper implements RowMapper<QuestionTrait>{

    @Override
    public QuestionTrait mapRow(ResultSet rs, int i) throws SQLException {
        QuestionTrait questionTrait =new QuestionTrait();
        questionTrait.setTraitId(rs.getInt("TRAIT_ID"));
        questionTrait.setTraitName(rs.getString("TRAIT_NAME"));
        return questionTrait;
    }
    
    
}
