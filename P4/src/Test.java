
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testCompareTo();
		//testContains();
		 
		testtoString();
		

	}
	
	private static void testtoString()
	{
		Interval interval1 = new Interval(24, 45, "p1");
		System.out.println(interval1.toString());
	}
	
	private static void testContains()
	{
		
		int point1 = 20;
		int point2 = 21;
		int point3 = 30;
		int point4 = 50;
		int point5 = -1;
		int point6 = 100;
		int point7 = 19;
		double point8 = 19.99999999999;
		
		
		Interval interval1 = new Interval(20.0, 50.0, "1st");
		
		System.out.println(interval1.contains(point8));
		System.out.println(interval1.contains(point1));
		System.out.println(interval1.contains(point2));
		System.out.println(interval1.contains(point3));
		System.out.println(interval1.contains(point4));
		System.out.println(interval1.contains(point5));
		System.out.println(interval1.contains(point6));
		System.out.println(interval1.contains(point7));

		
		
		
		
	}
	
	
	private static void testCompareTo()
	{
		Interval interval = new Interval(0, 4, "0,4");
		
		Interval interval1 = new Interval(2, 3, "2,3");
		System.out.println(interval.getLabel() + " compared to " + interval1.getLabel()
						+ " should return -1");
		System.out.println("Returns: " + interval.compareTo(interval1));
		System.out.println(interval1.getLabel() + " compared to " + interval.getLabel()
						+ " should return 1");
		System.out.println("Returns: " + interval1.compareTo(interval));
		
		Interval interval2 = new Interval(0,3,"0,3");
		Interval interval3 = new Interval(2,4,"2,4");
		System.out.println(interval2.getLabel() + " compared to " + interval3.getLabel()
			+ " should return -1");
		System.out.println("Returns: " + interval2.compareTo(interval3));
		System.out.println(interval3.getLabel() + " compared to " + interval2.getLabel()
			+ " should return 1");
		System.out.println("Returns: " + interval3.compareTo(interval2));
		
		
		System.out.println(interval2.getLabel() + " compared to " + interval.getLabel()
			+ " should return -1");
		System.out.println("Returns: " + interval2.compareTo(interval));
		System.out.println(interval.getLabel() + " compared to " + interval2.getLabel()
			+ " should return 1");
		System.out.println("Returns: " + interval.compareTo(interval2));
		
		System.out.println(interval.getLabel() + " compared to " + interval.getLabel()
			+ " should return 0");
		System.out.println("Returns: " + interval.compareTo(interval));
	}
	
	
	
	private static void testoverlaps(){
		
		Interval interval = new Interval(10, 30, "test1");
		
	
		Interval interval1 = new Interval(20, 50, "1st");
		System.out.println(interval.overlaps(interval1));
		
		Interval interval2 = new Interval(40, 50, "2nd");
		System.out.println(interval.overlaps(interval2));
		
		Interval interval3 = new Interval(0,5, "3rd");
		System.out.println(interval.overlaps(interval3));
		
		Interval interval4 = new Interval(30, 50, "4th");
		System.out.println(interval.overlaps(interval4));
		
		Interval interval5 = new Interval(0,10,"5th");
		System.out.println(interval.overlaps(interval5));
		
		Interval interval6 = new Interval(10, 30, "6th");
		System.out.println(interval.overlaps(interval6));
		
		Interval interval7 = new Interval(-5, 45, "7th");
		System.out.println(interval.overlaps(interval7));
		
		}

}
