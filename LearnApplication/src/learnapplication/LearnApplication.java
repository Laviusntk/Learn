/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication;

import java.util.*;
import learnapplication.Utilities.VulaSession;
import sun.misc.BASE64Encoder;
import com.google.gson.Gson;
import learnapplication.Client.VulaClient;
import learnapplication.requests.VulaRequestHandler;
import learnapplication.responses.User;
import learnapplication.services.VulaService;

/**
 *
 * @author nkateko
 */
public class LearnApplication {
    private String usr;
    private String pwd;
    public  VulaSession vsession;
    
    public LearnApplication(String _usr, String _pwd) throws Exception {
        this.usr = _usr;
        this.pwd = _pwd;
        vsession = new VulaSession(usr,pwd);        
    }
       
    public static void main(String[] args) throws Exception { 
        LearnApplication learn = new LearnApplication("username","password");
        User user = learn.vsession.request.getProfile();
        System.out.println(user);
    }

}
