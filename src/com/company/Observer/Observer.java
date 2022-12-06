package com.company.Observer;

//Implement observer pattern here!
public interface Observer {

    //This method will be called by observers
    public void update(Subject subject, Observer observer, String tweet);
}