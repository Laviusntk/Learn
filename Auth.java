import java.io.*;
import java.net.*;
import java.util.*;


import javax.xml.parsers.*;
import org.w3c.dom.*;

class Auth {
  private String username;
  private String password;

  public boolean authenticate(String _username, String _password) throws Exception{
    System.out.println("Authenticating with Sakai Please wait.....");
    username = _username;
    password = _password;

    URL url = new URL("https://vula.uct.ac.za/authn/login");
    Map<String,Object> params = new LinkedHashMap<>();
    params.put("eid", username);
    params.put("pw", password);
    params.put("reply_to_thread", 10394);

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
    conn.setDoOutput(true);
    conn.getOutputStream().write(postDataBytes);

    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    StringBuilder sb = new StringBuilder();
    System.out.println("Connection complete.....");
    for(int i =0; i < 150; i++){
      int c = in.read();
      if(i > 100)
        sb.append((char)c);
    }

    String response = sb.toString();
    response = response.replace(" ","").replace("<title>","").replace("</title>","").trim();
    System.out.println(response);

    if(response.equals("LoginRequired-Vula"))
      return false;
    else
      return true;
  }
}
