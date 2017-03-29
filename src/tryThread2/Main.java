package tryThread2;

import java.io.IOException;

public class Main {

	public static void main(String args[]) throws IOException, InterruptedException
	{
		System.out.println("hello");
		String source="D:/test2/";
		String destination="D:/output3.txt";
		int threadNo=3;
		
		RandomFileGenerate randomFiles = new RandomFileGenerate(source,100);
		
		ThreadManager manager = new ThreadManager();
		
		manager.initialize(source, destination, threadNo);
		String line= manager.readWithThread();
		System.out.println("final Result"+line);
		manager.write(line);
		
		
	}
	
}
