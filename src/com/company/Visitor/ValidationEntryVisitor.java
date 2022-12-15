package com.company.Visitor;

import com.company.Models.Group;
import com.company.Models.User;
import com.company.UI.AdminControlPanel;
import java.util.Hashtable;

public class ValidationEntryVisitor implements EntryVisitor {

    @Override
    public void visitUser(User user) {
        String ID = user.getUserID();

        //Get reference to user and group Database from admin control panel
        Hashtable<String, User> userDatabase = AdminControlPanel.getInstance().getUserDatabase();
        Hashtable<String, Group> groupDatabase = AdminControlPanel.getInstance().getGroupDatabase();

        //Traverse all userIDs and check the new user ID against the others in userDatabase
        if (userDatabase.contains(ID) || groupDatabase.containsKey(ID) || ID.matches(".*\\s.*")) {
            AdminControlPanel.getInstance().setIsIdInvalid(true);
        }
    }

    @Override
    public void visitGroup(Group group) {
        String ID = group.getGroupID();

        //Get reference to user Database from admin control panel
        Hashtable<String, User> userDatabase = AdminControlPanel.getInstance().getUserDatabase();

        //Traverse all userIDs and check the new user ID against the others in userDatabase
        if (userDatabase.contains(ID) || ID.matches(".*\\s.*")) {
            AdminControlPanel.getInstance().setIsIdInvalid(true);
        }
    }
}
