/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Auth;
import Models.AuthStatus;
import Models.DataStreamObject;
import Models.RepoObject;
import Models.RepositoryResource;
import Models.UploadAssignment;
import Models.UploadResponse;
import Utilities.FedoraSession;
import Utilities.VulaSession;
import com.google.gson.Gson;
import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author learnproject
 */
@RestController
public class RepositoryContentController {

    private final AtomicLong counter = new AtomicLong();
    private final String USERNAME_KEY = "_username";
    private final String PASSWORD_KEY = "_password";
    private final String BASE_URL = "https://vula.uct.ac.za/direct";
    public FedoraSession fsession;
//

    @RequestMapping("/repository/content")
    public ArrayList<RepositoryResource> assignmentUpload(@RequestHeader("data") String data) throws Exception {

        Gson gson = new Gson();

        Auth auth = gson.fromJson(data, Auth.class);

        VulaSession vsession = new VulaSession(auth.getUsername(), auth.getPassword());
        this.fsession = new FedoraSession("fedoraAdmin", "fedoraAdmin", vsession.request.getProfile(), vsession.client);

        final String HTML = browse();

        Document document = Jsoup.parse(HTML);
        Elements table = document.select("table");
        Elements arrayName = table.select("tr");

        ArrayList<RepositoryResource> resource = new ArrayList<RepositoryResource>();
        for (int i = 4; i < arrayName.size(); i++) {
            Elements col = arrayName.get(i).select("td");

            if (col.size() > 2) {
                String soapmessageString = getDataStreams(arrayName.get(i).select("td").get(0).text());
                JSONObject soapDatainJsonObject = XML.toJSONObject(soapmessageString);
                
                
                RepoObject datastream = gson.fromJson(soapDatainJsonObject.toString(), RepoObject.class);
                
                RepositoryResource tmp = new RepositoryResource(
                        col.get(0).text(),
                        col.get(1).text(),
                        col.get(2).text(),
                        col.get(3).text(),
                        col.get(4).text(),
                        col.get(5).text(),
                        col.get(6).text(),
                        col.get(7).text(),
                        col.get(8).text(),
                        col.get(9).text(),
                        col.get(10).text(),
                        col.get(11).text(),
                        col.get(12).text(),
                        col.get(13).text(),
                        col.get(14).text(),
                        col.get(15).text(),
                        col.get(16).text(),
                        col.get(17).text(),
                        col.get(18).text()
                );
                tmp.datastreams.add(datastream);
                resource.add(tmp);
            }
        }
        return resource;
    }

    public String browse() throws Exception {
        return this.fsession.request.browseObjects();
    }

    public String getDataStreams(String pid) throws Exception {
        return this.fsession.request.getDataStreams(pid);
    }
}
