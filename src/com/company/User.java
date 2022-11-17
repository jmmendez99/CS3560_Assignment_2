package com.company;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User implements Entry{
    //Properties of user
    //  unique id
    private String userID;
    //  list of user IDs that are following this user(followers)
    private List<String> followersIDList;
    //  list of user IDs being followed by this user(followings)
    private List<String> followingIDList;
    //  list of tweets from this user
    private List<String> newsFeedList;

    //Constructor
    //whenever a new user is instantiated, these things will be created/initialized
    //this constructor is triggered by the create user button in the admin panel
    public User(String userID) {
        this.userID = userID; //string that the user puts in the textField
        this.followersIDList = new ArrayList<>();
        this.followingIDList = new ArrayList<>();
        this.newsFeedList = new ArrayList<>();
    }

    //Getter and Setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<String> getFollowersIDList() {
        return followersIDList;
    }

    public void setFollowersIDList(List<String> followersIDList) {
        this.followersIDList = followersIDList;
    }

    public List<String> getFollowingIDList() {
        return followingIDList;
    }

    public void setFollowingIDList(List<String> followingIDList) {
        this.followingIDList = followingIDList;
    }

    public List<String> getNewsFeedList() {
        return newsFeedList;
    }

    public void setNewsFeedList(List<String> newsFeedList) {
        this.newsFeedList = newsFeedList;
    }

    //Class methods


    //Implementation should add user to the tree structure
    //this function is triggered by the create user button in the admin panel
    //Composite pattern component
    @Override
    public void addToTree() {
        //Get reference to root node from admin control panel
        AdminControlPanel admin = AdminControlPanel.getInstance();
        admin.root.add((MutableTreeNode)(new User(admin.userIdField.getText())));

        //add user to JTree in this method

    }
}
