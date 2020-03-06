/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          Program 2: Welcome to the Job Market
// FILE:             JobList.java
//
// TEAM:    Team 36
// Author 1: (Varun Srinivasan, vsreenivasan@wisc.edu, vsreenivasan, 001) 
// Author 2: (Shuoxuan Dong, sdong34@wisc.edu, sdong34, 001) 
// Author 3: (Harsha Kodavalla, kodavalla@wisc.edu, kodavalla,001) 
// Author 4: (Jordan Daymond, jordan.daymond@wisc.edu, daymond, 001) 
// Author 5: (Adam Opichka, aopichka@wisc.edu, aopichka, 001) 
// Author 6  (Owen Zinkgraf, ozinkgraf@wisc.edu, ozinkgraf, 001)
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: none
// 
// Online sources: none
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class stores all currently active jobs. It implements ListADT<Job> 
 * interface as presenting in the readings and lecture notes.  
 * The JobList class is implemented as a singly-linked chain of 
 * Listnode<Job> nodes with a header node and without a tail reference.  
 *
 * <p>Bugs: None
 *
 * @author 
 * Author 1: (Varun Srinivasan, vsreenivasan@wisc.edu, vsreenivasan, 001) 
 * Author 2: (Shuoxuan Dong, sdong34@wisc.edu, sdong34, 001) 
 * Author 3: (Harsha Kodavalla, kodavalla@wisc.edu, kodavalla,001) 
 * Author 4: (Jordan Daymond, jordan.daymond@wisc.edu, daymond, 001) 
 * Author 5: (Adam Opichka, aopichka@wisc.edu, aopichka, 001) 
 * Author 6  (Owen Zinkgraf, ozinkgraf@wisc.edu, ozinkgraf, 001)
 */
public class JobList implements ListADT<Job> {
	
	//the head node reference
	private Listnode<Job> head;
	//the size of ListADT
	private int size;
	
	//constructor
	public JobList()
	{
		this.head = new Listnode<Job>(null);
		this.size = 0;
	}
	
	
	
	/** 
	 * Adds an item at the end of the list
	 * 
	 *@param item - an item to add to the list
	 *@throws IllegalArgumentException if item is null
	 *              
     */
	public void add(Job item)
	{
		
		if(item == null)
		{
			throw new IllegalArgumentException();
		}
		
		//the new node to be added
		Listnode<Job> newNode = new Listnode<Job>(item);
		
		//current reference
		Listnode<Job> curr = head;
		
		//traverse to next none-null position
		while(curr.getNext() != null)
		{
			curr = curr.getNext();
		}
		
		curr.setNext(newNode);
		this.size++;
		
	}
	
	
	 /** Add an item at any position in the list
     * @param item - an item to be added to the list
     * @param pos - position at which the item must be added. 
     *              Indexing starts from 0
     * @throws IllegalArgumentException - if item is null
     * @throws IndexOutOfBoundsException - if pos is less than 
     *               0 or greater than size() - 1
     */

    public void add(int pos, Job item)
    {
    	//to check if the item to be added is valid
    	if(item == null)
		{
			throw new IllegalArgumentException();
		}
    	//to check if the pos is valid
    	if(pos < 0 || pos > size()-1)
    	{
    		throw new IndexOutOfBoundsException();
    	}
    	
    	Listnode<Job> curr = this.head;
    	Listnode<Job> newNode = new Listnode<Job>(item);
    	
    	for(int i = 0; i < pos; i++)
    	{
    		curr = curr.getNext();
    	}  
    	
    	newNode.setNext(curr.getNext());
    	//the new node to be added
    	curr.setNext(newNode);
    	
        this.size++;
    	
    	
    }
	
    
    /** 
     * Check if a particular item exists in the list
     * @param item
     *              the item to be checked for in the list
     * @return true
     *              if value exists, else false
     * @throws IllegalArgumentException
     *              if item is null
     */
     public boolean contains(Job item)
     {
    	//to check if the item to be added is valid
    	 if(item == null)
    	 {
    		 throw new IllegalArgumentException();
    	 }
    	 
    	 Listnode<Job> curr = this.head;
    	 
    	 //traverse through list
    	 for(int i = 0; i < size; i++)
    	 {
    		 if(curr.getNext().getData().toString().equals(item.toString()))
    		 {
    			 //the list contains item
    			return true;
    		 }
    		 curr = curr.getNext();
    	 }
    	 //the list do not contains item
    	 return false;
    	
     }

    
	
    /** Returns the position of the item to return
     * @param pos
     *              position of the item to be returned
     * @throws IndexOutOfBoundsException
     *              if position is less than 0 or greater than size() - 1
     */

     public Job get(int pos)
     {
    	//to check if the pos is valid
    	 if(pos < 0 || pos > size()-1)
      	 {
      		throw new IndexOutOfBoundsException();
      	 }
    	 
    	 Listnode<Job> curr = this.head;
    	 
    	 //traverse to position
    	 for(int i = 0; i <= pos; i++)
    	 {
    		 curr = curr.getNext();
    	 }
    	 
    	 return curr.getData(); 
    	 
     }
    
    
    /** 
     * Returns true if the list is empty
     * @return value is true if the list is empty
     *              else false
     */
     public boolean isEmpty()
     {
    	 return (this.size <= 0);
     }
    

    /** Removes the item at the given positions
     * @param pos
     *          the position of the item to be deleted from the list
     * @return returns the item deleted
     * @throws IndexOutOfBoundsException
     *          if the pos value is less than 0 or greater than size() - 1
     */
     public Job remove(int pos)
     {
    	//to check if the pos is valid
    	if(pos < 0 || pos > size()-1)
     	{
     		throw new IndexOutOfBoundsException();
     	}
    	
    	//reference to current node
    	Listnode<Job> curr = this.head;
    	//temporary node to save Job removed
    	Listnode<Job> temp;
    	//traverse to position
    	for(int i = 0; i < pos; i++)
    	{
    		curr = curr.getNext();
    	}
    	
    	temp = curr.getNext();
    	//removed
    	curr.setNext(curr.getNext().getNext());
    	//decrease size
    	this.size--;
    	//return removed data
    	return temp.getData();
    		
    	 
     }
    
    /**
      * Returns the size of the singly linked list
      * @return the size of the singly linked list
      * 
      */
     public int size()
     {
    	return this.size;
     }
    
     /**
      * Iterator constructor 
      * @return a new Iterator of the list
      * 
      */  
     public JobListIterator<Job> iterator()
     {
    	 return new JobListIterator<Job>(head);
     }

	
	

}
