package com.company;

import java.util.ArrayList;
import java.util.List;

public class Group implements Entry {
    //Properties of group
    // unique ID
    private String groupID;
    // list of entries which consist of all users/subgroups that are part of the parent group
    private List<Entry> entries;

    //Constructor
    public Group(String groupID) {
        this.groupID = groupID;
        entries = new ArrayList<>();
    }

    //Getter and Setters
    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    //Class methods


    //Implementation should add groups/subgroups/users to the tree structure
    //this function is triggered by the create group button in the admin panel
    //Composite pattern component
    @Override
    public void addToTree() {
        //check if instanceof entry is a user, then we must add it as a child of the parent group
        //  else we just create a new Group() object and add it to the tree structure
        //need to get access to the root of the JTree from the admin panel in order to iterate through
        //  the tree and find where we should add the child user/group
        //if group exists in tree?, return it, else create group
        //set entries
        //get entries
        //call addToTree() method on each entry
        //whenever a group is created
    }
}
