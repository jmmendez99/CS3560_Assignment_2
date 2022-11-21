package com.company;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.Objects;

public class UserView {
    /*Properties of user view*/
    //Might need to make these private in the future and then use getter/setter methods to access JComponents
    public JFrame frame;

    /*Constructor*/
    public UserView(User user) {
        //Set up Java Swing GUI here
        /*JFrame*/
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(500, 450));


        /*JTextAreas*/
        //UserId and Tweet Message fields
        JTextArea userIdFollowField = new JTextArea();
        JTextArea tweetMessageField = new JTextArea();

        //Styling
        userIdFollowField.setBounds(10,10,300,50);
        tweetMessageField.setBounds(10, 210, 300, 50);

        userIdFollowField.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));
        tweetMessageField.setBorder(BorderFactory.createEtchedBorder(Color.magenta, Color.magenta));
        tweetMessageField.setLineWrap(true);


        /*JButtons*/
        //Follow and Post Tweet buttons
        JButton userIdFollowButton = new JButton("Follow User");
        JButton postTweetButton = new JButton("Post Tweet");

        //Styling
        userIdFollowButton.setBounds(320, 10, 140, 50);
        postTweetButton.setBounds(320, 210, 140, 50);

        userIdFollowButton.setFocusable(false);
        postTweetButton.setFocusable(false);

        userIdFollowButton.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));
        postTweetButton.setBorder(BorderFactory.createEtchedBorder(Color.magenta, Color.magenta));

        userIdFollowButton.setBackground(new Color(128,148,230));
        postTweetButton.setBackground(Color.magenta);


        /*JLists*/
        //Current Following JList and ListModel
        JList<String> followingList = new JList<>();
        DefaultListModel<String> followingModel = new DefaultListModel<>();

        //Add following data to model
        followingModel.addElement("Bob");

        //Styling
        followingList.setModel(followingModel);
        followingList.setLayoutOrientation(JList.VERTICAL);
        followingList.setVisibleRowCount(-1);

        //News Feed JList and ListModel
        JList<String> newsFeedList = new JList<>();
        DefaultListModel<String> newsFeedModel = new DefaultListModel<>();

        //TODO: need to put .addElement calls in the actionPerformed function for this class' buttons
        //Add news feed data to model
        newsFeedModel.addElement("First tweet");

        //Styling
        newsFeedList.setModel(newsFeedModel);
        newsFeedList.setLayoutOrientation(JList.VERTICAL);
        newsFeedList.setVisibleRowCount(-1);


        /*JScrollPanes*/
        //Current Following scroll pane
        JScrollPane followingListScroller = new JScrollPane(followingList);

        //Styling
        followingListScroller.setBounds(10,70, 450, 125);
        followingListScroller.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));

        //News Feed scroll pane
        JScrollPane newsFeedListScroller = new JScrollPane(newsFeedList);

        //Styling
        newsFeedListScroller.setBounds(10, 270, 450, 125);
        newsFeedListScroller.setBorder(BorderFactory.createEtchedBorder(Color.magenta, Color.magenta));


        /*Add components to JFrame*/
        frame.add(userIdFollowField);
        frame.add(tweetMessageField);

        frame.add(userIdFollowButton);
        frame.add(postTweetButton);

        frame.add(followingListScroller);
        frame.add(newsFeedListScroller);
        frame.setVisible(true);
    }

    /*Getters and Setters*/
    public void setWindowTitle() {
        //Get reference to different jTree components from admin
        AdminControlPanel admin = AdminControlPanel.getInstance();

        //Need this model to get references to selected nodes in the tree
        TreeSelectionModel selectionModel = admin.tree.getSelectionModel();

        if (selectionModel.getSelectionCount() > 0) {
            //Get selected node
            DefaultMutableTreeNode selectedNode =
                    (DefaultMutableTreeNode) Objects.requireNonNull(admin.tree.getSelectionPath()).getLastPathComponent();

            //Get userId from selected node in JTree and set UserView's window title to that userId
            frame.setTitle(selectedNode.getUserObject().toString());
        }
    }
}
