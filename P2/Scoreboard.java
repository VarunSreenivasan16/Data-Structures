/////////////////////////////////////////////////////////////////////////////
// Semester:          CS367 Spring 2016 
// PROJECT:          (P2: Welcome to the Job Market)
// FILE:             (Scoreboard.java)
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

/**
 * The Scoreboard class contains a single ListADT<Job> that contains jobs which
 *     have been fully completed. The class can print the status of the
 *     Scoreboard object, including the total score and the list of jobs which
 *     have been completed.
 *
 * <p>Bugs: (No Bugs)
 *
 * @author Team36
 */

public class Scoreboard implements ScoreboardADT{
	
	//jobs in an instance of JobList
	private ListADT<Job> jobs; 
	
	
	/**
	 * This is the constructor for an instance of a Scoreboard.
	 */
	public Scoreboard()
	{
		//Job instance is initialized
		this.jobs = new JobList();
	}
	
	/**
    * Calculates the total combined number of points for every job in the 
    *            scoreboard.
    * 
    * @return The summation of all the points for every job currently stored 
    *         in the scoreboard.
    */
	public int getTotalScore()
	{
		//sum is a local variable that records total points
		int sum = 0;
		
		//jobDone is an instance of JobListIterator
		Iterator<Job> jobDone = jobs.iterator();
		
		//calculates the sum of points
		while(jobDone.hasNext())
		{
			//sum is incremented
			sum += jobDone.next().getPoints();
		}
		
		return sum;
	
	}
	
	/**
    * Inserts the given job at the end of the scoreboard.
    * 
    * @param job 
    * 		The job that has been completed and is to be inserted into the 
    *       list.
    */
	public void updateScoreBoard(Job job)
	{
		//Job is added
		jobs.add(job);
	}
	
    /**
    * Prints out a summary of all jobs currently stored in the scoreboard. 
    * The formatting must match the example exactly.
    */
	public void displayScoreBoard()
	{
		System.out.println("Total Score: " + getTotalScore());
		System.out.println("The jobs completed: ");
		
		//jobDisplay is a local instance of JobListIterator
	    Iterator<Job> jobDisplay = jobs.iterator();
		
		while(jobDisplay.hasNext())
		{
			//jobDone is a local instance of Job
			Job jobDone = jobDisplay.next();
			
			System.out.println("Job Name: " + jobDone.getJobName());
			System.out.println("Points earned for this job: " + 
					jobDone.getPoints());
			System.out.println("--------------------------------------------");
		}
		
	}
	

}
