/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          Program 4: Schedule Planner
// FILE:             IntervalTree.java
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

import java.util.ArrayList;
import java.util.List;

/**
 * The IntervalTree class is responsible for holding all of the IntervalNodes
 * and storing them properly through methods such as insert and delete. The
 * class also has methods to find overlapping schedules and schedules that 
 * contain a certain point. 
 *
 * <p>Bugs: No bugs have been found
 *
 * @author Team 36
 */
public class IntervalTree<T extends Comparable<T>> implements IntervalTreeADT<T>{

	// Create an IntervalNode to store the root node
	private IntervalNode<T> root;

	/**
	 * The IntervalTree constructor simply creates a new, empty IntervalTree
	 * by setting the root node to null. 
	 *
	 */
	public IntervalTree()
	{
		root = null;
	}

	/**
	 * This method simply returns the root node of the tree. 
	 *
	 * @return root is the IntervalNode at the root of the tree
	 */
	@Override
	public IntervalNode<T> getRoot() {
		return root;
	}


	/**
	 * This method calls a helper method to set the max end 
	 * of a given subtree. 
	 *
	 * @param node is the node to potentially set a new max
	 * 			end as
	 */
	private void settingMaxEnd(IntervalNode<T> node)
	{
		//call helper method
		maxEndHelper(node);
	} 

	/**
	 * This method sets the new max end of the tree if necessary. 
	 *
	 * @param node is the node to potentially set a new max end as
	 */
	private void maxEndHelper(IntervalNode<T> node)
	{
		//check if node is null, simply return if so
		if(node == null)
		{
			return;
		}
		
		//recursively call to traverse all nodes in the tree
		maxEndHelper(node.getLeftNode());
		maxEndHelper(node.getRightNode());
		
		//check if left node and right child are null
		if(node.getLeftNode() == null && node.getRightNode() == null)
		{
			//set the max end of the node to be its own interval end
			node.setMaxEnd(node.getInterval().getEnd());
		}
		
		//check if only left child is null
		else if(node.getLeftNode() == null)
		{
			/*compare the values of the respective interval end and right
			 *        child's max end
			 */
			if(node.getInterval().getEnd().compareTo
					(node.getRightNode().getMaxEnd()) < 0)
			{
				/*if interval's end is lesser, set max end of the node to  
				 *   be the right child's max end value
				 */
				node.setMaxEnd(node.getRightNode().getMaxEnd());
			}
			else
			{
				//set the max end of the node to the interval's end value
				node.setMaxEnd(node.getInterval().getEnd());
			}

		}
		//check if only right child is null
		else if(node.getRightNode() == null)
		{
			/*compare the values of the respective interval end and left
			 *        child's max end
			 */
			if(node.getInterval().getEnd().compareTo
					(node.getLeftNode().getMaxEnd()) < 0)
			{	
				/*if interval's end value is lesser, set max end of the node   
				 *   to be the left child's max end value
				 */
				node.setMaxEnd(node.getLeftNode().getMaxEnd());
			}
			else
			{
				//set the max end of the node to the interval's end value
				node.setMaxEnd(node.getInterval().getEnd());
			}

		}
		//if both children aren't null
		else
		{
			/*max is a local variable that stores the max end value to be
			 *    assigned to the node 
			 */
			T max = null;

			//compare the left and right child's max end values
			if(node.getLeftNode().getMaxEnd().compareTo
					(node.getRightNode().getMaxEnd()) < 0)
			{
				//assign max the max end value of right child
				max = node.getRightNode().getMaxEnd();
			}
			else
			{
				//assign max the max end value of left child
				max = node.getLeftNode().getMaxEnd();
			}

			//compare the interval's end and the value in variable max
			if(max.compareTo(node.getInterval().getEnd()) > 0)
			{
				//assign the value max to the node's MaxEnd
				node.setMaxEnd(max);
			}
			else
			{
				//assign the interval end value to the node's MaxEnd
				node.setMaxEnd(node.getInterval().getEnd());
			}
		}
	}


	/**
	 * This method calls a helper method to add a given interval
	 * to the IntervalTree. 
	 *
	 * @throws IllegalArgumentException if interval is null or is found 
	 * to be a duplicate of an existing interval in this tree. 
	 * @param interval is the interval to be added to the tree
	 */
	@Override
	public void insert(IntervalADT<T> interval)
			throws IllegalArgumentException {

		//Set root equal to the node created by the insert helper method
		root = insert(root, interval);

		//Potentially set max end if given node creates a new max end
		settingMaxEnd(root);
	}

	/**
	 * This method inserts a new interval into the tree as a node by
	 * searching down the tree and when it reaches a null node, will be 
	 * inserted there. 
	 *
	 * @throws IllegalArgumentException if interval is null or is found 
	 * to be a duplicate of an existing interval in this tree. 
	 * @param n is the new node that will be created and added to the tree
	 * @param interval is the interval to be added
	 * @return a new node to be added to the tree
	 */
	private IntervalNode<T> insert(IntervalNode<T> n, IntervalADT<T> interval)
	{
		//Throw IllegalArgumentException if interval is null
		if(interval == null)
		{
			throw new IllegalArgumentException();
		}

		//If the node is null, make a new node with the interval as parameter
		if(n == null)
		{
			return new IntervalNode<T>(interval);
		}

		//Throw IllegalArgumentException if duplicate interval
		if(interval.compareTo(n.getInterval()) == 0 )
		{
			throw new IllegalArgumentException();
		}

		/*If interval belongs in left subtree, recursively call insert
		     to insert it in correct place*/
		if(interval.compareTo(n.getInterval()) < 0)
		{
			n.setLeftNode(insert(n.getLeftNode(),interval ));
			return n;
		}

		/*If interval belongs in right subtree, recursively call insert
		     to insert it in correct place*/
		else
		{
			n.setRightNode(insert(n.getRightNode(), interval));
			return n;
		}

	}

	/**
	 * Calls a helper method to delete an interval from the tree. 
	 *
	 * @throws IllegalArgumentException if interval is null
	 * @throws IntervalNotFoundException if the interval does not exist.
	 * @param interval is the interval to be deleted from the tree
	 */
	@Override
	public void delete(IntervalADT<T> interval)
			throws IntervalNotFoundException, IllegalArgumentException {

		//Set root to node created by deleteHelper method
		root = deleteHelper(root, interval);

		//Potentially set new maxEnd if removing this node causes that
		settingMaxEnd(root);
	}

	/**
	 * This method searches down the tree in an attempt to find the given
	 * interval to delete from the tree. Once found, it will delete the 
	 * interval in 1 of 3 ways. 
	 *
	 * @throws IllegalArgumentException if interval is null
	 * @throws IntervalNotFoundException if the interval does not exist.
	 * @param node is the node to be created containing the interval
	 * @param interval is the interval to be deleted from the tree
	 * @return the IntervalNode to be deleted
	 */
	@Override
	public IntervalNode<T> deleteHelper(IntervalNode<T> node,
			IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {

		//Throw necessary exceptions if needed
		if(interval == null)
		{
			throw new IllegalArgumentException();
		}
		
		if(node == null)
		{
			throw new IntervalNotFoundException(interval.toString());
		}

		//If found node, delete it
		if(interval.compareTo(node.getInterval()) == 0)
		{
			//If the node has no children, set child to null
			if(node.getLeftNode() == null && node.getRightNode() == null)
			{
				return null;
			}
			/*If node has one child, delete by setting appropriate child
			     of parent to the node's only child*/
			if(node.getLeftNode() == null)
			{
				return node.getRightNode();
			}
			if(node.getRightNode() == null)
			{
				return node.getLeftNode();
			}

			/*If node has two children, find successor and set that that into
			     the node's interval. Then, recursively call delete node on 
			     the right subtree. */
			IntervalADT<T> smallVal = node.getSuccessor().getInterval();
			node.setInterval(smallVal);
			node.setRightNode(deleteHelper(node.getRightNode(), smallVal));
			return node;

		}

		//If haven't found match, recursively call to check left subtree. 
		else if(interval.compareTo(node.getInterval()) < 0)
		{
			node.setLeftNode(deleteHelper(node.getLeftNode(), interval));
			return node;
		}

		//If haven't found match, recursively call to check right subtree. 
		else
		{
			node.setRightNode(deleteHelper(node.getRightNode(), interval));
			return node;
		}
	}


	/**
	 * This method will create and return a list of all intervals that
	 * overlap with the given interval. 
	 *
	 * @param interval is the interval to search for overlapping
	 * @return list of intervals that overlap with the input interval
	 */
	@Override
	public List<IntervalADT<T>> findOverlapping(IntervalADT<T> interval) {

		//Create a new list to hold overlapping intervals
		List<IntervalADT<T>> myList = new ArrayList<IntervalADT<T>>();

		//Add overlapping intervals to the list using helper method
		myList = findOverlappingHelper(root, interval, myList);

		//Return the list containing the overlapping intervals
		return myList;
	}

	/**
	 * Adds all intervals that overlap with the given interval to the list
	 * by recursively calling itself. 
	 *
	 * @param node is the node to begin the search
	 * @param interval is the interval to search for overlapping
	 * @param result is the list of intervals that will have the overlapping
	 * 			intervals added to it
	 * @return list of intervals that overlap with the input interval
	 */
	private List<IntervalADT<T>> findOverlappingHelper
	(IntervalNode<T> node, IntervalADT<T> interval, List<IntervalADT<T>> result)
	{
		/*Add an interval to the list if it isn't null, and it overlaps with the
		      given interval*/ 
		if(node != null)
		{
			if(interval.overlaps(node.getInterval()))
			{
				result.add(node.getInterval());
			}
		}

		/*Compare the given interval to intervals in the left subtree to check if 
		          it is in the tree*/
		if(node.getLeftNode() != null)
		{

			if(interval.getStart().compareTo
					(node.getLeftNode().getMaxEnd()) <= 0)
			{
				findOverlappingHelper(node.getLeftNode(), interval, result);
			}

		}

		/*Compare the given interval to intervals in the right subtree to check if 
		          it is in the tree*/
		if(node.getRightNode() != null)
		{
			if(interval.getStart().compareTo
					(node.getRightNode().getMaxEnd()) <= 0)
			{
				findOverlappingHelper(node.getRightNode(), interval, result);
			}
		}

		//Return the list 
		return result;

	}

	/**
	 * This method creates and returns a list of all intervals that
	 * contain the given point within the tree by calling a helper
	 * method. 
	 *
	 * @param point is the point to be checked in the tree
	 * @return a list of all intervals that contain the given point
	 */
	@Override
	public List<IntervalADT<T>> searchPoint(T point) {

		//Create a new list to hold intervals that contain given point
		List<IntervalADT<T>> myList = new ArrayList<IntervalADT<T>>();

		//Add intervals that contain point using helper method
		myList = searchPointHelper(point, root, myList);

		//Return the list
		return myList;

	}

	/**
	 * Add all of the intervals that contain the point to the list
	 * by recursively calling itself. 
	 *
	 * @throws IllegalArgumentException if point is null
	 * @param point is the point to be checked in the tree
	 * @param node is the node to begin the search at
	 * @param list is the list of intervals that will be returned
	 * @return a list of all intervals that contain the given point
	 */
	private List<IntervalADT<T>> searchPointHelper(T point, IntervalNode<T> node,
			List<IntervalADT<T>> list)
	{
		//Throw IllegalArgumentException if point is null
		if(point == null)
		{
			throw new IllegalArgumentException();
		}

		//Add an interval to the list if it contains the point
		if(node != null)
		{
			if(node.getInterval().contains(point))
			{
				list.add(node.getInterval());
			}
		}

		//Recursively check left subtree for intervals containing point
		if(node.getLeftNode() != null){
			if(point.compareTo(node.getLeftNode().getMaxEnd()) <= 0)
			{
				searchPointHelper(point, node.getLeftNode(), list);
			}
		}

		//Recursively check right subtree for intervals containing point
		if(node.getRightNode() != null){
			if(point.compareTo(node.getRightNode().getMaxEnd())<= 0)
			{
				searchPointHelper(point, node.getRightNode(), list);
			}	
		}

		//Return the list 
		return list;
	}


	/**
	 * This method simply calls the size method to return to the user
	 * the number of nodes in the IntervalTree. 
	 *
	 * @return the number of nodes in the tree.
	 */
	@Override
	public int getSize() {
		//Call helper method
		return size(root);
	}

	/**
	 * This method returns the number of nodes in the tree by recursively
	 * calling itself and counting all of the nodes in the tree. 
	 *
	 * @param node is the node to start at to count the number of nodes
	 * @return the number of nodes in the tree
	 */
	public int size(IntervalNode<T> node)
	{
		//Size is 0 if tree is empty
		if(node == null)
		{
			return 0;
		}

		/*Go through tree to count all of the nodes in the tree by starting
		     at the root and going through the left and right subtrees*/
		else
		{
			return (size(node.getLeftNode()) + size(node.getRightNode()) + 1);
		}
	}

	/**
	 * This method calls the height helper method to return the height of
	 * the tree. 
	 *
	 * @return the height of the IntervalTree
	 */
	@Override
	public int getHeight() {
		//Call helper method
		return height(root);
	}

	/**
	 * This method recursively calls itself to count the height of the 
	 * IntervalTree.
	 *
	 * @param node is the node to start at to count the height
	 * @return the height of the IntervalTree
	 */
	private int height(IntervalNode<T> node)
	{
		//Height is 0 if tree is empty
		if(node == null)
		{
			return 0;
		}

		//Go through the tree to determine the height of the tree
		else
		{
			return 1 + 
					Math.max(height(node.getLeftNode()), 
							height(node.getRightNode()));
		}
	}

	/**
	 * This method calls the containsHelper method to determine whether
	 * or not the given interval is in the IntervalTree. 
	 *
	 * @param interval is the interval to be checked if it is in the tree
	 * @return true if the interval is in the tree, false otherwise
	 */
	@Override
	public boolean contains(IntervalADT<T> interval) {
		//Call helper method
		return containsHelper(root, interval);
	}

	/**
	 * This method will recursively call itself to check all intervals in the
	 * tree and determine whether or not the given interval is in the tree. 
	 *
	 * @throws IllegalArgumentException if interval is null
	 * @param node is the node to start checking if the interval is in the tree
	 * @param interval is the interval to be checked if it is in the tree
	 * @return true if the interval is in the tree, false otherwise
	 */
	private boolean containsHelper(IntervalNode<T> node, IntervalADT<T> interval)
	{
		//Throw IllegalArgumentException if interval is null
		if(interval == null)
		{
			throw new IllegalArgumentException();
		}

		//If node is null, false because interval not in tree
		if(node == null)
		{
			return false;
		}

		//Return true if interval is in the tree by using compareTo method
		if(node.getInterval().compareTo(interval) == 0)
		{
			return true;
		}

		/*Recursively call containsHelper if interval isn't found yet and 
		              should be in left subtree*/
		if(interval.compareTo(node.getInterval()) < 0)
		{
			return containsHelper(node.getLeftNode(), interval);
		}

		/*Recursively call containsHelper if interval isn't found yet and 
		              should be in right subtree*/
		else
		{
			return containsHelper(node.getRightNode(), interval);
		}
	}

	/**
	 * This method is responsible for printing the statistics (height
	 * and size) of the tree with the proper format. 
	 *
	 */
	@Override
	public void printStats() {
		//Simply print the height and size of the tree with proper format
		System.out.println("-----------------------------------------");
		System.out.println("Height: " + getHeight());
		System.out.println("Size: " + getSize());
		System.out.println("-----------------------------------------");
	}

}
