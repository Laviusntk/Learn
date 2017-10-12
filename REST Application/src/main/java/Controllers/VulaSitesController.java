/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Auth;
import Models.UploadFile;
import Models.UploadResponse;
import Utilities.FedoraSession;
import Utilities.VulaSession;
import com.google.gson.Gson;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import responses.Resource;
import responses.ResourceCollection;
import responses.Site;
import responses.SiteCollection;
import responses.User;

/**
 *
 * @author learnproject
 */
@RestController
public class VulaSitesController {
    private Auth auth;
    @RequestMapping("/sites")
    public Site[] getSites(@RequestHeader("data") String _auth) throws Exception {
        Gson gson = new Gson();
        this.auth = gson.fromJson(_auth, Auth.class);
        VulaSession vsession = new VulaSession(this.auth.getUsername(), this.auth.getPassword());
        User user = vsession.request.getProfile();
        return vsession.request.getSites().getSite_collection();
    }
    
}
