/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication;

import com.google.gson.Gson;
import static com.sun.javafx.css.SizeUnits.S;
import learnapplication.services.FedoraAPIService;
import java.io.*;
import java.net.*;
import java.util.*;
import learnapplication.Client.ServiceGenerator;
import learnapplication.Utilities.VulaApiUtils;
import learnapplication.Utilities.VulaSessionUtils;
import learnapplication.models.Credentials;
import learnapplication.requests.GetUserDetails;
import learnapplication.responses.User;
import learnapplication.services.VulaAPIServices;
import learnapplication.services.VulaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sun.misc.BASE64Encoder;

/**
 *
 * @author nkateko
 */
public class LearnApplication {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter student number : ");
        String usr = input.next();
        System.out.print("Please enter Password : ");
        String pwd = input.next();
        
        VulaSessionUtils session = new VulaSessionUtils(usr,pwd);
        
        
        
        //VulaService vservice = VulaApiUtils.getVulaService("");
        //VulaService s = ServiceGenerator.createService(VulaService.class, "mtllav001", "3712lav123@@@NTKGeekSaw");
        //s.getUserProfile();
        //new GetUserDetails().getUser(vservice," ");
    }

    
}
