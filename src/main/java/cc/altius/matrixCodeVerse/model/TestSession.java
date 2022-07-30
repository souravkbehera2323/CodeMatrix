/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author akil
 */
public class TestSession implements Serializable {

    private int testSessionId;
    private Date createdDate;
    private Date lastModifiedDate;
    private int lastSortOrderId;
    private Date testStartedTime;
    private Date testEndedTime;
    private Taker taker;
    private TestType testType;
    private final PropertyStatsGroup currentStats;
    private PopulationStatsGroup populationStats;
    private Question currentQuestion;
    private String[] currentQuestionResponse;
    private int noOfQuestionsAnswered;
    private long questionStartedTime;
    private long questionEndedTime;

    public long getQuestionStartedTime() {
        return questionStartedTime;
    }

    public void setQuestionStartedTime(long questionStartedTime) {
        this.questionStartedTime = questionStartedTime;
    }

    public long getQuestionEndedTime() {
        return questionEndedTime;
    }

    public void setQuestionEndedTime(long questionEndedTime) {
        this.questionEndedTime = questionEndedTime;
    }

    public TestSession() {
        this.currentStats = new PropertyStatsGroup();
    }

//    public void setPopulationStats(PopulationStatsGroup populationStats) {
//        this.populationStats = populationStats;
//    }
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

    public int getLastSortOrderId() {
        return lastSortOrderId;
    }

    public void setLastSortOrderId(int lastSortOrderId) {
        this.lastSortOrderId = lastSortOrderId;
    }

    public Date getTestStartedTime() {
        return testStartedTime;
    }

    public void setTestStartedTime(Date testStartedTime) {
        this.testStartedTime = testStartedTime;
    }

    public Date getTestEndedTime() {
        return testEndedTime;
    }

    public void setTestEndedTime(Date testEndedTime) {
        this.testEndedTime = testEndedTime;
    }

    public int getNoOfQuestionsAnswered() {
        return noOfQuestionsAnswered;
    }

    public void setNoOfQuestionsAnswered(int noOfQuestionsAnswered) {
        this.noOfQuestionsAnswered = noOfQuestionsAnswered;
    }

    public Taker getTaker() {
        return taker;
    }

    public void setTaker(Taker taker) {
        this.taker = taker;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    public PropertyStatsGroup getCurrentStats() {
        return currentStats;
    }

//    public PopulationStatsGroup getPopulationStats() {
//        return populationStats;
//    }
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public String[] getCurrentQuestionResponse() {
        return currentQuestionResponse;
    }

    public void setCurrentQuestionResponse(String[] currentQuestionResponse) {
        this.currentQuestionResponse = currentQuestionResponse;
    }

    public String getNormalDistributionForPopulationString(int id, int type) {
        double StdDev = 0, Mean = 0;
        StringBuilder sb = new StringBuilder();
        if (this.getPopulationStats().getStats(type, id) != null) {
            StdDev = this.getPopulationStats().getStats(type, id).getStdDeviation();
            Mean = this.getPopulationStats().getStats(type, id).getMean();
        }
        sb.append("Mean=").append(this.getCurrentStats().getStats(type, id).getMean())
                .append(", POpulation Mean=").append(Mean)
                .append(", Population StdDev=").append(StdDev)
                .append(", NormalDist=").append(this.getNormalDistributionForPopulation(id, type));

        return sb.toString();
    }

    public Double getNormalDistributionForPopulation(int id, int type) {
        double StdDev = 0, Mean = 0;
        StringBuilder sb = new StringBuilder();
        if (this.getPopulationStats().getStats(type, id) != null) {
            StdDev = this.getPopulationStats().getStats(type, id).getStdDeviation();
            Mean = this.getPopulationStats().getStats(type, id).getMean();
        }
        return NormalDistribution.NORMAL_DISTRIBUTION(
                this.getCurrentStats().getStats(type, id).getMean(),
                Mean,
                StdDev);
    }

    public PopulationStatsGroup getPopulationStats() {
        return populationStats;
    }

    public void setPopulationStats(PopulationStatsGroup populationStats) {
        this.populationStats = populationStats;
    }

    @Override
    public String toString() {
        return "TestSession{" + "testSessionId=" + testSessionId + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", lastSortOrderId=" + lastSortOrderId + ", testStartedTime=" + testStartedTime + ", testEndedTime=" + testEndedTime + ", taker=" + taker + ", testType=" + testType + ", currentStats=" + currentStats + ", populationStats=" + populationStats + ", currentQuestion=" + currentQuestion + ", currentQuestionResponse=" + currentQuestionResponse + '}';
    }

}
