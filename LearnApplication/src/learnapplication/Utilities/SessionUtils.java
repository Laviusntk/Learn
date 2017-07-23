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
public abstract class SessionUtils {
    
    protected String SESSION_ID;
    protected String SESSION_SUB_URL;
    protected String BASE_URL;
    private static User user;
    
    public SessionUtils(String SESSION_SUB_URL, String BASE_URL) {
        this.SESSION_SUB_URL = SESSION_SUB_URL;
        this.BASE_URL = BASE_URL;
    }
    
    public abstract void create();
    public abstract String getSessionID();

    public static void setUser(User user) {
        SessionUtils.user = user;
    }

    public static User getUser() {
        return user;
    }  
    
}
