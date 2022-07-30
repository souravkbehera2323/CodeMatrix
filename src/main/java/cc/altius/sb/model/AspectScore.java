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
 * @author yogesh
 */
public class AspectScore implements Serializable {

    private int aspectId;
    private String aspectName;
    private String traitName;
    private double totaleAspectScore;
    private double totalePopAspectScore;
    private List<Double> aspectScores;
    private double aspectMean;
    private double aspectStdDeviation;
    private int flag;
    public AspectScore(int aspectId) {
        this.aspectId = aspectId;
        this.aspectScores = new LinkedList<>();
    }

    public int getAspectId() {
        return aspectId;
    }

    public void setAspectId(int aspectId) {
        this.aspectId = aspectId;
    }

    public String getAspectName() {
        return aspectName;
    }

    public void setAspectName(String aspectName) {
        this.aspectName = aspectName;
    }

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }
    

    public double getTotaleAspectScore() {
        return totaleAspectScore;
    }

    public int getNoOfQuestionAnswered() {
        return this.aspectScores.size();
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void addAspectScore(double aspectScore,String aspectName,String traitName) {
        this.totaleAspectScore += aspectScore;
        this.aspectScores.add(aspectScore);
        this.aspectName=aspectName;
        this.traitName=traitName;
        this.flag=2;
        this.aspectMean = totaleAspectScore / getNoOfQuestionAnswered();
        double t = 0;
        for (double d : this.aspectScores) {
            t += Math.pow(d - this.aspectMean, 2);
        }
         t = t / (getNoOfQuestionAnswered() );
//        t = t * t;
       // t = t / (getNoOfQuestionAnswered() - 1);
        //this.aspectStdDeviation = Math.pow(t, 0.5);
        this.aspectStdDeviation = Math.sqrt(t);

    }

    public List<Double> getAspectScores() {
        return aspectScores;
    }

    public Double getAspectMean() {
        if (getNoOfQuestionAnswered() == 0) {
            return null;
        } else {
            return aspectMean;
        }
    }

    public Double getAspectStdDeviation() {
        if (getNoOfQuestionAnswered() == 0) {
            return null;
        } else {
            return aspectStdDeviation;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.aspectId;
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
        final AspectScore other = (AspectScore) obj;
        if (this.aspectId != other.aspectId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AspectScore{" + "aspectId=" + aspectId + ", totaleAspectScore=" + totaleAspectScore + ", aspectScores=" + aspectScores + ", aspectMean=" + aspectMean + ", aspectStdDeviation=" + aspectStdDeviation  + ", aspectName=" + aspectName +  ", traitName=" + traitName  +  ", flag=" + flag+'}';
    }

}
