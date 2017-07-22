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
import java.util.Scanner;
import static javafx.scene.input.KeyCode.S;
import static javax.swing.text.html.HTML.Tag.S;
import learnapplication.Utilities.VulaApiUtils;
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
    private static VulaService vService;
    public static void main(String[] args) throws Exception {
//        FedoraAPIService client = new FedoraAPIService();
//        
//        Scanner input = new Scanner(System.in);
//        System.out.print("Please enter file name: ");
//        File snd_file = new File(input.nextLine());
//        System.out.println("File exists : " +snd_file.exists());
//        System.out.println("File can be Read : " +snd_file.canRead());
//        System.out.println("File can be written : " +snd_file.canRead());
//        String file_name = snd_file.getName();
//        String mime = URLConnection.guessContentTypeFromName(file_name);
//
//
//        System.out.println("mimeType : "+mime);
//        String pid = client.createObject("candy","lovedare", "Lavius Nkateko Motileng");
//        System.out.println("PID : "+pid);
//        
//        String location = client.uploadFile(client.BASE_URL + "upload", snd_file);
//        System.out.println(location);
//        
//        client.createNewDataStream(pid, "newobject", mime, "uploading first version of the file",file_name, location);
        
        //client.gfindObjects("learn", "pid~learn:*");

        
//        Gson gson = new Gson();
//        int[] ints = {1, 2, 3, 4, 5};
//        String results = gson.toJson(new User()); 
//        System.out.println(results);
          //new VulaAPIServices();
    vService = VulaApiUtils.getVulaService();
//    vService.getUserProfile().enqueue(new Callback<User>() {
//    @Override
//    public void onResponse(Call<User> call, Response<User> response) {
// 
//        if(response.isSuccessful()) {
//            String username = response.body().getDisplayName();
//            System.out.println("Got name : "+username);
//        }else {
//            int statusCode  = response.code();
//            System.out.println("Error occured");            
//        }
//    }
// 
//    @Override
//    public void onFailure(Call<User> call, Throwable t) {
//        System.out.println("error loading from API");
//        }
//    });      
          
          
}
}
