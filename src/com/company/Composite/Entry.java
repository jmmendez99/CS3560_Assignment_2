package com.company.Composite;

import com.company.Visitor.Visitable;

/*Composite pattern component*/
public interface Entry extends Visitable {

    public void addToTree();
}
