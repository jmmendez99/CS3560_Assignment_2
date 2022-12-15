package com.company.Visitor;

import com.company.Composite.Entry;
import com.company.Models.Group;
import com.company.Models.User;
import com.company.UI.AdminControlPanel;

/*Visitor pattern component*/
public class CountEntryVisitor implements EntryVisitor {

    @Override
    public void visitUser(User user) {
        //Get reference to userCount from admin control panel
        int userCount  = AdminControlPanel.getInstance().getUserCount();

        //Increase by 1 and then set that value in admin control panel
        userCount++;
        AdminControlPanel.getInstance().setUserCount(userCount);
    }

    @Override
    public void visitGroup(Group group) {
        //Get reference to userCount from admin control panel
        int groupCount  = AdminControlPanel.getInstance().getGroupCount();

        //Increase by 1 and then set that value in admin control panel
        groupCount++;
        AdminControlPanel.getInstance().setGroupCount(groupCount);
    }
}
