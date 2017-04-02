package tryThread2;

import java.io.IOException;

public class Main {

	public static void main(String args[]) throws IOException, InterruptedException
	{
		System.out.println("hello");
		
		String source = "D:/test2/";
		String destination = "D:/output3.txt";
		int threadNo = 5;
		
		//RandomFileGenerate randomFiles = new RandomFileGenerate(source,1000);
		
		FileMerger manager = new FileMerger(source, destination, threadNo);
				
		String line = ""; 
		line = line + manager.readFiles();
		System.out.println("final Result" + line);
		
		manager.writeFile(line);
		
		
	}
	
}
