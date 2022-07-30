/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import java.io.Serializable;

/**
 *
 * @author akil
 */
public class TestType implements Serializable {

    private int testTypeId;
    private String testTypeDesc;

    public int getTestTypeId() {
        return testTypeId;
    }

    public void setTestTypeId(int testTypeId) {
        this.testTypeId = testTypeId;
    }

    public String getTestTypeDesc() {
        return testTypeDesc;
    }

    public void setTestTypeDesc(String testTypeDesc) {
        this.testTypeDesc = testTypeDesc;
    }

}
