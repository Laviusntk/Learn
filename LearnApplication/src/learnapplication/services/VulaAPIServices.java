package learnapplication.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import sun.misc.BASE64Encoder;
import java.io.*;
import java.net.*;
import java.util.*;


import javax.xml.parsers.*;
import org.w3c.dom.*;
/**
 * @author lavius nkateko motileng : mtllav001 2017
 */
public class VulaAPIServices {
    /*
    * Stores the user's current active site id. @TO DO
    */
    private String currentSiteID = "<site-id>";
    /*
    * Stores the   site id. @TO DO:
    */
    private String siteID = "<site-id>";    
    /*
    * Retrieve user's profile.
    */
    private String USER_PROFILE_SERVICE = "https://vula.uct.ac.za/direct/user/current.json"; 
    /*
    * Check if user a member of a given site.
    * Get site ID, search it in the list of memberships.
    */
    private String MEMBERSHIP_SERVICE = "https://vula.uct.ac.za/direct/membership.json";
    /*
    * Get the list of all assignments and automatically upload archive them.
    * Get the site ID of the assignments, use it to get the details of the site.
    * When upload the assignment details to the repository together with the 
    * details of the course it belongs to.
    */
    private String ASSIGNMENTS_SERVICE = "https://vula.uct.ac.za/direct/assignment/my.json";
    /*
    * Get the list of all content/resources for a given site.
    */
    private String RESOURCES_SERVICE = "https://vula.uct.ac.za/direct/content/site/"+siteID+".json";
    /*
    * Get the list of all user's sites.
    */
    private String SITES_SERVICES = "https://vula.uct.ac.za/direct/site.json";
    
    private String sessionID;
    
    public VulaAPIServices() throws Exception{
        System.out.println(authenticate("mtllav001","*************", "http://localhost:8080/fedora/objects/cs101:8/datastreams/"));
    }
      
  public boolean authenticate(String _username, String _password, String service_url) throws Exception{
    System.out.println("Authenticating with Sakai Please wait.....");
    String username = _username;
    String password = _password;

    URL url = new URL(service_url);
    Map<String,Object> params = new LinkedHashMap<>();
    params.put("eid", username);
    params.put("pw", password);
    params.put("reply_to_thread", 10394);
    params.put("file", new File("dropbox.png"));
    StringBuilder postData = new StringBuilder();
    for (Map.Entry<String,Object> param : params.entrySet()) {
        if (postData.length() != 0) postData.append('&');
        postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
        postData.append('=');
        postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
    }

    byte[] postDataBytes = postData.toString().getBytes("UTF-8");
    System.out.println("Connecting to Sakai.....");
    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
    conn.setRequestProperty("Authorization", "Basic "+(new BASE64Encoder()).encode(("fedoraAdmin"+":"+"fedoraAdmin").getBytes()));    
    conn.setDoOutput(true);
    conn.getOutputStream().write(postDataBytes);

    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    StringBuilder sb = new StringBuilder();
    System.out.println("Connection complete.....");
    String line;
    while ((line = in.readLine()) != null) {
        sb.append(line);
    }
    in.close();    
    

    String response = sb.toString();
    response = response.replace(" ","").replace("<title>","").replace("</title>","").trim();
    System.out.println(response);

    if(response.equals("LoginRequired-Vula"))
      return false;
    else
      return true;
}
}
