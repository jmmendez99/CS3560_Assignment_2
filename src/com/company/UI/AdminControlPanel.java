package com.company.UI;

import com.company.Models.Group;
import com.company.Models.User;
import com.company.Visitor.CountEntryVisitor;
import com.company.Visitor.EntryVisitor;
import com.company.Visitor.LastUpdateEntryVisitor;
import com.company.Visitor.ValidationEntryVisitor;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

//Implement singleton pattern here!
public class AdminControlPanel implements ActionListener {
    /*Properties of admin panel*/
    //Database of users and groups and userViews
    private Hashtable<String, User> userDatabase = new Hashtable<>();
    private Hashtable<String, Group> groupDatabase = new Hashtable<>();
    private Hashtable<String, UserView> userViewDatabase = new Hashtable<>();
    private final List<String> positiveWordsDatabase = new ArrayList<>(Arrays.asList("happy","love","great","awesome","fun","exciting"));

    //Properties of analysis buttons
    private int userCount;
    private int groupCount;
    private boolean isIdValid;
    private String lastUpdatedUser;

    //Might need to make these private in the future and then use getter/setter methods to access JComponents
    public JTree tree;
    public DefaultMutableTreeNode root;
    public JTextField userIdField, groupIdField;
    public JButton addUserButton, addGroupButton , openUserViewButton ;
    public JButton showTotalUsersButton, showTotalGroupsButton, showTotalTweetsButton,  showPercentPositiveButton;
    public JButton validateIDButton, lastUpdatedUserButton;

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


        /*ID Validation Button */
        validateIDButton = new JButton("Validate IDs");

        //Styling
        validateIDButton.setBounds(375,340,180,50);

        validateIDButton.setFocusable(false);

        validateIDButton.addActionListener(this);


        /*Last Updated User Button*/
        lastUpdatedUserButton = new JButton("Most Recent User");

        //Styling
        lastUpdatedUserButton.setBounds(570,340,180,50);

        lastUpdatedUserButton.setFocusable(false);

        lastUpdatedUserButton.addActionListener(this);


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

        frame.add(validateIDButton);
        frame.add(lastUpdatedUserButton);

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

    public int getUserCount() { return userCount; }

    public void setUserCount(int userCount) { this.userCount = userCount; }

    public int getGroupCount() { return groupCount; }

    public void setGroupCount(int groupCount) { this.groupCount = groupCount; }

    public boolean getIsIdValid() { return isIdValid; }

    public void setIsIdInvalid(boolean isIdValid) { this.isIdValid = isIdValid; }

    public String getLastUpdatedUser() { return lastUpdatedUser; }

    public void setLastUpdatedUser(String lastUpdatedUser) { this.lastUpdatedUser = lastUpdatedUser; }

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
            //Check if textField is empty
            if (userIdField.getText().isEmpty()) {
               userIdField.setText("Enter a valid user ID.");
            } else {
                //Create User and put them in the userDatabase and add them to the JTRee
                User user = new User();
                user.setUserID(userIdField.getText());
                userDatabase.put(userIdField.getText(), user);
                user.addToTree();

                //Initialize EntryVisitors and have user call its accept() method for those visitors
                EntryVisitor countEntries = new CountEntryVisitor();
                EntryVisitor validEntry = new ValidationEntryVisitor();
                user.accept(countEntries);
                user.accept(validEntry);
            }
        }

        //Add group
        if (e.getSource() == addGroupButton) {
            //Check if textField is empty
            if (groupIdField.getText().isEmpty()) {
                groupIdField.setText("Enter a valid group ID.");
            } else {
                //Create Group and put them in the groupDatabase and add them to the JTRee
                Group group = new Group();
                group.setGroupID(groupIdField.getText().toUpperCase());
                groupDatabase.put(group.getGroupID(), group);
                group.addToTree();

                //Initialize EntryVisitors and have group call its accept() method for those visitors
                EntryVisitor countEntries = new CountEntryVisitor();
                EntryVisitor validEntry = new ValidationEntryVisitor();
                group.accept(countEntries);
                group.accept(validEntry);
            }
        }

        //Show total users
        if (e.getSource() == showTotalUsersButton) {
            //Create popup dialog to show results
            int userCount = AdminControlPanel.getInstance().getUserCount();
            JOptionPane.showMessageDialog(
                    null,
                    "Total Users: " + userCount,
                    "User Count",
                    JOptionPane.PLAIN_MESSAGE);
        }

        //Show total users
        if (e.getSource() == showTotalGroupsButton) {
            //Create popup dialog to show results
            int groupCount = AdminControlPanel.getInstance().getGroupCount();
            JOptionPane.showMessageDialog(
                    null,
                    "Total Users: " + groupCount,
                    "User Count",
                    JOptionPane.PLAIN_MESSAGE);
        }

        //Show total users
        if (e.getSource() == showTotalTweetsButton) {
            //Create popup dialog to show results
            int totalTweets = UserView.getInstance().getTweetCount();
            JOptionPane.showMessageDialog(
                    null,
                    "Total tweets: " + totalTweets,
                    "User Count",
                    JOptionPane.PLAIN_MESSAGE);
        }

        //Show total users
        if (e.getSource() == showPercentPositiveButton) {
            //Create popup dialog to show results
            int positiveCount = UserView.getInstance().getPositiveCount();
            JOptionPane.showMessageDialog(
                    null,
                    "Total positive messages: " + positiveCount,
                    "User Count",
                    JOptionPane.PLAIN_MESSAGE);
        }

        //User and Group ID verification
        if (e.getSource() == validateIDButton) {
            //Create popup dialog to show results
            boolean isIdValid = AdminControlPanel.getInstance().getIsIdValid();
            JOptionPane.showMessageDialog(
                    null,
                    "There are invalid IDs: " + isIdValid,
                    "ID Verification",
                    JOptionPane.PLAIN_MESSAGE);
        }

        //Last updated user
        if (e.getSource() == lastUpdatedUserButton) {
            //Create EntryVisitor and have our user accept the visitor
            EntryVisitor lastUpdate = new LastUpdateEntryVisitor();
            AdminControlPanel.getInstance().getUser().accept(lastUpdate);

            //Create popup dialog to show results
            String lastUpdatedUser = getLastUpdatedUser();
            JOptionPane.showMessageDialog(
                    null,
                    "Last Updated User: " + lastUpdatedUser,
                    "Latest Update",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}
