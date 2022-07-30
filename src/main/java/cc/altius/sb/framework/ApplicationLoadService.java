/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.framework;

import cc.altius.sb.dao.UserDao;
import cc.altius.sb.model.BusinessFunction;
import cc.altius.sb.model.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author umesh
 */
@Service
public class ApplicationLoadService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void fetchData() {
        ApplicationSession applicationSession = ApplicationSession.getCurrent();
    }

    /**
     * Method to get the role list
     *
     * @return Returns the role list
     */
    public List<Role> getRoleList() {
        return this.userDao.getRoleList();
    }

    /**
     * Method to get the business function list
     *
     * @return Returns the role list
     */
    public List<BusinessFunction> getBusinessFunctionList() {
        return this.userDao.getBusinessFunctionList();
    }

    /**
     * Method to get the canCreateRole list from roleId
     *
     * @param roles RoleId used to get who can create the roles
     * @return Returns the canCreateRole list from roleId
     */
    public List<Role> getCanCreateRoleList(List<Role> roles) {
        return this.userDao.getCanCreateRoleList(roles);
    }

}
