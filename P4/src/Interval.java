/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          Program 4: Schedule Planner
// FILE:             Interval.java
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
 * Interval is a class that is responsible for creating new intervals that can 
 * be added to the schedule planner. Each interval contains a start, end, and 
 * label. 
 *
 * <p>Bugs: No bugs have been found
 *
 * @author Team 36
 */
public class Interval<T extends Comparable<T>> implements IntervalADT<T> {


	//start is an instance variable that stores the interval's starting point 
	private T start;

	//end is an instance variable that stores the end point of the interval
	private T end;

	//label is an instance variable that stores label of an interval
	private String label;

	/**
	 * Interval constructor that instantiates instance variables of the 
	 *          Interval class
	 * 
	 * @param start - start point of an interval
	 * @param end - end point of interval
	 * @param label - label of an interval
	 */
	public Interval(T start, T end, String label)
	{
		this.start = start;
		this.end = end;
		this.label = label;
	}

	/**
	 * getter method that returns start
	 * 
	 * @return start - the start of the interval
	 */
	@Override
	public T getStart() 
	{
		return start;
	}

	/**
	 * getter method that returns end
	 * 
	 * @return end - the end of the interval 
	 */
	@Override
	public T getEnd() 
	{
		return end;
	}

	/**
	 * getter method that returns label
	 * 
	 * @return label - the name of the interval 
	 */
	@Override
	public String getLabel() 
	{
		return label;
	}

	/**
	 * The overlaps method is responsible for checking the given
	 * interval to another to see if it overlaps. 
	 *
	 * @throws IllegalArgumentException if other is null
	 * @param other is the target interval to compare for overlap
	 * @return true if this interval overlaps with the other interval
	 */
	@Override
	public boolean overlaps(IntervalADT<T> other) {

		//Throw IllegalArgumentException if other is null
		if(other == null)
		{
			throw new IllegalArgumentException();
		}

		//Return false if the two intervals don't overlap
		if(this.getEnd().compareTo(other.getStart()) < 0 
				|| this.getStart().compareTo(other.getEnd())> 0)
		{
			return false;
		}
		
		//True otherwise
		else
		{
			return true;
		}

	}

	/**
	 * the contains method checks if the interval contains a particular point
	 * 
	 * @param point is the point to check if the interval contains
	 * @return true if the point is in the interval, false otherwise
	 */
	@Override
	public boolean contains(T point) {

		//Return true if the point is within the interval
		if(point.compareTo(this.getStart()) >= 0 
				&& point.compareTo(this.getEnd()) <=0)
		{
			return true;
		}
		
		//False otherwise
		else 
		{
			return false;
		}

	}

	/**
	 * the compareTo method compares two intervals
	 * 
	 * @param - the interval to compare with
	 * @return - negative if this interval's comes before the other interval, 
	 * positive if this interval comes after the other interval,
	 * and 0 if the intervals are the same
	 * 
	 */
	@Override
	public int compareTo(IntervalADT<T> other) {

		//Return 1 if this interval comes after other
		if(this.getStart().compareTo(other.getStart()) > 0)
		{
			return 1;
		}
		//Return -1 if this interval comes before other
		else if(this.getStart().compareTo(other.getStart()) < 0)
		{
			return -1;
		}
		//If starts are the same, compare ends
		else
		{
			//Return 1 if this interval comes after other
			if(this.getEnd().compareTo(other.getEnd()) > 0)
			{
				return 1;
			}
			//Return -1 if this interval comes before other 
			else if(this.getEnd().compareTo(other.getEnd()) < 0)
			{
				return -1;
			}
			//Intervals are the same, so return 0
			else
			{
				return 0;
			}

		}
	}

	/**
	 * the toString method returns the interval information including
	 *     the start, end, and label of the interval
	 * 
	 * @return String containing information about the interval
	 */
	public String toString()
	{
		//Return the interval in String format with correct format
		return label + " [" + start + ", " + end+"]";
	}

}
