package com.company;

import javax.swing.*;
import java.awt.*;

//We do not use the singleton pattern here because we want to create
//multiple versions of this view when users click on users in the tree.
public class UserView {

    //public constructor
    public UserView() {
        //Set up Java Swing GUI here
        //JFrame set up
        JFrame frame = new JFrame();
        frame.setTitle("Mini Twitter"); //replace this title with
                                        //user we are viewing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setVisible(true);
    }
}
