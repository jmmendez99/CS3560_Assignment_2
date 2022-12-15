package com.company.Visitor;

import com.company.Models.Group;
import com.company.Models.User;
import com.company.UI.AdminControlPanel;

import java.util.Enumeration;
import java.util.Hashtable;

public class LastUpdateEntryVisitor implements EntryVisitor {
    @Override
    public void visitUser(User user) {
        //Get reference to userDatabase from admin control panel
        Hashtable<String, User> userDatabase = AdminControlPanel.getInstance().getUserDatabase();

        //Iterate through all users and find which one updated last based on their lastUpdateTime attribute
        long latest = user.getLastUpdateTime();
        String ID = user.getUserID();

        Enumeration<String> e = userDatabase.keys();
        while (e.hasMoreElements()) {
            //Get key
            String key = e.nextElement();

            //Access lastUpdateTime attribute
            if (userDatabase.get(key).getLastUpdateTime() >= latest) {
                latest = userDatabase.get(key).getLastUpdateTime();
                ID = key;
            }
        }

        //Set user associated with the latest variable in admin control panel
        AdminControlPanel.getInstance().setLastUpdatedUser(ID);
    }

    @Override
    public void visitGroup(Group group) {}
}
