/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.Utilities;

import learnapplication.Client.FedoraClient;
import learnapplication.Client.VulaClient;
import learnapplication.requests.FedoraRequestHandler;
import learnapplication.requests.VulaRequestHandler;
import learnapplication.responses.User;

/**
 *
 * @author learnproject
 */
public class FedoraSession {
    private FedoraClient client;
    public FedoraRequestHandler request;  
    private final String FEDORA_BASE_URL = "http://localhost:8080/fedora";
    
    public FedoraSession(String user, String secret, User _user, VulaClient vclient) throws Exception{
        client = new FedoraClient(FEDORA_BASE_URL, user, secret, vclient);
        client.authenticate("/describe");        
        request = new FedoraRequestHandler(client, _user);
    }     
    
    public void renewSession() throws Exception{
        client.authenticate("/describe");       
    }    
}
