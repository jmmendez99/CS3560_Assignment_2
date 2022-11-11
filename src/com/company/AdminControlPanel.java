package com.company;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Implement singleton pattern here!
public class AdminControlPanel implements ActionListener {

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

    //Making global references to buttons and text-fields and tree
    JTree tree;
    JTextField userIdField, groupIdField;
    JButton addUserButton, addGroupButton , openUserViewButton ;
    JButton showTotalUsersButton, showTotalGroupsButton, showTotalTweetsButton,  showPercentPositiveButton;

    //private constructor
    public AdminControlPanel() {
        //Set up Java Swing GUI here
        //JFrame set up
        JFrame frame = new JFrame();
        frame.setTitle("Mini Twitter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(780, 575));

        //JTree set up
        DefaultMutableTreeNode root = new  DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode group = new  DefaultMutableTreeNode("Group");
        root.add(group);

        tree = new JTree(root);
        tree.setBounds(10,10,350,500);
        tree.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.LIGHT_GRAY));

        //JTextFields
        userIdField = new JTextField(5);
        groupIdField = new JTextField(5);

        userIdField.setBounds(375, 10, 270, 50);
        groupIdField.setBounds(375, 70, 270, 50);

        userIdField.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.LIGHT_GRAY));
        groupIdField.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.LIGHT_GRAY));

        //JButtons
        //User and Group Buttons
        addUserButton = new JButton("Add User");
        addGroupButton = new JButton("Add Group");
        openUserViewButton = new JButton("Open User View");

        addUserButton.setBounds(650, 10, 100, 50);
        addGroupButton.setBounds(650, 70, 100, 50);
        openUserViewButton.setBounds(375, 130 , 375, 50);

        addUserButton.addActionListener(this);
        addGroupButton.addActionListener(this);
        openUserViewButton.addActionListener(this);

        addUserButton.setFocusable(false);
        addGroupButton.setFocusable(false);
        openUserViewButton.setFocusable(false);


        //Analysis Buttons
        showTotalUsersButton = new JButton("Show Total Users");
        showTotalGroupsButton = new JButton("Show Total Groups");
        showTotalTweetsButton = new JButton("Show Total Tweets");
        showPercentPositiveButton = new JButton("Show Positive Percent");

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
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openUserViewButton) {
            UserView userView = new UserView();
        }
        if (e.getSource() == addUserButton) {
            User user = new User(userIdField.getText());
            user.addToTree();
        }
        if (e.getSource() == addGroupButton) {
            Group group = new Group(groupIdField.getText());
            group.addToTree();
        }

    }
}
