package homework2;

import javax.swing.*;
import java.util.ArrayList;

//Implement the visitor interface to design visitor pattern
public class TweetCount implements Visitor
{
    private int tweetCount = 0;     //number of tweests are posted
    private double positiveCount = 0;   //number of positive tweets are posted
    private String [] positiveWords = {"happy", "good", "great", "excelent"};   //define positive words

    public void visit(User user) {

        //Get the size of user's tweetList and store in the user instance
        DefaultListModel tweetList = user.getTweetList();
        setTweetCount(getTweetCount() + tweetList.getSize());

        //count for positive tweets
        ArrayList<String> tweetPosted = user.getTweets();
        for(String tweet : tweetPosted)
        {
            String lowerCaseTweet = tweet.toLowerCase();
            for(String word : positiveWords)
            {
                if (lowerCaseTweet.contains(word))
                {
                    positiveCount++;
                }
            }
        }
    }

    @Override
    public void visit(Group group) {
        //The number of tweets do not change when a group is visited
        setTweetCount(getTweetCount());
        setPositiveCount(getPositiveCount());

    }

    public int getTweetCount() {
        return tweetCount;
    }

    public void setTweetCount(int tweetCount) {

        this.tweetCount = tweetCount;
    }

    public double getPositiveCount()
    {
        return positiveCount;
    }

    public void setPositiveCount(double positiveCount)
    {
        this.positiveCount = positiveCount;
    }
}

