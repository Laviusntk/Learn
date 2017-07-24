/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication;

import java.util.*;
import learnapplication.Utilities.VulaSessionUtils;
import sun.misc.BASE64Encoder;
import com.google.gson.Gson;
import learnapplication.Client.VulaClient;
import learnapplication.responses.User;
import learnapplication.services.VulaService;

/**
 *
 * @author nkateko
 */
public class LearnApplication {
    private static final String VULA_BASE_URL = "https://vula.uct.ac.za/direct";
    
    public static void main(String[] args) throws Exception {
        String usr = "username";
        String pwd = "password";
        
        VulaClient client = new VulaClient(VULA_BASE_URL, usr, pwd);
        client.authenticate("/session");
        String json_user = client.request("/user/current.json", "","");
        //System.out.println(json_user);
        
        Gson gson = new Gson();
        
        User user = gson.fromJson(json_user, User.class);
        System.out.println(user.toString());
        
    }

}
