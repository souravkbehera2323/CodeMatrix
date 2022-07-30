/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Akil Mahimwala
 */
public class Role implements Serializable {

    private String roleId;
    private String roleName;
    private List<BusinessFunction> businessFunctions;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<BusinessFunction> getBusinessFunctions() {
        return businessFunctions;
    }

    public void setBusinessFunctions(List<BusinessFunction> businessFunctions) {
        this.businessFunctions = businessFunctions;
    }

    @Override
    public int hashCode() {
       int hash = 7;
        hash = 53 * hash + (this.roleId != null ? this.roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
       if (obj instanceof Role) {
            Role r = (Role) obj;
            if (r.getRoleId().equals(this.roleId)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Role{" + "roleId=" + roleId + ", roleName=" + roleName + '}';
    }
}
