/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnapplication.responses;

/**
 *
 * @author learnproject
 */
public class Collection {
    
    private String entityPrefix;
    private String[] content_collection;

    public Collection() {
    }

    public Collection(String entityPrefix, String[] content_collection) {
        this.entityPrefix = entityPrefix;
        this.content_collection = content_collection;
    }

    public void setEntityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
    }

    public void setContent_collection(String[] content_collection) {
        this.content_collection = content_collection;
    }

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public String[] getContent_collection() {
        return content_collection;
    }

    
}
