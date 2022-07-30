/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.service.impl;

import cc.altius.matrixCodeVerse.model.TestSession;
import cc.altius.sb.dao.RegisterDao;
import cc.altius.sb.model.Taker;
import cc.altius.sb.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;
    
    @Override
    public int addTaker(Taker taker, String lastNameId) {
        return this.registerDao.addTaker(taker, lastNameId);
    }

    @Override
    public boolean existTakerByTakerDetails(String lastName, String email) {
         if (this.registerDao.getTakerByTakerLastName(lastName, email)) {
             
            return true;
        } else {
             
            return false;
        }
    }

    @Override
    public int addTestSession(TestSession testsession, int takerId) {
        return this.registerDao.addTestSession(testsession, takerId);
    }

    @Override
    public boolean existTakerByEmailId(String email) {
        if (this.registerDao.getExistTakerByEmailId(email)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addContactsInInfusionsoft(Taker taker) {
        this.registerDao.addContactsInInfusionsoft(taker);
    }

}
