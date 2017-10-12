/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import java.util.ArrayList;

/**
 *
 * @author learnproject
 */
public class AssignmentCollection {

    private String entityPrefix;
    private Assignment[] assignment_collection;

    public AssignmentCollection() {
    }

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public Assignment[] getAssignment_collection() {
        return assignment_collection;
    }

    public void setEntityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
    }

    public void setAssignment_collection(Assignment[] assignment_collection) {
        this.assignment_collection = assignment_collection;
    }

    public ArrayList<Assignment> getAssignmentByID(String ownerID) {
        ArrayList<Assignment> results = new ArrayList<>();
        for (int i = 0; i < assignment_collection.length; i++) {
            if (assignment_collection[i].getCreator().equals(ownerID)) {
                results.add(assignment_collection[i]);
            }
        }
        return results;
    }

    @Override
    public String toString() {
        return "AssignmentCollection{" + "entityPrefix=" + entityPrefix + ", assignment_collection=" + assignment_collection + '}';
    }
}
