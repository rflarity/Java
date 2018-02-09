
// This program takes a word and then builds the list of other possible
// words out of the letter by letter combination and outputs to the user

import java.util.*;

public class AnagramManager {
	
	public Word[] wordList;
	public Set<String> theSet;
		
	// main ctor
	public AnagramManager(List<String> d) {
	
		if ( d.size() <= 0 ) throw new IllegalArgumentException("Nothing there");
		// create an array of strings with Word objects in them. 
		wordList = new Word[d.size()];
		theSet = new TreeSet<String>();
		int count = 0;
		for (String x : d) {
			Word myWord = new Word(x);
			wordList[count] = myWord;
			count++;
		}
	}
	
	// sortbyWord uses compareTo and a comparator method to sort by words
	public void sortByWord() {
		Arrays.sort(wordList, Word.wordSort);
	}
	
	// sortbyForm uses arrays.sort with compareTo with word forms
	public void sortByForm() { 
		Arrays.sort(wordList); 
	}
	
	// string getAnagram, calls the getAnagrams to fill up the set of words
	public String getAnagram(String word) {
	
		// build the set
		word.toLowerCase();
		getAnagrams(word);
		Random randy = new Random();
		int count = 0;
		// use value from set to print the anagram
		if (!theSet.isEmpty())
			count = randy.nextInt(theSet.size());
		for (String s : theSet) {
			if (count == 0)
				return ": " + s;
			count--; 
		}
		// if the set is empty
		return "\nYour word was not found in the list.";
	}
	
	// get a set of Anagrams
	public Set<String> getAnagrams(String word) {
	
		// create new instance to compare forms
		Word str = new Word(word);
		// clears set of any old words
		theSet.clear();
		for (int i = 0; i < wordList.length-1; i++) {
			if (wordList[i].getForm().equals(str.getForm()))
				theSet.add(wordList[i].getWord());	// only need the word value
		}
		// adds an extra line for ease of reading
		System.out.println("");
		return theSet;
	}
	
	// prints first 5 and last 5 of the list in dictionary
	// or whatever is in the array if its less than 10...
	public String toString() {
	
		if (wordList.length >= 10) {
			for (int i = 0; i < 5; i++)
				System.out.print(wordList[i].toString());
			System.out.print("[...]");
			for (int i = wordList.length-5; i < wordList.length; i++)
				System.out.print(wordList[i].toString());
			return "\n";
		}
		else
			for (int i = 0; i < wordList.length; i++) 
				System.out.print(wordList[i].toString());
			return "\n";
	}
	
} // end class Anagram Manager
