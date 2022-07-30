/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model;

import java.util.Date;

/**
 *
 * @author altius
 */
public class QuestionType {

    private int questionTypeId;
    private String questionTypeName;
    private String elementName;
    private Question questions;

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public Question getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "QuestionType{" + "questionTypeId=" + questionTypeId + ", questionTypeName=" + questionTypeName + ", elementName=" + elementName + ", questions=" + questions + '}';
    }
    
}
