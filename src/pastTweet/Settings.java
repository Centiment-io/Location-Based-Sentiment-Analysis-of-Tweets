package pastTweet;
/* 
 * This class makes it easier to transition code from one computer to another.  
 */

public class Settings {
	
	// To update all local settings, all you will need to do is change the currentUser string
	
			
	// Database settings
	private static String url = "jdbc:mysql://localhost:3306/twitterdata";
	private static String dbName = "twitterdata";
	private static String userName = "root"; 
	private static String password = "root";
	
	
	private static long scrapeTimer = 60L;
	
	private static int numberOfScrapingSessions = 5;
	
	public static void configure(){}
	
	public static int getNumberOfScrapingSessions() {
		return numberOfScrapingSessions;
	}
	
	public static long getScrapeTimer(){
		return scrapeTimer;
	}
	
	public static String getMySQLURL(){
		String input = null;
		input = url;
		return input;
	}
	
	public static String getMySQLUserName(){
		return userName;
	}
	
	public static String getMySQLPassword(){
		return password;
	}
	
	
	
}


