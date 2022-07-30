/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.Role;
import cc.altius.sb.model.User;
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
public class UserListResultSetExtractor implements ResultSetExtractor<List<User>> {

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<User> userList = new LinkedList<>();
        int oldUserId = 0, newUserId;
        User user = new User();
        while (rs.next()) {
            newUserId = rs.getInt("USER_ID");
            if (oldUserId != newUserId) {
                if (oldUserId!=0) {
                    userList.add(user);
                }
                user = new User();
                user.setUserId(newUserId);
                user.setUsername(rs.getString("USERNAME"));
                user.setName(rs.getString("NAME"));
                user.setEmailId(rs.getString("EMAIL_ID"));
                user.setPhoneNo(rs.getString("PHONE_NO"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setActive(rs.getBoolean("ACTIVE"));
                user.setExpired(rs.getBoolean("EXPIRED"));
                user.setFailedAttempts(rs.getInt("FAILED_ATTEMPTS"));
                user.setExpiresOn(rs.getDate("EXPIRES_ON"));
                user.setOutsideAccess(rs.getBoolean("OUTSIDE_ACCESS"));
                user.setLastLoginDate(rs.getTimestamp("LAST_LOGIN_DATE"));
                user.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
                user.setLastModifiedDate(rs.getTimestamp("LAST_MODIFIED_DATE"));
                User createdBy = new User();
                createdBy.setUserId(rs.getInt("CREATED_BY"));
                createdBy.setUsername(rs.getString("CREATED_BY_USERNAME"));
                user.setCreatedBy(createdBy);
                User lastModifiedBy = new User();
                lastModifiedBy.setUserId(rs.getInt("LAST_MODIFIED_BY"));
                lastModifiedBy.setUsername(rs.getString("LAST_MODIFIED_BY_USERNAME"));
                user.setLastModifiedBy(lastModifiedBy);
                user.setRoles(new LinkedList<>());
            }
            Role role = new Role();
            role.setRoleId(rs.getString("ROLE_ID"));
            role.setRoleName(rs.getString("ROLE_NAME"));
            user.getRoles().add(role);
            oldUserId = newUserId;
        }
        if (user.getUserId() != 0) {
            userList.add(user);
        }
        return userList;
    }

}
