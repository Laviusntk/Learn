/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.Utilities;

import learnapplication.Client.RetrofitClient;
import learnapplication.services.VulaService;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.http.Headers;

/**
 *
 * @author learnproject
 */
public class VulaApiUtils {

    public static String API_BASE_URL = "https://vula.uct.ac.za/direct/";

    public static VulaService getVulaService() {
        RetrofitClient client = new RetrofitClient();
        return client.getClient().create(VulaService.class);
    }

}
