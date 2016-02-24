package pastTweet;
/***********************************************************************
 * Version History
 * 
 * Version_No		Date			Author  	Reason for Modification
 * 1.0				24-Jan-2016     Pavan		Initial Version
 */
// this method adds all the parameters to the prepareStatement to build the query
import java.sql.PreparedStatement;
import java.sql.SQLException;
import twitter4j.Status;
import twitter4j.TwitterException;

public class PrepareStatement 
{
	public static PreparedStatement prepareStatement(Status status, PreparedStatement preparedStatement)throws TwitterException, SQLException
	{
		String tweet = status.getText();
		String tweetTime = new java.sql.Timestamp(status.getCreatedAt().getTime()).toString();
		String userLocation = status.getUser().getLocation();
    	String useronScreenName = status.getUser().getScreenName();
    	String tweetID = Long.toString(status.getId());
    	String isRetweet = Boolean.toString(status.isRetweet());
    	String inReplyto = "";
    	String textOfTweetStrippedOfAllNonAlphaNumericCharacters = null;
    	textOfTweetStrippedOfAllNonAlphaNumericCharacters = tweet.replaceAll("[^\\x00-\\x7F]", " ");
    	
    	
    	preparedStatement.setString(1, textOfTweetStrippedOfAllNonAlphaNumericCharacters);
    	preparedStatement.setString(4, tweetTime);
    	preparedStatement.setString(5, userLocation);
    	preparedStatement.setString(7, useronScreenName);
    	preparedStatement.setString(8, tweetID);
    	preparedStatement.setString(9, isRetweet);
    	preparedStatement.setString(10, inReplyto);
    	
    	
    	return preparedStatement;
	}
}

/*
tweet   Varchar(170),
keyword Varchar(30),
theme	Varchar(30),
tweetTime	Varchar(30),
userLocation	Varchar(30),
sentimentScore	INT(6),
useronScreenName	Varchar(30),
tweetID		Varchar(30),
isretweeted	Varchar(30),
inReplyTO	Varchar(30)
*/