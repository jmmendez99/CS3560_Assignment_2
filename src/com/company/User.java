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
    private List<String> newsFeed;

    //Constructor
    //whenever a new user is instantiated, these things will be created/initialized
    //this constructor is triggered by the create user button in the admin panel
    public User(String userID) {
        this.userID = userID; //this should be the string the user puts in the text field
        this.followersIDList = new ArrayList<>();
        this.followingIDList = new ArrayList<>();
        this.newsFeed = new ArrayList<>();
    }

    //Getter and Setters

    //Class methods


    //Implementation should add user to the tree structure
    //this function is triggered by the create user button in the admin panel
    //Composite pattern component
    @Override
    public void addToTree() {
        //add user to JTree in this method
    }
}
