/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.requests;

import com.google.gson.Gson;
import java.io.File;
import java.net.URLConnection;
import java.net.URLEncoder;
import learnapplication.Client.FedoraClient;
import learnapplication.Client.VulaClient;
import learnapplication.responses.User;
import static learnapplication.services.FedoraAPIService.sendPostRequest;
import static learnapplication.services.FedoraAPIService.upload;

/**
 *
 * @author learnproject
 */
public class FedoraRequestHandler {
    private final String NEW_OBJECT_PATH = "/objects/new";
    private final String DATASTREAM_PATH = "/objects";
    private final String FLASH_VALUE = "false";
    private FedoraClient client;
    private User user;
    
    public FedoraRequestHandler(FedoraClient _client, User _user) {
        this.client = _client;
        this.user = _user;
    }

    public String createObject(String _label) throws Exception {
        return this.client.request(
                NEW_OBJECT_PATH,
                "?flash=false"
                + "&label=" + URLEncoder.encode(_label, "UTF-8")
                + "&namespace=" + URLEncoder.encode(user.getDisplayId(), "UTF-8")
                + "&ownerId=" + URLEncoder.encode(user.getDisplayName(), "UTF-8"),"");
    }
    
  public String createDataStream(String _pid, String _dsid,  String _logmsg, String fileUrl) throws Exception{
        File snd_file = new File(fileUrl);  
        return this.client.request(
                  DATASTREAM_PATH + "/",
                  _pid
                  +"/datastreams/"
                  + _dsid+"?"
                  + "mimeType=" +URLConnection.guessContentTypeFromName(snd_file.getName())
                  + "&dsLabel=" +URLEncoder.encode(snd_file.getName(), "UTF-8")
                  + "&ignoreContent=false"
                  + "&versionable=true"
                  + "&controlGroup=M"                          
                  + "&logMessage=" + URLEncoder.encode(_logmsg, "UTF-8"),
                  snd_file,
                  fileUrl
                  );
  }
  
  /*
      public String createDataStream(String _pid, String _dsid, String _logmsg, File snd_file) throws Exception {
        return this.client.request(
                DATASTREAM_PATH + "/",
                _pid
                + "/datastreams/"
                + _dsid + "?"
                + "mimeType=application/pdf" + URLConnection.guessContentTypeFromName(snd_file.getName())
                + "&dsLabel=saple.pdf" 
                + "&ignoreContent=false"
                + "&versionable=true"
                + "&controlGroup=E"
                + "&altIDs=" + URLEncoder.encode("JSESSIONID=3b8b3ee3-0505-4532-9da9-0364b049ad05.vula7b; Path=/; Secure; HttpOnly", "UTF-8")       
                + "&dsLocation=" + URLEncoder.encode("http://www.pacific.edu/Documents/student-life/health%20services/NutriCat/PacificCooksHoliday.pdf", "UTF-8")        
                + "&logMessage=" + URLEncoder.encode(_logmsg, "UTF-8"),
                snd_file
        );
    }
  */
}
