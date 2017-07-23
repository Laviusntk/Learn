/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.models;

/**
 *
 * @author learnproject
 */
public class Credentials{
    private String _username;
    private String _password;

    public Credentials(String email, String password) {
        this._username = email;
        this._password = password;
    }

    public String getUsername() {
        return _username;
    }

    public String getPassword() {
        return _password;
    }
    
    
}
