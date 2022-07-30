/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.exception;

/**
 *
 * @author akil
 */
public class DataStoreException extends Exception {

    private String mistake;

    public DataStoreException() {
        super();
        this.mistake = "Unkown error occurred";
    }

    public DataStoreException(String mistake) {
        super(mistake);
        this.mistake = mistake;
    }

    public String getMistake() {
        return mistake;
    }
}
