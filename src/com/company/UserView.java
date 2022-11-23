package com.company;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Objects;

public class UserView implements ActionListener {
    /*Properties of user view*/
    private String userViewUser;
    private static UserView instance;

    //Might need to make these private in the future and then use getter/setter methods to access JComponents
    public JFrame frame;
    public JButton followUserButton, tweetButton;
    public JTextArea userIdFollowField, tweetMessageField;
    public JList<String> followingJList, newsFeedJList;
    public DefaultListModel<String> followingModel, newsFeedModel;
    public JScrollPane followingListScroller, newsFeedListScroller;

    public UserView() {
        //Set up Java Swing GUI here
        /*JFrame*/
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(500, 450));


        /*JTextAreas*/
        //UserId field
        userIdFollowField = new JTextArea();
        //Styling
        userIdFollowField.setBounds(10,10,300,50);
        userIdFollowField.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));

        //Tweet Message field
        tweetMessageField = new JTextArea();
        //Styling
        tweetMessageField.setBounds(10, 210, 300, 50);
        tweetMessageField.setBorder(BorderFactory.createEtchedBorder(Color.magenta, Color.magenta));
        tweetMessageField.setLineWrap(true);


        /*JButtons*/
        //Follow button
        followUserButton = new JButton("Follow User");
        //Styling
        followUserButton.setBounds(320, 10, 140, 50);
        followUserButton.setFocusable(false);
        followUserButton.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));
        followUserButton.setBackground(new Color(128,148,230));

        //Post Tweet button
        tweetButton = new JButton("Post Tweet");
        //Styling
        tweetButton.setBounds(320, 210, 140, 50);
        tweetButton.setFocusable(false);
        tweetButton.setBorder(BorderFactory.createEtchedBorder(Color.magenta, Color.magenta));
        tweetButton.setBackground(Color.magenta);

        //Action listeners for follow and post tweet buttons
        followUserButton.addActionListener(this);
        tweetButton.addActionListener(this);


        /*JLists*/
        //Get reference to admin panel and userDatabase from admin control panel
        AdminControlPanel admin = AdminControlPanel.getInstance();

        //Current Following JList and ListModel
        followingJList = new JList<>();
        followingModel = new DefaultListModel<>();

        //Add user's following data to model
        followingModel.addElement("Users I am following:");
        for (String following : admin.getUser().getFollowingIDList()) {
            followingModel.addElement(following);
        }

        //Set data model to JList
        followingJList.setModel(followingModel);

        //Styling
        followingJList.setLayoutOrientation(JList.VERTICAL);
        followingJList.setVisibleRowCount(-1);


        //News Feed JList and ListModel
        newsFeedJList = new JList<>();
        newsFeedModel = new DefaultListModel<>();

        //Add user's news feed data to model
        newsFeedModel.addElement("News Feed:");
        for (String news : admin.getUser().getNewsFeedList()) {
            newsFeedModel.addElement(news);
        }

        //Set data model to JList
        newsFeedJList.setModel(newsFeedModel);

        //Styling
        newsFeedJList.setLayoutOrientation(JList.VERTICAL);
        newsFeedJList.setVisibleRowCount(-1);


        /*JScrollPanes*/
        //Current Following scroll pane
        followingListScroller = new JScrollPane(followingJList);
        //Styling
        followingListScroller.setBounds(10,70, 450, 125);
        followingListScroller.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));

        //News Feed scroll pane
        newsFeedListScroller = new JScrollPane(newsFeedJList);
        //Styling
        newsFeedListScroller.setBounds(10, 270, 450, 125);
        newsFeedListScroller.setBorder(BorderFactory.createEtchedBorder(Color.magenta, Color.magenta));


        /*Add components to JFrame*/
        frame.add(userIdFollowField);
        frame.add(tweetMessageField);

        frame.add(followUserButton);
        frame.add(tweetButton);

        frame.add(followingListScroller);
        frame.add(newsFeedListScroller);
        frame.setVisible(true);
    }

    /*Getters and Setters*/
    public static UserView getInstance() {
        if (instance == null) {
            instance = new UserView();
        }
        return instance;
    }

    public String getUserViewUser() { return userViewUser; }

    public void setUserViewUser() {
        //Get reference to different jTree components from admin
        AdminControlPanel admin = AdminControlPanel.getInstance();

        //Need this model to get references to selected nodes in the tree
        TreeSelectionModel selectionModel = admin.tree.getSelectionModel();

        if (selectionModel.getSelectionCount() > 0) {
            //Get selected node
            DefaultMutableTreeNode selectedNode =
                    (DefaultMutableTreeNode) Objects.requireNonNull(admin.tree.getSelectionPath()).getLastPathComponent();

            //Get userId from selected node in JTree and set UserView's frame title to that userId
            frame.setTitle(selectedNode.getUserObject().toString());

            //Also set userViewUser's value to the string above so that we can use its value later
            this.userViewUser = selectedNode.getUserObject().toString();
        }
    }

    /*Operations that are performed when each button is pressed*/
    @Override
    public void actionPerformed(ActionEvent e) {

        //Follow user
        if (e.getSource() == followUserButton) {
            //Get reference to userDatabase from admin control panel
            Hashtable<String, User> userDatabase = AdminControlPanel.getInstance().getUserDatabase();

            //Check if userId is in userDatabase
            if (!userDatabase.containsKey(userIdFollowField.getText())) {
                userIdFollowField.setText("That user does not exist.");
            } else {
                //Get user we are going to follow from userDatabase
                User targetUser = userDatabase.get(userIdFollowField.getText());

                //Attach userViewUser as an observer to the targetUser
                targetUser.attach(userDatabase.get(getUserViewUser()));

                //Add current userViewUser to the targetUser's followers ID list
                targetUser.getFollowersIDList().add(getUserViewUser());

                //Add newly followed targetUser to userViewUser's list of followings
                userDatabase.get(getUserViewUser()).getFollowingIDList().add(userIdFollowField.getText());

                //In order to update the JList automatically, we must create a new DefaultListModel,
                //add new data to the original JList, initialize the new model with the old model,
                //and then set that new model onto our original JList
                DefaultListModel<String> newFollowingModel;
                followingModel.addElement(userIdFollowField.getText()); //add to original model first so that element is appended to JList
                newFollowingModel = followingModel;
                followingJList.setModel(newFollowingModel);

                //In case a userViewUser's window is closed, we must show the newly updated
                //userViewUser's followings the next time their window is opened by setting
                //their original JList model's value to the value of newFollowingModel.
                followingModel = newFollowingModel;
            }
        }

        //Post tweet
        if (e.getSource() == tweetButton) {
            //Check if postTweetField is empty
            if (tweetMessageField.getText().isEmpty()) {
                tweetMessageField.setText("Enter a valid tweet.");
            } else {
                //Get reference to userDatabase from admin control panel
                Hashtable<String, User> userDatabase = AdminControlPanel.getInstance().getUserDatabase();

                //Get reference to userViewUser from the userDatabase
                User userViewUser = userDatabase.get(getUserViewUser());

                //Add tweet to userViewUser's news feed list
                String tweet = userViewUser.getUserID() + ": " + tweetMessageField.getText();
                userViewUser.getNewsFeedList().add(tweet);
                userViewUser.setNewsFeedList(tweet);

                //In order to update the JList automatically, we must create a new DefaultListModel,
                //add new data to the original JList, initialize the new model with the old model,
                //and then set that new model onto our original JList
                DefaultListModel<String> newNewsFeedModel;
                newsFeedModel.addElement(tweet); //add to original model first so that element is appended to JList
                newNewsFeedModel = newsFeedModel;
                newsFeedJList.setModel(newNewsFeedModel);

                //In case a userViewUser's window is closed, we must show the newly updated
                //userViewUser's followings the next time their window is opened by setting
                //their original JList model's value to the value of newFollowingModel.
                newsFeedModel = newNewsFeedModel;
            }
        }
    }
}
