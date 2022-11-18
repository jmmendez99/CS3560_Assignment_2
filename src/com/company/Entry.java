package com.company;

import java.awt.event.ActionEvent;

//Implement Composite pattern here!
//This interface unifies Users AND Groups
public interface Entry {

    //The purpose of this addToTree method should be that each entry, whether
    //a user or a group, should add themselves to the tree structure that consists
    //of users and groups.
    //might need to add the user/group id as a parameter for this
    public void addToTree(ActionEvent e);
}
