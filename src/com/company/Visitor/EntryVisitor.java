package com.company.Visitor;

import com.company.Models.Group;
import com.company.Models.User;

/*Visitor pattern component*/
public interface EntryVisitor {

    public int visitUser(User user);
    public int visitGroup(Group group);
}
