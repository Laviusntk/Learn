/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication;
import learnapplication.Utilities.RESTClient;
import java.io.*;
import java.net.*;

/**
 *
 * @author nkateko
 */
public class LearnApplication {

    public void browse() throws Exception{
    	String url = "http://localhost:8080/fedora/describe";

	URL obj = new URL(url);
	HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
	conn.setReadTimeout(5000);
	conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
	conn.addRequestProperty("User-Agent", "Mozilla");
	conn.addRequestProperty("Referer", "google.com");

	System.out.println("Request URL ... " + url);

	boolean redirect = false;

	// normally, 3xx is redirect
	int status = conn.getResponseCode();
	if (status != HttpURLConnection.HTTP_OK) {
		if (status == HttpURLConnection.HTTP_MOVED_TEMP
			|| status == HttpURLConnection.HTTP_MOVED_PERM
				|| status == HttpURLConnection.HTTP_SEE_OTHER)
		redirect = true;
	}

	System.out.println("Response Code ... " + status);
        
	if (redirect) {

		// get redirect url from "location" header field
		String newUrl = conn.getHeaderField("Location");

		// get the cookie if need, for login
		String cookies = conn.getHeaderField("Set-Cookie");

		// open the new connnection again
		conn = (HttpURLConnection) new URL(newUrl).openConnection();
		conn.setRequestProperty("Cookie", cookies);
		conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
		conn.addRequestProperty("User-Agent", "Mozilla");
		conn.addRequestProperty("Referer", "google.com");

		System.out.println("Redirect to URL : " + newUrl);
	}

	BufferedReader in = new BufferedReader(
                              new InputStreamReader(conn.getInputStream()));
	String inputLine;
	StringBuffer html = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		html.append(inputLine+"\n");
	}
	in.close();

	System.out.println("URL Content... \n" + html.toString());
	System.out.println("Done");        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        LearnApplication learn = new LearnApplication();
        
        RESTClient client = new RESTClient();
        
        String REST_URL = "http://localhost:8080/fedora/describe"; 
        String REPOSITORY_NAME = "learn";
        String RESULT_PAGE = "describe";
        
        client.getRepositoryInfo(REST_URL, REPOSITORY_NAME, RESULT_PAGE);
    }
    
}
