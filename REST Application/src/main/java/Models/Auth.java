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
public class Auth {
    private String _username;
    private String _password;

    public Auth(String _username, String _password) {
        this._username = _username;
        this._password = _password;
    }

    public String getUsername() {
        return _username;
    }

    public String getPassword() {
        return _password;
    }

    @Override
    public String toString() {
        return "Auth{" + "_username=" + _username + ", _password=" + _password + '}';
    }
     
}
