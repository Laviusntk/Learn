/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.requests;

import com.google.gson.Gson;
import learnapplication.Client.VulaClient;
import learnapplication.responses.User;

/**
 *
 * @author learnproject
 */
public class VulaRequestHandler {
    private final String SESSION_PATH = "/session";
    private final String PROFILE_PATH = "/user/current.json";
    
    private Gson gson;
    private VulaClient client;
    
    public VulaRequestHandler(VulaClient _client) {
        this.gson = new Gson();
        this.client = _client;
    }
        
    public final User getProfile() throws Exception{
        String json_user = this.client.request(this.PROFILE_PATH, "","");
        return gson.fromJson(json_user, User.class); 
    }
    
}
