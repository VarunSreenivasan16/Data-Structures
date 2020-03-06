/////////////////////////////////////////////////////////////////////////////
// Semester:          CS367 Spring 2016 
// PROJECT:          (P1: Grade Estimator)
// FILE:             (ScoreIterator.java)
//
// Authors: Team 36
// Author 1: (Varun Srinivasan, vsreenivasan@wisc.edu, vsreenivasan, 001)
// Author 2: (Shuoxuan Dong, sdong34@wisc.edu, sdong34, 001) 
// Author 3: (Harsha Kodavalla, kodavalla@wisc.edu, kodavalla,001)
// Author 4: (Jordan Daymond, jordan.daymond@wisc.edu, daymond, 001)
// Author 5: (Adam Opichka, aopichka@wisc.edu, aopichka, 001)
// Author 6  (Owen Zinkgraf, ozinkgraf@wisc.edu, ozinkgraf, 001)
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: none
//
// Online sources: none
//////////////////////////// 80 columns wide //////////////////////////////////


import java.util.NoSuchElementException;

/**
 * ScoreIterator implements the ScoreIteratorADT interface and is used to 
 * 		iterate through an instances of ScoreList, which contain instances of
 *      Score
 * 
 * &lt;p&gt;Bugs: (No Bugs)
 * 
 * @author Team36
 *
 */
public class ScoreIterator implements ScoreIteratorADT {
	
	/*
	 * myList is an instance field that stores a reference of the place  
	 *        in memory where the reference to a ScoreList instance is stored
	 */
	private ScoreList myList;
	
	/*
	 * currPos is instance field that stores a reference of the place  
	 *        in memory where the current Position of the ScoreIterator is 
	 *        stored
	 */
	private int currPos;
	
	/*
	 * categoryName instance field that stores a reference of the place  
	 *        in memory where the reference to the String containing the
	 *        category name is stored
	 */
	private String categoryName;
	
	/**
	 * The ScoreIterator constructor initializes the data fields of the 
	 *     ScoreIterator instance
	 *  
	 * POSTCONDITIONS
	 * the instance fields are instantiated in the constructor   
	 *     
	 * @param list - the ScoreList instance
	 * @param categoryName - the component name (eg Midterm, final, homework)
	 */
	public ScoreIterator(ScoreList list, String categoryName)
	{
		this.myList = list;
		this.categoryName = categoryName;
		currPos = 0;
	}
	
	/**
	 * Checks whether iteration has more elements
	 *        If so, returns true else returns false
	 * 
	 * @return true if the iteration has more elements
	 */
	public boolean hasNext()
	{
		return (currPos < myList.size());
	}
	
	/**
	 * returns the next score instance in the iteration
	 * 
	 * PRECONDITIONS
	 * An instance of score must be present
	 * 
	 * @return the next Score instance in the iteration
	 * @throws NoSuchElementException
	 */
	public Score next() throws NoSuchElementException
	{
		//checks if an element is present
		if(!hasNext())
		{
			//exception is thrown if no element is present
			throw new NoSuchElementException();
		}
		
		//checks if the category initials are same
		if(myList.get(currPos).getCategory().charAt(0) == categoryName.charAt(0))
		{
			//returns Score instance and advances pointer
			Score result = myList.get(currPos);
			currPos++;
			
			return result;
		}
		else 
		{
			return  null;
		}
		
		
	}

}
