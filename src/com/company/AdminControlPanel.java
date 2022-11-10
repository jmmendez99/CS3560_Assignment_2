package com.company;


//Implement singleton pattern here!
public class AdminControlPanel {

    //3 required things for singleton pattern:
    //private static reference
    private static AdminControlPanel instance;

    //static getter
    public static AdminControlPanel getInstance() {
        if (instance == null) {
            instance = new AdminControlPanel();
        }
        return instance;
    }

    //private constructor
    private AdminControlPanel() {
        //Set up Java Swing GUI here


    }

}
