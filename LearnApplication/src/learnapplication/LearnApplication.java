/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication;
import java.util.*;
import learnapplication.Utilities.VulaSessionUtils;
import sun.misc.BASE64Encoder;
import com.google.gson.Gson;
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
        //s.getUserProfile();
        //new GetUserDetails().getUser(vservice," ");
    }

    
}
