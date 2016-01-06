/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2.
	private static final int THRESHOLD = 1000;

	Dictionary dict;

	public NearbyWords(Dictionary dict) {
		this.dict = dict;
	}

	/**
	 * Return the list of Strings that are one modification away from the input
	 * string.
	 * 
	 * @param s
	 *            The original String
	 * @param wordsOnly
	 *            controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly) {
		List<String> retList = new ArrayList<String>();
		insertions(s, retList, wordsOnly);
		subsitution(s, retList, wordsOnly);
		deletions(s, retList, wordsOnly);
		return retList;
	}

	/**
	 * Add to the currentList Strings that are one character mutation away from
	 * the input string.
	 * 
	 * @param s
	 *            The original String
	 * @param currentList
	 *            is the list of words to append modified words
	 * @param wordsOnly
	 *            controls whether to return only words or any String
	 * @return
	 */
	public void subsitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for (int index = 0; index < s.length(); index++) {
			for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char) charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if (!currentList.contains(sb.toString()) && (!wordsOnly || dict.isWord(sb.toString()))
						&& !s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}

	/**
	 * Add to the currentList Strings that are one character insertion away from
	 * the input string.
	 * 
	 * @param s
	 *            The original String
	 * @param currentList
	 *            is the list of words to append modified words
	 * @param wordsOnly
	 *            controls whether to return only words or any String
	 * @return
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly) {

		if (s == null || currentList == null)
			return;
		

		for (int index = 0; index <= s.length(); index++) {

			String word = "";

			for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++) {

				String prefix = s.substring(0, index);
				String postfix = s.substring(index);
				word = (prefix + (char) charCode + postfix).trim();
				
				if (!currentList.contains(word) && (!wordsOnly || dict.isWord(word))
						&& !s.equals(word)) {
					currentList.add(word);
				}
				

			/*	if (currentList.contains(word)) {
					break;
				}
				
				
				

				if (wordsOnly) {

					if (dict.isWord(word)) {

						currentList.add(word);
						inserts++;

					}

				} else {

					currentList.add(word);
					inserts++;
				}
*/
			}

		}
		
	//	System.out.println("Inserts Expected " +(( s.length()+1 )*26) +" : " + inserts);

	}

	/**
	 * Add to the currentList Strings that are one character deletion away from
	 * the input string.
	 * 
	 * @param s
	 *            The original String
	 * @param currentList
	 *            is the list of words to append modified words
	 * @param wordsOnly
	 *            controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly) {

		if (s == null || currentList == null)
			return;
		
		char[] word = new char[s.length() - 1];
		int wordIndex = 0;

		for (int i = 0; i < s.length(); i++) {

			for (int j = 0; j < s.length(); j++) {

				if (i != j) {
					word[wordIndex] = s.charAt(j);
					wordIndex++;
				}

			}

			String wordStr = new String(word);
			
			if (!currentList.contains(wordStr) && (!wordsOnly || dict.isWord(wordStr))
					&& !s.equals(wordStr)) {
				currentList.add(wordStr);
			}

			/*
			if (wordsOnly) {

				if (dict.isWord(wordStr)) {

					currentList.add(wordStr);
					deletions++;

				}

			} else {

				currentList.add(wordStr);
				deletions++;
			}
*/
			wordIndex = 0;

		}

		//System.out.println("Deletions expected " + s.length() + " : " + deletions );
	}

	/**
	 * Add to the currentList Strings that are one character deletion away from
	 * the input string.
	 * 
	 * @param word
	 *            The misspelled word
	 * @param numSuggestions
	 *            is the maximum number of suggestions to return
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		// initial variables
		List<String> queue = new LinkedList<String>(); // String to explore
		HashSet<String> visited = new HashSet<String>(); // to avoid exploring
															// the same
															// string multiple
															// times
		List<String> retList = new LinkedList<String>(); // words to return

		// insert first node
		queue.add(word);
		visited.add(word);

		// TODO: Implement the remainder of this method, see assignment for
		// algorithm
		outerloop:
		while(!queue.isEmpty() ){
			
			
			String curr = queue.remove(0);
			List<String> neighbors = distanceOne(curr,true);  
			
			for (String n : neighbors) {
				
				if(!visited.contains(n)){
					
					visited.add(n);
					queue.add(n);
					
					if(dict.isWord(n)){
						retList.add(n);
						if(retList.size()==numSuggestions){break outerloop;}
						
					}
					
				}
				
				
			}
			
			
			
		}
		
		

		return retList;

	}

	public static void main(String[] args) {

		String word = "tailo";
		Dictionary d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, "data/dict.txt");

		NearbyWords w = new NearbyWords(d);

		List<String> l = w.distanceOne(word, true);
		System.out.println("One away word Strings for for \"" + word + "\" are:");

		System.out.println(l + "\n");

		word = "kangaro";
		List<String> suggest = w.suggestions(word, 10);
		System.out.println("Spelling Suggestions for \"" + word + "\" are:");
		System.out.println(suggest);

	}

}
