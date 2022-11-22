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

    //Might need to make these private in the future and then use getter/setter methods to access JComponents
    public JFrame frame;
    public JButton followUserButton, postTweetButton;
    public JTextArea userIdFollowField, tweetMessageField;
    public JList<String> followingList, newsFeedList;
    public DefaultListModel<String> followingModel, newsFeedModel;
    public JScrollPane followingListScroller, newsFeedListScroller;

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
        userIdFollowField = new JTextArea();
        tweetMessageField = new JTextArea();

        //Styling
        userIdFollowField.setBounds(10,10,300,50);
        tweetMessageField.setBounds(10, 210, 300, 50);

        userIdFollowField.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));
        tweetMessageField.setBorder(BorderFactory.createEtchedBorder(Color.magenta, Color.magenta));
        tweetMessageField.setLineWrap(true);


        /*JButtons*/
        //Follow and Post Tweet buttons
        followUserButton = new JButton("Follow User");
        postTweetButton = new JButton("Post Tweet");

        //Styling
        followUserButton.setBounds(320, 10, 140, 50);
        postTweetButton.setBounds(320, 210, 140, 50);

        followUserButton.setFocusable(false);
        postTweetButton.setFocusable(false);

        followUserButton.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));
        postTweetButton.setBorder(BorderFactory.createEtchedBorder(Color.magenta, Color.magenta));

        followUserButton.setBackground(new Color(128,148,230));
        postTweetButton.setBackground(Color.magenta);

        //Action listeners for follow and post tweet buttons
        followUserButton.addActionListener(this);
        postTweetButton.addActionListener(this);


        /*JLists*/
        //Current Following JList and ListModel
        followingList = new JList<>();
        followingModel = new DefaultListModel<>();

        //Add following data to model
        for (String following : user.getFollowingIDList()) {
            followingModel.addElement(following);
        }

        //Set data model to JList
        followingList.setModel(followingModel);

        //Styling
        followingList.setLayoutOrientation(JList.VERTICAL);
        followingList.setVisibleRowCount(-1);

        //News Feed JList and ListModel
        newsFeedList = new JList<>();
        newsFeedModel = new DefaultListModel<>();

        //Add news feed data to model
        for (String news : user.getNewsFeedList()) {
            newsFeedModel.addElement(news);
        }

        //Set data model to JList
        newsFeedList.setModel(newsFeedModel);

        //Styling
        newsFeedList.setLayoutOrientation(JList.VERTICAL);
        newsFeedList.setVisibleRowCount(-1);


        /*JScrollPanes*/
        //Current Following scroll pane
        followingListScroller = new JScrollPane(followingList);

        //Styling
        followingListScroller.setBounds(10,70, 450, 125);
        followingListScroller.setBorder(BorderFactory.createEtchedBorder(new Color(128,148,230), new Color(128,148,230)));

        //News Feed scroll pane
        newsFeedListScroller = new JScrollPane(newsFeedList);

        //Styling
        newsFeedListScroller.setBounds(10, 270, 450, 125);
        newsFeedListScroller.setBorder(BorderFactory.createEtchedBorder(Color.magenta, Color.magenta));


        /*Add components to JFrame*/
        frame.add(userIdFollowField);
        frame.add(tweetMessageField);

        frame.add(followUserButton);
        frame.add(postTweetButton);

        frame.add(followingListScroller);
        frame.add(newsFeedListScroller);
        frame.setVisible(true);
    }

    /*Getters and Setters*/
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
                //populate that model with data, and then set that model onto our original JList
                DefaultListModel<String> newFollowingModel = new DefaultListModel<>();
                newFollowingModel.addElement(userIdFollowField.getText());
                followingList.setModel(newFollowingModel);

                //In case a userViewUser's window is closed, we must show the newly updated
                //userViewUser's followings the next time their window is opened by setting
                //their original JList model's value to the value of newFollowingModel.
                followingModel = newFollowingModel;
            }
        }

        //Post tweet
        //TODO: whenever this button is pressed, we should call the notifyObservers() function
        //TODO: also, we should call the update() function here for all observers
        //TODO: need to reload the UserView's DefaultListModel after the new data has been created
        if (e.getSource() == postTweetButton) {

            //Check if postTweetField is empty
            if (tweetMessageField.getText().isEmpty()) {
                tweetMessageField.setText("Enter a valid tweet.");
            } else {
                //Get reference to userDatabase from admin control panel
                Hashtable<String, User> userDatabase = AdminControlPanel.getInstance().getUserDatabase();

                //Get userViewUser's Users object
                User userViewUser = userDatabase.get(getUserViewUser());

                //TODO: whenever a new tweet is added to a users list, we should then call the notifyObservers()
                // method which will call the update() method for each observer/follower of userViewUser.
                // We should put the notifyObservers() method in a setter/add method so that whenever a user
                // adds a tweet to their own newsFeedList, observers will receive that change and update their
                // respective newsFeed JLists.


            }

        }

    }
}
