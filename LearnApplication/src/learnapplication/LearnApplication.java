/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication;

import com.google.gson.Gson;
import static com.sun.javafx.css.SizeUnits.S;
import learnapplication.services.FedoraAPIService;
import java.io.*;
import java.net.*;
import java.util.*;
import learnapplication.Client.ServiceGenerator;
import learnapplication.Utilities.VulaApiUtils;
import learnapplication.models.Auth;
import learnapplication.models.Credentials;
import learnapplication.requests.GetUserDetails;
import learnapplication.responses.User;
import learnapplication.services.VulaAPIServices;
import learnapplication.services.VulaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sun.misc.BASE64Encoder;

/**
 *
 * @author nkateko
 */
public class LearnApplication {

    public static String USERNAME_KEY = "_username";
    public static String PASSWORD_KEY = "_password";
    public static String PASSWORD_VALUE = "3712lav123@@@NTKGeekSaw";
    public static String USERNAME_VALUE = "mtllav001";
    public static String COOKIE = "none";
    public static String SESSION = "none";

    public static String VULA_BASE_URL = "https://vula.uct.ac.za/direct";

    public static void main(String[] args) throws Exception {

        Map<String, String> map = new LinkedHashMap<>();
        map.put(USERNAME_KEY, USERNAME_VALUE);
        map.put(PASSWORD_KEY, PASSWORD_VALUE);

        String LOGIN_REQUEST_URL = VULA_BASE_URL + "/session";

        URL url = new URL(LOGIN_REQUEST_URL);

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
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Map<String, List<String>> response = conn.getHeaderFields();

        System.out.println("Printing Response Header...\n");

        System.out.println("+-----------------------------------------------------------+");
        for (Map.Entry<String, List<String>> entry : response.entrySet()) {
            System.out.println("| " + entry.getKey() + " \t : \t" + entry.getValue());
            String tmp = entry.getKey() + "";
            if (tmp.trim().equals("Set-Cookie")) {
                COOKIE = entry.getValue().toString() + "";
                COOKIE = COOKIE.replace("[", "").replace("]", "");
            }
        }
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("Cookie : " + COOKIE);

        // "Post data send ... waiting for reply");
        int code = conn.getResponseCode(); // 200 = HTTP_OK
        System.out.println("Response    (Code):" + code);
        System.out.println("Response (Message):" + conn.getResponseMessage());

        // read the response
        DataInputStream input = new DataInputStream(conn.getInputStream());
        int c;
        StringBuilder resultBuf = new StringBuilder();
        while ((c = input.read()) != -1) {
            resultBuf.append((char) c);
        }
        input.close();
        SESSION = resultBuf.toString();
        System.out.println("SESSION Key: " + SESSION);
        sendData();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        StringBuilder result = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            result.append(line);
//        }
//        System.out.println(result.toString());
    }

    public static String sendData() throws IOException {
        // curl_init and url
        URL url = new URL(VULA_BASE_URL+"/user/current.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //  CURLOPT_POST
        con.setRequestMethod("GET");

        // CURLOPT_FOLLOWLOCATION
        con.setInstanceFollowRedirects(true);

        //String postData = "my_data_for_posting";
        con.setRequestProperty("Cookie", COOKIE);

//        con.setDoOutput(true);
        con.setDoInput(true);

        
        Map<String, List<String>> response = con.getHeaderFields();

        System.out.println("Printing User Details Response Header...\n");

        System.out.println("+-----------------------------------------------------------+");
        for (Map.Entry<String, List<String>> entry : response.entrySet()) {
            System.out.println("| " + entry.getKey() + " \t : \t" + entry.getValue());
        }
        System.out.println("+-----------------------------------------------------------+");
        
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
        System.out.println(results);
        return results;
    }
}

//    public static void main(String[] args) throws Exception {
//        FedoraAPIService client = new FedoraAPIService();
//        
//        Scanner input = new Scanner(System.in);
//        System.out.print("Please enter file name: ");
//        File snd_file = new File(input.nextLine());
//        System.out.println("File exists : " +snd_file.exists());
//        System.out.println("File can be Read : " +snd_file.canRead());
//        System.out.println("File can be written : " +snd_file.canRead());
//        String file_name = snd_file.getName();
//        String mime = URLConnection.guessContentTypeFromName(file_name);
//
//
//        System.out.println("mimeType : "+mime);
//        String pid = client.createObject("candy","lovedare", "Lavius Nkateko Motileng");
//        System.out.println("PID : "+pid);
//        
//        String location = client.uploadFile(client.BASE_URL + "upload", snd_file);
//        System.out.println(location);
//        
//        client.createNewDataStream(pid, "newobject", mime, "uploading first version of the file",file_name, location);
//client.gfindObjects("learn", "pid~learn:*");
//        Gson gson = new Gson();
//        int[] ints = {1, 2, 3, 4, 5};
//        String results = gson.toJson(new User()); 
//        System.out.println(results);
//new VulaAPIServices();
//          VulaService vservice = VulaApiUtils.getVulaService();
//         VulaService s = ServiceGenerator.createService(VulaService.class, "mtllav001", "3712lav123@@@NTKGeekSaw");
//          s.getUserProfile();
//          new GetUserDetails().getUser(vservice,"");
//
//        String username = "mtllav001";
//        String password = "3712lav123@@@NTKGeekSaw";
//        
//        Map<String, String> map =  new LinkedHashMap<String, String>();
//        map.put("_username",username);
//        map.put("_password",password);
//        
//        VulaService vservice = VulaApiUtils.getVulaService();
//        new GetUserDetails().getUser(vservice, map);
//    }

