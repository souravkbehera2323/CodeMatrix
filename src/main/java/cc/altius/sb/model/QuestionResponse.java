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
public class QuestionResponse {
        
     private String questionOptionValue;
     private String questionOptionAdditionalValue;
     private Date created;
     private Date modified;
     private String startedQuestion;
     private String endedQuestion;
     private Taker taker;
     private Question question;
//     private QuestionTestType test;
     private QuestionOption questionOption;

    
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

    public String getStartedQuestion() {
        return startedQuestion;
    }

    public void setStartedQuestion(String startedQuestion) {
        this.startedQuestion = startedQuestion;
    }

    public String getEndedQuestion() {
        return endedQuestion;
    }

    public void setEndedQuestion(String endedQuestion) {
        this.endedQuestion = endedQuestion;
    }

    

    public Taker getTaker() {
        return taker;
    }

    public void setTaker(Taker taker) {
        this.taker = taker;
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionOption getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(QuestionOption questionOption) {
        this.questionOption = questionOption;
    }

    @Override
    public String toString() {
        return "QuestionResponse{" + "questionOptionValue=" + questionOptionValue + ", questionOptionAdditionalValue=" + questionOptionAdditionalValue + ", created=" + created + ", modified=" + modified + ", startedQuestion=" + startedQuestion + ", endedQuestion=" + endedQuestion + ", taker=" + taker + ", question=" + question + ", questionOption=" + questionOption + '}';
    }

    
}
