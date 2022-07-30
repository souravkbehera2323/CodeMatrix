/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author akil
 */
public class RoleResultSetExtractor implements ResultSetExtractor<List<Role>> {

    @Override
    public List<Role> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Role> roleList = new LinkedList<>();
        String oldRoleId = "";
        String newRoleId;
        Role role = new Role();
        while (rs.next()) {
            newRoleId = rs.getString("ROLE_ID");
            if(!oldRoleId.equals(newRoleId)) {
                if (!oldRoleId.equals("")) {
                    roleList.add(role);
                }
                role = new Role();
                role.setRoleId(newRoleId);
                role.setRoleName(rs.getString("ROLE_NAME"));
                role.setBusinessFunctions(new LinkedList<>());
            }
            role.getBusinessFunctions().add(new BusinessFunctionRowMapper().mapRow(rs, 1));
            oldRoleId = newRoleId;
        }
        roleList.add(role);
        return roleList;
    }
}
