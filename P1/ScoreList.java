/////////////////////////////////////////////////////////////////////////////
// Semester:          CS367 Spring 2016 
// PROJECT:          (P1: Grade Estimator)
// FILE:             (ScoreList.java)
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

/**
 * The ScoreList class implements the ScoreListADT and when instantiated it can
 *     be used as a container of Score instances
 *     
 * &lt;p&gt;Bugs: (No Bugs)
 *     
 * @author Team36
 *
 */
public class ScoreList implements ScoreListADT{
	
	/*
	 * items is an instance field that stores a reference of the place  
	 *      in memory where the reference to the array containing Score 
	 *      instances is stored
	 */
	private Score [] items;
	
	/*
	 * numItems is an instance field that stores a reference of the place in 
	 *         memory  where the number of items present in the items array 
	 *         is stored
	 */
	private int numItems;
	
	
	/**
	 * The ScoreList constructor initializes the data fields of the ScoreList
	 *     object
	 *     
	 * POSTCONDITIONS
	 * the instance fields are instantiated in the constructor
	 * 
	 */
	public ScoreList()
	{
		this.items = new Score[100];
		this.numItems = 0;
	}
	
	/** 
	 * Returns the number of Scores in the list or zero
	 * @return the number of scores in this list
	 */
	public int size()
	{	
		return this.numItems;	
	}
	
	/** 
	 * Adds the score to the end of this list.
	 * 
	 * PRECONDITIONS
	 * s must not be null
	 * 
	 * POSTCONDITIONS
	 * the score instance is added to the end of the list
	 * 
	 * @param s a non-null Score to place as the last item in the list.
	 * @throws IllegalArgumentException
	 */
	public void add(Score s)throws IllegalArgumentException
	{
		//ensures that parameter meets pre-condition else throws exception
		if(s == null)
		{
			throw new IllegalArgumentException();
		}
		
		//ExpandArray() method is called to expand items array if it is full
		if(numItems == items.length)
		{
			ExpandArray();
		}
		
		//Score instance is added to the array and numItems is incremented
		items[numItems] = s;
		numItems++;	
	}
	
	/**
	 * Removes and returns the item at index position i.
	 * If i is less than zero or greater than size()-1,
	 * will throw an IndexOutOfBoundsException.
	 * 
	 * PRECONDITIONS
	 * i must be greater than or equal to 0
	 * i must be less than size()-1
	 * 
	 * POSTCONDITIONS
	 * Array elements are accordingly shifted to ensure ScoreList is contiguous
	 * 
	 * @param i must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	public Score remove(int i) throws IndexOutOfBoundsException
	{
		//ensures parameters meet pre-conditions else throws an exception
		if(i < 0 || i > size() - 1)
		{	
			throw new IndexOutOfBoundsException();
		}
		
		//item to remove is retrieved
		Score scores = items[i];
		
		//ensures that ScoreList is contiguous
		for(int j = i + 1; j < numItems; j++)
		{	
			items[j-1] = items[j];	
		}
		
		//the index from where Score instance was removed is set to null
		items[numItems - 1] = null;
		numItems--;
		
		return scores;			
	}
	
	/**
	 * Returns (without removing) the item at index position i.
	 * If i is less than zero or greater than size()-1,
	 * will throw an IndexOutOfBoundsException.
	 * 
	 * PRECONDITIONS
	 * i must be greater than or equal to 0
	 * i must be less than size()-1
	 * 
	 * @param i must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	public Score get(int i) throws IndexOutOfBoundsException
	{	
		//check if parameter meets pre-conditions else throws an exception
		if(i < 0 || i > size()-1)
		{	
			throw new IndexOutOfBoundsException();	
		}
		
		//Score instance is retrieved
		Score scores = items[i];
		
		return scores;		
	}
	
	/**
	 * This method expands the items array when full
	 * 
	 * PRECONDITIONS
	 * items array must be full
	 * 
	 * POSTCONDITIONS
	 * items references a new array twice the size of the previous one
	 * 
	 */
	private void ExpandArray()
	{
		//a new Array twice the size of items is created
		Score [] a = new Score[items.length * 2];
		
		//array elements from items are copied to a
		for(int i = 0; i < numItems; i++)
		{	
			a[i] = items[i];	
		}
		
		//items now references a;
		items = a;	
	}

}
