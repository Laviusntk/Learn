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
public class Session {
    public AuthStatus vulasession;
    public AuthStatus fedoraSession;

    public Session(AuthStatus vulasession, AuthStatus fedoraSession) {
        this.vulasession = vulasession;
        this.fedoraSession = fedoraSession;
    }
    
}
