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
import learnapplication.Client.VulaClient;
import learnapplication.Utilities.FedoraSession;
import learnapplication.requests.VulaRequestHandler;
import learnapplication.responses.Collection;
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
    
    public LearnApplication(String _usr, String _pwd) throws Exception {
        this.usr = _usr;
        this.pwd = _pwd;
        vsession = new VulaSession(usr, pwd);
        fsession = new FedoraSession("fedoraAdmin", "fedoraAdmin", vsession.request.getProfile(), vsession.client);
    }

    public static void main(String[] args) throws Exception {
        LearnApplication learn = new LearnApplication("mtllav001", "3712lav123@@@NTKGeekSaw");
        User user = learn.vsession.request.getProfile();
        Collection collection = learn.vsession.request.getResources();
        System.out.println(collection);
        
        //System.out.println(user);
//        String objectResponse = learn.fsession.request.createObject("Big Data");
//        String pid = objectResponse;
//        System.out.println("PID : " + pid);
//        
//        String location = "https://vula.uct.ac.za/access/content/group/021b5a33-2bb0-485f-b753-00754a2be47d/CSHonoursProjects2017v4.pdf"; 
//        String datastreamResponse = learn.fsession.request.createDataStream(pid, user.getLastName(), "teting upload method", location);
//        String datastream = datastreamResponse;
//        System.out.println("Datastream : " + datastream);
        
        //String results = learn.fsession.request.browseObjects("Big Data","pid~"+pid+":*");
        //System.out.println(results);
    }

}
