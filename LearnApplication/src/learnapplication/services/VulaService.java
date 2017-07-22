/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.services;
import learnapplication.responses.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 *
 * @author learnproject
 */
public interface VulaService {
   @GET("user/current.json")
   Call<User> getUserProfile();
}
