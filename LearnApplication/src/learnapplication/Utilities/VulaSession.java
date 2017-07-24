/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.Utilities;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import learnapplication.Client.VulaClient;
import learnapplication.models.Credentials;
import learnapplication.requests.VulaRequestHandler;
import learnapplication.responses.User;
import sun.misc.BASE64Encoder;

/**
 * @author learnproject
 */
public class VulaSession{
    private VulaClient client;
    public VulaRequestHandler request;
    private final String VULA_BASE_URL = "https://vula.uct.ac.za/direct";    

    public VulaSession(String user, String secret) throws Exception{
        client = new VulaClient(VULA_BASE_URL, user, secret);
        client.authenticate("/session");        
        request = new VulaRequestHandler(client);
    }     
    
    public void renewSession() throws Exception{
        client.authenticate("/session");
        request = new VulaRequestHandler(client);        
    }
}
