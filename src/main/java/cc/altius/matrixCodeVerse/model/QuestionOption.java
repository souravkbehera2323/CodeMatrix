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
public class QuestionOption implements Serializable {

    private int questionOptionId;
    private String questionOptionText;
    private String questionOptionValue;
    private String questionOptionAdditionalValue;

    public int getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(int questionOptionId) {
        this.questionOptionId = questionOptionId;
    }

    public String getQuestionOptionText() {
        return questionOptionText;
    }

    public void setQuestionOptionText(String questionOptionText) {
        this.questionOptionText = questionOptionText;
    }

    public String getQuestionOptionValue() {
        return questionOptionValue;
    }

    public void setQuestionOptionValue(String questionOptionValue) {
        this.questionOptionValue = questionOptionValue;
    }


    public String getQuestionOptionAdditionalValue() {
        return questionOptionAdditionalValue;
    }

    public void setQuestionOptionAdditionalValue(String questionOptionAdditionalValue) {
        this.questionOptionAdditionalValue = questionOptionAdditionalValue;
    }

    @Override
    public String toString() {
        return "QuestionOption{" + "questionOptionId=" + questionOptionId + ", questionOptionText=" + questionOptionText + ", questionOptionValue=" + questionOptionValue + ", questionOptionAdditionalValue=" + questionOptionAdditionalValue + '}';
    }


    

}
