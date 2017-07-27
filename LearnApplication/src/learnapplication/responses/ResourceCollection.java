/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.responses;

import java.util.ArrayList;

/**
 *
 * @author learnproject
 */
public class ResourceCollection{
    
    private String entityPrefix;
    private Resource[] content_collection;

    public ResourceCollection() {
    }  

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public Resource[] getContent_collection() {
        return content_collection;
    }
    
    public ArrayList<Resource> getResourceByID(String ownerID){
        ArrayList<Resource> results = new ArrayList<>();
        for(int i = 0; i < content_collection.length; i++){
            if(content_collection[i].getAuthorId().equals(ownerID))
                results.add(content_collection[i]);
        }
        return results;
    }

    @Override
    public String toString() {
        String resources =  "";
        for(int i = 0; i < content_collection.length; i++)
            resources += content_collection[i].toString() + ",\n";
        return "Collection{\n" + "entityPrefix=" + entityPrefix + ",\n content_collection=" + resources + '}';
    }
    
    
}
