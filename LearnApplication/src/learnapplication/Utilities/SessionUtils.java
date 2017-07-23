/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.Utilities;

/**
 *
 * @author learnproject
 */
public abstract class SessionUtils {
    
    protected String SESSION_ID;
    protected String SESSION_SUB_URL;
    
    public abstract void create();
    public abstract String getSessionID();    
}
