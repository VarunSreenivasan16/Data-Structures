import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Reducer solves the following problem: given a set of sorted input files (each
 * containing the same type of data), merge them into one sorted file. 
 *
 */
public class Reducer {
    // list of files for stocking the PQ
    private List<FileIterator> fileList;
    
    /*
     * type is an instance variable that corresponds to type of record
     * 
     * dirName is an instance variable that corresponds to directory of input 
     *    files
     * 
     * outFile is an instance variable that corresponds to name of output file
     */
    private String type,dirName,outFile;

    /**
     * The main method handles the execution of the entire program
     * @param args
     *        args[0] - type of record
     *        args[1] - directory of input files
     *        args[2] - name of output file
     */
    public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: java Reducer <weather|thesaurus> <dir_name> <output_file>");
			System.exit(1);
		}

		String type = args[0];
		String dirName = args[1];
		String outFile = args[2];

		Reducer r = new Reducer(type, dirName, outFile);
		r.run();
	
    }

	/**
	 * Constructs a new instance of Reducer with the given type (a string indicating which type of data is being merged),
	 * the directory which contains the files to be merged, and the name of the output file.
	 */
    public Reducer(String type, String dirName, String outFile) {
		this.type = type;
		this.dirName = dirName;
		this.outFile = outFile;
    }

	/**
	 * Carries out the file merging algorithm described in the assignment description. 
	 */
    public void run() {
    	
    	File dir = new File(dirName);
		File[] files = dir.listFiles();
		Arrays.sort(files);

		Record r = null;

		// list of files for stocking the PQ
		fileList = new ArrayList<FileIterator>();
		
		

		for(int i = 0; i < files.length; i++) {
			File f = files[i];
			if(f.isFile() && f.getName().endsWith(".txt")) {
				//fileList.add(fif.makeFileIterator(f.getAbsolutePath()));
				fileList.add(new FileIterator(f.getAbsolutePath(), i));
				
			}
		}

		switch (type) {
		case "weather":
			r = new WeatherRecord(fileList.size());
			break;
		case "thesaurus":
			r = new ThesaurusRecord(fileList.size());
			break;
		default:
			System.out.println("Invalid type of data! " + type);
			System.exit(1);
		}
		
		
		//fQ is an instance of FileLinePriorityQueue
		FileLinePriorityQueue fQ = 
				new FileLinePriorityQueue(r.getNumFiles(), r.getComparator());
		
			
		try
		{
			
			//creates the file on which the output is written
			File file = new File(outFile);
			
			//output is a local instance of PrintWriter
			PrintWriter output = new PrintWriter(file);
			
			//loop to access all files
			for(int i = 0; i < r.getNumFiles(); i++)
			{
				
				//checks if file has another line
				if(fileList.get(i).hasNext())
				{
					//f is a local variable that stores the FileLine obtained
					FileLine f = fileList.get(i).next();
				
					//insert into the priorityQueue
					fQ.insert(f);	
					
				}
				
			}
		
			//fL is a local instance of FileLine
			FileLine fL = null;
		
			//i is a local variable used to store the FileIterator Index
			int index = 0;
		
			/*z is a local variable that stores the number of line iterations
			 *  completed
			 */
			int z = 0;
		
			//loop continues until priorityQueue is empty
			while(!fQ.isEmpty())	
			{	
				//fL stores reference to the removed FileLine 
				fL = fQ.removeMin();
			
				//stores FileIterator index
				index = fL.getFileIterator().getIndex();
			
				//checks if r is an instance of ThesaurusRecord
				if(r instanceof ThesaurusRecord)
				{
				
					/*prints out the line on the file if at least one line 
					 *       iteration has been completed and the line to be 
					 *       printed out has been completely processed and 
					 *       formatted
					 */
					if( z!=0 && !fL.getString().substring(0, 
							fL.getString().indexOf(':')).equals
							(r.toString().substring(0, 
									r.toString().indexOf(':'))))
					{
						//prints out content 
						output.println(r.toString());	
					}
			
				}
			
				//checks if r is an instance of WeatherRecord
				else if(r instanceof WeatherRecord)
				{
				
					/*numberOfCommasforFL is a local variable that stores the 
					 *      value corresponding to the number of commas 
					 *      encountered while parsing a FileLine
					 */
					int numberOfCommasforFL = 0;
				
					/*lineToCompare is a local variable that stores a reference 
					 *    of the place in memory where its string contents 
					 *    corresponding to the FileLine's string contents are
					 *    stored
					 */
					String lineToCompare = fL.getString();
				
					/*k is a local variable that tracks position of where a  
					 *  comma appears
					 */
					int k = 0;
				
					/*j is a local variable used in the following while loop to 
					 *  propagate through each character of the string
					 * 
					 */
					int j = 0;
				
					/*loop continues until two commas don't appear. In this case
					 *     the loop continues until we obtain the station and 
					 *     date values for the FileLine instance 
					 */
					while(numberOfCommasforFL < 2)
					{
						//detects if character is a comma
						if(lineToCompare.charAt(j) == ',')
						{
							//increment number of commas
							numberOfCommasforFL++;
						
							//set value of j to k
							k = j;
						}
					
						//increment j
						j++;
					}
				
				
					/*modify lineToCompare to store only the String containing 
					 *       station and date values
					 */
					lineToCompare = lineToCompare.substring(0, k);
				
					/*numberOfCommasforR is a local variable that stores the 
					 *      value corresponding to the number of commas 
					 *      encountered while parsing the String obtained from 
					 *      calling the toString method
					 */
					int numberOfCommasforR = 0;
					
					/*lineToCompareWith is a local variable that stores a  
					 *    reference of the place in memory where its string 
					 *    contents corresponding to the String returned by 
					 *    calling toString are stored
					 */   
					String lineToCompareWith = r.toString();
					
					/*y is a local variable that tracks position of where a 
					 *  comma appears
					 */
					int y = 0;
					
					/*l is a local variable used in the following while loop to 
					 *  propagate through each character of the string
					 * 
					 */
					int l = 0;
					
					/*loop continues until two commas don't appear. In this 
					 *     case the loop continues until we obtain the station 
					 *     and date values from the string contents  
					 *     lineToCompareWith references
					 */
					while(numberOfCommasforR < 2)
					{
						//detects if character is a comma
						if(lineToCompareWith.charAt(l) == ',')
						{
							//increment number of commas
							numberOfCommasforR++;
							
							//set l's value to y
							y = l;
						}
						
						//increment l
						l++;
						
					}
					
					/*modify lineToCompareWith to store only the String  
					 *       containing station and date values
					 */
					lineToCompareWith = lineToCompareWith.substring(0, y);	
					
					/*prints out the line on the file if at least one line 
				     *       iteration has been completed and the line to be 
				     *       printed out has been completely processed and 
				     *       formatted
				     */
					if(z!=0 && !lineToCompare.equals(lineToCompareWith) && 
							r.toString().charAt(r.toString().length() -1)!= ',')
					{
						//prints out content
						output.println(r.toString());
					}
					
				}
				
				//join method is called
				r.join(fL);
				
				/*checks if the file from which which FileLine was removed 
				 *       has more FileLines
				 */
				if(fileList.get(index).hasNext())
				{
					//store the reference to FileLine
					fL = fileList.get(index).next();
					
					//insert in PriorityQueue
					fQ.insert(fL);
					
				}
				
				//increment z
				z++;
			}
			
		    /*join method is called(specifically for last String to be 
		     *     printed out)
		     */
			r.join(fL);
			
			//Print to file
			output.println(r.toString());
			
			//close the PrintWriter stream
			output.close();
		
		
		//catch PriorityQueueFullException	
		}catch(PriorityQueueFullException e)
		{
			System.out.println("Priority Queue is full");
		}
		
		//catch PriorityQueueEmptyException	
		catch(PriorityQueueEmptyException e)
		{
			System.out.println("Priority Queue is empty");
		}
		//catch FileNotFoundException	
		catch(FileNotFoundException e)
		{
			System.out.println("File Not found");
		}
		
		//catch any Runtime exception, specifically file formatting errors
		catch(Exception e)
		{
			System.out.println("Error has occured in File Formatting");
			System.out.println(e.toString());
		}
		
    }
    
}
