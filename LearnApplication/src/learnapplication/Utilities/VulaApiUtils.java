/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.Utilities;

import learnapplication.Client.RetrofitClient;
import learnapplication.services.VulaService;
import okhttp3.Credentials;
import retrofit2.http.Headers;

/**
 *
 * @author learnproject
 */
public class VulaApiUtils {
    public static final String BASE_URL = "https://vula.uct.ac.za/direct/";
    final String CR = "Authorization : " + Credentials.basic("mtllav001", "3712lav123@@@NTKGeekSaw");
    
    public static VulaService getVulaService() {
        return RetrofitClient.getClient(BASE_URL).create(VulaService.class);
    }    
    
    
}
