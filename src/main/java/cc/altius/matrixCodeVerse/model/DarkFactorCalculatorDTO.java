/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import java.io.Serializable;

/**
 *
 * @author altius
 */
public class DarkFactorCalculatorDTO  implements Serializable{
    private int takerId;
    private double questionOptionValue;

    public int getTakerId() {
        return takerId;
    }

    public void setTakerId(int takerId) {
        this.takerId = takerId;
    }

    public double getQuestionOptionValue() {
        return questionOptionValue;
    }

    public void setQuestionOptionValue(double questionOptionValue) {
        this.questionOptionValue = questionOptionValue;
    }

    @Override
    public String toString() {
        return "DarkFactorCalculatorDTO{" + "takerId=" + takerId + ", questionOptionValue=" + questionOptionValue + '}';
    }
    
    
    
}
