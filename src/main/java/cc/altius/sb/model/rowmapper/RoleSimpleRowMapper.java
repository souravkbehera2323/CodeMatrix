/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author akil
 */
public class RoleSimpleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role r = new Role();
        r.setRoleId(rs.getString("ROLE_ID"));
        r.setRoleName(rs.getString("ROLE_NAME"));
        return r;
    }

}
