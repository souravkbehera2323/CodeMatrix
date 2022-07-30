/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao.impl;

import cc.altius.sb.dao.LoginDao;
import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.model.rowmapper.CustomUserDetailsResultSetExtractor;
import cc.altius.sb.utils.LogUtils;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author akil
 */
@Repository
public class LoginDaoImpl implements LoginDao {

    private final Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Method to get Customer object from username
     *
     * @param username Username used to login
     * @return Returns the Customer object, null if no object could be found
     */
    @Override
    public CustomUserDetails getCustomUserByUsername(String emailId) {
        logger.info("Inside the getCustomerUserByUsername method - " + emailId + " " + LogUtils.getArgsString(), LogUtils.getIpAddress(), LogUtils.getUsername());
//        String sqlString = "SELECT us_user.*, us_role.* FROM us_user left join us_user_role on us_user.USER_ID=us_user_role.USER_ID left join us_role on us_role.ROLE_ID=us_user_role.ROLE_ID WHERE USERNAME=?";
      String sqlString = "SELECT us_user.*, us_role.* FROM us_user left join us_user_role on us_user.USER_ID=us_user_role.USER_ID left join us_role on us_role.ROLE_ID=us_user_role.ROLE_ID WHERE EMAIL_ID=?";
        return this.jdbcTemplate.query(sqlString, new CustomUserDetailsResultSetExtractor(), emailId);
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
        logger.info("Inside the getBusinessFunctionsForUserId method - " + userId + " " + LogUtils.getArgsString(), LogUtils.getIpAddress(), LogUtils.getUsername());
        String sqlString = "SELECT BUSINESS_FUNCTION_ID FROM us_user_role LEFT JOIN us_role_business_function ON us_user_role.ROLE_ID=us_role_business_function.ROLE_ID WHERE us_user_role.USER_ID=? AND BUSINESS_FUNCTION_ID IS NOT NULL";
        return this.jdbcTemplate.queryForList(sqlString, String.class, userId);
    }

}
