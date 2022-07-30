/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.service;

import cc.altius.sb.exception.DataStoreException;
import cc.altius.sb.model.Password;
import cc.altius.sb.model.Role;
import cc.altius.sb.model.User;
import java.util.List;

/**
 *
 * @author akil
 */
public interface UserService {

    public List<User> getUserList();

    public void updateUser(User user) throws DataStoreException;

    public User getUserByUsername(String username,String emailId);

    public boolean existUserByUsername(String username,String emailId);

    public User getUserByUserId(int userId);

    public boolean confirmPassword(Password password);

    public void updatePassword(Password password, int offset);

    public List<String> getBusinessFunctionsForUserId(int userId);
    
    public List<Role> getRolesForUserId(int userId);

    public int incrementFailedCountForUsername(String username);

    public void loginSuccessUpdateForUserId(int userId);

    public void resetFailedAttempts(int userId);

    public String resetPassword(int userId);

    public boolean canCreateRoles(List<Role> roles, List<Role> canCreateRoles);

    public void updateCanCreateRoles(String roleId, String[] canCreateRoleIds);
    
    public List<Role> getCreateRolesList(String roleId);

    public int addUser(User user) throws DataStoreException;
    
    public boolean isTakerRegistered(int takerId);
}
