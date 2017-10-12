package Controllers;

import Client.VulaClient;
import Models.Auth;
import Models.AuthStatus;
import Utilities.VulaSession;
import com.google.gson.Gson;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import responses.AssignmentCollection;
import responses.ResourceCollection;
import responses.Site;
import responses.SiteCollection;
import responses.User;

/**
 *
 * @author learnproject
 */
@RestController
public class VulaContentController {

    private final AtomicLong counter = new AtomicLong();
    protected static SecureRandom random = new SecureRandom();
    private final String USERNAME_KEY = "_username";
    private final String PASSWORD_KEY = "_password";
    private final String BASE_URL = "https://vula.uct.ac.za/direct";
    private Auth auth;

    @RequestMapping("/content/vula")
    public ArrayList<Site> login(@RequestHeader("data") String _auth) throws Exception {
        Gson gson = new Gson();
        this.auth = gson.fromJson(_auth, Auth.class);
        VulaSession vsession = new VulaSession(this.auth.getUsername(), this.auth.getPassword());
        User user = vsession.request.getProfile();
        SiteCollection sites = vsession.request.getSites();

        ArrayList<Site> my_sites = new ArrayList<>();

        for (int i = 0; i < sites.getSite_collection().length; i++) {
            Site site = sites.getSite_collection()[i];
            ResourceCollection content = vsession.request.getResources(site.getId());
            site.mycontent = content.getContent_collection();
            AssignmentCollection assignments = vsession.request.getAssignmentsBySiteId(site.getId());
            site.assignments = assignments.getAssignment_collection();
            my_sites.add(site);
        }
        
        
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Now sending vula content data..");
        System.out.println("------------------------------------------------------------------------");
        return my_sites;
    }

}
