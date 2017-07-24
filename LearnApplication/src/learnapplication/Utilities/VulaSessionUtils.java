/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.Utilities;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import learnapplication.models.Credentials;
import learnapplication.responses.User;
import sun.misc.BASE64Encoder;

/**
 * @author learnproject
 */
public class VulaSessionUtils extends SessionUtils {

    private final String USERNAME_KEY = "_username";
    private final String PASSWORD_KEY = "_password";

    public VulaSessionUtils(String username, String password) {
        super("/session", "https://vula.uct.ac.za/direct");
        super.setCredntials(new Credentials(username, password));
    }

    @Override
    public void create() throws Exception{
        
        Map<String, String> map = new LinkedHashMap<>();
        map.put(USERNAME_KEY, super.credntials.getUsername());
        map.put(PASSWORD_KEY, super.credntials.getPassword());
        
        URL url = new URL(super.getSessionUrl());

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : map.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        System.out.println("Connecting to Sakai.....");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Authorization", "Basic "+(new BASE64Encoder()).encode((super.credntials.getUsername()+":"+super.credntials.getPassword()).getBytes()));        
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Map<String, List<String>> response = conn.getHeaderFields();

        for (Map.Entry<String, List<String>> entry : response.entrySet()) {
            String tmp = entry.getKey() + "";
            if (tmp.trim().equals("Set-Cookie")) {
                String COOKIE = entry.getValue().toString() + "";
                super.SESSION_ID =COOKIE.replace("[", "").replace("]", "");
            }
        }
        
        // "Post data send ... waiting for reply");
        int code = conn.getResponseCode(); // 200 = HTTP_OK
        System.out.println("Response    (Code):" + code);
        System.out.println("Response (Message):" + conn.getResponseMessage());
        if(code != 200 && code != 201)
            throw new Exception("Failed to create session");
        else
         System.out.println("+-------------------------------------------------------------------------------------------+");
         System.out.println("| Cookie : " + super.SESSION_ID + " |");
         System.out.println("+-------------------------------------------------------------------------------------------+");           
    }

    @Override
    public void create(String _sessionID) {
        super.SESSION_ID = _sessionID;
    }
    
}
