/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao;

import cc.altius.sb.exception.DataStoreException;
import cc.altius.sb.model.BusinessFunction;
import cc.altius.sb.model.Password;
import cc.altius.sb.model.Role;
import cc.altius.sb.model.User;
import java.util.List;

/**
 *
 * @author akil
 */
public interface UserDao {

    public List<User> getUserList();
    
    public List<Role> getRoleList();
    
    public List<BusinessFunction> getBusinessFunctionList();
        
    public User getUserByUsername(String username,String EmailId);

    public User getUserByUserId(int userId);

    public boolean confirmPassword(Password password);

    public void updateCanCreateRoles(final String roleId, final String[] canCreateRoleIds);
    
    public List<Role> getCanCreateRoleList(List<Role> roles);
    
    public boolean canCreateRoles(List<Role> roles, List<Role> canCreateRoles);
    
    public void updatePassword(Password password, int offset);
    
    public List<String> getBusinessFunctionsForUserId(int userId);
    
    public List<Role> getRolesForUserId(int userId);
    
    public int incrementFailedCountForUsername(String username);
    
    public void loginSuccessUpdateForUserId(int userId);
    
    public void resetFailedAttempts(int userId);
    
    public String resetPassword(int userId);

    public int addUser(User user) throws DataStoreException;

    public void updateUser(User user) throws DataStoreException;
    
    public boolean isTakerRegistered(int takerId);
}
