
public class TestIntervalNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSuccessor();
	
	}
	
	private static void testConstructor()
	{
		Interval interval1 = new Interval(24, 45, "p1");
		
		IntervalNode intervalnode = new IntervalNode(interval1);
		
		System.out.println(intervalnode.toString());
	}
	
	private static void testSuccessor()
	{
		Interval interval = new Interval(24, 45, "p0");
		Interval interval2 = new Interval(35, 50, "h1");
		Interval interval3 = new Interval(12, 30, "Owen");
		Interval interval4 = new Interval(28, 40, "Adam");
		Interval interval5 = new Interval(40, 55, "Varun");
		
		
		IntervalNode intervalnode = new IntervalNode(interval);
		IntervalNode intervalnode1 = new IntervalNode(interval2);
		IntervalNode intervalnode2 = new  IntervalNode(interval3);
		IntervalNode intervalnode3 = new IntervalNode(interval4);
		IntervalNode intervalnode4 = new IntervalNode(interval5);
		
		
		
		intervalnode.setRightNode(intervalnode1);
		intervalnode.setLeftNode(intervalnode2);
		intervalnode1.setLeftNode(intervalnode3);
		intervalnode1.setRightNode(intervalnode4);
		
		System.out.print(intervalnode.getSuccessor());
	}

}
