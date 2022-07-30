/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.model.Role;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author akil
 */
public class CustomUserDetailsResultSetExtractor implements ResultSetExtractor<CustomUserDetails> {

    @Override
    public CustomUserDetails extractData(ResultSet rs) throws SQLException, DataAccessException {
        CustomUserDetails user = new CustomUserDetails();
        boolean isFirst = true;
        while (rs.next()) {
            if (isFirst) {
                user.setUserId(rs.getInt("USER_ID"));
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
                user.setRoles(new LinkedList<>());
                Blob blob = rs.getBlob("IMAGE_DATA");
                long size = blob.length();
                user.setImageData(new byte[(int)size]);
                try {
                    blob.getBinaryStream().read(user.getImageData());
                } catch (IOException i) {
                }
            }
            Role role = new Role();
            role.setRoleId(rs.getString("ROLE_ID"));
            role.setRoleName(rs.getString("ROLE_NAME"));
            user.getRoles().add(role);
            isFirst = false;
        }
        return user;
    }

}
