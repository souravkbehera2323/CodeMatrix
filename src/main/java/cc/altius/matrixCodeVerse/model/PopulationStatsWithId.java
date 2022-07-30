/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

/**
 *
 * @author yogesh
 */
public class PopulationStatsWithId extends PopulationStats {

    private int id;

    public PopulationStatsWithId() {
        super();
    }

    public PopulationStatsWithId(int id, int noOfQuestions, double totalScore, Double mean, Double stdDeviation) {
        super(noOfQuestions, totalScore, mean, stdDeviation);
        this.id = id;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
