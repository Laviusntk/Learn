/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import responses.Resource;

/**
 *
 * @author learnproject
 */
public class UploadFile {
    public String _username;
    public String _password;
    public Resource resource;
    public String pid;
    public String course_acronym;

    public UploadFile(String _username, String _password, Resource resource, String pid, String course_acronym) {
        this._username = _username;
        this._password = _password;
        this.resource = resource;
        this.pid = pid;
        this.course_acronym = course_acronym;
    }
    
    
}
