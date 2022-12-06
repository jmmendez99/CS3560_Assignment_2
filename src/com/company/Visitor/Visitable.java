package com.company.Visitor;

import com.company.Visitor.EntryVisitor;

/*Visitor pattern component*/
//TODO: implement this interface for both the User and Group classes
public interface Visitable {

    public void accept(EntryVisitor visitor);
}
