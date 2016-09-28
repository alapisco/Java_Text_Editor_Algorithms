package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete
 * ADT
 * 
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

	private TrieNode root;
	private int size;

	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
		size = 0;
	}

	/**
	 * Insert a word into the trie. For the basic part of the assignment (part
	 * 2), you should ignore the word's case. That is, you should convert the
	 * string to all lower case as you insert it.
	 */
	public boolean addWord(String word) {

		word = word.toLowerCase();

		TrieNode currentNode = root;

		for (int i = 0; i < word.length(); i++) {

			char currentChar = word.charAt(i);
			TrieNode child = currentNode.getChild(currentChar);

			// if child doesnt exist, create it
			if (child == null) {

				currentNode.insert(currentChar);
				currentNode = currentNode.getChild(currentChar);
			} else {

				currentNode = child;

			}

			if (i == word.length() - 1 && !currentNode.endsWord()) {
				currentNode.setEndsWord(true);
				size++;

			}else if (i == word.length() - 1 && currentNode.endsWord()){return false;}

		}

		return true;
	}

	/**
	 * Return the number of words in the dictionary. This is NOT necessarily the
	 * same as the number of TrieNodes in the trie.
	 */
	public int size() {

		return size;
	}

	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) {

		if (s == null || s.trim().equals("")) {
			return false;
		}

		s = s.toLowerCase();

		TrieNode currentNode = root;

		for (int i = 0; i < s.length(); i++) {

			char currentChar = s.charAt(i);
			TrieNode child = currentNode.getChild(currentChar);
			currentNode = child;

			if (child == null) {
				return false;
			}

			

		}
		
		return currentNode.endsWord();

		//return true;
	}

	/**
	 * * Returns up to the n "best" predictions, including the word itself, in
	 * terms of length If this string is not in the trie, it returns null.
	 * 
	 * @param text
	 *            The text to use at the word stem
	 * @param n
	 *            The maximum number of predictions desired.
	 * @return A list containing the up to n best predictions
	 */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		// TODO: Implement this method
		// This method should implement the following algorithm:
		// 1. Find the stem in the trie. If the stem does not appear in the
		// trie, return an
		// empty list

		String s = prefix.toLowerCase();

		TrieNode currentNode = root;

		for (int i = 0; i < s.length(); i++) {

			char currentChar = s.charAt(i);
			TrieNode child = currentNode.getChild(currentChar);

			currentNode=child;
			
			if (child == null) {
				break;
			}

		}
		
		if (currentNode==null){
			
			return new ArrayList<String>();
			
		}
		
		

		// 2. Once the stem is found, perform a breadth first search to generate
		// completions
		// using the following algorithm:
		// Create a queue (LinkedList) and add the node that completes the stem
		// to the back
		// of the list.
		// Create a list of completions to return (initially empty)
		// While the queue is not empty and you don't have enough completions:
		// remove the first Node from the queue
		// If it is a word, add it to the completions list
		// Add all of its child nodes to the back of the queue
		// Return the list of completions

		Queue<TrieNode> q = new LinkedList<TrieNode>();
		q.add(currentNode);
		List<String> completions = new ArrayList<String>();
		
		while(!q.isEmpty() && completions.size()<numCompletions){
			
			TrieNode tn = q.remove();
			
			if(tn.endsWord()){
				
				completions.add(tn.getText());
			}
			
			Set<Character> childrenChar = tn.getValidNextCharacters();
			for (Character c : childrenChar) {
				
				q.add(tn.getChild(c));
				
			}
			
		}
		
		return completions;
	}

	// For debugging
	public void printTree() {
		printNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr) {
		if (curr == null)
			return;

		System.out.println(curr.getText());

		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}

}