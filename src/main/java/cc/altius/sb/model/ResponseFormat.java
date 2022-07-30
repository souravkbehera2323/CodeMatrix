/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model;

import java.io.Serializable;

/**
 *
 * @author yogesh
 */
public class ResponseFormat implements Serializable {

    private String status;
    private String failedReason;
    private String parameter;
    private int failedValue;

    public int getFailedValue() {
        return failedValue;
    }

    public void setFailedValue(int failedValue) {
        this.failedValue = failedValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "ResponseFormat{" + "status=" + status + ", failedReason=" + failedReason + ", parameter=" + parameter + '}';
    }
}
