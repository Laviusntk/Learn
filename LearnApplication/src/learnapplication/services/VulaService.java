/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.services;
import static javafx.scene.AccessibleAttribute.HEADER;
import learnapplication.responses.User;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
/**
 *
 * @author learnproject
 */
public interface VulaService {
//   @Headers("Cookie: JSESSIONID=C2CDE938A24B4B51F6EFD61721EDB677; SignOnDefault=MTLLAV001; PS_DEVICEFEATURES=width:1366 height:768 pixelratio:1 touch:0 geolocation:1 websockets:1 webworkers:1 datepicker:0 dtpicker:0 timepicker:0 dnd:1 sessionstorage:1 localstorage:1 history:1 canvas:1 svg:1 postmessage:1 hc:0 maf:0; _ga=GA1.3.773602981.1500579399; JSESSIONID=9ae0d410-0781-48b6-8831-3cf2521e4ba5.vula5a; pasystem_timezone_ok=true")
   @POST("user/ae89c591-ca73-4f79-95a7-13b948ea2e94.json")
   Call<User> getUserProfile(@Field("body") String body);
   
//   @POST(LOGIN)
//   Call<ResponseBody> loginWithCredentials(@Body LoginCredentials data);   
}
