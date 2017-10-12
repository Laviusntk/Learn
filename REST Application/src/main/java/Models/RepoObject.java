/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author learnproject
 */
    public class RepoObject {
    DataStreamObject objectDatastreams;

    public RepoObject() {
    }

    public DataStreamObject getObjectDatastreams() {
        return objectDatastreams;
    }

    @Override
    public String toString() {
        return "RepoObject{" + "objectDatastreams=" + objectDatastreams + '}';
    }
    
}
