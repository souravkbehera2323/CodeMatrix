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
public class QuestionTestType {

    private int questionTestTypeId;
    private String questionTestTypeName;
    private Date created;
    private Date modified;
    private QuestionResponse questionResponses;
    private Question questions;

    public int getQuestionTestTypeId() {
        return questionTestTypeId;
    }

    public void setQuestionTestTypeId(int questionTestTypeId) {
        this.questionTestTypeId = questionTestTypeId;
    }

    public String getQuestionTestTypeName() {
        return questionTestTypeName;
    }

    public void setQuestionTestTypeName(String questionTestTypeName) {
        this.questionTestTypeName = questionTestTypeName;
    }

    
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public QuestionResponse getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(QuestionResponse questionResponses) {
        this.questionResponses = questionResponses;
    }

    public Question getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "QuestionTestType{" + "questionTestTypeId=" + questionTestTypeId + ", questionTestTypeName=" + questionTestTypeName + ", created=" + created + ", modified=" + modified + ", questionResponses=" + questionResponses + ", questions=" + questions + '}';
    }

    
    
}
