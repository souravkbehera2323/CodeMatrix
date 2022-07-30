/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.framework;

import cc.altius.maven.AllAltiusUtil;
import cc.altius.sb.model.BusinessFunction;
import cc.altius.sb.model.Role;
//import cc.altius.utils.StringUtils;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author altius
 */
@Component
@Scope("application")
public class ApplicationSession {

    @Autowired
    private ApplicationLoadService applicationLoadService;
    private List<Role> roleList = new LinkedList<>();
    private List<BusinessFunction> businessFunctionList = new LinkedList<>();

    /**
     * Method to get the current application session
     *
     * @return Returns the session of applicationContext from the class
     */
    public static ApplicationSession getCurrent() {
        return ApplicationContextProvider.getApplicationContext().getBean(ApplicationSession.class);
    }

    public ApplicationSession() {
    }

    /**
     * Method to reload all the most required lists
     *
     */
    public void reloadAll() {
        reloadRoleList();
        reloadBusinessFunctionList();
    }

    /**
     * Method to reload role lists
     *
     */
    public void reloadRoleList() {
        this.roleList = null;
        this.roleList = applicationLoadService.getRoleList();
    }
    
    /**
     * Method to reload business function list
     *
     */
    public void reloadBusinessFunctionList() {
        this.businessFunctionList = null;
        this.businessFunctionList = applicationLoadService.getBusinessFunctionList();
    }

    /**
     * Method to get the role list
     *
     * @return Returns the role list
     */
    public List<Role> getRoleList() {
        if (this.roleList != null && !this.roleList.isEmpty()) {
            return this.roleList;
        } else {
            this.roleList = applicationLoadService.getRoleList();
            return this.roleList;
        }
    }

    /**
     * Method to get the role from roleId
     *
     * @param roleId
     * @return Returns role object
     */
    public Role getRoleById(String roleId) {
        Role role;
        if (AllAltiusUtil.isBlank(roleId)) {
            return null;
        }
        role = new Role();
        role.setRoleId(roleId);
        int idx = this.roleList.indexOf(role);
        if (idx >= 0) {
            return this.roleList.get(idx);
        } else {
            return null;
        }
    }
    
    /**
     * Method to get the canCreateRole list from roleId
     *
     * @param roles roleId used to get the role of user
     * @return Returns the canCreateRole list
     */
    public List<Role> getCanCreateRoleList(List<Role> roles) {
        return applicationLoadService.getCanCreateRoleList(roles);
    }
    
    /**
    * Method to get the List of Business functions
     * @return Returns the list of Business Functions that are available in the system
    */
    public List<BusinessFunction> getBusinessFunctionList() {
        if (this.businessFunctionList != null && !this.businessFunctionList.isEmpty()) {
            return this.businessFunctionList;
        } else {
            this.businessFunctionList = applicationLoadService.getBusinessFunctionList();
            return this.businessFunctionList;
        }
    }
}
