/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication;

//import java.util.*;
import learnapplication.Utilities.VulaSession;
import sun.misc.BASE64Encoder;
import com.google.gson.Gson;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import learnapplication.Client.VulaClient;
import learnapplication.Utilities.FedoraSession;
import learnapplication.requests.VulaRequestHandler;
import learnapplication.responses.Resource;
import learnapplication.responses.ResourceCollection;
import learnapplication.responses.Site;
import learnapplication.responses.SiteCollection;
import learnapplication.responses.User;

/**
 *
 * @author nkateko
 */
public class LearnApplication {

    private String usr;
    private String pwd;
    public VulaSession vsession;
    public FedoraSession fsession;
    public User user;

    public LearnApplication(String _usr, String _pwd) throws Exception {
        this.usr = _usr;
        this.pwd = _pwd;
        this.vsession = new VulaSession(usr, pwd);
        this.fsession = new FedoraSession("fedoraAdmin", "fedoraAdmin", vsession.request.getProfile(), vsession.client);
        this.user = vsession.request.getProfile();
        SiteCollection sites = this.vsession.request.getSites();

        ArrayList<Site> my_sites = new ArrayList<>();

        for (int i = 0; i < sites.getSite_collection().length; i++) {
            Site site = sites.getSite_collection()[i];
            ResourceCollection content = this.vsession.request.getResources(site.getId());
            site.mycontent = content.getResourceByID(this.user.getId());
            my_sites.add(site);
        }

        this.user.sites = my_sites;
    }

    public String ingest(String ObjectName, String fileURL, String logMSG) {
        try {
            String objectResponse = this.fsession.request.createObject(ObjectName);
            String pid = objectResponse;
            String datastreamResponse = this.fsession.request.createDataStream(pid, this.user.getLastName(), logMSG, fileURL);
            String datastream = datastreamResponse;
            if (datastream.trim().equals("201")) {
                return "Object " + ObjectName + " created successfully";
            } else {
                return "Error uploading file";
            }
        } catch (Exception e) {
            return "Error uploading file";
        }

    }

    
    public String browse(String SearchTerm) {
        try {
            return this.fsession.request.browseObjects(SearchTerm, "pid~" + this.user.getDisplayId() + ":*");
            //"pid~"+pid+":*"
        } catch (Exception e) {
            return "Error retreiving objects";
        }
    }
    
    public String synchronize() {
        try {
            String objectResponse = this.fsession.request.createObject(user.getEid());
            String pid = objectResponse;
            System.out.println("PID : " + pid);

            ArrayList<Site> usersites = this.user.sites;

            for (int i = 0; i < usersites.size(); i++) {
                Site site = usersites.get(i);
                for (int j = 0; j < site.mycontent.size(); j++) {
                    Resource resource = site.mycontent.get(j);
                    String location = resource.getUrl();
                    //System.out.println("Location url : " + location);
                    if (!resource.getType().equals("collection")) {
                        String datastreamResponse = this.fsession.request.createDataStream(pid, resource.getTitle(), resource.toString(), location);
                        String datastream = datastreamResponse;
                        System.out.println("Datastream : " + datastream + "\n\n\n");
                    }
                }
            }
            return "Synchronization successfull";
        } catch (Exception e) {
            return "Sycnhronization failed";
        }
    }

    public static void main(String[] args) throws Exception {
        LearnApplication learn = new LearnApplication("", "");
        System.out.println(learn.synchronize());
//        System.out.println(learn.browse("mtllav001"));
    }

}
