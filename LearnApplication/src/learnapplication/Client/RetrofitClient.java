/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.Client;

import java.io.IOException;
import retrofit2.Retrofit;
import retrofit2.GsonConverterFactory;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.util.TextUtils;
import sun.misc.BASE64Encoder;

public class RetrofitClient {

    public static String API_BASE_URL = "https://vula.uct.ac.za/direct/";
    private static Retrofit retrofit = null;
    private static OkHttpClient client;
    private OkHttpClient.Builder httpClient;
    
    public RetrofitClient(String username, String password) {
//        this.client = new OkHttpClient();
//        this.client.newBuilder().addInterceptor(new BasicAuthInterceptor(username, password)).build();
        this.httpClient = new OkHttpClient.Builder();
        this.httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                String credentials = username + ":" + password; 
                Request request = original.newBuilder()
                        .header("User-Agent", "Learn")
                        .header("Accept", "application/json")
//                        .header("Authorization", "Basic "+(new BASE64Encoder()).encode(credentials.getBytes()) ) //Credentials.basic(username, password)
                        .addHeader("Cookie","JSESSIONID=e2ddb6eb-91f4-4696-a916-7283dae585e1.vula7b; Path=/; HttpOnly")
                        //.method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        client = httpClient.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }        
    

    
    public static Retrofit getClient(){
        return retrofit;
    }

//    public static Retrofit getClient(String baseUrl) {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(baseUrl)
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }

}
