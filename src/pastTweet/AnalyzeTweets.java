package pastTweet;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.mysql.jdbc.Statement;

public class AnalyzeTweets 
{
	static Connection con = null;
	static PreparedStatement queryToStoreTweets = null;
	static Statement statement = null;
	static ResultSet resultSet = null;
	static TreeMap<String, String> location_stateCode_map = new TreeMap<>();
	static TreeSet locations = new TreeSet();
	int one=0;
    int two=0;
    int three =0;
    int four =0;
    static TreeMap<String, Integer[]> location_sentiment = new TreeMap<>();
	public static void main(String[] args) throws Exception 
	{
		readStateCodes();
		con = DriverManager.getConnection(Settings.getMySQLURL(),Settings.getMySQLUserName(),Settings.getMySQLPassword());
		String queryToFetchLocationSentiment = "select userLocation, sentimentScore from Tweets"
				+ " where userLocation is not null and theme = 'Donald Trump'";
		statement = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		resultSet = statement.executeQuery(queryToFetchLocationSentiment);
		int i=0;
	    String location = "";
	    String stateCode = "";
	    //String temp ="";
	    boolean flag = false;
	    int found_locations=0;
	    int nooftweets =0;
	    String[] location_parts;
	    Integer[] temp;
	    int senti=0;
	    String state ="";
		while(resultSet.next()!=false)
		{
			nooftweets++;
			flag = false;
			location = resultSet.getString("userLocation");
			senti = resultSet.getInt("sentimentScore");
			locations.add(location);
			if(location_stateCode_map.containsKey(location))
			{
				state = location_stateCode_map.get(location);
				if(!location_sentiment.containsKey(state))
				{
					temp = new Integer[5];
					Arrays.fill(temp, 0);
					temp[senti]++;
					location_sentiment.put(state, temp);
				}
				else
				{
					location_sentiment.get(state)[senti]++;
				}
			}
			
				
				
				
			/*for(String key : location_stateCode_map.keySet())
			{
				location = location.toLowerCase();
				location_parts = location.split(" ");
				for(String loc : location_parts)
				{
					loc = loc.replace(",", "");
					if(loc.equalsIgnoreCase(key))
					{
						System.out.println(location  
								+ " matched location " + key
								+ " state code " + location_stateCode_map.get(key));
						found_locations++;
						flag = true;
						break;
					}
					
				}
				if(flag) break;
			}
			if(!flag)
			{
				System.out.println("cound not find state code for " + location);
			}*/
			
			
		}
		/*System.out.println("no of locations : " + locations.size()  
				+ "no of tweets " + nooftweets + 
				"state code found for " + found_locations);*/
		for(String key : location_sentiment.keySet())
		{
			temp = location_sentiment.get(key);
			//System.out.println(key + " " + temp[0] +" "+ temp[1]  +" "+ temp[2]  +" "+ temp[3] + " " + temp[4]);
			/*System.out.println("{ \"state\" :"  + "\""+key+"\"," );
			System.out.println("\"opinions\" :");
			System.out.println("{");
			int j;
			for(j =0; j<4; j++)
				System.out.println("\""+j+"\" :" + temp[j]+",");
			System.out.println("\""+j+"\" :" + temp[j]);
			System.out.println("} },");*/
			int low,high;
			float mid;
			low = temp[1];
			mid=temp[2];
			high = temp[3];
			System.out.println("sampleData[" + "'" + key+ "']={low:d3.min([" + low+","+mid+","+high+"])"
			+", high:d3.max([" + low+","+mid+","+high+"])," 
			+" high:d3.max([" + low+","+mid+","+high+"]),"
			+"avg:Math.round(("+(low+mid+high)+")/3)," 
			+ "color::d3.interpolate(\"black\",\"white\")(" + mid/100+")};") ;
		}
		
	}
	
	public static void readStateCodes() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("parsedStateCodes.txt"));
		String line;
		String[] values;
	    while ((line = br.readLine()) != null)
	    {
	       //line = line.replace("\"", "");
	       values = line.split("-");
	       //System.out.println(values[0] + " " + values[1]);
	       try{
	    	   if(!values[1].equals("_") & values[1].length()==2)
		    	   location_stateCode_map.put(values[0], values[1]);
	       }
	       catch(Exception e)
	       {
	    	   //System.out.println("exceptio at" + line);
	       }
	    }
	    for(String key : location_stateCode_map.keySet())
	    {
	    	//System.out.println(key + " stateCode " + location_stateCode_map.get(key) );
	    }
		
	}

}
