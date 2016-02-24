package pastTweet;

/***********************************************************************
 * Version History
 * 
 * Version_No		Date			Author  	Reason for Modification
 * 1.0				23-Jan-2016     Pavan		Initial Version
 */
// this code does the Authentication and returns instance of Twitter
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class BuildConfiguration {
	
	public static TwitterStreamFactory getTwitter()
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("ntDCD4j3Fszoyk0UUufTUSC2W");
		cb.setOAuthConsumerSecret("OpJ4Fl17owoAuYHWEitpIzrrs5raC0qqMW7AwuazyqOj6ZKw6o");
		cb.setOAuthAccessToken("3186142171-TxQ4Yv8zyEJRVmv4MNP88Rt51gSJQMt2reapKSO");
		cb.setOAuthAccessTokenSecret("d1o6DTLjiJXQzBvGZ1EYoa59HuVaN0tZ3BosEsydz0aEP");
		return new TwitterStreamFactory(cb.build());
	}

}
