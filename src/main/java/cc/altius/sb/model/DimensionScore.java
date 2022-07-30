/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author altius
 */
public class DimensionScore implements Serializable {

    private int dimensionId;
    private String dimensionName;
    private double totalScore;
    private List<Double> scores;
    private double mean;
    private double stdDeviation;

    public DimensionScore(int dimensionId) {
        this.dimensionId = dimensionId;
        this.scores = new LinkedList<>();
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public double getTotalScore() {
        return totalScore;
    }

    private int getNoOfQuestionsAnswered() {
        return this.scores.size();
    }

    public void addScore(double score,String dimensionName) {
        this.totalScore += score;
        this.scores.add(score);
        this.dimensionName=dimensionName;
        this.mean = this.totalScore / getNoOfQuestionsAnswered();
        double t = 0;
        for (double d : this.scores) {
            t += Math.pow(d - this.mean,2);
        }
//        t = t * t;
        t = t / (getNoOfQuestionsAnswered() - 1);
        this.stdDeviation = Math.pow(t, 0.5);
    }

    public List<Double> getScores() {
        return scores;
    }

    public Double getMean() {
        if (getNoOfQuestionsAnswered() == 0) {
            return null;
        } else {
            return mean;
        }
    }

    public Double getStdDeviation() {
        if (getNoOfQuestionsAnswered() == 0) {
            return null;
        } else {
            return stdDeviation;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.dimensionId;
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
        final DimensionScore other = (DimensionScore) obj;
        if (this.dimensionId != other.dimensionId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DimensionScore{" + "dimensionId=" + dimensionId + ", totalScore=" + totalScore + ", mean=" + mean + ", stdDeviation=" + stdDeviation  + ", dimensionName=" + dimensionName +'}';
    }

    
}
