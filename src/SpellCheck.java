import java.util.*;
import java.util.Map.Entry;


public class SpellCheck {
	//Creates the HashMap of distance of given word in the PreSearch database.
	private static HashMap<String,Integer> sortByEditDistance(String keyword, Init_Search ps) {
		HashMap<String, Integer> indexDistance = new HashMap<String,Integer>();
		Iterator<Entry<String,HashSet<Integer>>> iterator = ps.index.entrySet().iterator();
		while(iterator.hasNext())   
		{  			
			Map.Entry<String, HashSet<Integer>> me = (Map.Entry<String, HashSet<Integer>>)iterator.next();
			String word = me.getKey().toString();			
			int distance = Sequences.editDistance(word, keyword); // Edit Distance
			if(distance<3 && distance!=0) {
				indexDistance.put(word, distance);				
			}
		}
		
		HashMap<String,Integer> sortedDistance = Decend_SortByRank_And_Ouccurances.sortValues(indexDistance);
		return sortedDistance;
	}
	
	//Searches for similar words in the database.
	public static void spellCheck(String keyword,int numberofResults,Init_Search ps) {		
		HashMap<String,Integer> sortedDistance = sortByEditDistance(keyword,ps);
		Iterator<Entry<String, Integer>> iterator2 = sortedDistance.entrySet().iterator();  
		while(iterator2.hasNext())   
		{  			
			Map.Entry<String, Integer> me = (Map.Entry<String, Integer>)iterator2.next();
			String word = me.getKey().toString();			
			System.out.println("Instead showing results for : "+word);
			
			//Searching for the word with lowest distance.
			Map<String,Integer> sortedMap;
			ArrayList<String> as = ps.find(word);
			String phrase = word.toLowerCase();
			
			sortedMap = Decend_SortByRank_And_Ouccurances.sortByRank(as, phrase);			
			Search.printResult(sortedMap,numberofResults);
			break;
		}
		
	}
}
