package tryThread2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class RandomFileGenerate {
	String destination="D:/new/"; 
	
	RandomFileGenerate(String destination,int number) throws IOException
	{
		if( destination.isEmpty() != true )
		{
			//System.out.println("null");
			this.destination=destination;
		}
		else
		{
			System.out.println("NOT   NULL");
			
		}
		
		for( int i=0; i < number; i++ )
		{
			File currentFile=new File(destination + "rand" + i + ".txt");
			FileWriter writer=new FileWriter(currentFile);
			
			//for serial string no in files
			String uuid = "  " + i ;  
			//for random strings in files			
			//String uuid = UUID.randomUUID().toString();
			
			//System.out.println("uuid = " + uuid);
			
			writer.write(uuid);
			
			writer.close();
			
		}

		
	}
	
}
