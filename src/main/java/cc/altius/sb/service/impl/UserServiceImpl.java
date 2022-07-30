/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.service.impl;

import cc.altius.sb.dao.UserDao;
import cc.altius.sb.exception.DataStoreException;
import cc.altius.sb.model.Password;
import cc.altius.sb.model.Role;
import cc.altius.sb.model.User;
import cc.altius.sb.service.UserService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author akil
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * Method to get the list of Users
     *
     * @return Returns the list of users by calling a method from userDao
     */
    @Override
    public List<User> getUserList() {
        return this.userDao.getUserList();
    }

    /**
     * Method to update/edit the user
     *
     * @param user user that you want to update
     * @throws cc.altius.sb.exception.DataStoreException
     */
    @Override
    public void updateUser(User user) throws DataStoreException {
        this.userDao.updateUser(user);
    }

    /**
     * Method to create a new user
     *
     * @param user user that you want to create
     * @return Returns the user object by calling a method from userDao
     * @throws cc.altius.sb.exception.DataStoreException
     */
    @Override
    public int addUser(User user) throws DataStoreException {
        return userDao.addUser(user);
    }

    /**
     * Method to get the user object from username
     *
     * @param username Username used to get the user by their name
     * @return Returns the user object by calling a method from userDao
     */
    @Override
    public User getUserByUsername(String username, String emailId) {
        return this.userDao.getUserByUsername(username, emailId);
    }

    /**
     * Method to check whether the username already exist or not
     *
     * @param username
     * @return Returns true when username already exist Returns false when
     * username does not exist
     */
    @Override
    public boolean existUserByUsername(String username, String EmailId) {
        return this.userDao.getUserByUsername(username, EmailId) != null;
    }

    /**
     * Method to get the user object from userId
     *
     * @param userId UserId used to get the user by their Id
     * @return Returns the user object by calling a method from userDao
     */
    @Override
    public User getUserByUserId(int userId) {
        return this.userDao.getUserByUserId(userId);
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
        return this.userDao.confirmPassword(password);
    }

    /**
     * Method to update the password for existing user
     *
     * @param password Password to get the password
     * @param offset Offset to calculate the offset date
     */
    @Override
    public void updatePassword(Password password, int offset) {
        this.userDao.updatePassword(password, offset);
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
        return this.userDao.getBusinessFunctionsForUserId(userId);
    }

    /**
     * Method to get the list of Roles that a userId has access to
     *
     * @param userId userId that you want to get the Roles for
     * @return Returns a list of Roles that the userId has access to
     */
    @Override
    public List<Role> getRolesForUserId(int userId) {
        return this.userDao.getRolesForUserId(userId);
    }

    /**
     * Method to update the login failure count from username
     *
     * @param username username is used to get the user of whom you want to
     * update the failedCount
     * @return Returns the updated login failedCount of a user by calling a
     * method from userDao
     */
    @Override
    public int incrementFailedCountForUsername(String username) {
        return this.userDao.incrementFailedCountForUsername(username);
    }

    /**
     * Method to update the user's login details
     *
     * @param userId UserId is used to get the user of which you want to update
     * the login details by calling a method from userDao
     */
    @Override
    public void loginSuccessUpdateForUserId(int userId) {
        this.userDao.loginSuccessUpdateForUserId(userId);
    }

    /**
     * Method to set the number of failed attempts from userId
     *
     * @param userId userId is used to get the user of which you want to reset
     * the failed attempts
     */
    @Override
    public void resetFailedAttempts(int userId) {
        this.userDao.resetFailedAttempts(userId);
    }

    /**
     * Method to reset the password, the system will give you a randomly
     * generated password
     *
     * @param userId userId is used to get the user of which you want to reset
     * the failed attempts
     * @return Returns the new Password string
     */
    @Override
    public String resetPassword(int userId) {
        return this.userDao.resetPassword(userId);
    }

    /**
     * Method to get who can create roles by the roleId
     *
     * @param roles
     * @param canCreateRoles
     * @return Returns true if user has access to create a role Returns false if
     * user does not have access to create a role
     */
    @Override
    public boolean canCreateRoles(List<Role> roles, List<Role> canCreateRoles) {
        return this.userDao.canCreateRoles(roles, canCreateRoles);
    }

    /**
     * Method to update the canCraeteRoles table
     *
     * @param roleId RoleId used to get the id of role which you want to create
     * @param canCreateRoleIds canCreateRoleIds is used to get who can create
     * the role
     */
    @Override
    public void updateCanCreateRoles(String roleId, String[] canCreateRoleIds) {
        this.userDao.updateCanCreateRoles(roleId, canCreateRoleIds);
    }

    /**
     * Method to get the list of Roles that a particular RoleId can create
     *
     * @param roleId RoleId for which you need the List of Roles
     * @return List of Roles that the RoleId can Create
     */
    @Override
    public List<Role> getCreateRolesList(String roleId) {
        List<Role> roleList = new LinkedList<>();
        Role r = new Role();
        r.setRoleId(roleId);
        roleList.add(r);
        return this.userDao.getCanCreateRoleList(roleList);
    }

    @Override
    public boolean isTakerRegistered(int takerId) {
        return this.userDao.isTakerRegistered(takerId);
    }

}
