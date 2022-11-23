package com.company;

import java.util.ArrayList;
import java.util.List;

//Implement observer pattern here!
public abstract class Subject {

    //Maintain a list of observers for each concrete subject
    private List<Observer> observers = new ArrayList<Observer>();

    public List<Observer> getObservers() {
        return observers;
    }

    //Attach observers
    public void attach(Observer observer) {
        observers.add(observer);
    }

    //Notify observers
    public void notifyObservers(String tweet) {
        for (Observer observer : observers) {
            observer.update(this, tweet);
        }
    }
}
