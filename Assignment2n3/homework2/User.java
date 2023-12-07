package homework2;
import javax.swing.*;
import java.util.ArrayList;


public class User implements Component, Observer, Subject {


    private String userID;      //unique identifier for user
    private ArrayList<String> following = new ArrayList<>(); //List of users that user are curently following
    private ArrayList<String> tweets = new ArrayList<>();    //List of tweets that user has posted
    private ArrayList<Observer> observers = new ArrayList<Observer>();  //List of observers for the user

    private DefaultListModel userList = new DefaultListModel();
    private DefaultListModel<String> tweetList = new DefaultListModel();

    // JTextArea where the newsFeed will be displayed
    private JTextArea newsFeed = new JTextArea();

    private long creationTime; //To store the user creation time

    private long lastUpdateTime; // to strore the update tweet time
    public User(String userID)
    {
        this.userID = userID;

    }
    public String getUserID()
    {

        return this.userID;
    }
    public ArrayList<String> getFollowingList()
    {
        return this.following;
    }

    public ArrayList<String> getTweets()
    {
        return tweets;
    }

    public DefaultListModel getUserList()
    {
        return userList;
    }

    public DefaultListModel getTweetList()
    {
        return tweetList;
    }

    public JTextArea getNewsFeed()
    {
        return newsFeed;
    }

    // Accepts a Visitor and applies the visit method to the current user.
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);

    }

    // Implementation of the update method from the Observer pattern.
    //Updates the news feed JTextArea with the latest tweet from the subject user.
    @Override
    public void update(Subject subject) {
        JTextArea subjectTextArea = this.getNewsFeed();
        User user = (User) subject;
        subjectTextArea.removeAll();
        String  allText = user.getNewsFeed().getText();
        String[] lines = allText.split("\n");
        String lastLine = lines[lines.length - 1];
        subjectTextArea.append("\n");
        subjectTextArea.append(lastLine);

    }

    // Adds a new observer to the list of observers for the user.
    @Override
    public void add(Observer newobserver) {
        observers.add(newobserver);

    }

    //Notifies all observers about changes in the user.
    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(this);

        }
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}