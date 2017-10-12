package Controllers;

import Client.VulaClient;
import Models.Auth;
import Models.AuthStatus;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    private final AtomicLong counter = new AtomicLong();
    protected static SecureRandom random = new SecureRandom();
    private final String USERNAME_KEY = "_username";
    private final String PASSWORD_KEY = "_password";
    private final String BASE_URL = "https://vula.uct.ac.za/direct";
    private Auth auth;

    @RequestMapping("/login")
    public AuthStatus login(@RequestHeader("data") String _auth) throws Exception {
        Gson gson = new Gson();
        this.auth = gson.fromJson(_auth, Auth.class);
//        System.out.println(auth.getUsername()+"&"+ auth.getPassword());
        VulaClient client = new VulaClient(BASE_URL, this.auth.getUsername(), this.auth.getPassword());
        return client.authenticate("/session");
    }

}
