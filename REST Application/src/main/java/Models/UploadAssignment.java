/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import responses.Assignment;
import responses.Resource;

/**
 *
 * @author learnproject
 */
public class UploadAssignment {
    public String _username;
    public String _password;
    public Assignment assignment;
    public String pid;

    public UploadAssignment(String _username, String _password, Assignment assignment, String pid) {
        this._username = _username;
        this._password = _password;
        this.assignment = assignment;
        this.pid = pid;
    }
   
}
