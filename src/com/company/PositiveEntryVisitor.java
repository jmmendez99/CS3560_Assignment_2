package com.company;

/*Visitor pattern component*/
public class PositiveEntryVisitor implements EntryVisitor {

    @Override
    public int visitUser(User user) {
        return 0;
    }

    @Override
    public int visitGroup(Group group) {
        for (Entry entry: group.getEntries()) {
            entry.accept(this);
        }
        return 0;
    }
}
