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
public class PropertyStats extends PopulationStats implements Serializable {

    private final List<QuestionResponseBasic> questionResponseList; // list of questionResponses added to this PropertyStats

    public PropertyStats() {
        this.questionResponseList = new LinkedList<>();
    }

    private void updateStdDeviation() {
        double temp = 0;
        for (QuestionResponseBasic qr : this.questionResponseList) {
            temp += Math.pow(qr.getScore() - this.getMean(), 2);
        }
        temp = temp / (this.getNoOfQuestions() - 1);
        this.setStdDeviation(Math.pow(temp, 0.5));
    }

    /*
    Use this function to add one Score the Property stats
     */
    public void addScore(QuestionResponseBasic questionResponse) {
        this.setNoOfQuestions(this.getNoOfQuestions()+1);
        this.setTotalScore(this.getTotalScore()+questionResponse.getScore());
        this.questionResponseList.add(questionResponse);
        this.setMean(this.getTotalScore() / this.getNoOfQuestions());
        updateStdDeviation();
    }

    @Override
    public String toString() {
        return "PropertyStats{" + "noOfQuestions=" + this.getNoOfQuestions() + ", totalScore=" + this.getTotalScore() + ", mean=" + this.getMean() + ", stdDeviation=" + this.getStdDeviation() + ", variance=" + this.getVariance() + ", Normal Dist=" + this.getNormalDistribution() + "}\n";
    }

    public String getNormalDistribution() {
        if (this.questionResponseList.size() == 0) {
            return "";
        } else {
            return "Score=" + this.questionResponseList.get(this.questionResponseList.size() - 1).getScore() + ", mean=" + this.getMean() + ", StdDev=" + this.getStdDeviation() + ", NormalDist=" + NormalDistribution.NORMAL_DISTRIBUTION(this.questionResponseList.get(this.questionResponseList.size() - 1).getScore(), this.getMean(), this.getStdDeviation());
        }

    }

    public Double getScore(int id) {
        QuestionResponseBasic qrb = new QuestionResponseBasic();
        qrb.setId(id);
        int idx = this.questionResponseList.indexOf(qrb);
        if (idx >= 0) {
            return this.questionResponseList.get(idx).getScore();
        } else {
            return null;
        }
    }
}
