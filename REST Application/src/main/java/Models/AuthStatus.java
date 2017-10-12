/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

public class AuthStatus {
    private int Code;
    private String Message;

    public AuthStatus(int Code, String Message) {
        this.Code = Code;
        this.Message = Message;
    }
    
    public int getCode() {
        return Code;
    }

    public String getMessage() {
        return Message;
    }
    
}
