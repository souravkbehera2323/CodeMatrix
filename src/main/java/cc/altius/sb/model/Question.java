/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author altius
 */
public class Question implements Serializable {

    private int questionId;
    private String questionName;
    private String description;
    private QuestionTrait questionTrait;
    private QuestionAspect questionAspect;
    private QuestionDimension questionDimension;
    private Integer sortOrder;
    private boolean active;
    private boolean isReverseKey;
    private int usesModifierOnQuestion;
    private int modifierValue;
    private Date createdDate;
    private User createdBy;
    private Date lastModifiedDate;
    private User lastModifiedBy;
    private QuestionType questionType;
    private QuestionTestType questionTestType;
    private String lastQuestionId;
    private List<QuestionOption> questionOptions;
    private boolean descToggle;
    
    public Question() {
//       this.questionOptions = new LinkedList<QuestionOption>();
        this.questionOptions = new LinkedList<>();

   }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionTrait getQuestionTrait() {
        return questionTrait;
    }

    public void setQuestionTrait(QuestionTrait questionTrait) {
        this.questionTrait = questionTrait;
    }

    public QuestionAspect getQuestionAspect() {
        return questionAspect;
    }

    public void setQuestionAspect(QuestionAspect questionAspect) {
        this.questionAspect = questionAspect;
    }

    public QuestionDimension getQuestionDimension() {
        return questionDimension;
    }

    public void setQuestionDimension(QuestionDimension questionDimension) {
        this.questionDimension = questionDimension;
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

    public boolean isIsReverseKey() {
        return isReverseKey;
    }

    public void setIsReverseKey(boolean isReverseKey) {
        this.isReverseKey = isReverseKey;
    }

    public int getUsesModifierOnQuestion() {
        return usesModifierOnQuestion;
    }

    public void setUsesModifierOnQuestion(int usesModifierOnQuestion) {
        this.usesModifierOnQuestion = usesModifierOnQuestion;
    }

    public int getModifierValue() {
        return modifierValue;
    }

    public void setModifierValue(int modifierValue) {
        this.modifierValue = modifierValue;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    
    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public QuestionTestType getQuestionTestType() {
        return questionTestType;
    }

    public void setQuestionTestType(QuestionTestType questionTestType) {
        this.questionTestType = questionTestType;
    }

    
    public String getLastQuestionId() {
        return lastQuestionId;
    }

    public void setLastQuestionId(String lastQuestionId) {
        this.lastQuestionId = lastQuestionId;
    }

    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.questionId;
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
        final Question other = (Question) obj;
        if (this.questionId != other.questionId) {
            return false;
        }
        return true;
    }

    public boolean isDescToggle() {
        return descToggle;
    }

    public void setDescToggle(boolean descToggle) {
        this.descToggle = descToggle;
    }

    @Override
    public String toString() {
        return "Question{" + "questionId=" + questionId + ", questionName=" + questionName + ", description=" + description + ", questionTrait=" + questionTrait + ", questionAspect=" + questionAspect + ", questionDimension=" + questionDimension + ", sortOrder=" + sortOrder + ", active=" + active + ", isReverseKey=" + isReverseKey + ", usesModifierOnQuestion=" + usesModifierOnQuestion + ", modifierValue=" + modifierValue + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", questionType=" + questionType + ", questionTestType=" + questionTestType + ", lastQuestionId=" + lastQuestionId + ", questionOptions=" + questionOptions + ", descToggle=" + descToggle + '}';
    }

    
    
    
}
