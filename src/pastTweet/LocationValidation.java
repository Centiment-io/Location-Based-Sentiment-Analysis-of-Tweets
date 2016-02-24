

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocationValidation {
	Map<String,String> mp;
	Set<String> set;
	Map<String,String> excelCodes=new HashMap<>();

	public void initMap()
	{
 mp=new HashMap<>();
		set=new HashSet<>();
		mp.put("Alabama","AL");
		mp.put("Missouri","MO");
		mp.put("Alaska","AK");
		mp.put("Montana","MT");
		mp.put("Arizona","AZ");
		mp.put("Nebraska","NE");
		mp.put("Arkansas","AR");
		mp.put("Nevada","NV");
		mp.put("California","CA");
		mp.put("New Hampshire","NH");
		mp.put("Colorado","CO");
		mp.put("New Jersey","NJ");
		mp.put("Connecticut","CT");
		mp.put("New Mexico","NM");
		mp.put("Delaware","DE");
		mp.put("New York","NY");
		mp.put("District of Columbia","DC");
		mp.put("North Carolina","NC");
		mp.put("Florida","FL");
		mp.put("North Dakota","ND");
		mp.put("Georgia","GA");
		mp.put("Ohio","OH");
		mp.put("Hawaii","HI");
		mp.put("Oklahoma","OK");
		mp.put("Idaho","ID");
		mp.put("Oregon","OR");
		mp.put("Illinois","IL");
		mp.put("Pennsylvania","PA");
		mp.put("Indiana","IN");
		mp.put("Rhode Island","RI");
		mp.put("Iowa","IA");
		mp.put("South Carolina","SC");
		mp.put("Kansas","KS");
		mp.put("South Dakota","SD");
		mp.put("Kentucky","KY");
		mp.put("Tennessee","TN");
		mp.put("Louisiana","LA");
		mp.put("Texas","TX");
		mp.put("Maine","ME");
		mp.put("Utah","UT");
		mp.put("Maryland","MD");
		mp.put("Vermont","VT");
		mp.put("Massachusetts","MA");
		mp.put("Virginia","VA");
		mp.put("Michigan","MI");
		mp.put("Minnesota","MN");
		mp.put("West Virginia","WV");
		mp.put("Mississippi","MS");
		mp.put("Wisconsin","WI");
		mp.put("Wyoming","WY");



		for(String key : mp.keySet() )
		{
			set.add(mp.get(key).trim());
		}



	}
	static int count=0;

	public String locationvalidate(String str, Map<String,String> hm)
	{
		str = str.trim();
		Set<String> set = hm.keySet();
		String[] s;
		if(str.equals(null)){
			count++;
			return null;
		}

		if(str.lastIndexOf(',')> 0)
			s=str.split(",");
			else
			s=str.split(" ");

		if(s[s.length-1].trim().length()==2)
			return hm.get(s[s.length-1].trim());

		if(set.contains(s[s.length-1].trim()))
			return hm.get(s[s.length-1].trim());
		else if (s.length>1&&set.contains(s[s.length-1].trim()+" "+s[s.length-2].trim()))
			return hm.get(set.contains(s[s.length-1].trim()+" "+s[s.length-2].trim()));

		if(s[s.length-1].trim().equals("USA")&& s.length>1){
			if(s[s.length-2].trim().length()==2)
				return s[s.length-2].trim();
			else if(set.contains(s[s.length-2].trim()))
				hm.get(s[s.length-2].trim());
			else if(s.length>2&&set.contains(s[s.length-2].trim()+" "+s[s.length-3].trim()))
				return hm.get(s[s.length-2].trim()+" "+s[s.length-3].trim());
		}
		else{
			if(s[s.length-1].trim().length()==2)
				return s[s.length-1].trim().toUpperCase();
			else if(set.contains(s[s.length-1].trim()))
				hm.get(s[s.length-1].trim());
			else if(s.length>1&&set.contains(s[s.length-1].trim()+" "+s[s.length-2].trim()))
				return hm.get(s[s.length-1].trim()+" "+s[s.length-2].trim());
		}

			count++;
		return null;



















		/*String s[];
		String forMap=str;
		str=str.trim();
		if(str.equals(null))
		{
			excelCodes.put(forMap, "_");
			return;
		}
		if(str.equals("new york")||str.equals("New York")||str.equals("New york"))
		{
			System.out.println("NY");
			excelCodes.put(forMap, "NY");
		}
		else
		{
		if(str.lastIndexOf(',')> 0){
		s=str.split(",");}
		else{
			s=str.split(" ");}

		if(s[s.length-1].trim().equals("USA")&& s.length>1)
		{
			//System.out.println(s[s.length-2]);
			if(mp.get(s[s.length-2].toUpperCase()) != null)
			{
				excelCodes.put(forMap, mp.get(s[s.length-2]));
				System.out.println(mp.get(s[s.length-2]));
			}

		}
		if(s[s.length-1].trim().length()==2&&s.length>=1)
		{
			//System.out.println(s[s.length-1]);
			String checkStr=s[s.length-1].trim();
			if(set.contains(checkStr))
			{
				excelCodes.put(forMap,checkStr );
				System.out.println(checkStr);
			}
		}
		else{
			excelCodes.put(forMap,"_" );
			return;
		}
		}*/

	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//locationvalidate("(312)975-7638");
		//locationvalidate("Texas,USA".trim());
		//locationvalidate("pleasasant, CA");
		LocationValidation lv=new LocationValidation();
		lv.initMap();

		BufferedReader s=new BufferedReader(new FileReader("/home/vamsi/Desktop/states1.txt"));

		//Scanner s=new Scanner("/home/vamsi/Desktop/states1.txt");
		System.out.println("Got text file");
		int i=0;
		List<String> al=new ArrayList();
		String temp;

		try {
			while((temp=s.readLine())!=null){

				al.add(temp);
			//temp=s.nextLine();

					}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int j=0;
		try
		{

		for( j=0;j<al.size();j++)
		{
			//System.out.println(al.get(j));

		System.out.println(al.get(j)+"::"+lv.locationvalidate(al.get(j),lv.mp));

		System.out.println("total count"+ count);
		}
		}

		catch(Exception e){
		System.out.println("Exception:@ "+al.get(j));
		e.printStackTrace();
		}
		System.out.println("total count"+ j);

		System.out.println("Size of stateMap is: "+lv.excelCodes.size());

		Set result=lv.excelCodes.entrySet();
		System.out.println("---------------------------------");
		for(String str1: lv.excelCodes.keySet())
			System.out.println(str1 +"-" +lv.excelCodes.get(str1));
	}

}
