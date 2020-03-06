/////////////////////////////////////////////////////////////////////////////
// Semester:          CS367 Spring 2016 
// PROJECT:          (P2: Welcome to the Job Market)
// FILE:             (JobListIterator.java)
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The JobListIterator class will be used to iterate through a chain of 
 * 		nodes of type Job. It has direct access to the chain of nodes
 * 		within Joblist. 
 *
 * <p>Bugs: (No Bugs)
 *
 * @author Team36
 */
public class JobListIterator<Job> implements Iterator<Job>{
	
	//create a Listnode to use as reference in the chain of nodes
	private Listnode<Job> curr;
	
	/**
	 * This is the constructor for an instance of a JobListIterar.
	 *
	 * @param head is a Listnode that is a header node
	 */
	public JobListIterator(Listnode<Job> head)
	{
		this.curr = head;
	}
	

	/**
	 * This method tests to see if curr's next node is null and if it is,
	 * 		the method will return false, otherwise true.
	 *
	 * @return true if there is a next node to be processed
	 */
	public boolean hasNext()
	{
		return (curr.getNext() != null);
	}
	
	
	/**
	 * This method processes and returns the next Job in the chain of 
	 * 		nodes.
	 *
	 * @return job - the next Job in the chain of nodes
	 * @throws NoSuchElementException
	 */
	@Override
	public Job next()
	{
		//if there is no Job to be processed, throw exception
	    if(!hasNext())
	    {
	    	throw new NoSuchElementException();
	    }
	    
	    //create a new Job using the data of the next node
	    curr = curr.getNext();
	    Job job = curr.getData();
	     
	    //return the Job
	    return job;
	}
	
	/**
	 * The remove method has not been implemented and will throw an
	 * 		UnsupportedOperationException if it is called. 
	 *
	 * @throws UnsupportedOperationException
	 */
	public void remove() 
	{
		  throw new UnsupportedOperationException();
	}

}
