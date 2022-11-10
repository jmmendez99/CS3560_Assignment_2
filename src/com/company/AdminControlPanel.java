package com.company;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

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
        //JFrame set up
        JFrame frame = new JFrame();
        frame.setTitle("Mini Twitter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(960, 700));
        frame.setVisible(true);

        //JTree set up
        DefaultMutableTreeNode root = new  DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode group = new  DefaultMutableTreeNode("Group");
        root.add(group);

        JTree tree = new JTree(root);
        tree.setBounds(10,10,350,500);
        tree.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.LIGHT_GRAY));

        //JTextFields
        JTextField userIdField = new JTextField(5);
        JTextField groupIdField = new JTextField(5);

        userIdField.setBounds(375, 10, 270, 50);
        groupIdField.setBounds(375, 70, 270, 50);
        userIdField.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.LIGHT_GRAY));
        groupIdField.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.LIGHT_GRAY));

        //JButtons
        //User and Group Buttons
        JButton addUserButton = new JButton("Add User");
        JButton addGroupButton = new JButton("Add Group");
        JButton openUserViewButton = new JButton("Open User View");

        addUserButton.setBounds(650, 10, 100, 50);
        addGroupButton.setBounds(650, 70, 100, 50);
        openUserViewButton.setBounds(375, 130 , 375, 50);
        addUserButton.setFocusable(false);
        addGroupButton.setFocusable(false);
        openUserViewButton.setFocusable(false);

        //Analysis Buttons
        JButton showTotalUsersButton = new JButton("Show Total Users");
        JButton showTotalGroupsButton = new JButton("Show Total Groups");
        JButton showTotalTweetsButton = new JButton("Show Total Tweets");
        JButton showPercentPositiveButton = new JButton("Show Positive Percent");

        showTotalUsersButton.setBounds(375, 400, 180, 50);
        showTotalGroupsButton.setBounds(570, 400, 180, 50);
        showTotalTweetsButton.setBounds(375, 460, 180, 50);
        showPercentPositiveButton.setBounds(570, 460, 180, 50);
        showTotalUsersButton.setFocusable(false);
        showTotalGroupsButton.setFocusable(false);
        showTotalTweetsButton.setFocusable(false);
        showPercentPositiveButton.setFocusable(false);

        //Add components to JFrame
        frame.add(tree);
        frame.add(userIdField);
        frame.add(groupIdField);
        frame.add(addUserButton);
        frame.add(addGroupButton);
        frame.add(openUserViewButton);
        frame.add(showTotalUsersButton);
        frame.add(showTotalGroupsButton);
        frame.add(showTotalTweetsButton);
        frame.add(showPercentPositiveButton);

    }
}
