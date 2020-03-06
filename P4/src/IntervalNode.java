/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          Program 4: Schedule Planner
// FILE:             IntervalNode.java
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
 * This class defines the IntervalNode for the IntervalTree. This node has three
 * components: 1) interval - the data that we want to store in this node 2)
 * maxEnd - this represents the maximum end of any interval stored in the tree
 * rooted at this node 3) leftNode and rightNode - the left and right node
 * references in the IntervalTree.
 * 
 * This class will be used while constructing the IntervalTree.
 *
 * @param <T>
 *            the template parameter for the data field - interval.
 */

public class IntervalNode<T extends Comparable<T>> {
	// Interval stored in the node.
	private IntervalADT<T> interval;

	// Each node stores the maxEnd of the interval in its subtree.
	private T maxEnd;

	// LeftNode and RightNode.
	private IntervalNode<T> leftNode, rightNode;

	/**
	 * Constructor to create a new IntervalNode. Set the interval data structure
	 * present as member variable above and maxEnd associated with the interval.
	 * Hint: Use interval.getEnd() to get the end of the interval. Note: In your
	 * intervalTree, this will get updated subsequently.
	 * 
	 * @param interval
	 *            the interval data member.
	 */
	public IntervalNode(IntervalADT<T> interval) 
	{	
		this.interval = interval;
		this.maxEnd = interval.getEnd();
		this.leftNode = null;
		this.rightNode = null;	
	}

	/**
	 * Returns the next in-order successor of the BST. Hint: Return left-most
	 * node in the right subtree. Return null if there is no rightNode.
	 *
	 * @return in-order successor node
	 */
	public IntervalNode<T> getSuccessor() 
	{

		//check if right child doesn't exist
		if(this.getRightNode() == null)
		{
			return null;
		}

		/*temp is a local variable that stores a reference to the successor 
		 *     node
		 */
		IntervalNode<T> temp = this.getRightNode();

		//the helper method smallest is called
		temp = smallest(temp);

		//return successor node
		return temp;

	}

	/**
	 * smallest is an auxillary-helper method that returns the 
	 *          in-order successor node
	 * 
	 * @param node
	 * @return the successor node
	 */
	private IntervalNode<T> smallest(IntervalNode<T> node)
	{
		//check if left child is null
		if(node.getLeftNode() == null)
		{
			return node;
		}
		else
		{
			//recursively call the method
			return smallest(node.getLeftNode());
		}

	}

	/**
	 * Returns the interval associated with the node.
	 * 
	 * @return the interval data field.
	 */
	public IntervalADT<T> getInterval() {
		return interval;
	}

	/**
	 * Setter for the interval.
	 * 
	 * @param interval
	 *            the interval to be set at this node.
	 */
	public void setInterval(IntervalADT<T> interval) {
		this.interval = interval;
	}

	/**
	 * Setter for the maxEnd. This represents the maximum end point associated
	 * in any interval stored at the subtree rooted at this node (inclusive of
	 * this node).
	 * 
	 * @param maxEnd
	 *            the maxEnd associated with this node.
	 *
	 */
	public void setMaxEnd(T maxEnd) {
		this.maxEnd = maxEnd;
	}

	/**
	 * Getter for the maxEnd member variable.
	 * 
	 * @return the maxEnd.
	 */
	public T getMaxEnd() {
		return maxEnd;
	}

	/**
	 * Getter for the leftNode reference.
	 *
	 * @return the reference of leftNode.
	 */
	public IntervalNode<T> getLeftNode() {
		return leftNode;
	}

	/**
	 * Setter for the leftNode of this node.
	 * 
	 * @param leftNode
	 *            the left node.
	 */
	public void setLeftNode(IntervalNode<T> leftNode) {
		this.leftNode = leftNode;
	}

	/**
	 * Getter for the rightNode of this node.
	 * 
	 * @return the rightNode.
	 */
	public IntervalNode<T> getRightNode() {
		return rightNode;
	}

	/**
	 * Setter for the rightNode of this node.
	 * 
	 * @param rightNode
	 *            the rightNode of this node.
	 */
	public void setRightNode(IntervalNode<T> rightNode) {
		this.rightNode = rightNode;
	}


}
