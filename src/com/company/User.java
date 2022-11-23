package com.company;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends Subject implements Entry , Observer {
    /*Properties of user*/
    //unique id
    private String userID;
    //list of user IDs that are following this user(followers)
    private List<String> followersIDList;
    //list of user IDs being followed by this user(followings)
    private List<String> followingIDList;
    //list of tweets from this user
    private List<String> newsFeedList;

    /*Constructor*/
    public User() {
        this.followersIDList = new ArrayList<>();
        this.followingIDList = new ArrayList<>();
        this.newsFeedList = new ArrayList<>();
    }

    /*Getter and Setters*/
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

    public void setNewsFeedList(String tweet) {
        this.newsFeedList.add(tweet);
        notifyObservers(tweet);
    }


    /*Class methods*/

    //TODO: if adding users to an existing group, need to update that group's list of users.
    /*Composite pattern component*/
    //Implementation should add user to the tree structure
    @Override
    public void addToTree() {
        //Get reference to different jTree components from admin
        AdminControlPanel admin = AdminControlPanel.getInstance();

        //Need this model to get references to selected nodes in the tree
        TreeSelectionModel selectionModel = admin.tree.getSelectionModel();

        if (selectionModel.getSelectionCount() > 0) {
            //Get selected node
            DefaultMutableTreeNode selectedNode =
                    (DefaultMutableTreeNode) Objects.requireNonNull(admin.tree.getSelectionPath()).getLastPathComponent();

            //Get userId from TextField
            DefaultMutableTreeNode newNode =
                    new DefaultMutableTreeNode(admin.userIdField.getText());

            //Add newNode to the selected parent node
            selectedNode.add(newNode);

            //Get reference to JTree's model and reload model with new node
            DefaultTreeModel model = (DefaultTreeModel) admin.tree.getModel();
            model.reload();
        }
    }

    //TODO: this method needs to be changed in order to get the observer pattern correct.
    /*Observer pattern component*/
    @Override
    public void update(Subject subject, String tweet) {
        //Check if subject's type is that of a User
        if (subject instanceof User) {
            //Get reference to userView from UserView
            UserView userView = UserView.getInstance();

            //Add tweet user observer's news feed list

            //In order to update the JList automatically, we must create a new DefaultListModel,
            //add new data to the original JList, initialize the new model with the old model,
            //and then set that new model onto our original JList
            DefaultListModel<String> newNewsFeedModel;
            userView.newsFeedModel.addElement(tweet); //add to original model first so that element is appended to JList
            newNewsFeedModel = userView.newsFeedModel;
            userView.newsFeedJList.setModel(newNewsFeedModel);

            //In case a userViewUser's window is closed, we must show the newly updated
            //userViewUser's followings the next time their window is opened by setting
            //their original JList model's value to the value of newFollowingModel.
            userView.newsFeedModel = newNewsFeedModel;

        }
    }
}
