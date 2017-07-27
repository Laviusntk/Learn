/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.requests;

import com.google.gson.Gson;
import learnapplication.Client.VulaClient;
import learnapplication.responses.ResourceCollection;
import learnapplication.responses.Resource;
import learnapplication.responses.Site;
import learnapplication.responses.SiteCollection;
import learnapplication.responses.User;

/**
 *
 * @author learnproject
 */
public class VulaRequestHandler {    
    private Gson gson;
    private VulaClient client;
    
    public VulaRequestHandler(VulaClient _client) {
        this.gson = new Gson();
        this.client = _client;
    }
        
    public final User getProfile() throws Exception{
        String json_user = this.client.request("/user/current.json", "","");
        return gson.fromJson(json_user, User.class); 
    }
    
    public final ResourceCollection getResources(String siteID) throws Exception{
        String json_resources = this.client.request("/content/site/"+siteID+".json", "", "");
        return gson.fromJson(json_resources, ResourceCollection.class);
    }
    
    public final SiteCollection getSites() throws Exception{
        String json_sites = this.client.request("/site.json", "", "");
        return gson.fromJson(json_sites, SiteCollection.class);
    }

    
}
