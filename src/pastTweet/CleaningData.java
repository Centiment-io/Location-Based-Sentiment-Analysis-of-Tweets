package pastTweet;

public class CleaningData 
{
	public static void main(String[] args)
	{
		String str = "RT @EricTrump: Celebrating a big win for @realDonaldTrump tonight in SC! #Trump2016 #MakeAmericaGreatAgain #TrumpTrain https://t.co/WsvAfEh";
			str =	str.replaceAll("[^\\x00-\\x7F]", " ");
			System.out.println(str);
			int index =0;
			int lastIndex =0;
		while(str.contains("@"))
		{
			index = str.indexOf(" \\@");
			lastIndex = str.indexOf(" ", index);
			str = str.replaceAll(str.substring(index, lastIndex), "");
		}
		System.out.println(str);
	}
}
