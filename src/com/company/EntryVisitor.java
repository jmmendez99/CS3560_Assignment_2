package com.company;

/*Visitor pattern component*/
public interface EntryVisitor {

    public int visitUser(User user);
    public int visitGroup(Group group);
}
