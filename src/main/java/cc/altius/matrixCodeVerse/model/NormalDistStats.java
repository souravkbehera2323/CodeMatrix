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
public class NormalDistStats {

    private int id;
    private String name;
    private double score;
    private double currentMean;
    private double populationMean;
    private double populationStdDev;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getCurrentMean() {
        return currentMean;
    }

    public void setCurrentMean(double currentMean) {
        this.currentMean = currentMean;
    }

    public double getPopulationMean() {
        return populationMean;
    }

    public void setPopulationMean(double populationMean) {
        this.populationMean = populationMean;
    }

    public double getPopulationStdDev() {
        return populationStdDev;
    }

    public void setPopulationStdDev(double populationStdDev) {
        this.populationStdDev = populationStdDev;
    }

    public double getNormalDistribution() {
        return NormalDistribution.NORMAL_DISTRIBUTION(currentMean, populationMean, populationStdDev);
    }

    @Override
    public String toString() {
        return "NormalDistStats{" + "id=" + id + ", name=" + name + ", score=" + score + ", currentMean=" + currentMean + ", populationMean=" + populationMean + ", populationStdDev=" + populationStdDev + '}';
    }

}
