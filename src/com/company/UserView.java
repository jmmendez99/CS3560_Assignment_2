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
        frame.setTitle("UserView"); //replace this title with
                                        //user we are viewing
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(500, 450));

        //JTextAreas
        JTextArea userIdFollowField = new JTextArea();
        JTextArea tweetMessageField = new JTextArea();

        userIdFollowField.setBounds(10,10,300,50);
        tweetMessageField.setBounds(10, 210, 300, 50);

        userIdFollowField.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.blue));
        tweetMessageField.setBorder(BorderFactory.createEtchedBorder(Color.orange, Color.orange));
        tweetMessageField.setLineWrap(true);

        //JButtons
        JButton userIdFollowButton = new JButton("Follow User");
        JButton tweetMessageButton = new JButton("Post Tweet");

        userIdFollowButton.setBounds(320, 10, 140, 50);
        tweetMessageButton.setBounds(320, 210, 140, 50);

        //JLists
        //Current Following
        JList<String> followingList = new JList<>();
        DefaultListModel<String> followingModel = new DefaultListModel<>();

        followingList.setModel(followingModel);
        followingList.setLayoutOrientation(JList.VERTICAL);
        followingList.setVisibleRowCount(-1);

        followingModel.addElement("Bob"); //test data for current following JList

        //News Feed
        JList<String> newsFeedList = new JList<>();
        DefaultListModel<String> newsFeedModel = new DefaultListModel<>();

        newsFeedList.setModel(newsFeedModel);
        newsFeedList.setLayoutOrientation(JList.VERTICAL);
        newsFeedList.setVisibleRowCount(-1);

        newsFeedModel.addElement("First tweet"); //test data for news feed JList

        //JScrollPanes
        //Current Following
        JScrollPane followingListScroller = new JScrollPane(followingList);
        followingListScroller.setBounds(10,70, 450, 125);
        followingListScroller.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.blue));

        //News Feed
        JScrollPane newsFeedListScroller = new JScrollPane(newsFeedList);
        newsFeedListScroller.setBounds(10, 270, 450, 125);
        newsFeedListScroller.setBorder(BorderFactory.createEtchedBorder(Color.orange, Color.orange));

        //Add components to JFrame
        frame.add(userIdFollowField);
        frame.add(tweetMessageField);

        frame.add(userIdFollowButton);
        frame.add(tweetMessageButton);

        frame.add(followingListScroller);
        frame.add(newsFeedListScroller);
        frame.setVisible(true);
    }
}
