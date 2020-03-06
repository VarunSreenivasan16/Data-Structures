/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          Program 5
// FILE:             MapApp.java 
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class that reads/parses the input file and creates NavigationGraph
 * object.
 * 
 * @author CS367
 *
 */
public class MapApp {

	private NavigationGraph graphObject;

	/**
	 * Constructs a MapApp object
	 * 
	 * @param graph
	 *            NaviagtionGraph object
	 */
	public MapApp(NavigationGraph graph) {
		this.graphObject = graph;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java MapApp <pathToGraphFile>");
			System.exit(1);
		}

		// read the filename from command line argument
		String locationFileName = args[0];
		try {
			NavigationGraph graph = createNavigationGraphFromMapFile(locationFileName);
			MapApp appInstance = new MapApp(graph);
			appInstance.startService();

		} catch (FileNotFoundException e) {
			System.out.println("GRAPH FILE: " + locationFileName + " was not found.");
			System.exit(1);
		} catch (InvalidFileException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

	}

	/**
	 * Displays options to user about the various operations on the loaded graph
	 */
	public void startService() {

		System.out.println("Navigation App");
		Scanner sc = new Scanner(System.in);

		int choice = 0;
		do {
			System.out.println();
			System.out.println("1. List all locations");
			System.out.println("2. Display Graph");
			System.out.println("3. Display Outgoing Edges");
			System.out.println("4. Display Shortest Route");
			System.out.println("5. Quit");
			System.out.print("Enter your choice: ");

			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("Please select a valid option: ");
			}
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println(graphObject.getVertices());
				break;
			case 2:
				System.out.println(graphObject.toString());
				break;
			case 3: {
				System.out.println("Enter source location name: ");
				String srcName = sc.next();
				Location src = graphObject.getLocationByName(srcName);

				if (src == null) {
					System.out.println(srcName + " is not a valid Location");
					break;
				}

				List<Path> outEdges = graphObject.getOutEdges(src);
				System.out.println("Outgoing edges for " + src + ": ");
				for (Path path : outEdges) {
					System.out.println(path);
				}
			}
			break;

			case 4:
				System.out.println("Enter source location name: ");
				String srcName = sc.next();
				Location src = graphObject.getLocationByName(srcName);

				System.out.println("Enter destination location name: ");
				String destName = sc.next();
				Location dest = graphObject.getLocationByName(destName);

				if (src == null || dest == null) {
					System.out.println(srcName + " and/or " + destName + " are not valid Locations in the graph");
					break;
				}

				if (src == dest) {
					System.out.println(srcName + " and " + destName + " correspond to the same Location");
					break;
				}

				System.out.println("Edge properties: ");
				// List Edge Property Names
				String[] propertyNames = graphObject.getEdgePropertyNames();
				for (int i = 0; i < propertyNames.length; i++) {
					System.out.println("\t" + (i + 1) + ": " + propertyNames[i]);
				}
				System.out.println("Select property to compute shortest route on: ");
				int selectedPropertyIndex = sc.nextInt() - 1;

				if (selectedPropertyIndex >= propertyNames.length) {
					System.out.println("Invalid option chosen: " + (selectedPropertyIndex + 1));
					break;
				}

				String selectedPropertyName = propertyNames[selectedPropertyIndex];
				List<Path> shortestRoute = graphObject.getShortestRoute(src, dest, selectedPropertyName);
				for(Path path : shortestRoute) {
					System.out.print(path.displayPathWithProperty(selectedPropertyIndex)+", ");
				}
				if(shortestRoute.size()==0) {
					System.out.print("No route exists");
				}
				System.out.println();

				break;

			case 5:
				break;

			default:
				System.out.println("Please select a valid option: ");
				break;

			}
		} while (choice != 5);
		sc.close();
	}

	/**
	 * Reads and parses the input file passed as argument create a
	 * NavigationGraph object. The edge property names required for
	 * the constructor can be got from the first line of the file
	 * by ignoring the first 2 columns - source, destination. 
	 * Use the graph object to add vertices and edges as
	 * you read the input file.
	 * 
	 * @param graphFilepath
	 *            path to the input file
	 * @return NavigationGraph object
	 * @throws FileNotFoundException
	 *             if graphFilepath is not found
	 * @throws InvalidFileException
	 *             if header line in the file has < 3 columns or 
	 *             if any line that describes an edge has different number of properties 
	 *             	than as described in the header or 
	 *             if any property value is not numeric 
	 */

	public static NavigationGraph createNavigationGraphFromMapFile(String graphFilepath) throws 
	FileNotFoundException, InvalidFileException{

		// Create a new file using the parameter and create a scanner to read from that file
		File file = new File(graphFilepath);
		Scanner scanner = new Scanner(file);

		// Use the scanner to pass up the source and destination words
		scanner.next();
		scanner.next();
		
		// Save the rest of the first line to be analyzed as edge properties
		String firstLine = scanner.nextLine();
		firstLine = firstLine.trim();

		// If this is empty, throw exception stating there are less than 3 columns
		if(firstLine.equals(""))
		{
			scanner.close();
			throw new InvalidFileException("< 3 columns");

		}

		// Create an ArrayList of strings to store edge property names
		ArrayList<String> propertyNames = new ArrayList<String>();
		String temp = "";
		int pos = 0;

		// Iterate through the first line to establish edge properties
		for(int i = 0; i < firstLine.length(); i++)
		{
			// If the character is a space, use substring to save the 
			// property name and add this property to the ArrayList
			if(firstLine.charAt(i) == ' ')
			{
				temp = firstLine.substring(pos, i).trim();
				pos = i+1;
				propertyNames.add(temp);

			}
			
			// If it's the last word, use substring to save that
			// word and add it to the ArrayList
			else if(i == firstLine.length() - 1)
			{

				temp = firstLine.substring(pos, i+1).trim();
				propertyNames.add(temp);

			}
		}

		// Create an array of Strings to store the property names
		String [] propNames = propertyNames.toArray(new String[propertyNames.size()]);
		int sum = propNames.length + 2;

		// Create a new navigation graph object
		NavigationGraph navigate = new NavigationGraph(propNames);
		
		// Initialize string variables to store the names read in from the scanner
		String scanFile = "";
		String source = "";
		String destination = "";

		// Iterate through the file while there is more data to be read in 
		while(scanner.hasNextLine())
		{
			// Read in the name of the source and destination
			source = scanner.next().toLowerCase();
			destination = scanner.next().toLowerCase();
			
			// Take in the rest of the line, holding edge property values
			scanFile = scanner.nextLine().trim();
			
			// Create a new List of these numbers read in 
			List<Double> properties = new ArrayList<Double>();


			// Iterate through the rest of the line after the locations
			int position = 0;
			for(int i = 0; i < scanFile.length(); i++)
			{
				if(scanFile.charAt(i) == ' ')
				{
					// Attempt to add the number to the List
					try
					{
						properties.add(Double.parseDouble(scanFile.substring(position, i).trim()));

					}
					// Catch an exception if the input data is not numeric
					catch(Exception e )
					{
						throw new InvalidFileException("not numeric");
					}	
					//Increment position
					position = i + 1;


				}
				else if(i == scanFile.length() - 1)
				{
					// Attempt to add the number to the List
					try
					{
						properties.add(Double.parseDouble(scanFile.substring(position, i+1).trim()));

					}
					// Catch an exception if the input data is not numeric
					catch(Exception e)
					{
						throw new InvalidFileException("not numeric");
					}

				}


			}

			// Throw exception if the file doesn't have proper amount of data
			int total = properties.size() + 2;
			if(sum != total)
			{
				throw new InvalidFileException("< size of properties");
			}

			// Create a new path using the source and destination locations
			// read in from the file
			Location src = new Location(source);
			Location dest = new Location(destination);
			Path path = new Path(src, dest, properties);

			// Add a vertex to the graph with the correct method 
			// based on the location's information
			if(navigate.getLocationByName(source) == null)
			{
				navigate.addVertex(src);
			}
			if(navigate.getLocationByName(destination) == null)
			{
				navigate.addVertex(dest);
			}
			if(navigate.getEdgeIfExists(src, dest) == null)
			{
				navigate.addEdge(src, dest, path);
			}



		}
		//Close the scanner
		scanner.close();

		//Return the newly created NavigationGraph
		return navigate;

	}

}
