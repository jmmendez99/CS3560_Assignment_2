package com.company;

/*Visitor pattern component*/
public class CountEntryVisitor implements EntryVisitor {

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
