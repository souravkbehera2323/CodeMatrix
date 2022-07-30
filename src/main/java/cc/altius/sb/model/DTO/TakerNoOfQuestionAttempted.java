/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.DTO;

import java.util.Date;

/**
 *
 * @author yogesh
 */
public class TakerNoOfQuestionAttempted {

    private int takerId;
    private String firstName;
    private String lastName;
    private String noOfQuestionAttempted;
    private Date testStarted;
    private Date testEnded;
    private String referredBy;
    private String email;
    private String phone;
    private String zipCode;
    private boolean active;

    public int getTakerId() {
        return takerId;
    }

    public void setTakerId(int takerId) {
        this.takerId = takerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNoOfQuestionAttempted() {
        return noOfQuestionAttempted;
    }

    public void setNoOfQuestionAttempted(String noOfQuestionAttempted) {
        this.noOfQuestionAttempted = noOfQuestionAttempted;
    }

    public Date getTestStarted() {
        return testStarted;
    }

    public void setTestStarted(Date testStarted) {
        this.testStarted = testStarted;
    }

    public Date getTestEnded() {
        return testEnded;
    }

    public void setTestEnded(Date testEnded) {
        this.testEnded = testEnded;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

//    @Override
//    public String toString() {
//        return "TakerNoOfQuestionAttempted{" + "firstName=" + firstName + ", lastName=" + lastName + ", noOfQuestionAttempted=" + noOfQuestionAttempted + ", testStarted=" + testStarted + ", testEnded=" + testEnded + ", referredBy=" + referredBy + '}';
//    }
    @Override
    public String toString() {
        return "TakerNoOfQuestionAttempted{" + "takerId=" + takerId + ", firstName=" + firstName + ", lastName=" + lastName + ", noOfQuestionAttempted=" + noOfQuestionAttempted + ", testStarted=" + testStarted + ", testEnded=" + testEnded + ", referredBy=" + referredBy + ", email=" + email + ", phone=" + phone + ", zipCode=" + zipCode + ", active=" + active + '}';
    }

}
