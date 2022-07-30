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
public class QuestionTrait implements Serializable{
    
    private int traitId;
    private String traitName;
    private boolean active;
    private QuestionDimension questionDimension;

    public int getTraitId() {
        return traitId;
    }

    public void setTraitId(int traitId) {
        this.traitId = traitId;
    }

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public QuestionDimension getQuestionDimension() {
        return questionDimension;
    }

    public void setQuestionDimension(QuestionDimension questionDimension) {
        this.questionDimension = questionDimension;
    }
    
    
    
}
