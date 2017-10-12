/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author nkateko
 */
import Models.Auth;
import Models.SiteContent;
import Utilities.VulaSession;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import responses.Assignment;
import responses.Resource;
import responses.Site;
import responses.User;

@RestController
public class SitesResourcesController {

    private Auth auth;

    @RequestMapping("/site/resource")
    public SiteContent getSites(@RequestHeader("data") String _auth, @RequestParam(value = "site_id", defaultValue = "none") String site_id) throws Exception {
        Gson gson = new Gson();
        this.auth = gson.fromJson(_auth, Auth.class);
        VulaSession vsession = new VulaSession(this.auth.getUsername(), this.auth.getPassword());
        Resource[] resources = vsession.request.getResources(site_id).getContent_collection();
        Assignment[] assignments = vsession.request.getAssignmentsBySiteId(site_id).getAssignment_collection();
        return new SiteContent(resources, assignments);
    }
}
