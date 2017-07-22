/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.Client;

import retrofit2.Retrofit;
import retrofit2.GsonConverterFactory;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import org.apache.http.util.TextUtils;

public class RetrofitClient {

    public static String API_BASE_URL = "https://vula.uct.ac.za/direct/";
    private static Retrofit retrofit = null;
    private static OkHttpClient client = new OkHttpClient();

    public RetrofitClient(String username, String password) {
        this.client = new OkHttpClient();
        this.client.newBuilder().addInterceptor(new BasicAuthInterceptor("mtllav001", "3712lav123@@@NTKGeekSaw"));
    }

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
