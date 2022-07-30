/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.service.impl;

import cc.altius.sb.dao.ImportDao;
import cc.altius.sb.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author akil
 */
@Service
public class ImportServiceImpo implements ImportService {

    @Autowired
    private ImportDao importDao;
    
    @Override
    public void doImport() {
        this.importDao.doImport();
    }
    
}
