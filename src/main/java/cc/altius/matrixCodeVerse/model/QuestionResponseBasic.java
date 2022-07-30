/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import java.io.Serializable;

/**
 *
 * @author akil
 */
public class QuestionResponseBasic implements Serializable {

    private int id;
    private double score;

    public QuestionResponseBasic() {
    }

    public QuestionResponseBasic(int id, double score) {
        this.id = id;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final QuestionResponseBasic other = (QuestionResponseBasic) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "QuestionResponseBasic{" + "id=" + id + ", score=" + score + '}';
    }

}
