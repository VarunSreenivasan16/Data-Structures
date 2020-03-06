/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          P2: Welcome to the Job Market
// FILE:             Game.java
//
// Authors: Team 36
// Author1: (Varun Srinivasan, vsreenivasan@wisc.edu, vsreenivasan, 001)
// Author2: (Shuoxuan Dong, sdong34@wisc.edu, sdong34, 001)
// Author3: (Harsha Kodavalla, kodavalla@wisc.edu, kodavalla,001)
// Author4: (Jordan Daymond, jordan.daymond@wisc.edu, daymond, 001)
// Author5: (Adam Opichka, aopichka@wisc.edu, aopichka, 001)
// Author6: (Owen Zinkgraf, ozinkgraf@wisc.edu, ozinkgraf, 001)
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: None
// Online sources: None
//////////////////////////// 80 columns wide //////////////////////////////////
 
import java.util.Iterator;

/**
 * The Game class maintains an active list of all jobs and uses the JobSimulator 
 * class to create and add new jobs to the listing. 
 *  
 *
 * @author Team 36
 */

public class Game{

    //list is an instance of JobList
    private ListADT<Job> list;
    
    //scoreBoard is an instance of Scoreboard
    private ScoreboardADT scoreBoard;
    
    //timeToPlay is an instance field that records the time left in the game
    private int timeToPlay;
    
    //jobSimulator is an instance of JobSimulator
    private JobSimulator jobSimulator;

    /**
     * Constructor. Initializes all variables.
     * @param seed
     * seed used to seed the random number generator in the Jobsimulator class.
     * @param timeToPlay
     * duration used to determine the length of the game.
     */
    public Game(int seed, int timeToPlay){
       	
    	this.list = new JobList();
    	this.scoreBoard = new Scoreboard();
    	this.timeToPlay = timeToPlay;
    	this.jobSimulator = new JobSimulator(seed);
    	
    }

    /**
     * Returns the amount of time currently left in the game.
     * @returns the amount of time left in the game.
     */
    public int getTimeToPlay() {
    	
        return this.timeToPlay;
    }

    /**
     * Sets the amount of time that the game is to be executed for.
     * Can be used to update the amount of time remaining.
     * @param timeToPlay
     *        the remaining duration of the game
     */
    public void setTimeToPlay(int timeToPlay) {
    	
    	this.timeToPlay = timeToPlay;
    }

    /**
     * States whether or not the game is over yet.
     * @returns true if the amount of time remaining in
     * the game is less than or equal to 0,
     * else returns false
     */
    
    public boolean isOver(){
    	
        return (this.timeToPlay <= 0);
    }
    
    /**
     * This method simply invokes the simulateJobs method
     * in the JobSimulator object in order to create jobs.
     */
    public void createJobs(){
    	
    	jobSimulator.simulateJobs(this.list, this.timeToPlay);

    }

    /**
     * @returns the length of the Joblist.
     */
    public int getNumberOfJobs(){
    	
        return this.list.size();
    }

    /**
     * Adds a job to a given position in the joblist.
     * Also requires to calculate the time Penalty involved in
     * adding a job back into the list and update the timeToPlay
     * accordingly
     * @param pos
     *      The position that the given job is to be added to in the list.
     * @param item
     *      The job to be inserted in the list.
     */
    public void addJob(int pos, Job item){
    	 
    	this.list.add(pos, item);
    	
    	//timePenalty
    	setTimeToPlay(getTimeToPlay() - pos);
    }

    /**
     * Adds a job to the joblist.
     * @param item
     *      The job to be inserted in the list.
     */
    public void addJob(Job item){
    	
    	this.list.add(item);
    	
    	//timePenalty
    	setTimeToPlay(getTimeToPlay() - getNumberOfJobs());
    }

    /**
     * Given a valid index and duration,
     * executes the given job for the given duration.
     *
     * This function should remove the job from the list and
     * return it after applying the duration.
     *
     * This function should set duration equal to the
     * amount of time remaining if duration exceeds it prior
     * to executing the job.
     * After executing the job for a given amount of time,
     * check if it is completed or not. If it is, then
     * it must be inserted into the scoreBoard.
     * This method should also calculate the time penalty involved in
     * executing the job and update the timeToPlay value accordingly
     * @param index
     *      The job to be inserted in the list.
     * @param duration
     *      The amount of time the given job is to be worked on for.
     * @return temp
     *       Returns the job that was updated.     
     */
    public Job updateJob(int index, int duration){
    	
    	//If duration greater than remaining time to play,
    	//set duration to remaining time to play.
    	
    	//time penalty 
    	setTimeToPlay(getTimeToPlay() - index);
    	
    	if(duration > getTimeToPlay())
    	{
    		duration = getTimeToPlay();
    	}
    	
    	Job temp = null;
    	
    	//Iterate for duration
    	for(int i = 0; i <= duration; i++)
    	{
    		//Increment steps completed based on duration
    		list.get(index).setSteps(i);
    		
    		//If job is completed, store that index value in the
    		//temp job, and return the temp job.
    		if(list.get(index).isCompleted())
    		{
    			this.scoreBoard.updateScoreBoard(list.get(index));
    			temp = list.remove(index);
    			setTimeToPlay(getTimeToPlay() - i);
    			return temp;
    		}
    	}
    	
    	//If Job is not completed, decrement based on duration
    	if(!list.get(index).isCompleted())
    	{
    		list.get(index).setTimeUnits(list.get(index).getTimeUnits() 
    				- duration);
    		list.get(index).setSteps(0);
    		temp = list.remove(index);
    		setTimeToPlay(getTimeToPlay() - duration);
    		
    	}
    	
        	
    	
    	
        return temp;
    }

    /**
     * This method produces the output for the initial Job Listing, IE:
     * "Job Listing
     *  At position: job.toString()
     *  At position: job.toString()
     *  ..."
     *
     */
    public void displayActiveJobs(){
    	
    	System.out.println("You have " + getTimeToPlay() + " left in the "
    			+ "game!");
    	System.out.println("Job Listing");
    	
    	/*Creates an iterator in order to step through list, printing job 
    	 *        listings.
    	 */
    	Iterator<Job> jobs = list.iterator();
    	int i = 0;
    	
    	while(jobs.hasNext())
    	{
    		System.out.println("At position: " + i +" " + 
    				jobs.next().toString());
    		i++;
    	}
    	
    	System.out.println();

    }

    /**
     * This function simply invokes the displayScoreBoard method in the 
     *      ScoreBoard class.
     */
    public void displayCompletedJobs(){
    	
    	this.scoreBoard.displayScoreBoard();
    }

    /**
     * This function simply invokes the getTotalScore method of the 
     *      ScoreBoard class.
     *      
     * @return the value calculated by getTotalScore
     */
    public int getTotalScore(){
    	
        return this.scoreBoard.getTotalScore();
    }
}