/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao.impl;

import cc.altius.maven.AllAltiusUtil;
import cc.altius.sb.dao.UserDao;
import cc.altius.sb.exception.DataStoreException;
import cc.altius.sb.model.BusinessFunction;
import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.model.Password;
import cc.altius.sb.model.Role;
import cc.altius.sb.model.User;
import cc.altius.sb.model.rowmapper.BusinessFunctionRowMapper;
import cc.altius.sb.model.rowmapper.RoleResultSetExtractor;
import cc.altius.sb.model.rowmapper.RoleSimpleRowMapper;
import cc.altius.sb.model.rowmapper.UserListResultSetExtractor;
import cc.altius.sb.model.rowmapper.UserResultSetExtractor;
import cc.altius.sb.utils.LogUtils;
//import cc.altius.utils.DateUtils;
//import cc.altius.utils.PassPhrase;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author akil
 */
@Repository
public class UserDaoImpl implements UserDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> getUserList() {
        return this.jdbcTemplate.query("SELECT us_user.*, us_user_role.ROLE_ID, us_role.ROLE_NAME, createdBy.USERNAME CREATED_BY_USERNAME, lastModifiedBy.USERNAME LAST_MODIFIED_BY_USERNAME FROM us_user LEFT JOIN us_user_role ON us_user.USER_ID=us_user_role.USER_ID LEFT JOIN us_role ON us_user_role.ROLE_ID=us_role.ROLE_ID LEFT JOIN us_user createdBy on us_user.CREATED_BY=createdBy.USER_ID LEFT JOIN us_user lastModifiedBy on us_user.LAST_MODIFIED_BY=lastModifiedBy.USER_ID ORDER BY us_user.`USER_ID`, us_user_role.`ROLE_ID`", new UserListResultSetExtractor());
    }

    /**
     * Method to get the list of Roles that are available
     *
     * @return Returns a list of roles available in the system
     */
    @Override
    public List<Role> getRoleList() {
        return this.jdbcTemplate.query("SELECT r.*, bf.* FROM us_role r LEFT JOIN us_role_business_function rbf on r.`ROLE_ID`=rbf.`ROLE_ID` LEFT JOIN us_business_function bf on rbf.`BUSINESS_FUNCTION_ID`=bf.`BUSINESS_FUNCTION_ID`", new RoleResultSetExtractor());
    }

    /**
     * Method to get the list of Roles that a userId has access to
     *
     * @return Returns a list of roles that the userId has access to
     */
    @Override
    public List<BusinessFunction> getBusinessFunctionList() {
        return this.jdbcTemplate.query("SELECT * FROM us_business_function", new BusinessFunctionRowMapper());
    }

    @Override
    public int addUser(User user) throws DataStoreException {
        SimpleJdbcInsert userInsert = new SimpleJdbcInsert(this.dataSource).withTableName("us_user").usingGeneratedKeyColumns("USER_ID");
        Map<String, Object> params = new HashMap<>();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashPass = encoder.encode(user.getPassword());
        params.put("USERNAME", user.getUsername());
        params.put("NAME", user.getName());
        params.put("EMAIL_ID", user.getEmailId());
        //params.put("PHONE_NO", user.getPhoneNo());
        params.put("PASSWORD", hashPass);
        params.put("ACTIVE", user.isActive());
        params.put("EXPIRED", false);
        params.put("EXPIRES_ON", AllAltiusUtil.getOffsetFromCurrentDateObject(AllAltiusUtil.EST, -1));
        params.put("FAILED_ATTEMPTS", 0);
        //   params.put("OUTSIDE_ACCESS", user.isOutsideAccess());
        int curUserId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        params.put("CREATED_BY", curUserId);
        params.put("LAST_MODIFIED_BY", curUserId);
        Date dt = AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST);
        params.put("CREATED_DATE", dt);
        params.put("LAST_MODIFIED_DATE", dt);

        params.put("T_ZONE_ID  ", 0);
        params.put("IMAGE_DATA", "");

        int userId = userInsert.executeAndReturnKey(params).intValue();
        params.clear();
        String sqlString = "INSERT INTO us_user_role (USER_ID, ROLE_ID) VALUES(:userId, :roleId)";
        params.put("userId", userId);
        for (Role r : user.getRoles()) {
            params.remove("roleId");
            params.put("roleId", r.getRoleId());
            logger.debug(LogUtils.buildStringForLog(sqlString, params));
            this.namedParameterJdbcTemplate.update(sqlString, params);
        }
        return userId;
    }

    /**
     * Method to update/edit the us_user
     *
     * @param user us_user that you want to update
     * @throws cc.altius.sb.exception.DataStoreException
     */
    @Override
    @Transactional
    public void updateUser(User user) throws DataStoreException {
        String sqlString;
        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("name", user.getName());
        params.put("emailId", user.getEmailId());
        //  params.put("phoneNo", user.getPhoneNo());
        params.put("active", user.isActive());
        // params.put("outsideAccess", user.isOutsideAccess());
        params.put("userId", user.getUserId());
//        try {
//            params.put("imageData", user.getMultipartFile().getBytes());
//        } catch (IOException ex) {
//            logger.info("Could not upload image", ex);
//            throw new DataStoreException("Could not upload the image");
//        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String hash = encoder.encode(user.getPassword());
            sqlString = "UPDATE us_user SET USERNAME=:username, NAME=:name, EMAIL_ID=:emailId, PASSWORD=:password, ACTIVE=:active WHERE USER_ID=:userId";
            params.put("password", hash);
            logger.debug(LogUtils.buildStringForLog(sqlString, params));
            this.namedParameterJdbcTemplate.update(sqlString, params);
        } else {
            sqlString = "UPDATE us_user SET USERNAME=:username, NAME=:name, EMAIL_ID=:emailId, ACTIVE=:active WHERE USER_ID=:userId";
            logger.debug(LogUtils.buildStringForLog(sqlString, params));
            this.namedParameterJdbcTemplate.update(sqlString, params);
        }
        sqlString = "DELETE FROM us_user_role WHERE USER_ID=:userId";
        params.clear();
        params.put("userId", user.getUserId());
        logger.debug(LogUtils.buildStringForLog(sqlString, params));
        this.namedParameterJdbcTemplate.update(sqlString, params);

        sqlString = "INSERT INTO us_user_role (USER_ID, ROLE_ID) VALUES(:userId, :roleId)";
        for (Role r : user.getRoles()) {
            params.remove("roleId");
            params.put("roleId", r.getRoleId());
            logger.debug(LogUtils.buildStringForLog(sqlString, params));
            this.namedParameterJdbcTemplate.update(sqlString, params);
        }
        String[] splited = user.getName().split("\\s+");
        String sql = "UPDATE taker SET taker.`FIRST_NAME`='" + splited[0] + "',taker.`EMAIL`='" + user.getEmailId() + "'";
        if (splited.length > 1) {
            sql += ",taker.`LAST_NAME`= '";
            for (int i = 1; i < splited.length; i++) {
                sql += splited[i] + " ";
            }
            sql += "' ";
        }
        sql += " WHERE taker.`USER_ID`='" + user.getUserId() + "';";
        logger.info(sql);
        this.jdbcTemplate.update(sql);
    }

    /**
     * Method to get the user object from username
     *
     * @param username Username used to get the user by their name
     * @return Returns the user object, null if no object could be found
     */
    @Override
    public User getUserByUsername(String username, String emailId) {
        String sqlString = "SELECT us_user.*, us_user_role.ROLE_ID, us_role.ROLE_NAME,createdBy.USERNAME CREATED_BY_USERNAME, lastModifiedBy.USERNAME LAST_MODIFIED_BY_USERNAME FROM us_user LEFT JOIN us_user_role ON us_user.USER_ID=us_user_role.USER_ID LEFT JOIN us_role ON us_user_role.ROLE_ID=us_role.ROLE_ID LEFT JOIN us_user createdBy on us_user.CREATED_BY=createdBy.USER_ID LEFT JOIN us_user lastModifiedBy on us_user.LAST_MODIFIED_BY=lastModifiedBy.USER_ID WHERE us_user.USERNAME=? AND us_user.EMAIL_ID=? ";
        Object params[] = new Object[]{username, emailId};
        try {
            logger.debug(LogUtils.buildStringForLog(sqlString, params));
            return this.jdbcTemplate.query(sqlString, params, new UserResultSetExtractor());
        } catch (EmptyResultDataAccessException erda) {
            logger.warn("No User found with username" + LogUtils.getArgsString(), LogUtils.getIpAddress(), LogUtils.getUsername());
            return null;
        }
    }

    /**
     * Method to get the user object from userId
     *
     * @param userId UserId used to get the user by their Id
     * @return Returns the user object, null if no object could be found
     */
    @Override
    public User getUserByUserId(int userId) {
        String sqlString = "SELECT us_user.*, us_user_role.ROLE_ID, us_role.ROLE_NAME, createdBy.USERNAME CREATED_BY_USERNAME, lastModifiedBy.USERNAME LAST_MODIFIED_BY_USERNAME FROM us_user LEFT JOIN us_user_role ON us_user.USER_ID=us_user_role.USER_ID LEFT JOIN us_role ON us_user_role.ROLE_ID=us_role.ROLE_ID LEFT JOIN us_user createdBy on us_user.CREATED_BY=createdBy.USER_ID LEFT JOIN us_user lastModifiedBy on us_user.LAST_MODIFIED_BY=lastModifiedBy.USER_ID WHERE us_user.USER_ID=?";
        Object params[] = new Object[]{userId};
        try {
            logger.debug(LogUtils.buildStringForLog(sqlString, params));
            return this.jdbcTemplate.query(sqlString, params, new UserResultSetExtractor());
        } catch (EmptyResultDataAccessException erda) {
            logger.warn("No User found with userId" + LogUtils.getArgsString(), LogUtils.getIpAddress(), LogUtils.getUsername());
            return null;

        }
    }

    /**
     * Method to confirm the password
     *
     * @param password
     * @return Returns true when the password has been confirmed Returns false
     * when the password does not matched
     */
    @Override
    public boolean confirmPassword(Password password) {
        String sqlString = "SELECT us_user.PASSWORD FROM us_user WHERE us_user.USER_ID=:userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", password.getUserId());
        logger.debug(LogUtils.buildStringForLog(sqlString, params));
        String hash = this.namedParameterJdbcTemplate.queryForObject(sqlString, params, String.class);
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(password.getOldPassword(), hash);
    }

    /**
     * Method to update the canCraeteRoles table
     *
     * @param roleId RoleId used to get the id of us_role which you want to
     * create
     * @param canCreateRoleIds canCreateRoleIds is used to get who can create
     * the us_role
     */
    @Override
    public void updateCanCreateRoles(final String roleId, final String[] canCreateRoleIds) {
        String sqlString = "DELETE FROM us_can_create_roles where us_can_create_roles.ROLE_ID=?";
        this.jdbcTemplate.update(sqlString, roleId);
        SimpleJdbcInsert si = new SimpleJdbcInsert(jdbcTemplate).withTableName("us_can_create_roles");
        SqlParameterSource[] paramList = new SqlParameterSource[canCreateRoleIds.length];
        int i = 0;
        for (String r : canCreateRoleIds) {
            Map<String, Object> params = new HashMap<>();
            params.put("ROLE_ID", roleId);
            params.put("CAN_CREATE_ROLE", r);
            paramList[i] = new MapSqlParameterSource(params);
            i++;
        }
        si.executeBatch(paramList);
    }

    /**
     * Method to get the list of who can create roles
     *
     * @param roles roleId is used to get the us_role of us_user to access the
     * canCreateRole list
     * @return Returns the roleId object and the list of who can create roles
     */
    @Override
    public List<Role> getCanCreateRoleList(List<Role> roles) {
        String roleList = "";
        for (Role r : roles) {
            roleList += "'" + r.getRoleId() + "',";
        }
        if (roleList.length() > 1) {
            roleList = roleList.substring(0, roleList.length() - 1);
        }
        String sqlString = "SELECT us_role.`ROLE_ID`, us_role.`ROLE_NAME` from us_can_create_roles LEFT JOIN us_role on us_can_create_roles.CAN_CREATE_ROLE=us_role.ROLE_ID where us_can_create_roles.ROLE_ID in (" + roleList + ") group by us_role.ROLE_ID";
        return this.jdbcTemplate.query(sqlString, new RoleSimpleRowMapper());
    }

    /**
     * Method to get who can create roles by the roleId
     *
     * @param roles
     * @param canCreateRoles
     * @return Returns true if us_user has access to create a us_role Returns
     * false if us_user does not have access to create a us_role
     */
    @Override
    public boolean canCreateRoles(List<Role> roles, List<Role> canCreateRoles) {
        boolean result = false;
        boolean isFirst = true;
        String roleList = "";
        for (Role r : roles) {
            roleList += "'" + r.getRoleId() + "',";
        }
        if (roleList.length() > 1) {
            roleList = roleList.substring(0, roleList.length() - 1);
        }
        Map<String, Object> params = new HashMap<>();
        for (Role r : canCreateRoles) {
            String sqlString = "SELECT COUNT(*) FROM us_can_create_roles WHERE us_can_create_roles.ROLE_ID IN (" + roleList + ") AND us_can_create_roles.CAN_CREATE_ROLE=:canCreateRole";
            params.remove("canCreateRole");
            params.put("canCreateRole", r.getRoleId());
            int i = this.namedParameterJdbcTemplate.queryForObject(sqlString, params, Integer.class);
            if (isFirst) {
                result = (i > 0);
            } else {
                result &= (i > 0);
            }
        }
        return result;
    }

    /**
     * Method to update the password for existing us_user
     *
     * @param password Password to get the password
     * @param offset Offset to calculate the offset date
     */
    @Override
    public void updatePassword(Password password, int offset) {
        Date offsetDate = AllAltiusUtil.getOffsetFromCurrentDateObject(AllAltiusUtil.EST, offset);
        String sqlString = "UPDATE us_user SET PASSWORD=:hash, EXPIRES_ON=:expiresOn WHERE us_user.USER_ID=:userId";
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(password.getNewPassword());
        Map<String, Object> params = new HashMap<>();
        params.put("userId", password.getUserId());
        params.put("hash", hash);
        params.put("expiresOn", offsetDate);
        this.namedParameterJdbcTemplate.update(sqlString, params);
    }

    /**
     * Method to get the list of Business functions that a userId has access to
     *
     * @param userId userId that you want to get the Business functions for
     * @return Returns a list of Business functions that the userId has access
     * to
     */
    @Override
    public List<String> getBusinessFunctionsForUserId(int userId) {
        String sqlString = "SELECT BUSINESS_FUNCTION_ID FROM us_user_role LEFT JOIN us_role_business_function ON us_user_role.ROLE_ID=us_role_business_function.ROLE_ID WHERE us_user_role.USER_ID=?";
        return this.jdbcTemplate.queryForList(sqlString, String.class, userId);

    }

    /**
     * Method to get the list of Roles that a userId has access to
     *
     * @param userId userId that you want to get the Roles for
     * @return Returns a list of Roles that the userId has access to
     */
    @Override
    public List<Role> getRolesForUserId(int userId) {
        String sqlString = "SELECT r.`ROLE_ID`, r.`ROLE_NAME` from us_user_role ur LEFT JOIN us_role r on ur.`ROLE_ID`=r.`ROLE_ID` WHERE ur.`USER_ID`=?";
        return this.jdbcTemplate.query(sqlString, new RoleSimpleRowMapper(), userId);
    }

    /**
     * Method to update the login failure count from username
     *
     * @param username username is used to get the us_user of whom you want to
     * update the failedCount
     * @return Returns the updated login failedCount of a us_user
     */
    @Override
    public int incrementFailedCountForUsername(String username) {
        String sqlString = "UPDATE `us_user` SET us_user.`FAILED_ATTEMPTS`=us_user.`FAILED_ATTEMPTS`+1 WHERE us_user.`USERNAME`=?";
        return this.jdbcTemplate.update(sqlString, username);
    }

    /**
     * Method to update the us_user's login details
     *
     * @param userId UserId is used to get the us_user of which you want to
     * update the login details
     */
    @Override
    public void loginSuccessUpdateForUserId(int userId) {
        Date curDt = AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST);
        String sqlString = "UPDATE `us_user` SET us_user.`FAILED_ATTEMPTS`=0, us_user.`LAST_LOGIN_DATE`=:lastLoginDate WHERE us_user.`USER_ID`=:userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("lastLoginDate", curDt);
        this.namedParameterJdbcTemplate.update(sqlString, params);
    }

    /**
     * Method to set the number of failed attempts from userId
     *
     * @param userId userId is used to get the us_user of which you want to
     * reset the failed attempts
     */
    @Override
    public void resetFailedAttempts(int userId) {
        String sqlString = "UPDATE `us_user` SET us_user.`FAILED_ATTEMPTS`=0 WHERE us_user.`USER_ID`=?";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        this.jdbcTemplate.update(sqlString, userId);
    }

    /**
     * Method to reset the password, the system will give you a randomly
     * generated password
     *
     * @param userId userId is used to get the user of which you want to reset
     * the failed attempts
     * @return Returns the new password that has been set for the user
     */
    @Override
    public String resetPassword(int userId) {
        String pass = AllAltiusUtil.getSmallCasePassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashPass = encoder.encode(pass);
        String sqlString = "UPDATE `us_user` SET us_user.`PASSWORD`=?, us_user.`EXPIRES_ON`=? WHERE us_user.`USER_ID`=?";
        if (this.jdbcTemplate.update(sqlString, hashPass, "2001-01-01", userId) == 1) {
            return pass;
        } else {
            return "Could not reset password";
        }
    }
    
    @Override
    public boolean isTakerRegistered(int takerId) {
        String sql = "SELECT USER_ID FROM taker t where t.TAKER_ID=?";
        return this.jdbcTemplate.queryForObject(sql, Boolean.class, takerId);
    }


}
