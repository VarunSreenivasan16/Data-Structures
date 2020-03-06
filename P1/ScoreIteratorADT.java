/////////////////////////////////////////////////////////////////////////////
// Semester:          CS367 Spring 2016 
// PROJECT:          (P1: Grade Estimator)
// FILE:             (ScoreIteratorADT.java)
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
 * ScoreIteratorADT interface defines the behavior of the ScoreIterator
 * 
 * &lt;p&gt;Bugs: (No Bugs)
 * 
 * @author Team36
 *
 */
public interface ScoreIteratorADT {

	
	/**
	 * Checks whether iteration has more elements
	 *        If so, returns true else returns false
	 * 
	 * @return true if the iteration has more elements
	 */
	boolean hasNext();
	
	/**
	 * returns the next score instance in the iteration
	 * 
	 * PRECONDITIONS
	 * An instance of score must be present
	 * 
	 * @return the next Score instance in the iteration
	 * @throws NoSuchElementException
	 */
	Score next() throws NoSuchElementException ;
}
