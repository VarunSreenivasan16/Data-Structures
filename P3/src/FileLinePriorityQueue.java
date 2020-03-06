import java.util.Comparator;

/**
 * An implementation of the MinPriorityQueueADT interface. This implementation stores FileLine objects.
 * See MinPriorityQueueADT.java for a description of each method. 
 *
 */
public class FileLinePriorityQueue implements MinPriorityQueueADT<FileLine> {
    
	/*maxSize is an instance variable that stores the value corresponding 
	 *   to the maximum size of the priorityQueue
	 */
	private int maxSize;
	
	/*pq is an instance variable that stores a reference of the place in the 
	 *   memory where the reference to an array containing FileLine instances 
	 *   is stored 
	 */
	private FileLine[] pq;
	
	//cmp is an instance of Comparator that is used to compare FileLines
    private Comparator<FileLine> cmp;
    
    /*numItems is an instance variable that stores the number of items in the 
     *   array
     */
    private int numItems;

    /**
     * The FileLinePriorityQueue constructors instantiates all the instance 
     *     fields
     * @param initialSize - corresponds to size of priorityQueue
     * @param cmp - Comparator instance to be used
     */
    public FileLinePriorityQueue(int initialSize, Comparator<FileLine> cmp) {
    	
		this.cmp = cmp;
		this.maxSize = initialSize;
		this.pq = new FileLine[initialSize + 1];
		this.numItems = 0;
    }

    /**
     * The removeMin method removes the least value FileLine element as 
     *     determined by the comparator
     */
    public FileLine removeMin() throws PriorityQueueEmptyException {
		
    	//checks if priorityQueue is empty
    	if(isEmpty())
    	{
    		throw new PriorityQueueEmptyException();
    	}
    	
    	//temp is a local variable that stores the FileLine to be removed
     	FileLine temp = pq[1];
     	
     	//assign the last item in the array to be the first
    	pq[1] = pq[numItems];
    	
    	//decrement numItems
    	numItems--;
    	
    	//currPos is a local variable that is used to help us reheapify
    	int currPos = 1;
    	
    	//loop continues until we reach last element
    	while(currPos < (numItems))
    	{
    		/*leftChildIndex is a local variable that stores the position of 
    		 *    the parent's left child
    		 */
    		int leftChildIndex = currPos * 2;
    		
    		/*rightChildIndex is a local variable that stores the position of 
    		 *    the parent's left child
    		 */
    		int rightChildIndex = (currPos * 2) + 1;
    		
    		/*loop breaks if the value of leftChildIndex is greater than 
    		 *     numItems
    		 */
    		if(leftChildIndex > numItems)
    		{
    			break;
    		}
    		
    		/*minIndex is a local variable that stores the position of 
    		 *   child with a smaller value
    		 * 
    		 */
    		int minIndex = leftChildIndex;
    			
    		//check if rightChildIndex is valid
    		if(rightChildIndex <= numItems)
    		{
    			/*compare the FileLines in minIndex(leftChildIndex) and the
    			 *        rightChildIndex
    			 */
    			if(cmp.compare(pq[minIndex], pq[rightChildIndex]) > 0)
    			{
    				/*if FileLine in rightChildIndex is smaller set the value
    				 *   of rightChildIndex to minIndex 
    				 */
    				minIndex = rightChildIndex;
    			}
    		}
    		
    		/*if FileLine stored in parent index has greater value than that 
    		 *   in the child index, array is adjusted accordingly
    		 */
    		if(cmp.compare(pq[currPos], pq[minIndex]) > 0)
    		{
    			/*temps is a local variable that stores stores a reference to
    			 *      the FileLine stores at the minIndex position
    			 */
    			FileLine temps = pq[minIndex];
                
    			//swap the FileLine instance stored at the following positions
    			pq[minIndex] = pq[currPos];
    			pq[currPos] = temps;
    			
    			//set value of minIndex to currPos
    			currPos = minIndex;
    		}
    		
    		//break if child doesn't have smaller value than parent
    		else
    		{
    			break;
    		}
    		
    	}
    	
    	// return FileLine instance to be removed
		return temp;
    }

    /**
     * This method is used to insert a FileLine instance into the 
     *      PriorityQueue
     */
    public void insert(FileLine fl) throws PriorityQueueFullException {
		
    	//check if array is at full capacity
    	if(numItems == maxSize)
    	{
    		throw new PriorityQueueFullException();
    	}
    	
    	//add FileLine instance
    	pq[numItems + 1] = fl;
    	
    	//child is a local variable that stores position of the child
    	int child = numItems + 1;
    	
    	//parent is a local variable that stores positon of parent
    	int parent = 0;
    	
    	//loop continues until reahipifaction is complete
    	while(child > 1)
    	{
    		//instantiate parent
    		parent = child/2;
    		
    		//check if child value is smaller than parent
    		if(cmp.compare(pq[child], pq[parent]) < 0)
    		{
    			//swap elements accordingly using temp variable
    			FileLine temp = pq[child];
    			pq[child]= pq[parent];
    			pq[parent] = temp;
    		} 
    		
    		else
    		{
    			break;
    		}
    		
    		//set parent's value to child
    		child = parent;
    		
    	}
    	
    	//increment numItems
    	numItems++;
    		
    }

    /**
     * This method checks if priorityQueue is empty
     */
    public boolean isEmpty() {
		
		return (numItems == 0);
    }
    
}
