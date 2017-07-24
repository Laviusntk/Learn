/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.services;
import java.util.Map;
import static javafx.scene.AccessibleAttribute.HEADER;
import learnapplication.models.Credentials;
import learnapplication.responses.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
/**
 *
 * @author learnproject
 */
public interface VulaService {
   //@Headers("Content-Type: application/json")
   @GET("user/ae89c591-ca73-4f79-95a7-13b948ea2e94.json")
   Call<User> getUserProfile();
   
//   @POST(LOGIN)
//   Call<ResponseBody> loginWithCredentials(@Body LoginCredentials data);   
}
