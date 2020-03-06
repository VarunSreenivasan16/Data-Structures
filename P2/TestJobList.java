import java.util.Iterator;

public class TestJobList {

	public static void main(String[] args) {
		
		
		
		//Test 1 - Method Functionality
		Test1();
		
		//Test 2 - Iterator Test
		//Test2();
		

	}
	
	public static void Test1() 
	
	{
		JobList testList = new JobList();
		
		Job Job1 = new Job("job1", 10, 20);
		Job Job2 = new Job("job2", 30, 40);
		Job Job3 = new Job("job3", 15, 30);
		boolean test = true;
		
		Job job4 = new Job("job1", 10, 20);
		
		testList.add(Job1);
		testList.add(Job2);
		
		System.out.println(testList.contains(job4));
		System.out.println(testList.contains(Job1));
		System.out.println(testList.contains(Job3));
		
		Iterator<Job> jobDisplay = testList.iterator();
		while(jobDisplay.hasNext())
		{
			System.out.println(jobDisplay.next().toString());
		}
		
		
	}
		
		/*while (test) 
		{
			
			//Add a job, see if the job is there, get the expected position, get the size.
			testList.add(Job1);
			System.out.println(testList.contains(Job1));
			for (int i = 0; i < testList.size(); i++) 
			{
				System.out.println(testList.get(i));
			}
			System.out.println(testList.size());
			System.out.println();
			
			//Add a job, see if the job is there, get the expected position, get the size.
			testList.add(Job2);
			System.out.println(testList.contains(Job2));
			for (int i = 0; i < testList.size(); i++) 
			{
				System.out.println(testList.get(i));
			}
			System.out.println(testList.size());
			System.out.println();
			
			//Remove the job at index 0, (job1) and see if job1 is there.
			//Check index 0 to make sure the JobList is properly resorting
			//get the size
			testList.remove(0);
			System.out.println(testList.contains(Job1));
			for (int i = 0; i < testList.size(); i++) 
			{
				System.out.println(testList.get(i));
			}
			
			System.out.println(testList.size());
			System.out.println();
			
			
			//Add job at index 0
			testList.add(0, Job1);
			System.out.println(testList.contains(Job1));
			
			//For loop get all indexes
			for (int i = 0; i < testList.size(); i++) 
			{
				System.out.println(testList.get(i));
			}
			
			System.out.println(testList.size());
			System.out.println();
			
			//Add job at Index 1
			testList.add(1, Job3);
			System.out.println((testList.contains(Job3)));
			
			//For loop get all indexes
			for (int i = 0; i < testList.size(); i++) 
			{
				System.out.println(testList.get(i));
			}
			
			System.out.println(testList.size());
			System.out.println();
			
			//Add job at Index 0
			testList.add(0, Job3);
			System.out.println((testList.contains(Job3)));
			
			//For loop get all indexes
			for (int i = 0; i < testList.size(); i++) 
			{
				System.out.println(testList.get(i));
			}
			
			System.out.println(testList.size());
			System.out.println();

			test = false;
		}
	} //End Test 1
	
	public static void Test2() 
	{
		JobList testList = new JobList();
		Job Job1 = new Job("job1", 10, 20);
		Job Job2 = new Job("job2", 30, 40);
		Job Job3 = new Job("job3", 15, 30);
		
		testList.add(Job1);
		testList.add(Job2);
		testList.add(Job3);
			
		System.out.println("Printing testList iterator: " + testList.iterator());
		System.out.println("Printing testList iterator.hasNext(): " + testList.iterator().hasNext());
		System.out.println("Printing testList iterator.next()" + testList.iterator().next());
		
		//Shouldn't this move the iterator to the next index, so it prints Job2?
		testList.iterator().next();
		
		
		System.out.println("Printing testList iterator.next()" + testList.iterator().next());
		testList.iterator().next();
		
		System.out.println("Printing testList iterator.next()" + testList.iterator().next());

		System.out.println(testList.iterator().hasNext());
		
		//testList.iterator.next doesn't seem to be moving the iterator past it's initial
		//header value?
		
	}*/

}
