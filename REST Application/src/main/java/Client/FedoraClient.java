/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import org.apache.commons.io.IOUtils;
import okhttp3.Credentials;
import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Encoder;

/**
 *
 * @author learnproject
 */
public class FedoraClient extends Client {

    VulaClient vulaclient;

    public FedoraClient(String BASE_URL, String USERNAME, String PASSWORD, VulaClient _client) {
        super(BASE_URL, USERNAME, PASSWORD);
        this.vulaclient = _client;
    }

    @Override
    public String request(String path, String Query, String method) throws Exception {
        StringBuffer jsonString = null;
        try {
            URL url = new URL(super.BASE_URL + path + Query);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod(method);
            connection.setRequestProperty("User-Agent", "CodeJava Agent");
            connection.setRequestProperty("Authorization", super.credentials);
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept-Language", "Accept-Language");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Cookie", this.vulaclient.SESSION);
            //connection.setRequestProperty("Accept-Encoding", "gzip, deflate");     
            connection.setRequestProperty("Content-Type", "text/xml");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return jsonString.toString();
    }

    public String request(String path, String Query, File snd_file) throws Exception {
        String file_name = snd_file.getName();
        String mime = URLConnection.guessContentTypeFromName(file_name);
        String charset = "UTF8";
        String param = "value";
        String CRLF = "\r\n"; // Line separator required by multipart/form-data.
        String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.

        URLConnection connection = new URL(super.BASE_URL + path + Query).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("Authorization", super.credentials);
        connection.setRequestProperty("Cookie", this.vulaclient.SESSION);
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestProperty("Accept-Encoding", "gzip, deflate, UTF8, UTF-8");
        connection.setRequestProperty("enctype", "multipart/form-data");
        try (
                // InputStream inputstream = 
                OutputStream output = connection.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);) {

            // Send file
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"param\"").append(CRLF);
            writer.append(CRLF).append(param).append(CRLF).flush();
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + snd_file.getName() + "\"").append(CRLF);
            writer.append(CRLF).flush();

            //Files.copy(snd_file.toPath(), output);
//            URLConnection conn = new URL(fileUrl).openConnection();
//            conn.addRequestProperty("Cookie", this.vulaclient.SESSION);
//            InputStream in = conn.getInputStream();
           
            //InputStream snd = new URL(fileUrl).openConnection();
            //.openStream();
            
            InputStream inputstream = FileUtils.openInputStream(snd_file);
            IOUtils.copy(inputstream, output);
            
            output.flush(); // Important before continuing with writer!

            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.
            writer.append("--" + boundary + "--").append(CRLF).flush();
        }

        int responseCode = ((HttpURLConnection) connection).getResponseCode();
        System.out.println(responseCode);
        return "" + responseCode;
    }    
    
    public String request(String path, String Query, File snd_file, String fileUrl) throws Exception {
        String file_name = snd_file.getName();
        String mime = URLConnection.guessContentTypeFromName(file_name);
        String charset = "UTF8";
        String param = "value";
        String CRLF = "\r\n"; // Line separator required by multipart/form-data.
        String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.

        URLConnection connection = new URL(super.BASE_URL + path + Query).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("Authorization", super.credentials);
        connection.setRequestProperty("Cookie", this.vulaclient.SESSION);
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestProperty("Accept-Encoding", "gzip, deflate, UTF8, UTF-8");
        connection.setRequestProperty("enctype", "multipart/form-data");
        try (
                // InputStream inputstream = 
                OutputStream output = connection.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);) {

            // Send file
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"param\"").append(CRLF);
            writer.append(CRLF).append(param).append(CRLF).flush();
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + snd_file.getName() + "\"").append(CRLF);
            writer.append(CRLF).flush();

            //Files.copy(snd_file.toPath(), output);
            URLConnection conn = new URL(fileUrl).openConnection();
            conn.addRequestProperty("Cookie", this.vulaclient.SESSION);
            InputStream in = conn.getInputStream();
           
            //InputStream snd = new URL(fileUrl).openConnection();
            //.openStream();
            IOUtils.copy(in, output);
            output.flush(); // Important before continuing with writer!

            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.
            writer.append("--" + boundary + "--").append(CRLF).flush();
        }

        int responseCode = ((HttpURLConnection) connection).getResponseCode();
        System.out.println(responseCode);
        return "" + responseCode;
    }

    @Override
    public void generateCrentials() throws Exception {
        super.credentials = Credentials.basic(super.USERNAME, super.PASSWORD);
    }

}
