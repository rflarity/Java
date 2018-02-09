
// This word class is for the Anagram Manager to utilize
// Handles a single word and is comparable to itself
// Uses a comparator to overload compareTo

import java.util.Comparator;

public class Word implements Comparable<Word> {

	private String theWord; 
	private String canWord;
	
	public Word(String x) {
		x.toLowerCase();
		theWord = x;
		canWord = isCanonical(x);
	}
	
	public String getWord() { return theWord; }
	
	public String getForm() { return canWord;	}
	
	public String toString() {	return "[" +theWord + "=" + canWord + "]"; }
	
	// compares canWords to other canWords
	public int compareTo(Word other) {
		return getForm().compareTo(other.getForm());
	}
	
	// Create a new instance of comparator to invoke when called
	// this will allow comparison of multiple properties
	public static Comparator<Word> wordSort = new Comparator<Word>() {
		
		public int compare(Word word1, Word word2) {
		
			String w1 = word1.getWord();
			String w2 = word2.getWord();
			
			return w1.compareTo(w2);	
		}
	};
	
	// Method to flip the string around and use the canonical version
	// is a version of selection sort combined with recursion
	private String isCanonical(String s) {
	
		if (s.length() < 2)
			return s;
		int pos = 0;
		char min = s.charAt(0);
		for(int i = 1; i < s.length(); i++) {
			char c = s.charAt(i);
		   if (c < min) {
		 		min = c;
		  		pos = i;
		   }
		}
		return min + isCanonical(s.substring(0, pos) + s.substring(pos + 1));
	}

} // end word class
