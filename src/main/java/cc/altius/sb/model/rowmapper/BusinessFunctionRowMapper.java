/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.BusinessFunction;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author akil
 */
public class BusinessFunctionRowMapper implements RowMapper<BusinessFunction> {

    @Override
    public BusinessFunction mapRow(ResultSet rs, int rowNum) throws SQLException {
        BusinessFunction bf = new BusinessFunction();
        bf.setBusinessFunctionId(rs.getString("BUSINESS_FUNCTION_ID"));
        bf.setBusinessFunctionDesc(rs.getString("BUSINESS_FUNCTION_DESC"));
        return bf;
    }

}
