package com.company;

import java.util.ArrayList;
import java.util.List;

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
        this.userID = userID; //this should be the string the user puts in the text field
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
        //add user to JTree in this method
    }
}
