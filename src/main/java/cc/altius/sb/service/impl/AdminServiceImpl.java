/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.service.impl;

import cc.altius.sb.dao.AdminDao;
import cc.altius.sb.model.Role;
import cc.altius.sb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author akil
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    
    @Override
    public int addRole(Role role) {
        return this.adminDao.addRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return this.adminDao.updateRole(role);
    }
    
}
