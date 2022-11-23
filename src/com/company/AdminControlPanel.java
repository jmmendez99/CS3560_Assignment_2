package com.company;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Objects;

//Implement singleton pattern here!
public class AdminControlPanel implements ActionListener {
    /*Properties of admin panel*/
    //Database of users and groups and userViews
    private Hashtable<String, User> userDatabase = new Hashtable<>();
    private Hashtable<String, Group> groupDatabase = new Hashtable<>();
    private Hashtable<String, UserView> userViewDatabase = new Hashtable<>();

    //Might need to make these private in the future and then use getter/setter methods to access JComponents
    public JTree tree;
    public DefaultMutableTreeNode root;
    public JTextField userIdField, groupIdField;
    public JButton addUserButton, addGroupButton , openUserViewButton ;
    public JButton showTotalUsersButton, showTotalGroupsButton, showTotalTweetsButton,  showPercentPositiveButton;

    //private static reference
    private static AdminControlPanel instance;

    /*private constructor*/
    private AdminControlPanel() {
        //Set up Java Swing GUI here
        /*JFrame*/
        JFrame frame = new JFrame();
        frame.setTitle("Mini Twitter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(780, 575));


        /*JTree*/
        //Root node
        root = new  DefaultMutableTreeNode("Root");

        //Add root node to JTree
        tree = new JTree(root);

        //Styling
        tree.setBounds(10,10,350,500);
        tree.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.LIGHT_GRAY));
        tree.setShowsRootHandles(true);


        /*JTextFields*/
        userIdField = new JTextField(5);
        groupIdField = new JTextField(5);

        //Styling
        userIdField.setBounds(375, 10, 270, 50);
        groupIdField.setBounds(375, 70, 270, 50);

        userIdField.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.LIGHT_GRAY));
        groupIdField.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.LIGHT_GRAY));


        /*JButtons*/
        //User and Group Buttons
        addUserButton = new JButton("Add User");
        addGroupButton = new JButton("Add Group");
        openUserViewButton = new JButton("Open User View");

        //Styling
        addUserButton.setBounds(650, 10, 100, 50);
        addGroupButton.setBounds(650, 70, 100, 50);
        openUserViewButton.setBounds(375, 130 , 375, 50);

        addUserButton.setFocusable(false);
        addGroupButton.setFocusable(false);
        openUserViewButton.setFocusable(false);

        //Action listeners for user/group buttons
        addUserButton.addActionListener(this);
        addGroupButton.addActionListener(this);
        openUserViewButton.addActionListener(this);


        /*Analysis Buttons*/
        //These buttons will show statistics of users/groups
        showTotalUsersButton = new JButton("Show Total Users");
        showTotalGroupsButton = new JButton("Show Total Groups");
        showTotalTweetsButton = new JButton("Show Total Tweets");
        showPercentPositiveButton = new JButton("Show Positive Percent");

        //Styling
        showTotalUsersButton.setBounds(375, 400, 180, 50);
        showTotalGroupsButton.setBounds(570, 400, 180, 50);
        showTotalTweetsButton.setBounds(375, 460, 180, 50);
        showPercentPositiveButton.setBounds(570, 460, 180, 50);

        showTotalUsersButton.setFocusable(false);
        showTotalGroupsButton.setFocusable(false);
        showTotalTweetsButton.setFocusable(false);
        showPercentPositiveButton.setFocusable(false);

        //Action listeners for analysis buttons
        showTotalUsersButton.addActionListener(this);
        showTotalGroupsButton.addActionListener(this);
        showTotalTweetsButton.addActionListener(this);
        showPercentPositiveButton.addActionListener(this);


        /*Add components to JFrame*/
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

    /*Getters and Setters*/
    //static getter for private constructor
    public static AdminControlPanel getInstance() {
        if (instance == null) {
            instance = new AdminControlPanel();
        }
        return instance;
    }

    public Hashtable<String, User> getUserDatabase() { return userDatabase; }

    public Hashtable<String, Group> getGroupDatabase() { return groupDatabase; }

    public Hashtable<String, UserView> getUserViewDatabase() { return userViewDatabase; }

    public String getUserID() {
        //Need this model to get references to selected nodes in the tree
        TreeSelectionModel selectionModel = tree.getSelectionModel();

        //Get selected node
        DefaultMutableTreeNode selectedNode =
                (DefaultMutableTreeNode) Objects.requireNonNull(tree.getSelectionPath()).getLastPathComponent();

        //Get userId that is selected in the JTree and return it
        return selectedNode.getUserObject().toString();
    }

    public User getUser() {
        //Get selected node
        DefaultMutableTreeNode selectedNode =
                (DefaultMutableTreeNode) Objects.requireNonNull(tree.getSelectionPath()).getLastPathComponent();

        //Get userId that is selected in the JTree
        String userId = selectedNode.getUserObject().toString();

        //Get reference to userDatabase and return User object associated with the userId selected in the JTree.
        Hashtable<String, User> userDatabase = getUserDatabase();

        return userDatabase.get(userId);
    }

    /*Operations that are performed when each button is pressed*/
    @Override
    public void actionPerformed(ActionEvent e) {

        //Open user view
        if (e.getSource() == openUserViewButton) {
            //Create new user view and set its user with the one that is selected in the JTree
            UserView userView = new UserView();
            userView.setUserViewUser();
            userViewDatabase.put(userView.getUserViewUser(), userView);
        }

        //Add user
        if (e.getSource() == addUserButton) {
            //Check if userID already exists
            if (userDatabase.containsKey(userIdField.getText())) {
               userIdField.setText("User already exists. Enter another ID.");
            } else if (userIdField.getText().isEmpty())  {
                userIdField.setText("Enter a user ID.");
            } else {
                User user = new User();
                user.setUserID(userIdField.getText());
                userDatabase.put(userIdField.getText(), user);
                user.addToTree();
            }
        }

        //Add group
        if (e.getSource() == addGroupButton) {
            //Check if groupID already exists
            if (groupDatabase.containsKey(groupIdField.getText())) {
                groupIdField.setText("Group already exists. Enter another ID.");
            } else if (groupIdField.getText().isEmpty())  {
                groupIdField.setText("Enter a group ID.");
            } else {
                Group group = new Group();
                group.setGroupID(groupIdField.getText());
                groupDatabase.put(groupIdField.getText(), group);
                group.addToTree();
            }
            System.out.println(getGroupDatabase().toString());
        }
    }
}
