package com.company;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Entry{
    /*Properties of user*/
    //  unique id
    private String userID;
    //  list of user IDs that are following this user(followers)
    private List<String> followersIDList;
    //  list of user IDs being followed by this user(followings)
    private List<String> followingIDList;
    //  list of tweets from this user
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

    public void setNewsFeedList(List<String> newsFeedList) {
        this.newsFeedList = newsFeedList;
    }

    /*Class methods*/


    //TODO: if adding users to an existing group, need to update that group's list of users.
    /*Composite pattern component*/
    //Implementation should add user to the tree structure
    @Override
    public void addToTree(ActionEvent e) {
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
}
