package com.company;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group implements Entry, Visitable {
    /*Properties of group*/
    //unique ID
    private String groupID;
    //list of entries which consist of all users/subgroups that are part of the parent group
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

    //TODO: check if selected node is another group. If not, we cannot add the new group to that selected node
    //TODO: there has to be a function to call on each entry in the list of entries in order to have a true composite pattern, I think
    //      one function idea: check instanceof each entry in the list and change their JTree icon if they are a group or a user
    /*Composite pattern component*/
    //Implementation should add groups/subgroups/users to the tree structure
    @Override
    public void addToTree() {
        //check if instanceof entry is a user, then we must add it as a child of the parent group
        //  else we just create a new Group() object and add it to the tree structure

        //Get reference to different jTree components from admin
        AdminControlPanel admin = AdminControlPanel.getInstance();

        //Need this model to get references to selected nodes in the tree
        TreeSelectionModel selectionModel = admin.tree.getSelectionModel();

        if (selectionModel.getSelectionCount() > 0) {
            //Get selected node
            DefaultMutableTreeNode selectedNode =
                    (DefaultMutableTreeNode) Objects.requireNonNull(admin.tree.getSelectionPath()).getLastPathComponent();

            //Get groupId from TextField
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(admin.groupIdField.getText().toUpperCase());

            //Add newNode to the selected parent node
            selectedNode.add(newNode);

            //Get reference to JTree's model and reload model with new node
            DefaultTreeModel model = (DefaultTreeModel) admin.tree.getModel();
            model.reload();
        }
    }

    @Override
    public void accept(EntryVisitor visitor) {
        visitor.visitGroup(this);
    }
}
