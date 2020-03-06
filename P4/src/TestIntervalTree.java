
public class TestIntervalTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testGetRoot();
	}
	
	private static void testGetRoot(){
		IntervalTree intervalTree = new IntervalTree();
		Interval interval1 = new Interval(0, 24, "p0");
		Interval interval2 = new Interval(15, 33, "h1");
		Interval interval3 = new Interval(25, 41, "p1");
		Interval interval4 = new Interval(35, 49, "p2");
		Interval interval5 = new Interval(45, 58, "p3");
		Interval interval6 = new Interval(48, 67, "h2");
		Interval interval7 = new Interval(57,70, "p4");
		Interval interval8 = new Interval(67, 75, "p5");
		Interval interval9 = new Interval(72,83, "p6");
		Interval interval10 = new Interval(90, 98, "h3");
		Interval interval11 = new Interval(95, 107, "p7");
		Interval interval12 = new Interval(108, 120, "p8");
		intervalTree.insert(interval11);
		intervalTree.insert(interval2);
		intervalTree.insert(interval1);
		intervalTree.insert(interval9);
		intervalTree.insert(interval12);
		intervalTree.insert(interval8);
		intervalTree.insert(interval5);
		intervalTree.insert(interval10);
		intervalTree.insert(interval3);
		intervalTree.insert(interval7);
		intervalTree.insert(interval6);
		intervalTree.insert(interval4);
		
		System.out.println(intervalTree.getRoot());
	}

}
