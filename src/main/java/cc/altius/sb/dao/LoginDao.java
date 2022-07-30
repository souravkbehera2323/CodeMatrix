/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao;

import cc.altius.sb.model.CustomUserDetails;
import java.util.List;

/**
 *
 * @author akil
 */
public interface LoginDao {

    public CustomUserDetails getCustomUserByUsername(String username);

    public List<String> getBusinessFunctionsForUserId(int userId);

}
