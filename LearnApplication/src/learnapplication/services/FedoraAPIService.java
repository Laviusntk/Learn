/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

public class FedoraAPIService {
    private static final String LINE_FEED = "\r\n";
    public final String BASE_URL = "http://localhost:8080/fedora/";     
    
    private Object content;
    private String boundary;
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;
    
    
    public String createObject(String _label,String _namespace, String _owner){
        try{
            return sendPostRequest(BASE_URL
                    +"objects/new?flash=false"
                    + "&label=" + _label
                    + "&namespace=" + _namespace
                    + "&ownerId=" + URLEncoder.encode(_owner, "UTF-8"));            
        }catch(Exception e){
            return e.getMessage();
        }
    }      
    
  public String createDataStream(String _pid, String _dsid, String _mimeType, String _logmsg, String label, File snd_file, String mime){
      try{
          return upload(
                  BASE_URL+"objects/"
                  +_pid
                  +"/datastreams/"
                  + _dsid+"?"
                  + "mimeType=" + _mimeType
                  + "&dsLabel=" +label
                  + "&ignoreContent=false"
                  + "&versionable=true"
                  + "&controlGroup=M"                          
                  + "&logMessage=" + URLEncoder.encode(_logmsg, "UTF-8"),
                  snd_file,
                  mime
                  );
      }catch(Exception e){
          return e.getMessage();
      }
  }
    
  
    public String createNewDataStream(String _pid, String _dsid, String _mimeType, String _logmsg, String label, String location){
      try{
          String my_url = 
                  BASE_URL+"objects/"
                  +_pid
                  +"/datastreams/"
                  + _dsid+"?"
                  + "mimeType=" + _mimeType
                  + "&dsLabel=" +label
                  + "&ignoreContent=false"
                  + "&versionable=true"
                  + "&controlGroup=M"
                  + "&dsLocation=" + location                          
                  + "&logMessage=" + URLEncoder.encode(_logmsg, "UTF-8");
          
        HttpPost request = new HttpPost(my_url);
        request.setHeader("Authorization", "Basic "+(new BASE64Encoder()).encode(("fedoraAdmin"+":"+"fedoraAdmin").getBytes()));
        //request.setHeader("Accept", "image/jpg,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        //request.setHeader("Accept-Language", "Accept-Language");
        request.setHeader("Connection", "keep-alive");      
        
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request); 
        System.out.println("Code : "+response.getStatusLine().getStatusCode());

         return EntityUtils.toString(response.getEntity(), "UTF-8");  
      }catch(Exception e){
          return e.getMessage();
      }

    }
  
        public static String uploadfile(String url, String filename) throws IOException{
              String charset = "UTF8";
                           
              URLConnection connection = new URL(url).openConnection();
              connection.setDoOutput(true);
              connection.setRequestProperty("Content-Type", "attachement;  file="+filename);
              connection.setRequestProperty("Authorization", "Basic "+(new BASE64Encoder()).encode(("fedoraAdmin"+":"+"fedoraAdmin").getBytes()));
              connection.setRequestProperty("Connection", "keep-alive");
              connection.setRequestProperty("Accept-Encoding", "gzip, deflate, UTF8, UTF-8");

              int responseCode = ((HttpURLConnection) connection).getResponseCode();
              System.out.println(responseCode); // Should be 200
              return ""+responseCode;
    }
  
      public static String upload(String url, File snd_file, String mime) throws IOException{
              String charset = "UTF8";
              String param = "value";
              String CRLF = "\r\n"; // Line separator required by multipart/form-data.
              String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
              
              //URLConnection inputurl = new URL("http://www.geo.brown.edu/research/Milliken/GEOL0810_files/PlanetaryGeology_BackgroundMaterial.pdf").openConnection();
              //InputStream is = inputurl.getInputStream();
              //Files.copy(is, snd_file.toPath(), StandardCopyOption.REPLACE_EXISTING);
              //REPLACE_EXISTING
              
              URLConnection connection = new URL(url).openConnection();
              connection.setDoOutput(true);
              connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
              connection.setRequestProperty("Authorization", "Basic "+(new BASE64Encoder()).encode(("fedoraAdmin"+":"+"fedoraAdmin").getBytes()));
              connection.setRequestProperty("Accept", mime+",*");
              connection.setRequestProperty("Connection", "keep-alive");
              connection.setRequestProperty("Accept-Encoding", "gzip, deflate, UTF8, UTF-8");
              connection.setRequestProperty("enctype", "multipart/form-data");
              try (
                  // InputStream inputstream = 
                  OutputStream output = connection.getOutputStream();
                  PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
              ){
              
              // Send file
              writer.append("--" + boundary).append(CRLF);
              writer.append("Content-Disposition: form-data; name=\"param\"").append(CRLF);
              writer.append("Content-Type: "+mime+"; charset=" + charset).append(CRLF);
              writer.append(CRLF).append(param).append(CRLF).flush();
              writer.append("--" + boundary).append(CRLF);
              writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + snd_file.getName() + "\"").append(CRLF);
              writer.append("Content-Type: "+mime+"; charset=" + charset).append(CRLF); // Text file itself must be saved in this charset!
              writer.append(CRLF).flush();

              //System.out.println("Can read file : "+Files.isReadable(snd_file.toPath()));
              //System.out.println("Can write file : "+Files.isWritable(snd_file.toPath()));
              
              
              //Files.copy(is, output, StandardCopyOption.COPY_ATTRIBUTES);
              Files.copy(snd_file.toPath(), output);              
              output.flush(); // Important before continuing with writer!

              writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.
              writer.append("--" + boundary + "--").append(CRLF).flush();
          }

          // Request is lazily fired whenever you need to obtain information about response.
          int responseCode = ((HttpURLConnection) connection).getResponseCode();
          System.out.println(responseCode); // Should be 200
          return ""+responseCode;
    }
    
    public static String uploadFile(String requestUrl, File file) throws Exception{
               
        System.out.println("Path : " + file.getAbsolutePath());
        MultipartEntity entity = new MultipartEntity();
        entity.addPart("file", new FileBody(file));
        
        HttpPost request = new HttpPost(requestUrl);
        request.setEntity(entity);
        request.setHeader("Authorization", "Basic "+(new BASE64Encoder()).encode(("fedoraAdmin"+":"+"fedoraAdmin").getBytes()));
        request.setHeader("Accept", "image/jpg,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        request.setHeader("Accept-Language", "Accept-Language");
        request.setHeader("Connection", "keep-alive");
        //request.setHeader("Accept-Encoding", "gzip, deflate"); 
        request.setHeader("enctype", "multipart/form-data");        
        //request.setHeader("Content-Type", "image/png");
        
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request); 
        System.out.println("Code : "+response.getStatusLine().getStatusCode());
        //System.out.println("Message : "+);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

    public static String sendPostRequest(String requestUrl) {
        StringBuffer jsonString = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "CodeJava Agent");
            connection.setRequestProperty("Authorization", "Basic "+(new BASE64Encoder()).encode(("fedoraAdmin"+":"+"fedoraAdmin").getBytes()));
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept-Language", "Accept-Language");
            connection.setRequestProperty("Connection", "keep-alive");
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
    
    private void updateIndex(
            String restUrl,
            String action,
            String value,
            String repositoryName,
            String indexName,
            String indexDocXslt) {
    	doOp(restUrl
                        + "?operation=updateIndex"
                        + "&action=" + action
                        + "&value=" + value
                        + "&repositoryName=" + repositoryName
                        + "&indexName=" + indexName
                        + "&indexDocXslt=" + indexDocXslt
                        + "&restXslt=copyXml");
    }

    public void browseIndex(
            String restUrl,
            String startTerm,
            String fieldName,
            String indexName,
            int termPageSize,
            String resultPageXslt) {
    	try {
			doOp(BASE_URL + "objects"
			                + "?operation=browseIndex"
			                + "&startTerm=" + URLEncoder.encode(startTerm, "UTF-8")
			                + "&fieldName=" + fieldName
			                + "&indexName=" + indexName
			                + "&termPageSize=" + termPageSize
			                + "&restXslt=copyXml"
			                + "&resultPageXslt=" + resultPageXslt);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }

    public void gfindObjects(String _searchTerm, String _query) {
    	try {
			doOp(BASE_URL + "objects"
			              + "?pid=true"
                                      + "&title=true"
                                      + "&terms=*" +_searchTerm+"*"
                                      + "&query=" + _query 
                                      + "&maxResults=20");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void getRepositoryInfo(
            String restUrl,
            String repositoryName,
            String resultPageXslt) {
        
    	doOp(restUrl
                        + "?operation=getRepositoryInfo"
                        + "&repositoryName=" + repositoryName
                        + "&restXslt=copyXml"
                        + "&resultPageXslt=" + resultPageXslt);
    }

    private void getIndexInfo(
            String restUrl,
            String indexName,
            String resultPageXslt) {
    	doOp(restUrl
                        + "?operation=getIndexInfo"
                        + "&indexName=" + indexName
                        + "&restXslt=copyXml"
                        + "&resultPageXslt=" + resultPageXslt);
    }

    private void configure(
            String restUrl,
            String configName,
            String propertyName,
            String propertyValue) {
    	doOp(restUrl    + "?operation=configure"
                        + "&configName=" + configName
                        + "&propertyName=" + propertyName
                        + "&propertyValue=" + propertyValue);
    }

    private void run(
            String restUrl,
            String queryString) {
    	doOp(restUrl + queryString);
    }

    private void doOp(
            String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }
        URLConnection conn = null;
        try {
            conn = url.openConnection();
            conn.setRequestProperty("Authorization",
            		"Basic "+(new BASE64Encoder()).encode(("fedoraAdmin"+":"+"fedoraAdmin").getBytes()));

            
            conn.connect();
            System.out.println("Session cokie: "+conn.getHeaderField("Set-Cookie"));
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }
        content = null;
        try {
            content = conn.getContent();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
//        System.out.println((InputStream) content);
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader((InputStream)content));
        try {
            while ((line = br.readLine())!=null)
                System.out.println(line);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

