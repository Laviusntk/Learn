/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Client.FedoraClient;
import Client.VulaClient;
import requests.FedoraRequestHandler;
import requests.VulaRequestHandler;
import responses.User;

/**
 *
 * @author learnproject
 */
public class FedoraSession {
    private FedoraClient client;
    public FedoraRequestHandler request;  
    private final String FEDORA_BASE_URL = "http://localhost:8085/fedora";
    
    public FedoraSession(String user, String secret, User _user, VulaClient _client) throws Exception{
        client = new FedoraClient(FEDORA_BASE_URL, user, secret, _client);
        client.authenticate("/describe");        
        request = new FedoraRequestHandler(client, _user);
    }     
    
    public void renewSession() throws Exception{
        client.authenticate("/describe");       
    }    
}
