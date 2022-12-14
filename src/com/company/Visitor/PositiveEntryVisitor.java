package com.company.Visitor;

import com.company.Composite.Entry;
import com.company.Models.Group;
import com.company.Models.User;

/*Visitor pattern component*/
public class PositiveEntryVisitor implements EntryVisitor {

    @Override
    public void visitUser(User user) {
    }

    @Override
    public void visitGroup(Group group) {
        for (Entry entry: group.getEntries()) {
            entry.accept(this);
        }
    }
}
