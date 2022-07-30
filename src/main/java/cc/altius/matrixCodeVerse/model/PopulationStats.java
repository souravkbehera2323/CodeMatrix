/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

/**
 *
 * @author akil
 */
public class PopulationStats {

    private int noOfQuestions; // 0 when initialized
    private double totalScore; // 0 when initialized
    private Double mean; // null when initialized
    private Double stdDeviation; // null when initialized

    public PopulationStats() {
    }

    public PopulationStats(int noOfQuestions, double totalScore, Double mean, Double stdDeviation) {
        this.noOfQuestions = noOfQuestions;
        this.totalScore = totalScore;
        this.mean = mean;
        this.stdDeviation = stdDeviation;
    }

    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public void setStdDeviation(Double stdDeviation) {
        this.stdDeviation = stdDeviation;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public double getStdDeviation() {
        return stdDeviation;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public double getVariance() {
        return Math.pow(this.stdDeviation, 2);
    }

    public Double getMean() { // Returns null if no scores have been added as yet
        return this.mean;
    }

    @Override
    public String toString() {
        return "PopulationStats{" + "noOfQuestions=" + noOfQuestions + ", totalScore=" + totalScore + ", mean=" + mean + ", stdDeviation=" + stdDeviation + '}';
    }
    
    
}
