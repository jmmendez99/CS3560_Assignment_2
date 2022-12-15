package com.company.Visitor;

import com.company.Models.Group;
import com.company.Models.User;

/*Visitor pattern component*/
public interface EntryVisitor {

    void visitUser(User user);
    void visitGroup(Group group);
}
