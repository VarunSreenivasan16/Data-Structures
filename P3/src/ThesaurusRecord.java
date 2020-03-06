
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * The ThesaurusRecord class is the child class of Record to be used when merging thesaurus data.
 * The word field is the entry in the thesaurus, syn is the list of all associated synonyms.
 */

public class ThesaurusRecord extends Record{
    
    /*word is an instance variable that stores a reference of the place in 
     *     memory, where its string contents corresponding to the particular
     *     word obtained from FileLine is stored 
     */
	private String word;
	
	/*syn is an instance variable that stores a reference of the place in 
	 *    memory where a reference to an arraylist containing synonyms of
	 *    the particular word is stored
	 */
	private ArrayList<String> syn;
	
	/**
	 * Constructs a new ThesaurusRecord by passing the parameter to the parent constructor
	 * and then calling the clear method()
	 */
    public ThesaurusRecord(int numFiles) {
    	super(numFiles);
    	clear();
    }

    /**
	 * This Comparator should simply behave like the default (lexicographic) compareTo() method
	 * for Strings, applied to the portions of the FileLines' Strings up to the ":"
	 * The getComparator() method of the ThesaurusRecord class will simply return an
	 * instance of this class.
	 */
	private class ThesaurusLineComparator implements Comparator<FileLine> {
		public int compare(FileLine l1, FileLine l2) {
			
			/*line1 is a local variable that stores the word we need to process
			 *      from l1
			 */
			String line1 = l1.getString().substring(0, 
					l1.getString().indexOf(':'));
			
			/*line2 is a local variable that stores the word we need to process
			 *      from l2
			 */
			String line2 = l2.getString().substring(0, 
					l2.getString().indexOf(':'));
			
			//check if both words are the same
			if(line1.equals(line2))
			{
				return 1;
			}
			
			//place elements in array and compare using Array's sort method
			String [] sortArray = new String[2];
			sortArray[0] = line1;
			sortArray[1] = line2;
			Arrays.sort(sortArray);

			//check how have elements been adjusted
			if(sortArray[0].equals(line1))
			{
				return -1;
			}
			else
			{
				return 1;
			}
			
		}
		
		/**
		 * This method overrides Object's equals method and is used to compare
		 *      instances of Thesaurus Lines
		 */
		public boolean equals(Object o) {
			return this.equals(o);
		}
		
    }
    
	/**
	 * This method should simply create and return a new instance of the ThesaurusLineComparator class.
	 */
    public Comparator<FileLine> getComparator() {
		return new ThesaurusLineComparator();
    }
	
	/**
	 * This method should (1) set the word to null and (2) empty the list of synonyms.
	 */
    public void clear() {
		
    	word = null;
    	syn = new ArrayList<String>();
    }
	
	/**
	 * This method should parse the list of synonyms contained in the given FileLine and insert any
	 * which are not already found in this ThesaurusRecord's list of synonyms.
	 */
    public void join(FileLine w) {
		
    	/*wordtoAdd is a local variable that stores a reference of the place
    	 *     in memory, where its string contents corresponding to the word
    	 *     obtained from processing FileLine is stored
    	 */
    	String wordtoAdd = w.getString().substring(0, 
    			w.getString().indexOf(':'));
    	
    	//checks if wordtoAdd isn't equal to word
    	if(!wordtoAdd.equals(word))
    	{
    		//clear method is called
    		clear();
    		
    		//wordtoAdd is instantiated
    		word = wordtoAdd;
    	}
    	
    	//line references the String containing all the synonyms
    	String line = w.getString().substring(wordtoAdd.length() + 1);
    	
    	/*y is a local variable that tracks the position while iterating
    	 *  through the string
    	 */
    	int y = 0;
    	
    	//iterate through string in line
    	for(int i = 0; i < line.length(); i++)
    	{
    		//checks if i has reached last index
    		if(i == line.length() - 1)
    		{
    			/*last is a local variable that references the String 
    			 *     corresponding to the synonym to be processed
    			 */
    			String last = line.substring(y, i + 1);
    			
    			//checks if synonym is already present in arraylist
    			if(!syn.contains(last))
    			{
    				syn.add(last);
    			}
    			
    		}
    		
    		//checks if the character at i is a comma
    		else if(line.charAt(i) == ',')
    		{
    			/*z is a local variable that references the String 
    			 *     corresponding to the synonym to be processed
    			 */
    			String z = line.substring(y, i);
    			
    			//reset y's value
    			y = i + 1;
    			
    			//check if synonym is already present
    			if(!syn.contains(z))
    			{
    				syn.add(z);
    			}
    			
    		}
    		
    	}
    	
    }
     	
    
	/**
	 * returns the String that is eventually printed out on output file
	 */
    public String toString() {
    	
    	//sort the ArrayList
    	Collections.sort(syn);
    	
    	//out is a local variable that references the string to display
    	String out = "";
    	
    	//iterate through the ArrayList
    	for(int i = 0; i < syn.size() - 1; i++)
    	{
    		//format the string 
    		out += syn.get(i) + ",";
    	}
		
    	//return the String to display
    	return word + ":" + out + syn.get(syn.size()-1);
    	
	}
    
}
