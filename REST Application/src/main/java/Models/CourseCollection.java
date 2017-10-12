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
public class CourseCollection {
    private Course[] folder_collection;

    public CourseCollection(Course[] folder_collection) {
        this.folder_collection = folder_collection;
    }

    public Course[] getFolder_collection() {
        return folder_collection;
    }
    
    
}
