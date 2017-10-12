/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import responses.Assignment;
import responses.Resource;
import responses.Site;

/**
 *
 * @author nkateko
 */
public class SiteContent {
    public Resource[] mycontent;
    public Assignment[] assignments;

    public SiteContent(Resource[] mycontent, Assignment[] assignments) {
        this.mycontent = mycontent;
        this.assignments = assignments;
    }
    
}
