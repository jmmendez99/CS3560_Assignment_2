package com.company;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.Objects;

//We do not use the singleton pattern here because we want to create
//multiple versions of this view when users want to see info on a specific user.
public class UserView {
    /*Properties of user view*/
    private UserView instance;
    private String userId;

    //Might need to make these private in the future and then use getter/setter methods to access JComponents
    public JFrame frame;

    /*Constructor*/
    public UserView() {
        //Set up Java Swing GUI here
        /*JFrame*/
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(500, 450));

        /*JTextAreas*/
        JTextArea userIdFollowField = new JTextArea();
        JTextArea tweetMessageField = new JTextArea();

        userIdFollowField.setBounds(10,10,300,50);
        tweetMessageField.setBounds(10, 210, 300, 50);

        userIdFollowField.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));
        tweetMessageField.setBorder(BorderFactory.createEtchedBorder(Color.magenta, Color.magenta));
        tweetMessageField.setLineWrap(true);

        /*JButtons*/
        JButton userIdFollowButton = new JButton("Follow User");
        JButton postTweetButton = new JButton("Post Tweet");

        userIdFollowButton.setBounds(320, 10, 140, 50);
        postTweetButton.setBounds(320, 210, 140, 50);

        userIdFollowButton.setFocusable(false);
        postTweetButton.setFocusable(false);

        userIdFollowButton.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));
        postTweetButton.setBorder(BorderFactory.createEtchedBorder(Color.magenta, Color.magenta));

        userIdFollowButton.setBackground(new Color(128,148,230));
        postTweetButton.setBackground(Color.magenta);

        /*JLists*/
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

        /*JScrollPanes*/
        //Current Following
        JScrollPane followingListScroller = new JScrollPane(followingList);
        followingListScroller.setBounds(10,70, 450, 125);
        followingListScroller.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));

        //News Feed
        JScrollPane newsFeedListScroller = new JScrollPane(newsFeedList);
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
    public UserView getInstance() {
        if (instance == null) {
            instance = new UserView();
        }
        return instance;
    }

    public String getUserId() { return userId; }

    public void setUserId() {
        //Get reference to different jTree components from admin
        AdminControlPanel admin = AdminControlPanel.getInstance();

        //Need this model to get references to selected nodes in the tree
        TreeSelectionModel selectionModel = admin.tree.getSelectionModel();

        if (selectionModel.getSelectionCount() > 0) {
            //Get selected node
            DefaultMutableTreeNode selectedNode =
                    (DefaultMutableTreeNode) Objects.requireNonNull(admin.tree.getSelectionPath()).getLastPathComponent();

            //Get userId from selected node in JTree and set UserView's userId with that input
            frame.setTitle(this.userId = selectedNode.getUserObject().toString());
        }
    }
}
