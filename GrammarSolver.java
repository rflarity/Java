// Riley Flarity CS 145 Section 9am
// Programming Assignment #4, 05/04/17
//
// This program is for the grammar main to utilize
// Build a sentence and uses recursion to do so efficiently

import java.util.*;

public class GrammarSolver {

	private static Map<String, String> map;
	private static String sentence;
	private static Random rand;
	private static Scanner sc;
	private static String[] parts;
	private static ArrayList<String> parts2;
	
	// ctor
	public GrammarSolver() { System.out.println("Default GrammarSolver"); }
	
	// builds a grammar solver with a given list from file
	public GrammarSolver(List<String> r) {
		
		if (r.size() < 1 ) throw new IllegalArgumentException("size is < 1");
		map = new TreeMap<String, String>();
		rand = new Random();
		parts2 = new ArrayList<String>();
		sentence = "";
		for ( String s : r ) {
			// set key and value
			String key = (s.substring(0, s.indexOf("::=")));
			// enter the value after the "::=" which is 3 char long
			// will be split up later
			String value = (s.substring(s.indexOf("::=")+3, s.length()));
			// put into map if the key does not exist
			if (map.containsKey(key))
				throw new IllegalArgumentException("Same key value twice");
			else
				map.put(key, value);
		}
	} // end ctor
	
	// bool to terminate or not to terminate
	public static boolean contains(String symbol) {
		
		if (symbol.equals(null)) throw new IllegalArgumentException("no value");
		for(String s : map.keySet()) {
			if (symbol.equals(s))
				return true;
		}
		return false;
	}
	
	// builds random sentence using recursion
	public static String generate(String sym) {
		
		if (sym.equals(null)) throw new IllegalArgumentException();
		// initialize with user input
		if (contains(sym)) {
			String s1 = map.get(sym);  				// long pile of text must refine
			if (s1.contains("|")) {					// if the line has a | pipe
				parts = s1.split("[|]");			// fill up the String[]
				s1 = parts[rand.nextInt(parts.length)]; 	// random member of array
			}
			// Add everything to a list with scanner
			// No need to worry about white space
			Scanner sc = new Scanner(s1);  	
			while (sc.hasNext()) {	
				parts2.add(sc.next());
			}		
		}				

		// build the sentence out of array list
		if (!parts2.isEmpty()) {
			String s2 = parts2.get(0);
			parts2.remove(0);
			if (contains(s2)) {
				return generate(s2);
			}
			else if (parts2.size() >= 1) {
				sentence += s2 + " ";
				return generate(parts2.get(0));
			}
			// last element of list
			// hacky work around to reset sentence
			sentence += s2;
			s2 = sentence;
			sentence = "";
			return s2;
		}
		return sentence;
	} // end generate
	
	// creates set of non-terminals from file List<String> rules
	public static Set<String> getSymbols() { return map.keySet(); }
	
} // end class GrammarSolver
