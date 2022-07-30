/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author altius
 */
public class QuestionOption implements Serializable {

    private int questionOptionId;
    private String questionOptionName;
    private String questionValue;
    private String labelValue;
    private String labelPosition;
    private Integer sortOrder;
    private boolean active;
//    private QuestionResponse questionResponses;
//    private Question questions;

    public int getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(int questionOptionId) {
        this.questionOptionId = questionOptionId;
    }

    
    public String getQuestionValue() {
        return questionValue;
    }

    public void setQuestionValue(String questionValue) {
        this.questionValue = questionValue;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public String getLabelPosition() {
        return labelPosition;
    }

    public void setLabelPosition(String labelPosition) {
        this.labelPosition = labelPosition;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.questionOptionId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QuestionOption other = (QuestionOption) obj;
        if (!Objects.equals(this.questionOptionId, other.questionOptionId)) {
            return false;
        }
        return true;
    }

    public String getQuestionOptionName() {
        return questionOptionName;
    }

    public void setQuestionOptionName(String questionOptionName) {
        this.questionOptionName = questionOptionName;
    }

    @Override
    public String toString() {
        return "QuestionOption{" + "questionOptionId=" + questionOptionId + ", questionOptionName=" + questionOptionName + ", questionValue=" + questionValue + ", labelValue=" + labelValue + ", labelPosition=" + labelPosition + ", sortOrder=" + sortOrder + ", active=" + active + '}';
    }



    
}
