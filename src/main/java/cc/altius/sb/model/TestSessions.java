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
public class TestSessions {

    private int testSessionId;
    private Date createdDate;
    private Date lastModifiedDate;
    private Boolean active;
    private int lastQuestionId;
    private Date testStarted;
    private Date testEnded;
    private Taker taker;
    private DimensionScore dimensionScore;
    private TraitScore traitScore;
    private AspectScore aspectScore;
    private QuestionResponse questionResponses;
    private Question question;
    

    public int getTestSessionId() {
        return testSessionId;
    }

    public void setTestSessionId(int testSessionId) {
        this.testSessionId = testSessionId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }



    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    
    public int getLastQuestionId() {
        return lastQuestionId;
    }

    public void setLastQuestionId(int lastQuestionId) {
        this.lastQuestionId = lastQuestionId;
    }

    public Date getTestStarted() {
        return testStarted;
    }

    public void setTestStarted(Date testStarted) {
        this.testStarted = testStarted;
    }

    public Date getTestEnded() {
        return testEnded;
    }

    public void setTestEnded(Date testEnded) {
        this.testEnded = testEnded;
    }

    public Taker getTaker() {
        return taker;
    }

    public void setTaker(Taker taker) {
        this.taker = taker;
    }

    public QuestionResponse getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(QuestionResponse questionResponses) {
        this.questionResponses = questionResponses;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public DimensionScore getDimensionScore() {
        return dimensionScore;
    }

    public void setDimensionScore(DimensionScore dimensionScore) {
        this.dimensionScore = dimensionScore;
    }

    public TraitScore getTraitScore() {
        return traitScore;
    }

    public void setTraitScore(TraitScore traitScore) {
        this.traitScore = traitScore;
    }

    public AspectScore getAspectScore() {
        return aspectScore;
    }

    public void setAspectScore(AspectScore aspectScore) {
        this.aspectScore = aspectScore;
    }

    @Override
    public String toString() {
        return "TestSessions{" + "testSessionId=" + testSessionId + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", active=" + active + ", lastQuestionId=" + lastQuestionId + ", testStarted=" + testStarted + ", testEnded=" + testEnded + ", taker=" + taker + ", dimensionScore=" + dimensionScore + ", traitScore=" + traitScore + ", aspectScore=" + aspectScore + ", questionResponses=" + questionResponses + ", question=" + question + '}';
    }
    
    
    
}
