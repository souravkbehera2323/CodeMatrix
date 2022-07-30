/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model;

import java.io.Serializable;

/**
 *
 * @author akil
 */
public class BusinessFunction implements Serializable {

    private String businessFunctionId;
    private String businessFunctionDesc;

    public String getBusinessFunctionId() {
        return businessFunctionId;
    }

    public void setBusinessFunctionId(String businessFunctionId) {
        this.businessFunctionId = businessFunctionId;
    }

    public String getBusinessFunctionDesc() {
        return businessFunctionDesc;
    }

    public void setBusinessFunctionDesc(String businessFunctionDesc) {
        this.businessFunctionDesc = businessFunctionDesc;
    }

}
