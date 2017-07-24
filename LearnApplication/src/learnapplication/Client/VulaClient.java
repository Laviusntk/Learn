/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.Client;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 *
 * @author learnproject
 */
public class VulaClient extends Client {

    private final String USERNAME_KEY = "_username";
    private final String PASSWORD_KEY = "_password";

    public VulaClient(String BASE_URL, String USERNAME, String PASSWORD) {
        super(BASE_URL, USERNAME, PASSWORD);
    }

    @Override
    public String request(String _path, String Query, String method) throws Exception {
        // curl_init and url
        URL url = new URL(super.BASE_URL + _path + Query);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //  CURLOPT_POST
        con.setRequestMethod("GET");
        
        con.setInstanceFollowRedirects(true);

        con.setRequestProperty("Cookie", super.SESSION);
        con.setDoInput(true);


        // "Post data send ... waiting for reply");
        int code = con.getResponseCode(); // 200 = HTTP_OK
        System.out.println("Response    (Code):" + code);
        System.out.println("Response (Message):" + con.getResponseMessage());

        // read the response
        DataInputStream input = new DataInputStream(con.getInputStream());
        int c;
        StringBuilder resultBuf = new StringBuilder();
        while ((c = input.read()) != -1) {
            resultBuf.append((char) c);
        }
        input.close();
        String results = resultBuf.toString();
//        System.out.println(results);
        return results;
    }

    @Override
    public void generateCrentials() throws Exception {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(USERNAME_KEY, super.USERNAME);
        map.put(PASSWORD_KEY, super.PASSWORD);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : map.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        super.credentials = postData.toString();
    }

}
