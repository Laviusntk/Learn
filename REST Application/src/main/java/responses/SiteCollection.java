/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

/**
 *
 * @author learnproject
 */
public class SiteCollection {
    private String entityPrefix;
    private Site[] site_collection;

    public SiteCollection() {
    }

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public Site[] getSite_collection() {
        return site_collection;
    }

    public void setEntityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
    }

    public void setSite_collection(Site[] site_collection) {
        this.site_collection = site_collection;
    }

    @Override
    public String toString() {
        String resources =  "";
        for(int i = 0; i < site_collection.length; i++)
            resources += site_collection[i].toString() + ",\n";
        return "Collection{\n" + "entityPrefix=" + entityPrefix + ",\n content_collection=" + resources + '}';
    }    
}
