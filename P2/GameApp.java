/////////////////////////////////////////////////////////////////////////////
// Semester:          CS367 Spring 2016 
// PROJECT:          (P2: Welcome to the Job Market)
// FILE:             (GameApp.java)
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

import java.util.Scanner;

/**
 * The GameApp class is the main class which calls methods from all the other
 *     classes to execute the game
 *     
 * &lt;p&gt;Bugs: (No Bugs)
 * 
 * @author Team36
 *
 */
public class GameApp{
    /**
     * Scanner instance for reading input from console
     */
    private static final Scanner STDIN = new Scanner(System.in);
    
    //game is an instance of Game
    private Game game;

    /**
     * Constructor for instantiating game class
     * @param seed: Seed value as processed in command line
     * @param timeToPlay: Total time to play from command line
     */
    public GameApp(int seed, int timeToPlay)
    { 
    	//game instance is initialized
    	game = new Game(seed, timeToPlay);	
    }

    /**
     * Main function which takes the command line arguments and instantiate the
     * GameApp class.
     * The main function terminates when the game ends.
     * Use the getIntegerInput function to read inputs from console
     *
     * @param args: Command line arguments <seed> <timeToPlay>
     */
    public static void main(String[] args){

        System.out.println("Welcome to the Job Market!");
        
        /*seed is a local variable that stores the seed value obtained 
         *     from the command-line arguments 
         */
        int seed = 0;
        
        /*timeToPlay is a local variable that stores the time user wants to
         *      play the game for. It is obtained from the command-line 
         *      arguments
         */
        int timeToPlay = 0;
        
        
        
        try
        {
        	
        	//seed value is obtained by parsing String argument
        	seed = Integer.parseInt(args[0]);
        	
        	//timeToPlay is obtained by parsing String argument
        	timeToPlay = Integer.parseInt(args[1]);
        	
        	//gameApp is an instance of GameApp
        	GameApp gameApp = new GameApp(seed, timeToPlay);
        	
        	//start method is called
            gameApp.start();
        	
        
        }catch(Exception e)
        {
        	System.out.println(e.toString());
        }
       
        
    }

    /**
     * The start method is used to execute the entire game from start to end
     *  
     * Helper methods from other classes are called here to generate the 
     * required display and establish interaction with the user    
     */
    private void start(){
        
    
    while(!game.isOver())
    {
    	
      //createJobs method is called
      game.createJobs();
    	
      //displayActiveJobs method is called
      game.displayActiveJobs();
      
        
      /*pos is a local variable that stores the position of the job user wants 
       *    to work on 
       */
      int pos = getIntegerInput("Select a job to work on: ");
      
      STDIN.nextLine();
      
      /*pos is a local variable that stores the amount of time the user wants 
       *    to work on a job for
       */
      int duration = getIntegerInput("For how long would you like to work on "
      		+ "this job?: ");
      
      
      //checks if duration is negative
      if( duration < 0)
      {
    	  System.out.println("Can't enter negative value as duration");
    	  break;
      }
      
      //job is initialized by calling the updateJob method
      Job job = game.updateJob(pos, duration);
      
      
      //checks if job is completed
      if(job.isCompleted())
      {
    	  
    	  System.out.println("Job completed! Current Score: " + 
    			  game.getTotalScore());
    	  
    	  //displayCompletedJobs method is called
    	  game.displayCompletedJobs();
      }
      
      
      /*insert is a local variable that stores the position where the user 
       *       wants to insert the Job instance back into the list
       * 
       */
      int insert = 0;
      
      //check if Job is completed
      if(!job.isCompleted())
      {
    	  insert = getIntegerInput("At what position would you like to insert "
    	  		+ "the job back into the list?\n");
    	  
    	  
    	  if(insert < 0 ||  insert >= game.getNumberOfJobs())
          {
    		  //addJob method is called
    		  game.addJob(job);   
          }
          else
          {
        	  //addJob method is called
        	  game.addJob(insert, job);
          }
          
      }
      
      
    }
    
       System.out.println("Game Over!");
       System.out.println("Your final score: " + game.getTotalScore());
       
    
    }

    /**
     * Displays the prompt and returns the integer entered by the user
     * to the standard input stream.
     *
     * Does not return until the user enters an integer.
     * Does not check the integer value in any way.
     * @param prompt The user prompt to display before waiting for this integer.
     */
    public static int getIntegerInput(String prompt) {
        System.out.print(prompt);
        while ( ! STDIN.hasNextInt() ) {
            System.out.print(STDIN.next()+" is not an int.  "
            		+ "Please enter an integer.");
        }
        return STDIN.nextInt();
    }
}