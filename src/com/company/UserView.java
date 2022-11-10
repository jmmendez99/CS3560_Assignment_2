package com.company;

import javax.swing.*;
import java.awt.*;

//We do not use the singleton pattern here because we want to create
//multiple versions of this view when users click on users in the tree.
public class UserView {

    //public constructor
    public UserView() {
        //Set up Java Swing GUI here
        //JFrame set up
        JFrame frame = new JFrame();
        frame.setTitle("Mini Twitter"); //replace this title with
                                        //user we are viewing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setVisible(true);

        //JTextAreas
        JTextArea userIdFollowField = new JTextArea();
        JTextArea tweetMessageField = new JTextArea();

        userIdFollowField.setBounds(10,10,250,50);
        //tweetMessageField.setBounds(270, 10, 250, 50);

        //JButtons
        JButton userIdFollowButton = new JButton("Follow User");
        JButton tweetMessageButton = new JButton("Post Tweet");

        tweetMessageButton.setBounds(270, 10, 250, 50);

        //add setBounds methods here for buttons

        //JLists
        //List of people currently followed
        JList<String> followingList = new JList<>();
        DefaultListModel<String> followingModel = new DefaultListModel<>();
        followingList.setModel(followingModel);
        followingList.setLayoutOrientation(JList.VERTICAL);
        followingList.setVisibleRowCount(-1);
        //test data for current following JList
        followingModel.addElement("Bob");

        //List of tweets from yourself and people you follows
        JList<String> newsFeedList = new JList<>();
        DefaultListModel<String> newsFeedModel = new DefaultListModel<>();
        newsFeedList.setModel(newsFeedModel);
        newsFeedList.setLayoutOrientation(JList.VERTICAL);
        newsFeedList.setVisibleRowCount(-1);
        //test data for news feed JList
        newsFeedModel.addElement("First tweet");

        //JScrollPanes for data in JLists
        JScrollPane followingListScroller = new JScrollPane(followingList);
        followingListScroller.setBounds(10,70, 500, 150);

        JScrollPane newsFeedListScroller = new JScrollPane(newsFeedList);
        //newsFeedListScroller.setBounds();

        //Add components to frame
        frame.add(userIdFollowField);
        frame.add(tweetMessageField);
        frame.add(userIdFollowButton);
        frame.add(tweetMessageButton);
        frame.add(followingListScroller);

    }
}
