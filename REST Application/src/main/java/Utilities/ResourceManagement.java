/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.ArrayList;
import responses.Resource;

/**
 *
 * @author learnproject
 */
public class ResourceManagement {
    public ArrayList<String> getCources(ArrayList<Resource> resources){
        ArrayList<String> results = new ArrayList<String>();
        for(int i = 0; i < resources.size(); i++){
            if(resources.get(i).getContainer().split("/").length == 4 && resources.get(i).getType().equals("collection"))
                results.add(resources.get(i).getTitle());
        }
        return results;
    }
}
