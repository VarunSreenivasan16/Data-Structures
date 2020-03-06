/////////////////////////////////////////////////////////////////////////////
// Semester:          CS367 Spring 2016 
// PROJECT:          (P1: Grade Estimator)
// FILE:             (GradeEstimator.java)
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The GradeEstimator class is used to generate Grade reports 
 * 
 * &lt;p&gt;Bugs: (No Bugs)
 * 
 * @author Team36
 *
 */
public class GradeEstimator {
	
	/*
	 * letterGrades is an instance field that stores a reference of the place  
	 *       in memory where the reference to the array containing letter 
	 *       grades is stored
	 */
	private String [] letterGrades;
	
    /*
     * minimumThresholds is an instance field that stores a reference of the   
	 *        place in memory where the reference to the array containing  
	 *        thresholds is stored
     */
	private double [] minimumThresholds;
	
	/*
	 * categoryNames is an instance field that stores a reference of the   
	 *         place in memory where the reference to the array containing  
	 *         category names is stored
	 */
	private String [] categoryNames;
	
	/*
	 * categoryWeights is an instance field that stores a reference of the   
	 *         place in memory where the reference to the array containing  
	 *         weights of each category is stored
	 */
	private double [] categoryWeights;
	
	/*
	 * categoryOfScoreList is an instance field that stores a reference of the   
	 *         place in memory where the reference to the array containing  
	 *         instances of ScoreList is stored
	 */
	private ScoreList [] categoryOfScoreList;
	
	
	/**
	 * Default constructor that is used when no file is present
	 */
	public GradeEstimator()
	{
		this.letterGrades = Config.GRADE_LETTER;
		this.minimumThresholds = Config.GRADE_THRESHOLD;
		this.categoryNames = Config.CATEGORY_KEY;
		this.categoryWeights = Config.CATEGORY_WEIGHT;
		this.categoryOfScoreList = new ScoreList[categoryNames.length];
		
		
		//ScoreList instances in the categoryOfScoreList array are initialized
		for(int i = 0; i < this.categoryNames.length; i++)
		{
			this.categoryOfScoreList[i] = new ScoreList();
			
		}
		
	}
	
	/**
	 * The GradeEstimator constructor initializes the data fields of the 
	 *     GradeEstimator instance
	 *
	 * PRECONDITIONS
	 * the file to be read must be formatted correctly else exception is thrown
	 *
	 * POSTCONDITIONS
	 * the instance fields are instantiated in the constructor
	 * 
	 * @param scanFile - instance of Scanner
	 * @throws GradeFileFormatException 
	 */
	public GradeEstimator(Scanner scanFile) throws GradeFileFormatException
	{
		this.letterGrades = scanLines(scanFile);
		this.minimumThresholds = parsing(scanLines(scanFile));
		this.categoryNames = scanLines(scanFile);
		this.categoryWeights = parsing(scanLines(scanFile));
		this.categoryOfScoreList = new ScoreList[categoryNames.length];
	}

	/**
	 * The main method is used to run the program and generate the
	 * 	   grade sheets
	 * 
	 * PRECONDITIONS
	 * the file must be present
	 * the file to be read must be formatted correctly else exception is thrown
	 * 
	 * POSTCONDITIONS
	 * A grade report is generated
	 * 
	 * @param args - args[0] - file name is passed to args[0]
	 */
	public static void main(String[] args) {
		
		//checks if one command-line argument is passed
		if(args.length != 1)
		{
			//prints out default message if one argument isn't passed
			System.out.println(Config.USAGE_MESSAGE);
			
			try 
			{
				//DefaultReportFromConfig method is called
				System.out.println(DefaultReportFromConfig());
			
			}
			catch (GradeFileFormatException e)
			{
				System.out.println(e.toString());
			}
			
			
			
		}
		
		else
		{
			try
			{
				
				/*
				 * gradeEstimate is instantiated by calling the 
				 *      createGradeEstimatorFromFile method
				 */
				GradeEstimator gradeEstimate = createGradeEstimatorFromFile(args[0]);
				System.out.println(gradeEstimate.getEstimateReport());
			}
			
			catch(FileNotFoundException e)
			{
				System.out.println(e.toString());
			}
			catch (GradeFileFormatException e)
			{
				System.out.println(e.toString());
			}
			
						
		}


	}
	
	/**
	 * creates the GradeEstimator instance and calls methods to generate the
	 * 		   grade report
	 *
	 * PRECONDITIONS
	 * the file must be present
	 * the file to be read must be formatted correctly else exception is thrown
	 * 
	 * POSTCONDITIONS
	 * A grade report is compiled here and GradeEstimator instance is returned
	 * 
	 * @param gradeInfo - file name
	 * @return - GradeEstimator instance is returned
	 * @throws FileNotFoundException 
	 * @throws GradeFileFormatException
	 */
	public static GradeEstimator createGradeEstimatorFromFile(String gradeInfo) 
			throws FileNotFoundException, GradeFileFormatException
	{
		
		//file is an instance of the File class
		File file = new File(gradeInfo);
		
		//scanFile is an instance of the Scanner class
		Scanner scanFile = new Scanner(file);
		
		//gradeEstimate is an instance of the GradeEstimator class
		GradeEstimator gradeEstimate = new GradeEstimator(scanFile);
		
		//ScoreList instances in the categoryOfScoreList array are initialized
		for(int i = 0; i < gradeEstimate.categoryNames.length; i++)
		{
			gradeEstimate.categoryOfScoreList[i] = new ScoreList();
		}
		
		//addingScore method is called
		gradeEstimate.addingScore(gradeEstimate, scanFile);
			
		
		return gradeEstimate;
			
	}
	
	
	/**
	 * used when no argument is passed
	 * 
	 * @return - the report
	 * @throws GradeFileFormatException
	 */
	private static String DefaultReportFromConfig()throws GradeFileFormatException
			
	{
		
		
		GradeEstimator gradeEstimate = new GradeEstimator();
		
		
	     /*
	      * defualtReport is a local variable that stores a reference 
	      *           of the place in memory where its String contents,  
	      *           which correspond to the report when no
	      *           file is provided
	      */
		String defaultReport = "";
		
		//calculatingNumScores method is called
		defaultReport += gradeEstimate.calculatingNumScores();
		
		//calculatingGrades method is called
		defaultReport += gradeEstimate.calculatingGrades();
		
		
		return defaultReport;
		
		
		
	}
	
	
	/**
	 * compiles the grade report by calling the other methods
	 * 
	 * @return - the grade report
	 */
	public String getEstimateReport()throws GradeFileFormatException
	{
		/*
		 * report is a local variable that stores a reference of the
	     *        place in memory where its string contents, which 
	     *        correspond to the grade report, are stored
		 */
		String report = "";
		
		//individualComponents method is called
		report += individualComponents();
		
		//calculatingNumScores method is called
		report += calculatingNumScores();
		
		//calculatingGrades method is called
		report += calculatingGrades();
		
		return report;
	}
	
	
	/**
	 * Score instances to categoryOfScoreList array
	 * 
	 * @param gradeEstimate - instance of GradeEstimator
	 * @param scanFile - scanner instance
	 * @throws GradeFileFormatException
	 */
	private void addingScore(GradeEstimator gradeEstimate, Scanner scanFile)
			throws GradeFileFormatException
	{
		//checks if next line is present
		while (scanFile.hasNextLine()) 
		{
			try
			{
				
				/*
				 * scores is a local variable that stores a reference of the  
			     *        place in memory where the array containing Score 
			     *        instance parameters is stored
				 */
				String [] scores = gradeEstimate.scanLines(scanFile);
				
				/*
				 * assignmentName is a local variable that stores a reference 
			     *           of the place in memory where its String contents,  
			     *           which correspond to the name of the assignment 
			     *           retrieved from the line in the file, are stored
				 */
				String assignmentName = scores[0];
				
				/*
				 * points is a local variable that stores the value retrieved 
				 * 		  from the line in the file, which corresponds to 
				 *        points earned on the component
				 * 
				 * the input from the file is parsed to convert it to double
				 */
				double points = Double.parseDouble(scores[1]);
				
				/*
				 * maxPossible is a local variable that stores the value  
				 * 	  retrieved from the line in the file, which corresponds
				 *    to the maximum points that can be attained
				 * 
				 * the input from the file is parsed to convert it to double
				 */
				double maxPossible = Double.parseDouble(scores[2]);
				
				
			    //instance of Score is created using data obtained from file
				Score score = new Score(assignmentName, points, maxPossible);
			
				/*
				 * Score instance is accordingly added to the appropriate 
				 *       ScoreList container by checking if they are of the
				 *       same category
				 */
				for (int i = 0; i < gradeEstimate.categoryNames.length; i++) 
				{
					
					/*
					 * checks if the category name initial of the Score 
					 *        instance is same as that of any of the 
					 *        categories present in the categoryNames array
					 */
					if (score.getCategory().charAt(0) == 
							gradeEstimate.categoryNames[i].charAt(0))
					{	
						//Score instance is added to appropriate category
						gradeEstimate.categoryOfScoreList[i].add(score);
						break;
					}
				}
			
			}
			catch(Exception e)
			{
				throw new GradeFileFormatException();
			}
				
		}
	}
	
	
	
	
	
	/**
	 * calculates the number of graded components and returns it in a
	 * 			  String form as demanded by the report
	 * 
	 * @return - String, containing information on the number of components
	 */
	private String calculatingNumScores()throws GradeFileFormatException
	{
		try
		{
		/*
		 * numScores is a local variable that stores the number of Score 
		 *           instances that have been created using data from file
		 */
		int numScores = 0;
		
		/*
		 * each ScoreList container present in the categoryOfScoreList array
		 *      will be iterated through to count number of Score instances
		 */
		for (int i = 0; i < categoryOfScoreList.length; i++) 
		{
		
			//score is an instance of ScoreIterator
			ScoreIterator score = new ScoreIterator(categoryOfScoreList[i], 
					categoryNames[i]);
		
			//iterate through each ScoreList container
			while(score.hasNext())
			{
				//the ScoreIterator's next method is called
				score.next();
				
				//numScores is incremented
				numScores++;
			}
				
		}
		
		return "Grade estimate is based on " + numScores + " scores\n";
		
		}
		catch(Exception e)
		{
			throw new GradeFileFormatException();
		}
	
	}
	
	/**
	 * returns a String containing the component name of the Score instance
	 * 		   and the percentage earned on the particular component
	 * 
	 * @return String, containing information about the component name and
	 *                 percentage earned on the component
	 */
	private String individualComponents()throws GradeFileFormatException
	{
		try
		{
		/*
		 * componentScores is a local variable that stores a reference of the
	     *          place in memory where its string contents, which 
	     *          correspond to the individual component name and percentage
	     *          score obtained are stored
		 */
		String componentScores = "";
		
		/*
		 * each ScoreList container present in the categoryOfScoreList array
		 *      will be iterated through to obtain the component name and 
		 *      percentage earned on the component
		 */
		for(int i = 0; i < categoryOfScoreList.length; i++)
		{
			//scores is an instance of the ScoreIterator
			ScoreIterator scores = new ScoreIterator(categoryOfScoreList[i],
					categoryNames[i]);
			
			//iterate through the ScoreList container
			while(scores.hasNext())
			{
				//component is an instance of Score
				Score component = scores.next();
				
				//componentScores is appropriately concatenated
				componentScores += component.getName() + " " +
				String.format("%7.2f", component.getPercent()) +"\n";
			}
			
		}
		
		return componentScores;
		
		}
		catch(Exception e)
		{
			throw new GradeFileFormatException();
		}
		
	}
	
	
	/**
	 * returns weighted score, unweighted score, category weight, and category
	 *         name in the form of a string
	 *         
	 * @return String, containing weighted scores unweighted scores, category 
	 *         weights, and category names
	 */
	private String calculatingGrades() throws GradeFileFormatException
	{
	   try
	   {
		/*
	     * averageScore is a local variable that stores the percentage score
	     *              for a particular Score instance				
	     */
		double averageScore;
		
		/*
		 * weightedAverageScore is a local variable that stores the weighted
		 *         percentage obtained on a particular category (eg Midterm,
		 *         final, homework,etc) 
	     *              
		 */
		double weightedAverageScore;
		
		/*
		 * unweightedAverageScore is a local variable that stores the 
		 *           unweighted percentage obtained on a particular 
		 *           category (eg Midterm, final, homework,etc) 
		 */
		double unWeightedAverageScore;
		
		/*
		 * totalGrade is a local variable that stores the sum of the 
		 *      weightedAverageScore for all components
		 */
		double totalGrade = 0.0;
		
		/*
		 * components is a local variable that stores a reference of the
	     *            place in memory where its string contents, which 
	     *            include weighted score, unweighted score, category 
	     *            weight,and category name are stored in the form of 
	     *            a string   
		 */
		String components = "";
		
		/*
		 * letterGrade is a local variable that stores a reference of the
	     *            place in memory where its string contents, which 
	     *            corresponds to the letter grade earned, are stored
		 */
		String letterGrade = "";
		
		//iterates through the categoryOfScoreList array
		for (int i = 0; i < categoryOfScoreList.length; i++) 
		{
			averageScore = 0.0;
			weightedAverageScore = 0.0;
			
			
		    //scores is an instance of ScoreIterator
			ScoreIterator scores = new ScoreIterator(categoryOfScoreList[i],
					categoryNames[i]);
			
			//if no scores present full credit given
			if(!scores.hasNext())
			{
				averageScore = 100.0;
				weightedAverageScore = categoryWeights[i];
				unWeightedAverageScore = 100.0;
			}
			else
			{
				//iterate through the ScoreList container
				while(scores.hasNext())
				{
					averageScore += scores.next().getPercent();
				}
			
				//weightedAverageScore is calculated
				weightedAverageScore = ((averageScore / 
					categoryOfScoreList[i].size()) * categoryWeights[i]) / 100;
			
				//unWeightedAverageScore is calculated
				unWeightedAverageScore = (averageScore / 
					categoryOfScoreList[i].size());
			}
			
			//totalGrade is calculated
			totalGrade += weightedAverageScore;
			
			//all the data is appropriately concatenated in a String form
			components += String.format("%7.2f", weightedAverageScore)+ "% = " +
					String.format("%5.2f", unWeightedAverageScore) + "% of "+ 
					String.format("%2.0f", categoryWeights[i]) +
					"% for " + categoryNames[i] + "\n";
					
			
			
		}
		
		
		//components is concatenated 
		components += "--------------------------------\n";
		components = components + String.format("%7.2f", totalGrade) + 
				"% weighted percent\n";
		
		//iterated through minimumThresholds to obtain letter grade
		for (int i = 0; i < minimumThresholds[i]; i++)
		{
			//checks if totalGrade is in the particular grade band
			if (totalGrade >= minimumThresholds[i])
			{
				//letterGrade is assigned
				letterGrade = letterGrades[i];
				break;
				
			}
			
			if(i == minimumThresholds.length - 1)
			{
				letterGrade = "unable to estimate letter grade for " + totalGrade;
				break;
			}
		}		
		
		//components is concatenated
		components = components + "Letter Grade Estimate: " + letterGrade;
		
		return components;
		
		
	   }catch(Exception e)
	   {
		   throw new GradeFileFormatException();
	   }
		
	}
	
	
	/**
	 * this method parses an array of string elements to an array of double 
	 *      elements
	 *      
	 * PRECONDITIONS
	 * the array passed as an arguments, must have elements that can be parsed
	 *     to double. If not, an exception is thrown
	 * 
	 * POSTCONDITIONS
	 * all elements are parsed and array of double is returned
	 * 
	 * @param List - a String array that can be parsed to an array of double
	 * @return an array of double 
	 * @throws GradeFileFormatException
	 */
	private double [] parsing(String [] List) throws GradeFileFormatException
	{
		/*
		 * myList is a local variable that stores a reference of the place  
	     *        in memory where the array containing the elements that 
	     *        are parsed from the String array, is stored   
		 */
		double [] myList = new double[List.length];
		
		/*
		 * value is a local variable that stores the value of the element that 
		 *        has been parsed
		 */
		double value = 0.0;
		
		//iterate through the String array
		for(int i = 0; i < List.length; i++)
		{
			try
			{
				//value stores the parsed array element
				value = Double.parseDouble(List[i]);
				
				//the particular element is added to the array of double
				myList[i] = value;
				
			}
			catch(Exception e)
			{
				throw new GradeFileFormatException();
			}
			
		}
		
		return myList;
		
	}
	
	/**
	 * This method is used to read data from a single line of the file and 
	 *      store the data in an array
	 * @param scnr
	 * @return an array of String containing data retrieved from a single line
	 */
	private String [] scanLines(Scanner scnr)throws GradeFileFormatException
	{
		/*
		 * letter is a local variable that stores a reference of the place 
	     *        in memory where its String contents, which correspond 
	     *        to a single line read from the file, are stored
		 */
		String letter = scnr.nextLine();
		
		//throws exception if there is a blank line
		if(letter.length() == 0)
		{
			throw new GradeFileFormatException();
		}
		
		/*
		 * List is a local variable that stores a reference of the place  
	     *      in memory where the array containing the  elements that are
	     *      obtained from processing the letter variable is stored
		 */
		String [] List = new String[letter.length()];
		
		/*
		 * copiedList is a local variable that stores a reference of the place 
		 *       in memory, corresponding to where the array is stored  
		 *       
	     * The array elements are obtained from processing the List array
		 */
		String [] copiedList;
		
		/*
		 * numValuesEntered is a local variable that keeps track of the number
		 *    of elements that have been added to the List array 
		 */
		int numValuesEntered = 0;
		
		/*
		 * j is a local variable that is used as a marker to signal the 
		 *   point from which the String element should be obtained and 
		 *   added to the String array  
		 */
		int j = 0;
		
		//iterate through the String letter
		for(int i = 0; i < letter.length(); i++)
		{
			//checks for this particular character
			if(letter.charAt(i) == '#')
			{
				break;
			}
			
			/*
			 * if the character at current index is blank space and the 
			 *    character at the previous index isn't, it indicates that
			 *    the current index is the end point of the particular String 
			 *    element that must be captured. Substring method is used to 
			 *    capture the String element
			 */
			if(i!= 0 && i != letter.length()-1 && letter.charAt(i) == ' ' &&
					letter.charAt(i-1) != ' ')
			{
				//reference of the String element obtained is passed to value
				String value = letter.substring(j, i + 1).trim();
				
				//this reference is now stored in the List array
				List[numValuesEntered] = value;
				
				//numValuesEntered is incremented
				numValuesEntered++;
				
				/*j is assigned the value of i, so that j now marks the 
				 *  starting point of the next String element
				 */
				j = i;
			}
			
			/*
			 * when the last index is not a space character, the previous loop
			 *      will not be able to correctly process the String element. 
			 *      Thus, we use this else if loop to ensure that the if the 
			 *      String element contains a character at the last index, it
			 *      can be correctly processed and passed a String element
			 *      to the List array
			 */
			else if(i == (letter.length()-1))
			{
				//reference of the String element obtained is passed to value
				String val = letter.substring(j, i + 1).trim();
				
				//this reference is now stored in the List array
				List[numValuesEntered] = val;
				
				//numValuesEntered is incremented
				numValuesEntered++;
			
			}
		}
		
		copiedList = new String[numValuesEntered];
		
		//elements from List array are copied to the copiedList array
		for(int i = 0; i < numValuesEntered; i++)
		{
			copiedList[i] = List[i];
		}
	
		return copiedList;
	}

}
