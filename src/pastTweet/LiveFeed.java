package pastTweet;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.Principal;

import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class LiveFeed {

	static Connection con = null;
	static PreparedStatement queryToStoreTweets = null;
	

	public static int id = 1;

	private static String[] UserIDToTrack = { "@HillaryClinton", "#ImWithHer"};

	TwitterStream twitterStream;
	static TwitterStream twitterStream2;
	static String keyword = UserIDToTrack[0];
	static String theme = "Hillary Clinton";
	public static int increment() {
		id++;
		return id;
	}
     public static void main(String[] args)throws Exception 
     {
    	setupDBConnection();
    	collectUserTweets();
	}
	public static void collectUserTweets() throws TwitterException, SQLException, IOException {

		File file = new File("tweets_2.txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		/*
		 * When you want to get a Twitter Stream, you have to use the four
		 * credentials listed above.
		 */
		TwitterStreamFactory twitterStreamFactory2 = BuildConfiguration.getTwitter();
		twitterStream2 = twitterStreamFactory2.getInstance();
		
		//BufferedWriter writer = new 
		StatusListener listener = new StatusListener() {
			public void onStatus(Status status) {
				// store each tweet that we get
				try {
					/*if(status!=null && status.getUser().getLocation()!=null)
					{*/
						Twitter twitter = TwitterFactory.getSingleton();
						id++;
						String textOfTweetStrippedOfAllNonAlphaNumericCharacters = null;
						String textOfTweet = status.getText();
						textOfTweetStrippedOfAllNonAlphaNumericCharacters = textOfTweet.replaceAll("[^\\x00-\\x7F]", " ");
						int sentiment =0;
						/*if(status.getUser().getLocation().length()!=0)
						  {*/
							  sentiment = NLP.findSentiment(status.getText());
							  bw.write(id +" "+ status.getCreatedAt()+ " @ " + status.getUser().getScreenName() + " - " + status.getText()
			                    /*+ "location " + status.getUser().getLocation() + " place " + status.getPlace()*/ + " sentiment " + sentiment + "\n");
							  bw.flush();
							  storeInDB(status, sentiment);
							  
							  
						  /*}*/
					/*}*/
					
					if(id%100==0) System.out.println(" collected " + id + " tweets");
				}  catch (Exception e) {
					e.printStackTrace();
				}
			}

			// The four lines of code below are require for the Twitter4J
			// interface to work
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			public void onScrubGeo(long userId, long upToStatusId) {
			}

			public void onException(Exception ex) {
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}
		};
		
		
		
		/*
		 * These lasts lines down here are important.
		 */
		twitterStream2.addListener(listener);
		FilterQuery filterQuery = new FilterQuery(UserIDToTrack);
		twitterStream2.filter(filterQuery);

	}
	
	public static void setupDBConnection() throws SQLException
	{
		 con = DriverManager.getConnection(Settings.getMySQLURL(),Settings.getMySQLUserName(),Settings.getMySQLPassword());
 		con.setAutoCommit(false);
 		String query = "insert into Tweets" + 
 				"(" + 
 				"tweet, " + 
 				"keyword, " +
 				"theme, " +  
 				"tweetTime, " + 
 				"userLocation, " + 
 				"sentimentScore, " + 
 				"useronScreenName, " + 
 				"tweetID, " + 
 				"isretweeted, " + 
 				"inReplyTO" + 
 				") " + 
 				"values" +
 				"(?,?,?,?,?,?,?,?,?,?)";
 		
 		queryToStoreTweets = con.prepareStatement(query);
 		con.commit();
	}
	
	public static void storeInDB(Status tweet, int sentiment) throws SQLException, TwitterException
	{
		for(String key : UserIDToTrack)
		{
			keyword = key;
			break;
		}
		queryToStoreTweets.setString(2, keyword);
		queryToStoreTweets.setString(3, theme);
		
		queryToStoreTweets.setInt(6, sentiment);
		queryToStoreTweets = PrepareStatement.prepareStatement(tweet, queryToStoreTweets);
		queryToStoreTweets.executeUpdate();
        con.commit();
	  
	}
	

}
