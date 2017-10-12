/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author learnproject
 */
public class RepositoryResource {
    public String pid;
    public String label;
    public String state;
    public String ownerId;
    public String cDate;
    public String mDate;
    public String dcmDate;
    public String title;
    public String creator;
    public String subject;
    public String description;
    public String date;
    public String type;
    public String format;
    public String identifier;
    public String source;
    public String relation;
    public String coverage;
    public String rights;
    public ArrayList<RepoObject> datastreams = new ArrayList<RepoObject>();
    
    public RepositoryResource(String pid, String label, String state, String ownerId, String cDate, String mDate, String dcmDate, String title, String creator, String subject, String description, String date, String type, String format, String identifier, String source, String relation, String coverage, String rights) {
        this.pid = pid;
        this.label = label;
        this.state = state;
        this.ownerId = ownerId;
        this.cDate = cDate;
        this.mDate = mDate;
        this.dcmDate = dcmDate;
        this.title = title;
        this.creator = creator;
        this.subject = subject;
        this.description = description;
        this.date = date;
        this.type = type;
        this.format = format;
        this.identifier = identifier;
        this.source = source;
        this.relation = relation;
        this.coverage = coverage;
        this.rights = rights;
    }
    
    
}
