/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import com.google.gson.Gson;
import java.io.File;
import java.net.URLConnection;
import java.net.URLEncoder;
import Client.FedoraClient;
import Client.VulaClient;
import responses.User;
import services.FedoraAPIService;

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
                + "&ownerId=" + URLEncoder.encode(user.getDisplayName(), "UTF-8"), "POST");
    }

    public String createObject(String _pid, String _label) throws Exception {
        return this.client.request(
                "/objects/" + _pid,
                "?flash=false"
                + "&label=" + URLEncoder.encode(_label, "UTF-8")
                + "&namespace=" + URLEncoder.encode(user.getDisplayId(), "UTF-8")
                + "&ownerId=" + URLEncoder.encode(user.getDisplayName(), "UTF-8"), "POST");
    }

    public String addRelationShip(String _pid, String _subject, String _predicate, String _object, boolean _isliteral) throws Exception {
        return this.client.request(
                "/objects/" + _pid + "/relationships/new",
                "?subject=" + URLEncoder.encode(_subject, "UTF-8")
                + "&predicate=" + URLEncoder.encode(_predicate, "UTF-8")
                + "&object=" + URLEncoder.encode(_object, "UTF-8")
                + "&isLiteral=" + _isliteral,
                "POST"
        );
    }

    public String createDataStream(String _resourcename, String _mimeType, String _pid, String _dsid, String _logmsg, String fileUrl) throws Exception {
        File snd_file = new File(fileUrl);
        return this.client.request(
                DATASTREAM_PATH + "/",
                _pid
                + "/datastreams/"
                + _dsid.replace(" ", "") + "?"
                + "mimeType=" + _mimeType
                + "&dsLabel=" + _resourcename.replace(" ", "-")
                + "&ignoreContent=false"
                + "&versionable=true"
                + "&controlGroup=M"
                + "&logMessage=" + URLEncoder.encode(_logmsg, "UTF-8"),
                snd_file,
                fileUrl
        );
    }

    public String createDataStream(String _resourcename, String _mimeType, String _pid, String _dsid, String _logmsg, File snd_file) throws Exception {
        return this.client.request(
                DATASTREAM_PATH + "/",
                _pid
                + "/datastreams/"
                + _dsid.replace(" ", "") + "?"
                + "mimeType=" + _mimeType
                + "&dsLabel=" + _resourcename.replace(" ", "-")
                + "&ignoreContent=false"
                + "&versionable=true"
                + "&controlGroup=M"
                + "&logMessage=" + URLEncoder.encode(_logmsg, "UTF-8"),
                snd_file
        );
    }

    public String browseObjects()
            throws Exception {
        String url = "?pid=true&label=true&state=true&ownerId=true&cDate=true&mDate=true&dcmDate=true&title=true&creator=true&subject=true&description=true&publisher=true&contributor=true&date=true&type=true&format=true&identifier=true&source=true&relation=true&coverage=true&rights=true&terms=&query=pid~*&maxResults=10000";
        return this.client.request("/objects", url, "GET");
    }
    
    public String getDataStreams(String pid)
            throws Exception {
        return this.client.request("/objects/"+pid+"/datastreams", "?format=xml", "GET");
    }
    
    //                                      + "&terms=*" +_searchTerm+"*"
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
