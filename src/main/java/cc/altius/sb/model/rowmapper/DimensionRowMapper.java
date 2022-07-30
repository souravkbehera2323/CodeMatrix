/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.QuestionDimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class DimensionRowMapper implements RowMapper<QuestionDimension>{

    @Override
    public QuestionDimension mapRow(ResultSet rs, int i) throws SQLException {
        QuestionDimension dimension= new QuestionDimension();
        dimension.setDimensionId(rs.getInt("DIMENSION_ID"));
        dimension.setDimensionName(rs.getString("DIMENSION_NAME"));
        return dimension;
    }
    
    
    
}
