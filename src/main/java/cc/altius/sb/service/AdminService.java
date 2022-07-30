/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.service;

import cc.altius.sb.model.Role;

/**
 *
 * @author akil
 */
public interface AdminService {

    public int addRole(Role role);
    
    public int updateRole(Role role);
    
}
