package com.company.Visitor;

/*Visitor pattern component*/
public interface Visitable {

    void accept(EntryVisitor visitor);
}
