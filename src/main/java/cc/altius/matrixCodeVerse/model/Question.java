/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author akil
 */
public class Question implements Serializable {

    private int questionId;
    private String name;
    private String description;
    private Property dimension;
    private Property trait;
    private Property aspect;
    private int sortOrder;
    private boolean active;
    private boolean reverseKey;
    private int usesModifierOnQuestion;
    private int modifierValue;
    private int questionTypeId; // 1=Radio, 2=Text, 3=TextArea, 4=Order, 5=Multi-select
    private List<QuestionOption> questionOptionList;
    private boolean descToggle;

    public Question() {
        this.questionOptionList = new LinkedList<>();
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Property getDimension() {
        return dimension;
    }

    public void setDimension(Property dimension) {
        this.dimension = dimension;
    }

    public Property getTrait() {
        return trait;
    }

    public void setTrait(Property trait) {
        this.trait = trait;
    }

    public Property getAspect() {
        return aspect;
    }

    public void setAspect(Property aspect) {
        this.aspect = aspect;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isReverseKey() {
        return reverseKey;
    }

    public void setReverseKey(boolean reverseKey) {
        this.reverseKey = reverseKey;
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

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public List<QuestionOption> getQuestionOptionList() {
        return questionOptionList;
    }

    public void setQuestionOptionList(List<QuestionOption> questionOptionList) {
        this.questionOptionList = questionOptionList;
    }

    public boolean isDescToggle() {
        return descToggle;
    }

    public void setDescToggle(boolean descToggle) {
        this.descToggle = descToggle;
    }

    public Double getScore() {
        if (this.getQuestionOptionList() != null && !this.getQuestionOptionList().isEmpty() && this.getQuestionOptionList().size() == 1) {
            try {
                return Double.parseDouble(this.getQuestionOptionList().get(0).getQuestionOptionValue());
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Question{" + "questionId=" + questionId + ", name=" + name + ", description=" + description + ", dimension=" + dimension + ", trait=" + trait + ", aspect=" + aspect + ", sortOrder=" + sortOrder + ", active=" + active + ", reverseKey=" + reverseKey + ", usesModifierOnQuestion=" + usesModifierOnQuestion + ", modifierValue=" + modifierValue + ", questionTypeId=" + questionTypeId + ", questionOptionList=" + questionOptionList + ", descToggle=" + descToggle + '}';
    }

}
