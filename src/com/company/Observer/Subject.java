package com.company.Observer;

import com.company.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

//Implement observer pattern here!
public abstract class Subject {

    //Maintain a list of observers for each concrete subject
    private List<Observer> observers = new ArrayList<>();

    //Attach observers
    public void attach(Observer observer) {
        observers.add(observer);
    }

    //Notify observers
    public void notifyObservers(String tweet) {
        for (Observer observer : observers) {
            observer.update(this, observer, tweet);
        }
    }
}
