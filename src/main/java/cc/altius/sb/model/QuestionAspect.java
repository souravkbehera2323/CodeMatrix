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
public class QuestionAspect implements Serializable
{
    private int aspectId;
    private String aspectName;
    private boolean active;
    private QuestionTrait trait;

    public int getAspectId() {
        return aspectId;
    }

    public void setAspectId(int aspectId) {
        this.aspectId = aspectId;
    }

    public String getAspectName() {
        return aspectName;
    }

    public void setAspectName(String aspectName) {
        this.aspectName = aspectName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public QuestionTrait getTrait() {
        return trait;
    }

    public void setTrait(QuestionTrait trait) {
        this.trait = trait;
    }

    
    @Override
    public String toString() {
        return "Aspect{" + "aspectId=" + aspectId + ", aspectName=" + aspectName + ", active=" + active + ", trait=" + trait + '}';
    }
    
    
    
}
