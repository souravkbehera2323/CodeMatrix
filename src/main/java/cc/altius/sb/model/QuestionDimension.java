/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model;

import java.io.Serializable;

/**
 *
 * @author altius
 */
public class QuestionDimension implements Serializable{
    
    private int dimensionId;
    private String dimensionName;
    private boolean active;

    public int getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "QuestionDimension{" + "dimensionId=" + dimensionId + ", dimensionName=" + dimensionName + ", active=" + active + '}';
    }
    
    
}
