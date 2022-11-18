package com.company;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group implements Entry {
    /*Properties of group*/
    // unique ID
    private String groupID;
    // list of entries which consist of all users/subgroups that are part of the parent group
    private List<Entry> entries;

    /*Constructor*/
    public Group() {
        entries = new ArrayList<>();
    }

    /*Getter and Setters*/
    public String getGroupID() { return groupID; }

    public void setGroupID(String groupID) { this.groupID = groupID; }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    /*Class methods*/



    /*Composite pattern component*/
    //Implementation should add groups/subgroups/users to the tree structure
    @Override
    public void addToTree(ActionEvent e) {
        //check if instanceof entry is a user, then we must add it as a child of the parent group
        //  else we just create a new Group() object and add it to the tree structure

        //  the tree and find where we should add the child user/group

        //set entries
        //get entries
        //call addToTree() method on each entry
        //whenever a group is created

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
                    new DefaultMutableTreeNode(admin.groupIdField.getText());

            //Add newNode to the selected parent node
            selectedNode.add(newNode);

            //Get reference to JTree's model and reload model with new node
            DefaultTreeModel model = (DefaultTreeModel) admin.tree.getModel();
            model.reload();
        }
    }
}
