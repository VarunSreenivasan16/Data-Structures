/////////////////////////////////////////////////////////////////////////////
// Semester:          CS367 Spring 2016 
/////////////////////////////////////////////////////////////////////////////
// Semester:          CS367 Spring 2016 
// PROJECT:          (P1: Grade Estimator)
// FILE:             (Score.java)
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
 * The Score class is a blue print that defines the data fields and behavior
 *     of Score objects
 *     
 * &lt;p&gt;Bugs: (No Bugs)
 *     
 * @author Team36
 *
 */
public class Score {
	
	/*assignmentName is an instance field that stores a reference of the place  
	 *          in memory where the assignment name corresponding to the Score 
	 *          instance is stored
	 */
	private String assignmentName;
	
	/* pointsEarned is an instance field that stores a reference of the place  
	 *       in memory where the points earned corresponding to the Score 
	 *       instance is stored
	 */
	private double pointsEarned;
	
	/* maximumPoints is an instance field that stores a reference of the place  
	 *        in memory where the maximum points corresponding to the Score 
	 *        instance is stored
	 */
	private double maximumPoints;
	
	/**
	 * The Score constructor initializes the data fields of the Score
	 *     object
	 * 
	 * PRECONDITIONS
	 * assignment name must not be null
	 * points earned must not be less than 0 
	 * points earned must not be greater than maximum points
	 * maximum points must not be less than 0
	 * 
	 * POSTCONDITIONS
	 * the instance fields are instantiated in the constructor
	 *     
	 * @param assignmentName - the name of the assignment
	 * @param pointsEarned - the points Earned on the assignment
	 * @param maximumPoints - the maximum points possible on the assignment
	 * @throws IllegalArgumentException
	 */
	public Score(String assignmentName, double pointsEarned, double maximumPoints)
			throws IllegalArgumentException
	{
		//ensures that parameters meet pre-conditions else throws exception 
		if(assignmentName == null || 
			   (pointsEarned < 0 || pointsEarned > maximumPoints) 
			   || maximumPoints < 0)
		{
			throw new IllegalArgumentException();
		}
		
		//instance fields are instantiated 
		this.assignmentName = assignmentName;
		this.pointsEarned = pointsEarned;
		this.maximumPoints = maximumPoints;
	}
	
	/**
	 * It is an accesor method that provides us access to the Score object's 
	 *    assignmentName field
	 *    
	 * @return assignmentName
	 */
	public String getName()
	{	
		return this.assignmentName;	
	}
	
	/**
	 * It is an accesor method that provides us access to the Score object's 
	 *    pointsEarned field
	 *    
	 * @return pointsEarned field value
	 */
	public double getPoints()
	{	
		return this.pointsEarned;
	}
	
	/**
	 * It is an accesor method that provides us access to the Score object's 
	 *    maximum PointsEarned field
	 *    
	 * @return maximumPoints field value
	 */
	public double getMaxPossible()
	{
		return this.maximumPoints;
	}
	
	/**
	 * It is a method that returns the initial of the assignmentName
	 *    
	 * @return first character of the String corresponding to assignmentName
	 */
	public String getCategory()
	{
		//use the charAt method to obtain the character at the 0th index
		char c = assignmentName.charAt(0);
		String s = Character.toString(c);
		
		return s;
	}
	
	/**
	 * It is a method that returns the percentage obtained on the assignment
	 *    
	 * @return percentage score
	 */
	public double getPercent()
	{
		double percent = ((pointsEarned/ maximumPoints) * 100);
		
		return percent;
	}
	
	
}
