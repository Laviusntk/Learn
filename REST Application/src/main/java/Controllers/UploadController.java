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
public class UploadController {

    private final AtomicLong counter = new AtomicLong();
    protected static SecureRandom random = new SecureRandom();
    private final String USERNAME_KEY = "_username";
    private final String PASSWORD_KEY = "_password";
    private final String BASE_URL = "https://vula.uct.ac.za/direct";
    public FedoraSession fsession;
    private UploadFile uploadfile;

    @RequestMapping("/ingest")
    public UploadResponse upload(@RequestHeader("data") String data) throws Exception {
        Gson gson = new Gson();

        this.uploadfile = gson.fromJson(data, UploadFile.class);
        System.out.println(this.uploadfile);
        VulaSession vsession = new VulaSession(this.uploadfile._username, this.uploadfile._password);

        this.fsession = new FedoraSession("fedoraAdmin", "fedoraAdmin", vsession.request.getProfile(), vsession.client);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Now sending upload response data..");
        System.out.println("------------------------------------------------------------------------");

        Resource tmp = this.uploadfile.resource;
        String resource_name = Character.isDigit(tmp.getTitle().charAt(0)) ? "A" + tmp.getTitle().replace(" ", "-") : tmp.getTitle().replace(" ", "-"); 
        return ingest(
                this.uploadfile.course_acronym.replace(" ", ""),
                this.uploadfile.pid,
                resource_name.replace("-", " "),
                tmp.getType(), 
                "new file from vula", 
                this.uploadfile.resource.getUrl()
        );
    }

    public UploadResponse ingest(String course, String _pid, String _resourcename, String _mimeType, String _logmsg, String fileUrl) {
        try {
            try{
                System.out.println("Course Name : " +course);
                String objectResponse = this.fsession.request.createObject(_pid,course);                
            }catch(Exception e){}

            String datastreamResponse = this.fsession.request.createDataStream(_resourcename, _mimeType, _pid, _resourcename, _logmsg, fileUrl);
            String datastream = datastreamResponse;
            if (datastream.trim().equals("201")) {
                return new UploadResponse(201, "Object " + _resourcename + " uploaded successfully.");
            } else {
                return new UploadResponse(Integer.parseInt(datastream), "Error uploading file : "+_resourcename);
            }
        } catch (Exception e) {
            return new UploadResponse(500, "Error uploading file : " + _resourcename);
        }
    }
}
