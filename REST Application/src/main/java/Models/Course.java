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
public class Course {
    private String name;
    private folder[] folders;

    public Course(String name, folder[] folders) {
        this.name = name;
        this.folders = folders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public folder[] getFolders() {
        return folders;
    }

    public void setFolders(folder[] folders) {
        this.folders = folders;
    }
    
    
}
