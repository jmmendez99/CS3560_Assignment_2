package com.company;

//Implement Composite pattern here!
//This interface unifies Users AND Groups
public interface Entry {

    //The purpose of this addToTree method should be that each entry, whether
    //a user or a group, should add themselves to the tree structure that consists
    //of users and groups.
    public void addToTree();
}
