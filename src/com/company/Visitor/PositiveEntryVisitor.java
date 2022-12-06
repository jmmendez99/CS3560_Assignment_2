package com.company.Visitor;

import com.company.Composite.Entry;
import com.company.Models.Group;
import com.company.Models.User;

/*Visitor pattern component*/
public class PositiveEntryVisitor implements EntryVisitor {

    @Override
    public int visitUser(User user) {
        return 1;
    }

    @Override
    public int visitGroup(Group group) {
        int counter = 0;
        for (Entry entry: group.getEntries()) {
            entry.accept(this);
            counter++;
        }
        return counter;
    }
}
