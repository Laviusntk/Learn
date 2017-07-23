/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.requests;

import java.util.Map;
import learnapplication.Utilities.VulaApiUtils;
import learnapplication.models.Auth;
import learnapplication.models.Credentials;
import learnapplication.responses.User;
import learnapplication.services.VulaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author learnproject
 */
public class GetUserDetails {
    
    private  User user = null;    
    private boolean done = false;   
    public User getUser(VulaService service, Map map){
        service.getUserProfile(map).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    user = response.body();
                    if(user == null)
                        System.out.println("No user found");
                    else
                        System.out.println("Got user :"+ user.toString());
                }else {
                    int statusCode  = response.code();
                    System.out.println("Error occured in retreving user details");
                    System.out.println("Got the following response : " + statusCode);                    
                }
            }
 
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("error loading from API");
            }
        }); 
        
        return user;
    }
}
