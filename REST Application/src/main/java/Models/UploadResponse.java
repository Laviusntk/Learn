/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author learnproject
 */
public class UploadResponse {
   public int code;
   public String message;

    public UploadResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "UploadResponse:{" + "code:" + code + ", message:" + message + '}';
    }
   
}
