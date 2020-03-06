/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          Program 5
// FILE:             NavigationGraph.java 
//
// TEAM:    p5team 36
// Authors: (Varun Srinivasan, vsreenivasan@wisc.edu, vsreenivasan, 001)
// Author1: (Adam Opichka, aopichka@wisc.edu, aopichka, 001)
// Author2: (Owen Zinkgraf, ozinkgraf@wisc.edu, ozinkgraf, 001)
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: None 
// 
// Online sources: None
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.List;


/**
 * This class implements all of the methods for the graph such as adding
 * vertices, adding edges, and returning information about the graph. 
 *
 * <p>Bugs: None
 *
 * @author p5team 36
 */
public class NavigationGraph implements GraphADT<Location, Path> {

	// Array of names of the edge properties (instance variable)
	private String [] edgePropertyNames; 
	// ArrayList of vertices (instance variable)
	private List<GraphNode<Location,Path>> vertices; 
	
	/**
	 * This is the constructor that creates a new NavigationGraph using
	 * the edge property names and an ArrayList of vertices. 
	 *
	 * @param edgePropertyNames 
	 * 				the names of the edge properties as an array of strings
	 * @throws IllegalArgumentException if array is null 
	 */
	public NavigationGraph(String[] edgePropertyNames) 
	{
		// Throw an exception if edgePropertyNames is null
		if(edgePropertyNames == null)
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		this.edgePropertyNames = edgePropertyNames;
		this.vertices = new ArrayList<GraphNode<Location, Path>>();
		
	}

	
	/**
	 * This method is responsible for adding a vertex to the graph. 
	 *
	 * @param vertex is the vertex to be added to the graph 
	 * @throws IllegalArgumentException if vertex is null
	 */
	public void addVertex(Location vertex)
	{
		// Throw an exception if vertex is null
		if(vertex == null)
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		// Create a new vertex
		GraphNode<Location,Path> gn = new GraphNode<Location, 
				Path>(vertex, vertices.size()); // New vertex; local variable
		vertices.add(gn);
	}
	
	/**
	 * This method adds an edge to the graph using a source and destination
	 * location and a Path to represent the edge. 
	 *
	 * @param src is the source location
	 * @param dest is the destination location
	 * @param edge is the Path representing the edge 
	 * @throws IllegalArgument exception if any of the parameters are null,
	 * 				the source equals the destination,
	 * 				or source or destination aren't in the graph 
	 */
	public void addEdge(Location src, Location dest, Path edge)
	{
		// Throws an exception if any parameters are null
		if(src == null || dest == null || edge == null )
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		// Throws an exception if the source or destination aren't in the graph
		if(!contains(getVertices(), src) || !contains(getVertices(), dest))
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		// Throws an exception if the source equals the destination
		if(src.equals(dest))
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		// Search for src and add edge from src to dest
		for(int i = 0; i < vertices.size(); i++)
		{
			if(vertices.get(i).getVertexData().equals(src))
			{
				vertices.get(i).addOutEdge(edge);
			}
		}
	}
	
	/**
	 * This method simply returns the vertices in the graph as a List. 
	 *
	 * @return A List of the vertices in the graph 
	 */
	public List<Location> getVertices()
	{
		// ArrayList of Location objects; local variable
		List<Location> location = new ArrayList<Location>(); 
		
		// Adds vertices to ArrayList
		for(int i = 0; i < vertices.size(); i++)
		{
			location.add(vertices.get(i).getVertexData());
		}
		
		return location;
	}
	
	/**
	 * This method returns a Path if there is one between the source
	 * and destination locations. 
	 *
	 * @param src is the source location
	 * @param dest is the destination location
	 * @return the Path between src and dest, if there is one 
	 * @throws IllegalArgument exception if any of the parameters are null,
	 * 				the source equals the destination,
	 * 				or source or destination aren't in the graph 
	 */
	public Path getEdgeIfExists(Location src, Location dest)
	{
		// Throws an exception if any parameters are null
		if(src == null || dest == null )
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		// Throws an exception if the source or destination aren't in the graph
		if(!contains(getVertices(), src) || !contains(getVertices(), dest))
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		// Throws an exception if the source equals the destination
		if(src.equals(dest))
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		
		Path path = null; // The Path between src and dest; local variable
		Path temp = null; // Path used to look for edge; local variable
		
		/* Searches through all vertices, looking for an edge going from 
		 *          src to dest
		 */
		for(int i = 0; i < vertices.size(); i++)
		{
			for(int j = 0; j < vertices.get(i).getOutEdges().size(); j++)
			{
			    temp = vertices.get(i).getOutEdges().get(j);
				if(temp.getSource().equals(src) &&
						temp.getDestination().equals(dest))
				{
					// Edge found
					path = temp;
					break;
				}
			}
		}
		
		return path;
	}
	
	/**
	 * This method returns the out edges of a given location
	 * as a List. 
	 *
	 * @param src is the source location 
	 * @return a List of Paths that are the out edges of src
	 * @throws IllegalArgumentException if src is null
	 * 			or src is not in the graph 
	 */
	public List<Path> getOutEdges(Location src)
	{
		// Throws an exception if the source is null
		if(src == null )
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		// Throws an exception if the source isn't in the graph
		if(!contains(getVertices(), src))
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		// Searches through vertices to find src
		for(int i = 0; i < vertices.size(); i++)
		{
			if(vertices.get(i).getVertexData().equals(src))
			{
				// src found
				return vertices.get(i).getOutEdges();
			}
		}
		
		return null;
	}
	
	/**
	 * This method returns the neighbors of a given location as 
	 * a List. 
	 *
	 * @param vertex is the Location to find neighbors of 
	 * @return a List of Locations that are the neighbors of vertex
	 * @throws IllegalArgumentException if vertex is null or not in the
	 * 				graph
	 */
	public List<Location> getNeighbors(Location vertex)
	{
		// Throws an exception if vertex is null
		if(vertex == null )
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		// Throws an exception if vertex isn't in the graph
		if(!contains(getVertices(), vertex))
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		// ArrayList of Locations that are neighbors, local variable
		List<Location> neighbors = new ArrayList<Location>(); 
		
		// Searches through vertices to find vertex
		for(int i = 0; i < vertices.size(); i++)
		{
			if(vertices.get(i).getVertexData().equals(vertex))
			{
				// vertex is found
				// Searches through the Out Edges of vertex
				for(int j = 0; j < vertices.get(i).getOutEdges().size(); j++)
				{
					/* if the destination is not already in neighbors,
					 *    add it to neighbors
					 */
					if(!contains(neighbors, 
							vertices.get(i).getOutEdges().get(j).getDestination()))
					{
						neighbors.add
						(vertices.get(i).getOutEdges().get(j).getDestination());
					}
				}
			}
		}
		
		return neighbors;
	}
	
	/**
	 * This method returns the shortest route between two locations using 
	 * edgePropertyName. 
	 *
	 * @param src is the source location
	 * @param dest is the destination location
	 * @param edgePropertyName the edge property by which shortest route has to
	 * 			be calculated
	 * @return a List of Paths representing the shortest route 
	 * @throws IllegalArgumentException if any of the parameters are null,
	 * 				src or dest aren't in the graph,
	 * 				or src equals dest
	 */
	public List<Path> getShortestRoute(Location src, Location dest, String edgePropertyName)
	{
		// Throws an exception if any of the parameters are null
		if(src == null || dest == null || edgePropertyName == null )
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		// Throws an exception if the source or destination aren't in the graph
		if(!contains(getVertices(), src) || !contains(getVertices(), dest))
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		// Throws an exception if the source equals the destination
		if(src.equals(dest))
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		
		// Obtain path property
		int j = 0;
		for(int i = 0; i < edgePropertyNames.length; i++)
		{
			//obtain index of particular property name
			if(edgePropertyNames[i].equalsIgnoreCase(edgePropertyName))
			{
				j = i;
				break;
			}
		}
		
		//ArrayList corresponding to visited and completely updated locations
		List<Location> settledLocations = new ArrayList<Location>();
		
		//ArrayList corresponding to locations that are being updated
		List<Location> unsettledLocations = new ArrayList<Location>();
		
		// Total weight of each vertex
		double [] weights = new double[getVertices().size()];
		
		// Predecessor of each vertex
		Location [] predecessor = new Location[getVertices().size()]; 
		
		//Sets total weight to "infinity" and predecessor to null for each vertex
		for(int i = 0; i < getVertices().size(); i++)
		{
			weights[i] = Double.MAX_VALUE;
			predecessor[i] = null;
		}
	
		// Sets start vertex's total weight to 0
		weights[getId(src)] = 0; 
		
		// Sets start vertex's predecessor to null
		predecessor[getId(src)] = null; 
		
		//add src to unsettled Location list
		unsettledLocations.add(src);
		
		while(!unsettledLocations.isEmpty())
		{
			// sort unsettledLocaitons by weight
			unsettledLocations = sort(unsettledLocations, weights); 
			Location temp = unsettledLocations.remove(0);
			
			//add location to list of settled locations
			settledLocations.add(temp);
			
			//traverse through all neighbours of the current Location
			for(int i = 0; i < getNeighbors(temp).size(); i++)
			{
				//check and obtain path if it exists 
				Path path = getEdgeIfExists(temp,getNeighbors(temp).get(i) );
				
				//check if it is not there in settled location list
				if(path != null && 
						!contains(settledLocations,getNeighbors(temp).get(i)))
				{
					/*totalWeight is a local variable that is used to update 
					 *     contents of the weights array
					 */
					double totalWeight = weights[getId(temp)] + 
							path.getProperties().get(j);
					
					/* If total weight can be reduced, update total weight 
					 *    and predecessor
					 */
					if(totalWeight < weights[getId(getNeighbors(temp).get(i))])
					{
						weights[getId(getNeighbors(temp).get(i))] = totalWeight;
						predecessor[getId(getNeighbors(temp).get(i))] = temp;
					}
					
					/*if location isn't there in the unsettled location list, 
					 *   add it to the list
					 */
					if(!contains(unsettledLocations,getNeighbors(temp).get(i)))
					{
						unsettledLocations.add(getNeighbors(temp).get(i));
					}
					
				}
				
			}
		}
		
		//list is a local variable that stores Location objects
		List<Location> list = new ArrayList<Location>();
		list.add(dest);
		
		//temp is a local instance variable
		Location temp = predecessor[getId(dest)];
		
		//used to obtain all locations in the shortest route
		while(!contains(list,src) && temp != null)
		{
			list.add(temp);
			temp = predecessor[getId(list.get(list.size()-1))];
		}
		
		//sorts all the locations 
		List<Location>list2 = new ArrayList<Location>();
		
		if(!list.isEmpty())
		{
			for(int k = list.size()-1; k >= 0; k--)
			{
				list2.add(list.get(k));
			}
		}
		
		//ArrayList of path objects
		List<Path> list3 = new ArrayList<Path>();
		
		//add the paths to the list
		if(!list2.isEmpty())
		{
			for(int m = 0; m < list2.size() - 1; m++)
			{
				Path path = getEdgeIfExists(list2.get(m), list2.get(m+1));
				list3.add(path);
			}
		}
		
		return list3;	
		
	}
	
	/**
	 * This method returns true if the location is in the list, and 
	 * false otherwise. 
	 *
	 * @param myList is a List of locations to check 
	 * @param location is the location to check if it is in the List
	 * @return true if location is in myList, false otherwise 
	 */
	private boolean contains(List<Location> myList, Location location)
	{
		// Searches through myList for location
		for(int i = 0; i < myList.size(); i++)
		{
			if(location.equals(myList.get(i)))
			{
				// location is found
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This method sorts the graph based on the weights provided as a parameter
	 * and returns it as a List of Locations. 
	 *
	 * @param list is the List of Locations to sort
	 * @param weights is an array that determines how to sort the List
	 * @return a sorted List of Locations 
	 */
	private List<Location> sort(List<Location> list, double [] weights)
	{
		// The sorted list; local variable
		List<Location> sortedList = new ArrayList<Location>(); 
		
		// Sorts list
		while(!list.isEmpty())
		{
			// Used to traverse through list; local variable
			int index = 0;
			
			// Initial smallest weight; local variable
			double min = weights[getId(list.get(0))];
			
			// Finds smallest weight in list
			for(int i = 1; i < list.size(); i++)
			{
				if(min > weights[getId(list.get(i))])
				{
					min = weights[getId(list.get(i))];
					index = i;
				}
				
			}
			
			// Moves the Location with the smallest weight from list to sortedList
			sortedList.add(list.remove(index));
			
		}
		
		list = sortedList;
		
		return list;
	}
	
	
	/**
	 * This method simply returns a Location's ID
	 *
	 * @param source is the location to get the ID of
	 * @return an int representation of the Location's ID
	 */
	private int getId(Location source)
	{
		// the ID; local variable
		int id = 0;
		
		// Searches through vertices to find source
		for(int i = 0; i < vertices.size(); i++)
		{
			if(vertices.get(i).getVertexData().equals(source))
			{
				// Source found
				id = vertices.get(i).getId();
			}
		}
		
		return id;
	}

	
	/**
	 * This method simply returns the edge property names of the graph. 
	 *
	 * @return an array of Strings of the edge property names 
	 */
	public String[] getEdgePropertyNames()
	{
		return edgePropertyNames;
	}
	
	
	/**
	 * This method returns a String representation of the graph. 
	 *
	 * @return a String representation of the graph 
	 */
	public String toString()
	{
		// The string to be returned; local variable
		String temp = "";
		
		// Traverses through vertices
		for(int i = 0; i < getVertices().size(); i++)
		{
			// Traverses through out edges of a vertice
			for(int j = 0; j < vertices.get(i).getOutEdges().size(); j++ )
			{
				temp += vertices.get(i).getOutEdges().get(j).toString();
				
				// Adds a comma when there is a new out edge
				if( j < vertices.get(i).getOutEdges().size()-1)
				{
					temp+= ", ";
				}
				
			}
			
			// Creates a new line when the source changes
			if(i < getVertices().size() -1   && 
					vertices.get(i).getOutEdges().size() != 0)
			{
				temp +="\n";
			}
		
		}
		
		return temp;
	}
	
	
	/**
	 * Returns a Location object given its name
	 * 
	 * @param name
	 *            name of the location
	 * @return Location object
	 * @throws IllegalArgumentException if name is null
	 */
	public Location getLocationByName(String name) {
		
		// Throws exception if name is null
		if(name == null)
		{
			throw new IllegalArgumentException("Invalid Parameters");
		}
		
		// Searches vertices for name
		for(int i = 0; i < vertices.size(); i++)
		{
			if(vertices.get(i).getVertexData().getName().equalsIgnoreCase(name))
			{
				// name found
				return vertices.get(i).getVertexData();
			}
		}
		
		return null;
	}
	

}
