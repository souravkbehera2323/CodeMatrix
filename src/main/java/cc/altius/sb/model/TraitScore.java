/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author yogesh
 */
public class TraitScore implements Serializable {

    private int traitId;
    private String traitName;
    private double totaleTraitScore;
    private List<Double> traitScores;
    private double traitMean;
    private double traitStdDeviation;
    private double resultPercentile;
    private int flag;
    public TraitScore(int traitId) {
        this.traitId = traitId;
        this.traitScores = new LinkedList<>();
    }

    public int getTraitId() {
        return traitId;
    }

    public void setTraitId(int traitId) {
        this.traitId = traitId;
    }

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }

    public double getTotaleTraitScore() {
        return totaleTraitScore;
    }

    public int getNoOfQuestionAnswered() {
        return this.traitScores.size();
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void addTraitScore(double traitScore, String traitName) {
        this.totaleTraitScore += traitScore;
        this.traitScores.add(traitScore);
        this.traitName = traitName;
        this.traitMean = totaleTraitScore / getNoOfQuestionAnswered();
        this.flag=1;
        double t = 0;
        for (double d : this.traitScores) {
            t += Math.pow(d - this.traitMean, 2);
        }
//        t = t * t;
        t = t / (getNoOfQuestionAnswered() - 1);
        this.traitStdDeviation = Math.sqrt(t);
        resultPercentile =  (traitMean*traitStdDeviation*1)*100;  
    }

    public List<Double> getTraitScores() {
        return traitScores;
    }

    public Double getTraitMean() {
        if (getNoOfQuestionAnswered() == 0) {
            return null;
        } else {
            return traitMean;
        }
    }

    public Double getTraitStdDeviation() {
        if (getNoOfQuestionAnswered() == 0) {
            return null;
        } else {
            return traitStdDeviation;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.traitId;
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
        final TraitScore other = (TraitScore) obj;
        if (this.traitId != other.traitId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TraitScore{" + "traitId=" + traitId + ", totaleTraitScore=" + totaleTraitScore + ", traitScores=" + traitScores + ", traitMean=" + traitMean + ", traitStdDeviation=" + traitStdDeviation + ", traitName=" + traitName +", flag=" + flag +  '}';
    }

}
