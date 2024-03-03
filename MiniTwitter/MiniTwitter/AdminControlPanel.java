package homework2;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminControlPanel {

    private ArrayList <User> LUser;      //Store User Object
    private ArrayList<String> LUserName;    //Store username
    private ArrayList<String> LGroupName; //Store Groupname/ groupID
    DefaultTreeModel treeModel; //Manage the tree structure
    private int userCount, groupCount;  //tracking the number of users and groups are added

    //Design the admin control panel page
    public void showMiniTwitter() {

        //Declare the components for the admin panel
        JTree tree;
        JFrame frame;
        JTextField userID, groupID;
        JButton addUser, addGroup, UserView, UserTotal, GroupTotal, MessagesTotal, PositivePercentage, verifyID, lastUpdate;

        //Initialize user, username, groupname lists
        LUser = new ArrayList<>(20);
        LUserName = new ArrayList<>(20);
        LGroupName = new ArrayList<>(20);
        LGroupName.add("Root"); //Add the first group named Root to the list name of group


        // Creating the root node
        Group root = new Group("Root");

        //Declare the treeModel to ube used for the tree structure
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);


        //Initialize the frame for the admin panel
        frame = new JFrame("Mini Twitter Admin Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Initialize the JScrollPanel and JPanel components
        JScrollPane treeView = new JScrollPane(tree);
        treeView.setPreferredSize(new Dimension(200, 400));
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(6,5,5,5));

        // Create components for rightPanel
        userID = new JTextField();
        addUser = new JButton("Add User");
        groupID = new JTextField();
        addGroup = new JButton("Add Group");
        UserView = new JButton("Open User View");
        UserTotal = new JButton("Show User Total");
        GroupTotal = new JButton("Show Group Total");
        MessagesTotal = new JButton("Show Messages Total");
        PositivePercentage = new JButton("Show Positive Percentage Total");
        verifyID = new JButton("User/Group ID Verification");
        lastUpdate = new JButton("Last Updated User");



        // ActionListener for the addUser button
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new User
                User newUserNode = new User(userID.getText());
                newUserNode.setCreationTime(System.currentTimeMillis());

                // Add the user's username to the list of usernames
                LUserName.add(userID.getText());
                LUser.add(newUserNode);

                // Create a group instance of root
                Group group = (Group) root;

                // Select the path to determine the parent node
                TreePath parentPath = tree.getSelectionPath();

                // Determine the parent based on whether something is selected or not
                DefaultMutableTreeNode parent = (parentPath == null) ? root : (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                DefaultMutableTreeNode user = new DefaultMutableTreeNode(userID.getText(), false);

                // Add the new user node to the tree
                parent.add(user);

                // Add the user to the parent list
                ArrayList<Component> ComponentList = group.getComponentList();
                ComponentList.add(newUserNode);

                //Increase number of users
                userCount++;

                // Reload the tree model
                treeModel.reload();

            }
        });

         // ActionListener for the addGroup button
        addGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the group is clicked
                TreePath parentPath = tree.getSelectionPath();
                DefaultMutableTreeNode parent = (parentPath == null) ? root : (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                // Create a new group node to be added to the tree
                DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(groupID.getText(), true);
                parent.add(groupNode);

                // Create a new Group instance and initialize its composite list
                Group newGroup = new Group(groupID.getText());
                //Set the time creation time for the new group
                newGroup.setCreationTime(System.currentTimeMillis());
                ArrayList<Component> newList = new ArrayList<>();
                newGroup.setList(newList);
                LGroupName.add(groupID.getText());

                // Add the new group to the Root
                Group rootGroup = (Group) root;
                ArrayList<Component> topComponentList = rootGroup.getComponentList();

                //Print out the creation time of the group
                System.out.println("Group "+ groupID.getText() + " is created at: "+ newGroup.getCreationTime());

                topComponentList.add(newGroup);
                //Increase number of groups
                groupCount++;

                // Reload the tree model
                treeModel.reload();
            }
        });

        // ActionListener for the UserView button
        UserView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the last selected node in the tree
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                // Open the user view when a user is selected
                if (!selectedNode.getAllowsChildren()) {
                    showUserView(selectedNode);
                }
            }
        });

        // ActionListener for the VerifyID button
        verifyID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                    // Check if the ID is unique across users and groups
                    if (isIDUnique(LUserName, LGroupName)) {
                        JOptionPane.showMessageDialog(frame,
                                "" +
                                        "All the IDs are valid ",
                                "ID Verification", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "All the IDs are  not valid " ,
                                "ID Verification", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }

        );


        //ActionListener for the Last Updated User Button
        lastUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LastUpdatedUser visitedUser = new LastUpdatedUser();
                root.accept(visitedUser);
                User user = visitedUser.getLastUpdateUser();
                JOptionPane.showMessageDialog(frame,
                        "Last Updated User:  " + user.getUserID(),
                        "Last Update", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        //ActionListener for the UserTotal Button
        UserTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "Total number of users " + userCount,
                        "User Total", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // ActionListener for the GroupTotal Button
        GroupTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(frame,
                        "Total number of groups: " + groupCount,
                        "Group Total", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // ActionListener for the MessageTotal Button
        MessagesTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a TweetCount visitor and pass it to the accept method
                TweetCount tweetPost = new TweetCount();
                root.accept(tweetPost);

                // Get the number of the tweets are posted
                int tweetCount = tweetPost.getTweetCount();

                JOptionPane.showMessageDialog(frame,
                        "Total number of tweets: " + tweetCount,
                        "Message Total", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // ActionListener for the PositivePercentage Button
        PositivePercentage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a TweetCount visitor and pass it to the accept method
                TweetCount tweetPost = new TweetCount();
                root.accept(tweetPost);

                // Get the number of the tweets are posted
                int tweetCount = tweetPost.getTweetCount();
                //Get the number of the positive tweets are posted
                double positiveTweet = tweetPost.getPositiveCount();
                //Calculate the positive percentage
                double percentage = (positiveTweet/tweetCount)*100;
                JOptionPane.showMessageDialog(frame,
                        String.format("Positve tweets percentage: %.2f%%" ,percentage),
                        "Positive Tweets Percentage", JOptionPane.INFORMATION_MESSAGE);
          }

        });

        //Create the scroll pane
        JScrollPane buttonView = new JScrollPane(rightPanel);
        // Adding components to the rightPanel
        rightPanel.add(userID);
        rightPanel.add(addUser);
        rightPanel.add(groupID);
        rightPanel.add(addGroup);
        rightPanel.add(UserView);
        rightPanel.add(new JLabel()); // Placeholder for alignment
        rightPanel.add(verifyID);
        rightPanel.add(lastUpdate);
        rightPanel.add(UserTotal);
        rightPanel.add(GroupTotal);
        rightPanel.add(MessagesTotal);
        rightPanel.add(PositivePercentage);

        // Adding the treeView (left panel) and rightPanel (user's components) to the main frame
        frame.add(treeView, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);

        // Frame Packing and Visibility
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //Design the userView page
   void showUserView(DefaultMutableTreeNode user) {
       // Create a new frame with the username as the title
       JFrame frame2 = new JFrame(user.toString());
       // Retrieve the username for later reference
        String userName = user.toString();

       // Find the User object corresponding to the selected user
        int index = LUserName.indexOf(userName);
        User currentName = LUser.get(index);

       // Create panels for different sections of the user view
        JPanel Panel = new JPanel(new GridLayout(5, 0, 0, 20));
        JPanel newFeedPanel = new JPanel();

       // Retrieve the user's listModel to avoid data loss on window reopening
        DefaultListModel listModel = currentName.getUserList();

       // Create components for the Follow User section
        JPanel followUserPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        JTextField userViewId = new JTextField();
        JButton followUser= new JButton("Follow User");
        followUserPanel.add(userViewId);
        followUserPanel.add(followUser);

       // Create the list and scrollpane for the Current Following section
        JPanel followingListPanel = new JPanel();
        JList<String> followingList= new JList<String>(listModel);
        JScrollPane listfollow = new JScrollPane(followingList);
        listfollow.setBorder(BorderFactory.createTitledBorder("Current Following: "));
        followingListPanel.add(listfollow);
        listfollow.setPreferredSize(new Dimension(500, 300));

       // Retrieve the user's newsFeed JTextArea to prevent data loss
        DefaultListModel messageModel = currentName.getTweetList();

       // Create components for the "Post Tweet" section
        JPanel TweetPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        JTextField tweetMessage= new JTextField("");
        JButton postTweet= new JButton("Post Tweet");
        TweetPanel.add(tweetMessage);
        TweetPanel.add(postTweet);

       // Retrieve the user's newsFeed JTextArea to prevent data loss
       JTextArea currentNewsFeed = currentName.getNewsFeed();

       // Add the JTextArea to a JScrollPane and include it in the News Feed section
        JScrollPane newFeed = new JScrollPane(currentNewsFeed);
        newFeed.setBorder(BorderFactory.createTitledBorder("News Feed: "));
        newFeedPanel.add(newFeed);
        newFeed.setPreferredSize(new Dimension(500, 600));

       // Create a label for the creation time
       JLabel creationTimeLabel = new JLabel("Creation time of the user is: " + currentName.getCreationTime());

       // Combine all panels to the main panel
        Panel.add(creationTimeLabel);
        Panel.add(followUserPanel);
        Panel.add(followingListPanel);
        Panel.add(TweetPanel);
        Panel.add(newFeedPanel);
        Panel.setPreferredSize(new Dimension(500, 700));

       // Add the main panel to the new frame and configure the frame
        frame2.add(Panel);
        frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(true);

        // Action listener for the followUser Button
        followUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the current user's following list
                ArrayList<String> currentList = currentName.getFollowingList();
                // Get the inputted username
                String name = userViewId.getText();
                // Find the user to follow by using the inputted userName
                int index = LUserName.indexOf(name);

                // Check if the userNameArrayList contains the user you wish to follow or not
                if (index != -1 && LUserName.contains(userName)) {
                    User follow = LUser.get(index);
                    // Add the user to the current user's following list
                    currentList.add(follow.getUserID());
                    // Refresh the listModel
                    listModel.clear();
                    listModel.addElement(currentList);
                    // Add the currentUser to userToFollow as an observer
                   follow.add(currentName);
                }
            }
        });

        // Action listener for the postTweet Button
        postTweet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the current time when the tweet is posted
                long currentTime = System.currentTimeMillis();

                // Construct the tweet message
                String string =currentTime +": "+ currentName.getUserID() + ": " +  tweetMessage.getText();


                // Append the text to the current user's newsFeed and update the messageModel
                currentNewsFeed.setText(currentNewsFeed.getText() + "\n" + string);
                messageModel.addElement(currentNewsFeed);

                currentName.setLastUpdateTime(currentTime);

                // Notifying all the observers(followers)
                currentName.notifyObserver();

                // Add the new tweet to the tweet list
                currentName.getTweets().add(tweetMessage.getText());

            }
        });
    }

    boolean isIDUnique (ArrayList<String> list1, ArrayList<String> list2)
    {
        //copy the list1 to newlist
        ArrayList <String> newlist = new ArrayList<>(list1);

        //add all elements of list2 to newlist
        newlist.addAll(list2);
        Map<String, Integer> count = new HashMap<>();

        //Count the occurrence of each element in the new list to check for duplicate
        for(String str: newlist)
        {
            count.put(str,count.getOrDefault(str, 0) + 1);

        }

        //if any element appears more than once in the list
        if(count.values().stream().anyMatch(num -> num > 1))
            return false;

        //Check if any element contains a space
        if(newlist.stream().anyMatch(s -> s.contains(" ")))
            return false;

        return true;

    }



    // Method to create and display the UI
    void runApp()
    {
            showMiniTwitter();

    }
    }
