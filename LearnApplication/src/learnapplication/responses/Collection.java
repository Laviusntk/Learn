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
public class Collection<T>{
    
    private String entityPrefix;
    private T[] content_collection;

    public Collection() {
    }  

    public void setEntityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
    }

    public void setContent_collection(T[] content_collection) {
        this.content_collection = content_collection;
    }

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public T[] getContent_collection() {
        return content_collection;
    }

    @Override
    public String toString() {
        String resources =  "";
        for(int i = 0; i < content_collection.length; i++)
            resources += content_collection[i].toString() + ",\n";
        return "Collection{\n" + "entityPrefix=" + entityPrefix + ",\n content_collection=" + resources + '}';
    }
    
    
}
