/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao;

import cc.altius.matrixCodeVerse.model.TestSession;
import cc.altius.sb.model.Taker;

/**
 *
 * @author altius
 */
public interface RegisterDao {

    public int addTaker(Taker taker,String lastNameId);

    public boolean getTakerByTakerLastName(String lastName, String email);

    public int addTestSession(TestSession testsession,int takerId);

    public boolean getExistTakerByEmailId(String email);

    public void addContactsInInfusionsoft(Taker taker);

    
}
