/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.Utilities;

import learnapplication.responses.User;

/**
 *
 * @author learnproject
 */
public class VulaSessionUtils extends SessionUtils{
    private final String USERNAME_KEY = "_username";
    private final String PASSWORD_KEY = "_password";
    
    public VulaSessionUtils() {
        super("/session", "https://vula.uct.ac.za/direct");
    }
    
    
    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSessionID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
