import java.util.ArrayList;
import java.util.Comparator;

/**
 * The WeatherRecord class is the child class of Record to be used when merging weather data. Station and Date
 * store the station and date associated with each weather reading that this object stores.
 * l stores the weather readings, in the same order as the files from which they came are indexed.
 */
public class WeatherRecord extends Record{
    
	
	/*station is an instance field that stores the station number associated 
	 *        with the weather reading
	 */
	private int station;
	
	/*date is an instance field that stores the date associated with the 
	 *     weather reading
	 */
	private int date;
	
	/*/*
	 * data is an instance field that stores a reference of the place  
	 *       in memory where the reference to the arraylist containing letter 
	 *       weather readings is stored
	 */
	private ArrayList<Double> data;

	/**
	 * Constructs a new WeatherRecord by passing the parameter to the parent constructor
	 * and then calling the clear method()
	 */
    public WeatherRecord(int numFiles) {
		super(numFiles);
		clear();
    }
	
	/**
	 * This comparator should first compare the stations associated with the given FileLines. If 
	 * they are the same, then the dates should be compared. 
	 */
    private class WeatherLineComparator implements Comparator<FileLine> {
		public int compare(FileLine l1, FileLine l2) {
			
			/*l1Station is a local variable that stores the station number 
			 *          obtained from the l1 argument 
			 */
			int l1Station = Integer.parseInt
					(l1.getString().substring(0, l1.getString().indexOf(',')));
					
			/*l2Station is a local variable that stores the station number 
			 *          obtained from the l2 argument 
			 */
			int l2Station = Integer.parseInt
					(l2.getString().substring(0, l2.getString().indexOf(',')));
					
			//checks which number is larger
			if(l1Station < l2Station)
			{
				return -1;
			}
			else if (l1Station > l2Station)
			{
				return 1;
			}
			
			//if both are equal
			else
			{
				/*
				 * l1Date is a local variable that stores the date that is  
			     *        obtained from the l1 argument 
				 */
				int l1Date = Integer.parseInt(l1.getString().substring
						(l1.getString().indexOf(',') + 1, 
						l1.getString().lastIndexOf(',')));
				
				/*
				 * l2Date is a local variable that stores the date that is  
			     *        obtained from the l2 argument 
				 */
				int l2Date = Integer.parseInt(l2.getString().substring
						(l2.getString().indexOf(',') + 1, 
						l2.getString().lastIndexOf(',')));
				
				//checks which date is greater
				if(l1Date < l2Date)
				{
					return -1;
				}
				else if (l2Date > l1Date)
				{
					return 1;
				}
				else 
				{
					return 1;
				}
				
			}
			
		}
		
		/**
		 * This method overrides Object's equals method and is used to compare
		 *      instances of Weather Lines
		 */
		public boolean equals(Object o) {
			return this.equals(o);
		}
    }
    
	/**
	 * This method should simply create and return a new instance of the WeatherLineComparator
	 * class.
	 */
    public Comparator<FileLine> getComparator() {
		return new WeatherLineComparator();
    }
	
	/**
	 * This method should fill each entry in the data structure containing
	 * the readings with Double.MIN_VALUE
	 */
    public void clear() {
    	
    	//reset station to 0
    	station = 0;
    	
    	//reset date to 0
    	date = 0;
    	
    	//store pointer to new ArrayList
    	data = new ArrayList<Double>();

    	//set default values
    	for(int i = 0; i < getNumFiles(); i++)
    	{
    		data.add(Double.MIN_VALUE);
    	}
    	
    }

	/**
	 * This method should parse the String associated with the given FileLine to get the station, date, and reading
	 * contained therein. Then, in the data structure holding each reading, the entry with index equal to the parameter 
	 * FileLine's index should be set to the value of the reading. Also, so that
	 * this method will handle merging when this WeatherRecord is empty, the station and date associated with this
	 * WeatherRecord should be set to the station and date values which were similarly parsed.
	 */
    public void join(FileLine li) {
		
    	/*stationToAdd is a local variable that stores the station number 
		 *          obtained from the li argument 
		 */
    	int stationToAdd = Integer.parseInt(li.getString().substring
    			(0, li.getString().indexOf(',')));
    	
    	/*dateToAdd is a local variable that stores the station number 
		 *          obtained from the li argument 
		 */
    	int dateToAdd = Integer.parseInt(li.getString().substring
    			(li.getString().indexOf(',') + 1,
    			li.getString().lastIndexOf(',')));
    	
    	/*if either station or date from FileLine instance is different, 
    	 *   variables are reset
    	 */
    	if(station != stationToAdd || date != dateToAdd)
    	{
    		//clear method is called
    		clear();
    		
    		//station is re-initialized
    		station = stationToAdd;
    		
    		//date is re-initialized
    		date = dateToAdd;
    		
    	}
    	
    	/*line is a local variable stores the weather reading obtained 
    	 *     from the FileLine
    	 */
    	double line = Double.parseDouble(li.getString().substring
    			(li.getString().lastIndexOf(',')+ 1));
    	
    	//index is a local variable that stores the FileIterator index
    	int index = li.getFileIterator().getIndex();
    	
    	
    	/*weather reading is added to a particular position in the list based 
    	 *        on the index value
    	 */
    	data.add(index, line);
    	
    	//removes the default value for that particular reading
    	data.remove(index+1);
    	
    }
	
	/**
	 * returns the String that is eventually printed out on output file
	 */
    public String toString() {
    	
    	/*line is a local variable that stores a reference to a place in memory
    	 *     where its string contents corresponding to the string that must 
    	 *     be printed out are stored
    	 */
    	String line = "";
    	
    	
    	//access all values in arraylist
    	for(int i = 0; i < data.size(); i++)
    	{
    		//checks if value is the default one
    		if(data.get(i) == Double.MIN_VALUE)
    		{
    			/*line is appropriately formatted based on position of element
    			 *       in the list
    			 */
    			if( i == data.size() - 1)
    			{
    				line += "-";
    			}
    			else
    			{
    				line += "-,";
    			}
    			
    		}
    		
    		//if the element is not default value
    		else
    		{
    			/*line is appropriately formatted based on position of element
    			 *       in the list
    			 */
    			if(i == data.size() - 1)
    			{
    				line += data.get(i);
    			}
    			else
    			{
    				line += data.get(i) + ",";
    			}
    			
    		}
    		
    	}
		
    	//returns string to be displayed
		return station + "," + date + "," + line;
    }
}
