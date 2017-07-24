/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.Client;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import sun.misc.BASE64Encoder;

/**
 * @author learnproject
 */
public abstract class Client {

    protected final String BASE_URL;
    protected String SESSION;
    protected final String USERNAME;
    protected final String PASSWORD;
    protected String credentials;
    public final String METHOD_POST = "POST";
    public final String METHOD_GET = "GET";
    
    public Client(String BASE_URL, String USERNAME, String PASSWORD) {
        this.BASE_URL = BASE_URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public void authenticate(String _path) throws Exception {
        generateCrentials();
        
        URL url = new URL(BASE_URL + _path);

        byte[] postDataBytes = this.credentials.toString().getBytes("UTF-8");

        System.out.println("Connecting to API.....");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Authorization", this.credentials);
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Map<String, List<String>> response = conn.getHeaderFields();

        for (Map.Entry<String, List<String>> entry : response.entrySet()) {
            String tmp = entry.getKey() + "";
            if (tmp.trim().equals("Set-Cookie")) {
                String COOKIE = entry.getValue().toString() + "";
                this.SESSION = COOKIE.replace("[", "").replace("]", "");
            }
        }

        int code = conn.getResponseCode(); 
        System.out.println("Response    (Code):" + code);
        System.out.println("Response (Message):" + conn.getResponseMessage());

        if (code != 200 && code != 201) {
            throw new Exception("Failed to create session");
        } else {
            System.out.println("+-------------------------------------------------------------------------------------------+");
            System.out.println("| Cookie : " + this.SESSION + " |");
            System.out.println("+-------------------------------------------------------------------------------------------+");
        }
    }

    public abstract String request(String path, String Query, String method) throws Exception;

    public abstract void generateCrentials() throws Exception; // use this method to update the global credentails variable
}
