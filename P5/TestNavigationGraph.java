import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestNavigationGraph {

	public static void main(String[] args) throws FileNotFoundException, InvalidFileException {
		// TODO Auto-generated method stub
		
		NavigationGraph navigate = createNavigationGraphFromMap(args[0]);
		//testGetPropertyNames(navigate);
	//	testGetVertices(navigate);
		
		//for(int i = 0 )
		
		//testGetLocationBynames("computerscience", navigate);
		//testToString(navigate);
		
		/*testGetId("Computerscience", navigate);
		testGetId("Animalscience", navigate);
		testGetId("cafe", navigate);
		testGetId("chipotle", navigate);*/
		//testGetId()
		
		Location loc = navigate.getLocationByName("ANIMALsCiEncE");
		testGetNeighbours(navigate, loc);
		
		
		
	}
	
	
	
	
	
	
	private static void testGetEdgeIfExists(Location src, Location dest, NavigationGraph navigation){
		//Path path = navigation.getEdgeIfExists(, dest)
		Path path = navigation.getEdgeIfExists(src, dest);
		System.out.println(path.toString());
		
	}
	
	private static void testGetNeighbours(NavigationGraph navigation, Location location)
	{
		System.out.println(navigation.getNeighbors(location));
	}
	
	private static void testGetId(String locati, NavigationGraph navigate )
	{
		
	//	System.out.println(navigate.getId(navigate.getLocationByName(locati)));
	}
	
	private static void testToString(NavigationGraph navigate)
	{
		System.out.println(navigate.toString());
	}
	
	private static void testGetLocationBynames(String loc, NavigationGraph navigate)
	{
		Location loca =  navigate.getLocationByName(loc);
		System.out.println(loca.toString());
	}
	
	private static void testGetPropertyNames(NavigationGraph navigation)
	{
		String [] properties = navigation.getEdgePropertyNames();
		for(int i = 0; i < properties.length; i++)
		{
			System.out.println(properties[i]);
		}
	}
	
	private static void testGetVertices(NavigationGraph navigation)
	{
		List<Location> places = navigation.getVertices();
		for(int i = 0; i < places.size(); i++)
		{
			System.out.println(places.get(i));
		}
				
	}
	
	
	
	public static NavigationGraph createNavigationGraphFromMap(String graphFilepath) throws 
	FileNotFoundException, InvalidFileException{
		// TODO: read/parse the input file graphFilepath and create
		// NavigationGraph with vertices and edges
	
		File file = new File(graphFilepath);
		Scanner scanner = new Scanner(file);
		
		
		
		scanner.next();
		scanner.next();
		String firstLine = scanner.nextLine();
		firstLine = firstLine.trim();
		
		if(firstLine.equals(""))
		{
			throw new InvalidFileException("< 3 columns");
		}
		
		ArrayList<String> propertyNames = new ArrayList<String>();
		String temp = "";
		int pos = 0;
		
		for(int i = 0; i < firstLine.length(); i++)
		{
			if(firstLine.charAt(i) == ' ')
			{
				temp = firstLine.substring(pos, i).trim();
				pos = i+1;
				propertyNames.add(temp);
				
			}
			else if(i == firstLine.length() - 1)
			{
				temp = firstLine.substring(pos, i+1).trim();
				propertyNames.add(temp);
			}
		}
		
		
		String [] propNames = propertyNames.toArray(new String[propertyNames.size()]);
		
		
		NavigationGraph navigate = new NavigationGraph(propNames);
		String scanFile = "";
		String source = "";
		String destination = "";
		
		while(scanner.hasNextLine())
		{
			source = scanner.next().toLowerCase();
			destination = scanner.next().toLowerCase();
			scanFile = scanner.nextLine().trim();
			List<Double> properties = new ArrayList<Double>();
			
			int position = 0;
			for(int i = 0; i < scanFile.length(); i++)
			{
				if(scanFile.charAt(i) == ' ')
				{
					properties.add(Double.parseDouble(scanFile.substring(position, i).trim()));
					position = i + 1;
					
				}
				else if(i == scanFile.length() - 1)
				{
					properties.add(Double.parseDouble(scanFile.substring(position, i+1).trim()));
				}
			}
			
			
			Location src = new Location(source);
			Location dest = new Location(destination);
			Path path = new Path(src, dest, properties);
			
			if(!src.equals(navigate.getLocationByName(source)))
			{
				navigate.addVertex(src);
			}
			if(!dest.equals(navigate.getLocationByName(destination)))
			{
				navigate.addVertex(dest);
			}
			if(navigate.getEdgeIfExists(src, dest) == null)
			{
				navigate.addEdge(src, dest, path);
		 	}
		
			
			
		}
	scanner.close();
	
		return navigate;

}

	

}
